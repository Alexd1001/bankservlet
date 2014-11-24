/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Entidades.Cliente;
import Entidades.Cuenta;
import Entidades.Transacciones;
import Modelo.Clientes;
import Modelo.CuentaAhorros;
import Modelo.CuentaCte;
import java.io.IOException;
import java.util.Enumeration;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mao
 */
@WebServlet(name = "Registro", urlPatterns = {"/Registro"})
public class Registro extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param object
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    public void save(HttpServletRequest request, Clientes object)
    {    
        //Clientes object = new Clientes();
         Enumeration en = request.getParameterNames();
             
         
             while (en.hasMoreElements()) 
             {
                 String paramName = (String) en.nextElement();
                
              
                  switch (paramName) {
                 case "nombre":
                      object.setNombre(request.getParameter(paramName));
                     break;
                 case "apellido":
                      object.setApellido(request.getParameter(paramName));
                     break;
                 case "documentoid":
                     object.setDocumentoId(Integer.parseInt(request.getParameter(paramName)));
                     break;
                 case "saldo":
                     object.setCuenta(new CuentaAhorros());
                     object.getCuenta().setSaldo(Integer.parseInt(request.getParameter(paramName)));
                     break;
                 case "telefono":
                     object.setTelefono(Integer.parseInt(request.getParameter(paramName)));
                     break;
                 case "direccion":
                     object.setDireccion(request.getParameter(paramName));
                     break;      
                }
            }
             
    }
    
    
     
    public boolean create(EntityManager em, HttpServletRequest request, Clientes p)
    {   
        Cliente up = em.find(Cliente.class, Integer.parseInt(request.getParameter("documentoid")));
        boolean flag=false;
        if(up==null)
        {
        
        Transacciones q = new Transacciones();
        Cuenta h = new Cuenta();
        
        
        Cliente t = new Cliente();          
        t.setNombre(p.getNombre());
        t.setApellido(p.getApellido());
        t.setDireccion(p.getDireccion());
        t.setTelefono(p.getTelefono());
        t.setDocumentoid(p.getDocumentoId());
        
        h.setDocumentoId(t);
        h.setTipocuenta(Integer.parseInt(request.getParameter("tipocuenta")));
        
        q.setCuentaId(h);
        p.setCuenta(new CuentaCte());
        q.setSaldo(Integer.parseInt(request.getParameter("saldo")));
        
        
        em.getTransaction().begin(); 
        em.persist(t);
        em.persist(h);
        em.persist(q);
        em.getTransaction().commit();        
        flag=true;
        }
        return flag;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BankApplicationPU");
        EntityManager em = emf.createEntityManager();
        Clientes p = new Clientes();
        save(request,p);
        
        if(create(em,request,p))
        {
           HttpSession session=request.getSession();
           session.setAttribute("documento", Integer.parseInt(request.getParameter("documentoid")));
           response.setContentType("text/html");
           String site = new String("transacciones.html");
           response.setStatus(response.SC_MOVED_TEMPORARILY);
           response.setHeader("Location", site); 
        }
        else{
           response.setContentType("text/html");
           String site = new String("index.html");
           response.setStatus(response.SC_MOVED_TEMPORARILY);
           response.setHeader("Location", site); 
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
