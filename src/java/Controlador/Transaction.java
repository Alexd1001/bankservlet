package Controlador;

import Entidades.Cliente;
import Entidades.Cuenta;
import Entidades.Transacciones;
import Modelo.CuentaAhorros;
import Modelo.CuentaCte;
import Modelo.Cuentas;
import Modelo.Transaccion;
import Modelo.Transferencia;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author adavila
 */
@WebServlet(name = "Transaction", urlPatterns = {"/Transaction"})

public class Transaction extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    
    
    
   public ArrayList<String> consulta(EntityManager em, int id,String columna ) throws IOException
    {   
        
        ArrayList<String> lista = new ArrayList<String>();
       
             
        Query query =  em.createNativeQuery("select cuentaid,saldo,documentoid,tipocuenta,nombre,apellido,id from cliente inner join cuenta on cuenta.documento_id = cliente.documentoid  inner join transacciones on transacciones.CUENTA_ID=cuenta.CUENTAID where "+columna+"=?");
        
        query.setParameter(1, id);
        List<Object[]> lista1 = query.getResultList();
        
        
        if(!lista1.isEmpty())
        {
        lista.add("true");
        lista.add(Integer.toString( (int) lista1.get(0)[0]));
        lista.add(Integer.toString( (int) lista1.get(0)[1]));
        lista.add(Integer.toString( (int) lista1.get(0)[3]));
        lista.add( (String) lista1.get(0)[4]);
        lista.add( (String) lista1.get(0)[5]);
        lista.add(Integer.toString( (int) lista1.get(0)[2]));
        lista.add(Integer.toString( (int) lista1.get(0)[6]));
        
        }
        else
        {
            lista.add(0,"false");
            lista.add(1,"La Cuenta Destino No Existe, La transaccion no se ejecuto");
        }
        return lista;
   }
   
   
    public void actualizarRegristros(EntityManager em,int llave,int saldo) {     
              
       
        
         em.getTransaction().begin();
         Transacciones trans = em.find(Transacciones.class, llave);
         if (trans != null) 
            trans.setSaldo(saldo);   
    
        em.merge(trans);
        em.getTransaction().commit();    
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BankApplicationPU");
        EntityManager em = emf.createEntityManager();
        int intereses,docorigen;
        int documento = 0;
        int saldoorigen;
        int saldodestino;
        String valor = request.getParameter("valor");
        
        
        int cuentaDno= Integer.parseInt(request.getParameter("nocuenta"));
        
        HttpSession session=request.getSession(false);  
        documento = (Integer)session.getAttribute("documento");  
        
        
        ArrayList <String> listaorigen = consulta(em,documento,"documentoid");
        ArrayList <String> listadestino = consulta(em,cuentaDno,"cuentaid");
        
        
       
        
        if(Boolean.parseBoolean(listadestino.get(0)))
        {
                    
            Cuentas transaccliente;//Objeto tipo cuentas
   
            if(listaorigen.get(3).equals("1"))//1 ahorros
                 transaccliente=new CuentaAhorros();// Instancia cuenta ahorros y lo asigna a  transaccliente
               
            else
                 transaccliente=new CuentaCte();
            
            transaccliente.setSaldo(Integer.parseInt(listaorigen.get(2)));
            Transaccion objtransaccion=new Transferencia(transaccliente,transaccliente.getSaldo(),Integer.parseInt(listadestino.get(2)),Integer.parseInt(valor));
            
            ArrayList<Object>  listasaldos=objtransaccion.tipodetransaccion();
            
            saldoorigen= (int) listasaldos.get(0);
                actualizarRegristros(em,Integer.parseInt(listaorigen.get(7)),saldoorigen);
            
            saldodestino= (int) listasaldos.get(1);
            actualizarRegristros(em,Integer.parseInt(listadestino.get(7)),saldodestino);
            
            
            
            request.setAttribute("datosorigen", listaorigen);
            request.setAttribute("datosdestino", listadestino);
            request.setAttribute("valortransaccion", valor);
            RequestDispatcher rd = request.getRequestDispatcher("resumentransaccion.jsp");
            rd.forward(request, response);
           
            
            
            
        }
        
        
        
        
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}