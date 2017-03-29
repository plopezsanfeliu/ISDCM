/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;

/**
 *
 * @author Pau
 */
@WebServlet(name = "servletLoginUsu", urlPatterns = {"/servletLoginUsu"})
public class servletLoginUsu extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String username = request.getParameter("usuario");
            String password = request.getParameter("password");

            if (this.verifyUser(username, password)) {
                HttpSession sess = request.getSession(true);
                sess.setAttribute("username", username);
                
                response.sendRedirect("loginRes.jsp?login=" + 
                        URLEncoder.encode(String.valueOf(true), "UTF-8"));
            } else {
                response.sendRedirect("loginRes.jsp?login=" + 
                        URLEncoder.encode(String.valueOf(false), "UTF-8"));
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

    private boolean verifyUser(String username, String password) {
        boolean valid = false;
        try {
            Usuario u = new Usuario(username, password);
                        
            if (u.validateDBUser()) {
                valid = true;
            }
            
        } catch (NoSuchAlgorithmException | SQLException | 
                ClassNotFoundException ex) {
            Logger.getLogger(servletLoginUsu.class.getName()).log(Level.SEVERE, 
                    null, ex);
        }
        return valid;

    }
}
