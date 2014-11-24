package Controlador;

import Entidades.Cliente;
import Entidades.Cuenta;
import Entidades.Transacciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.TypedQuery;
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
       
             
        Query query =  em.createNativeQuery("select cuentaid,saldo,documentoid,tipocuenta,nombre,apellido from cliente inner join cuenta on cuenta.documento_id = cliente.documentoid  inner join transacciones on transacciones.CUENTA_ID=cuenta.CUENTAID where "+columna+"=?");
        
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
        }
        else
        {
            lista.add(0,"false");
            lista.add(1,"La Cuenta Destino No Existe, La transaccion no se ejecuto");
        }
        
        return lista;
   }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BankApplicationPU");
        EntityManager em = emf.createEntityManager();
        
       int documento = 0;
        
        int cuentaDno= Integer.parseInt(request.getParameter("nocuenta"));
        
        HttpSession session=request.getSession(false);  
        documento = (Integer)session.getAttribute("documento");  
        
        
        ArrayList <String> listaorigen = consulta(em,documento,"documentoid");
        ArrayList <String> listadestino = consulta(em,cuentaDno,"cuentaid");
        
        
        
        
        
        if(Boolean.parseBoolean(listaorigen.get(0)))
        { 
            
          
          
            
            try (PrintWriter out = response.getWriter()) {
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet tTransaction</title>");            
            out.println("</head>");
            out.println("<body>");
            for(String u: listaorigen){
            out.println("<h1>Servlet Transaction at " + u + "</h1>");}
            for(String u: listadestino){
            out.println("<h1>Servlet Transaction at " + u + "</h1>");}
            
            out.println("</body>");
            out.println("</html>");
            }
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