/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import modelo.DB;

/**
 *
 * @author Pau
 */
@WebService(serviceName = "SearchWS")
@Stateless()
public class SearchWS {

    private final String CONEXION = "jdbc:derby://localhost:1527/ISDCM";
    private final String USUARIO = "isdcm";
    private final String PASSWORD = "password";
    private Connection conexion;
    private Statement sentencia;
    private DB db;

    public SearchWS() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            this.conexion = DriverManager.getConnection(this.CONEXION, this.USUARIO, this.PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cerca de vídeos per títol
     *
     * @param title
     * @return
     */
    @WebMethod(operationName = "searchVideoByTitle")
    public String searchVideoByTitle(@WebParam(name = "title") String title) {
        String answer = "";
        ResultSet rs;

        try {
            String query = "SELECT TITLE, AUTHOR, DATE, DURATION, VIEWS, FORMAT, PATH FROM ISDCM.VIDEOS WHERE LOWER(TITLE) LIKE '%" + title.toLowerCase() + "%' FETCH FIRST 5 ROWS ONLY";
            System.out.println(query);
            sentencia = this.conexion.createStatement();

            rs = sentencia.executeQuery(query);

            while (rs.next()) {
                answer += rs.getString("title");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return answer;
    }

    /**
     * Cerca de vídeos per autor
     *
     * @param author
     * @return
     */
    @WebMethod(operationName = "searchVideoByAuthor")
    public String searchVideoByAuthor(@WebParam(name = "author") String author) {
        return db.searchVideoByAuthor(author);
    }

    /**
     * Cerca de vídeos per any
     *
     * @param year
     * @return
     */
    @WebMethod(operationName = "searchVideoByYear")
    public String searchVideoByYear(@WebParam(name = "year") String year) {
        return db.searchVideoByYear(year);
    }

}
