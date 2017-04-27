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

    public SearchWS() {
        try {
            //Class.forName("org.apache.derby.jdbc.ClientDriver");
            this.conexion = DriverManager.getConnection(this.CONEXION, this.USUARIO, this.PASSWORD);
            //Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SearchWS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * @param type 1 si es busca per títol, 2 si es busca per autor i 3 si es
     * busca per any de creació
     * @param str
     * @return Seqüència XML amb resposta
     */
    private String doSearch(int type, String str) {
        String answer = "";
        ResultSet rs;
        String query;

        try {
            switch (type) {
                case 1:
                    query = "SELECT TITLE, AUTHOR, DATE, DURATION, VIEWS, DESCRIPTION FROM ISDCM.VIDEOS WHERE LOWER(TITLE) LIKE '%" + str.toLowerCase() + "%'";

                    break;
                case 2:
                    query = "SELECT TITLE, AUTHOR, DATE, DURATION, VIEWS, DESCRIPTION FROM ISDCM.VIDEOS WHERE LOWER(AUTHOR) LIKE '%" + str.toLowerCase() + "%'";

                    break;
                default:
                    query = "SELECT TITLE, AUTHOR, DATE, DURATION, VIEWS, DESCRIPTION FROM ISDCM.VIDEOS WHERE LOWER(DATE) LIKE '%" + str.toLowerCase() + "%'";

            }
            sentencia = this.conexion.createStatement();

            rs = sentencia.executeQuery(query);

            while (rs.next()) {
                answer += "<myvideo>";
                answer += "<titlo> <b>Títol:</b> " + rs.getString("title") + "</titlo>"
                        + "<br/><author> <b>Autor:</b> " + rs.getString("author") + "</author>"
                        + "<br/><date> <b>Data de llançament(dd/mm/aaaa):</b> " +rs.getString("date") + "</date>"
                        + "<br/><duration> <b>Duració (hh:mm:ss):</b> " + rs.getString("duration") + "</duration>"
                        + "<br/><views> <b>Visulitzacions:</b> " + rs.getString("views") + "</views>"
                        + "<br/><description> <b>Descripció:</b> " + rs.getString("description") + "</description>";
                answer += "</myvideo>";
                answer += "<br/><hr /><br/>";
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return answer;
    }

    /**
     * Cerca de vídeos per títol
     *
     * @param title
     * @return
     */
    @WebMethod(operationName = "searchVideoByTitle")
    public String searchVideoByTitle(@WebParam(name = "title") String title) {
        return doSearch(1, title);
    }

    /**
     * Cerca de vídeos per autor
     *
     * @param author
     * @return
     */
    @WebMethod(operationName = "searchVideoByAuthor")
    public String searchVideoByAuthor(@WebParam(name = "author") String author) {
        return doSearch(2, author);
    }

    /**
     * Cerca de vídeos per any
     *
     * @param year
     * @return
     */
    @WebMethod(operationName = "searchVideoByYear")
    public String searchVideoByYear(@WebParam(name = "year") String year) {
        return doSearch(3, year);
    }

}
