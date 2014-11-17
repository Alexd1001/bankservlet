package Controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Entidades.Cliente;
import Entidades.Cuenta;
import Entidades.Transacciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adavila
 */
@WebServlet(urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /*public void save(Cliente object,HttpServletRequest request)
    {
         Enumeration en = request.getParameterNames();
             
             while (en.hasMoreElements()) 
             {
                 String paramName = (String) en.nextElement();
                
                
                switch (paramName) {
                 case "nombre":
                      object.setNombre(request.getParameter(paramName));
                     break;
                 case "cedula":
                     object.setCedulaid(Integer.parseInt(request.getParameter(paramName)));
                     break;
                 case "saldo":
                     object.setSaldo(Integer.parseInt(request.getParameter(paramName)));
                     break;
                }
            }
             
    }*/
     
    public void create(EntityManager em)
    {    
        Cliente p = new Cliente();
        
        p.setNombre("pepe");
        p.setApellido("zuluaga");
        p.setDireccion("Calle 21 no 8-12");
        p.setTelefono(312524503);
        p.setDocumentoid(1010194129);
        em.getTransaction().begin(); 
        em.persist(p);
        em.getTransaction().commit();  
        
        
            
              
    }
    
    
    /*public void create(EntityManager em,  Cliente object)
    {       Cuentas user = new Cuentas(); 
            user.setDocumentoid(object.getdocumentoId());
            user.setNombre(object.getNombre());
            user.setSaldo(object.getSaldo()); 
            em.getTransaction().begin(); 
            em.persist(user);
            em.getTransaction().commit();    
    }*/
     /*public boolean comparar(EntityManager em,HttpServletResponse response,HttpServletRequest request ) throws IOException
    {   
        boolean flag=false;
        Query query = em.createQuery("SELECT p FROM Usuarios p ", Cliente.class);
        List<Cliente> lista = query.getResultList();
        
        
        for (Cliente p : lista) {
        if(request.getParameter("documento").equals(p.getdocumentoId())){
            flag=true;
        break;    
        }   
        }
        return flag;
   }*/
    /* public void delete(EntityManager em,int accnumber)
    {
         em.getTransaction().begin();
         Cuentas y = em.find(Cuentas.class, accnumber);
         if (y != null) 
             em.remove(y);
         em.getTransaction().commit();
    }
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BankApplicationPU");
        EntityManager em = emf.createEntityManager();
        Cliente p = new Cliente();
        Cuenta h = new Cuenta();
        Transacciones q = new Transacciones();
       // save(p, request);
        p.setNombre("cepu");
        p.setApellido("xuluaga");
        p.setDireccion("Calle 21 no 8-12");
        p.setTelefono(31252853);
        p.setDocumentoid(201311123);
        
        h.setDocumentoId(p);
        h.setTipocuenta(2);
        
        q.setCuentaId(h);
        q.setSaldo(950000);
        
        em.getTransaction().begin(); 
        em.persist(p);
        em.persist(h);
        em.persist(q);
        em.getTransaction().commit();  
      response.setContentType("text/html");
      String site = new String("transacciones.html");

      response.setStatus(response.SC_MOVED_TEMPORARILY);
      response.setHeader("Location", site);    
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
