/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Video;

/**
 *
 * @author Pau
 */
@WebServlet(name = "servletRegistroVid", urlPatterns = {"/servletRegistroVid"})
public class servletRegistroVid extends HttpServlet {

    private int registred;

    private void createVideo(String title, String author, String date,
            String duration, String description, String format, String path,
            String username) {
        Video v = new Video(title, author, date, duration, description,
                format, path, username);

        this.registred = v.createDBVideo();
    }

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
        HttpSession sess = request.getSession(true);
        if (sess.getAttribute("username") == null) {
            response.sendRedirect("index.jsp");
        } else {
            String username = (String) sess.getAttribute("username");
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String date = request.getParameter("date");
            String duration = request.getParameter("duration");
            String description = request.getParameter("description");
            String format = request.getParameter("format");
            String path = request.getParameter("path");

            this.createVideo(title, author, date, duration, description, format,
                    path, username);

            response.sendRedirect("videoRes.jsp?registration="
                    + URLEncoder.encode(String.valueOf(this.registred), "UTF-8"));
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
