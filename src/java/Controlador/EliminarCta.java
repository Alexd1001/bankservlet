/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Entidades.Cliente;
import Entidades.Cuenta;
import Entidades.Transacciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Mao
 */
public class EliminarCta extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    
          
     public void eliminaRegristros(EntityManager em,int documentoid) {
           
       
       
       Query query =  em.createNativeQuery("select cuentaid,id from cliente inner join cuenta on cuenta.documento_id = cliente.documentoid  inner join transacciones on transacciones.CUENTA_ID=cuenta.CUENTAID where documentoid=?");
       query.setParameter(1, documentoid);
       
       List<Object[]> lista1 = query.getResultList();
        
         em.getTransaction().begin();
         Transacciones trans = em.find(Transacciones.class, lista1.get(0)[1]);
         if (trans != null) 
            em.remove(trans);
            
         
         Cuenta cta = em.find(Cuenta.class, lista1.get(0)[0]);
         if (cta != null) 
            em.remove(cta);
         
         Cliente cte = em.find(Cliente.class, documentoid);
         if (cte != null) 
            em.remove(cte);
         
          em.getTransaction().commit();    
           
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BankApplicationPU");
        EntityManager em = emf.createEntityManager();
        
        HttpSession session=request.getSession(false);  
        int documento = (Integer)session.getAttribute("documento");  
        
        eliminaRegristros(em,documento);
                
        session.invalidate();  
        response.setContentType("text/html");
        String site = new String("MainController");
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
