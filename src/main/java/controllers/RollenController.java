/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Rol;

/**
 *
 * @author Daan
 */
public class RollenController extends HttpServlet {

    private static String titelNieuw = "Nieuwe rol"; //Titel voor de Nieuwe gebruiker pagina
    private static String titelWijzig = "Wijzigen rol"; //Titel voor de Wijzig gebruiker pagina
    
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        HttpSession sessie = request.getSession();
        List<Rol> rollen;
        
        if (sessie.getAttribute("rollen") != null) {
            rollen = (List) sessie.getAttribute("rollen");
        } else {
            rollen = new LinkedList();
        }

        Rol formRol = getRolFromRequest(request);
        
        String uri = request.getRequestURI();

        int lastIndex = uri.lastIndexOf("/");
        String action = uri.substring(lastIndex + 1);
        String dispatchUrl = "index.jsp";

        if (action.equals("readall")) {
            request.setAttribute("rollen", rollen);
            dispatchUrl = "rollen.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
            rd.forward(request, response);
        }
        else if(action.equals("nieuw")){
            doorsturen(request, response, titelNieuw);
        }
        else if(action.equals("wijzig")){
            request.setAttribute("id", request.getParameter("id"));
            doorsturen(request, response, titelWijzig);
        }
        
        
        else if(action.equals("verwijder")){
            for (int i = 0; i < rollen.size(); i++) {
                Rol tempRol = (Rol) rollen.get(i);
                
                // Indien het CustomerNumber gelijk is aan de 'id' parameter,
                // verwijder deze gebruiker dan van de lijst
                if (tempRol.getRolNumber() == Long.parseLong(request.getParameter("id"))) {
                    rollen.remove(i);
                }
            }
            dispatchUrl = "readall";
            RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
            rd.forward(request, response);
        }
        else if(action.equals("dowijzig")){
            
            for (int i = 0; i < rollen.size(); i++) {
                Rol tempRol = (Rol) rollen.get(i);

                if (tempRol.getRolNumber() == Long.parseLong(request.getParameter("id"))) {
                    tempRol.setRolName(formRol.getRolName());
                }
                sessie.setAttribute("rollen", rollen);
            }
            
            dispatchUrl = "readall";
            RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
            rd.forward(request, response);
        }
        else if(action.equals("donieuw")){

            long uniekId = System.nanoTime();
            formRol.setRolNumber(uniekId);
            rollen.add(formRol);
            sessie.setAttribute("rollen", rollen);
            
            dispatchUrl = "readall";

            RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
            rd.forward(request, response);
        }

        
    }
    
//    public void doorsturen(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession sessie = request.getSession();
//        List<Rol> rollen;
//        
//        if (sessie.getAttribute("rollen") != null) {
//            rollen = (List) sessie.getAttribute("rollen");
//        } else {
//            rollen = new LinkedList();
//        }
//        
//        request.setAttribute("rollen", rollen);
//        RequestDispatcher rd = request.getRequestDispatcher("rollen.jsp");
//        rd.forward(request, response);
//    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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

    private Rol getRolFromRequest(HttpServletRequest request) {
        Rol r = new Rol();

        if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
            r.setRolNumber(Long.parseLong(request.getParameter("id")));
        }
        if (request.getParameter("rol") != null) {
            r.setRolName(request.getParameter("rol"));
        }
       

        return r;
    }
    
    private void doorsturen(HttpServletRequest request, HttpServletResponse response, String titel)
            throws ServletException, IOException {
        // Set de pagina titel op het request
        request.setAttribute("paginaTitel", titel);

        // Stuur het resultaat van gebruiker_wijzigen.jsp terug naar de client
        String address = "rol_wijzigen.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

}
