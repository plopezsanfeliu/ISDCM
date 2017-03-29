/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
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
import modelo.Usuario;

/**
 *
 * @author Pau
 */
@WebServlet(name = "servletRegistroUsu", urlPatterns = {"/servletRegistroUsu"})
public class servletRegistroUsu extends HttpServlet {

    private int registred;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param name
     * @param surname
     * @param email
     * @param username
     * @param password
     */
    private void createUser(String name, String surname, String email,
            String username, String password) {
        try {
            Usuario u = new Usuario(name, surname, email, username, password);
            this.registred = u.createDBUser();
        } catch (NoSuchAlgorithmException | SQLException
                | ClassNotFoundException ex) {
            Logger.getLogger(servletRegistroUsu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getRegisterStatus() {
        return registred;
    }

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("nombre");
        String surname = request.getParameter("apellidos");
        String email = request.getParameter("correo");
        String username = request.getParameter("usuario");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        if (password1.equals(password2)) {
            this.createUser(name, surname, email, username, password2);
        }
        response.sendRedirect("registroRes.jsp?registration="
                + URLEncoder.encode(String.valueOf(this.registred), "UTF-8"));
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
