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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    
   public ArrayList<String> comparar(EntityManager em,HttpServletRequest request ) throws IOException
    {   
        
        ArrayList<String> lista = new ArrayList<String>();
       
             
        
        //Cuenta up = em.find(Cuenta.class, Integer.parseInt(request.getParameter("nocuenta")));
        
        Query query = em.createQuery("SELECT x FROM Transacciones x ");
        List<Transacciones> lista1 = query.getResultList();
        
        lista.add(0,"false");
        for (Transacciones p : lista1) {
        
            
            
        if(p.getCuentaId().getCuentaid()==Integer.parseInt(request.getParameter("nocuenta")))
        {
            lista.add(0,"true");
            lista.add(1,Integer.toString(p.getSaldo()));
            break;
        }
        
            
            
        

        }
        
        
        
            
        
        
        
        return lista;
   }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BankApplicationPU");
        EntityManager em = emf.createEntityManager();
        int numerocuenta;
        
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
        for(Cookie cookie : cookies){
        if(cookie.getName().equals("name")) 
             numerocuenta= Integer.parseInt(cookie.getValue());
            }
        
        }
        
        ArrayList <String> lista= comparar(em,request);
        
        if(Boolean.parseBoolean(lista.get(0)))
        {
         
            try (PrintWriter out = response.getWriter()) {
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet tTransaction</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet tTransaction at " + lista.get(0)+ lista.get(1) + "</h1>");
            out.println("</body>");
            out.println("</html>");
            }
        }
        else{
        
        try (PrintWriter out = response.getWriter()) {
           
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet tTransaction</title>");            
            out.println("</head>");
            out.println("<body>");
           for(String u:lista) {
            out.println("<h1>Servlet tTransaction at " + request.getContextPath() +u+ "</h1>");
        }
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
