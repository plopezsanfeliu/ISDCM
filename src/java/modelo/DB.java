/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pau
 */
public class DB {

    private final String CONEXION = "jdbc:derby://localhost:1527/ISDCM";
    private final String USUARIO = "isdcm";
    private final String PASSWORD = "password";
    private Connection conexion;
    private Statement sentencia;

    public DB() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            this.conexion = DriverManager.getConnection(this.CONEXION, this.USUARIO, this.PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Crea vídeo a DB amb els paràmetres passats.
     *
     * @param title
     * @param author
     * @param date
     * @param duration
     * @param description
     * @param format
     * @param path
     * @param username
     */
    public void createDBVideo(String title, String author, String date, String duration,
            String description, String format, String path, String username) {

        String query = "INSERT INTO VIDEOS(title, author, date, duration, views, description, format, path, user_id)"
                + " VALUES (\'" + title + "\',\'" + author + "\',\'" + date + "\',\'" + duration + "\'," + 0 + ",\'" + description + "\',\'" + format + "\',\'" + path + "\',\'" + username + "\')";
        System.out.println(query);
        try {
            sentencia = this.conexion.createStatement();
            sentencia.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Crea usuari a DB amb els paràmetres passats.
     *
     * @param name
     * @param surname
     * @param email
     * @param username
     * @param password
     */
    public void createDBUser(String name, String surname, String email, String username, String password) {
        String query = "INSERT INTO USERS(name, surname, email, username, password)"
                + " VALUES (\'" + name + "\',\'" + surname + "\',\'" + email + "\',\'" + username + "\',\'" + password + "\')";

        try {
            sentencia = this.conexion.createStatement();
            sentencia.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Mètode cridat al intentar login que retorna cert o fals si les
     * credencials són correctes, mitjançant l'ús de consulta a BD.
     *
     * @param username
     * @param password
     * @return Booleà OK o KO.
     */
    public boolean validateDBUser(String username, String password) {
        String count = "0";
        boolean valid = false;

        try {
            String query = "SELECT COUNT(*) AS count FROM USERS WHERE username = '" + username + "' AND password = '" + password + "'";

            sentencia = this.conexion.createStatement();

            ResultSet rs = sentencia.executeQuery(query);

            while (rs.next()) {
                count = rs.getString("count");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!(count.equals("0"))) {
            valid = true;
        }

        return valid;
    }

    /**
     * Comprova les dues condicions (clau primària i unique) de username i email
     * consultant si existeix algún valor a la BD amb els paràmetres del
     * formulari.
     *
     * @param username
     * @param email
     * @return Codi d'error (o èxit)
     */
    public int securityCheck(String username, String email) {
        String count = "0";
        int errCode = 0;
        try {

            // comprovación de campo único email
            String query1 = "SELECT COUNT(*) AS count FROM USERS WHERE username = '" + username + "'";
            // comprovación de único username
            String query2 = "SELECT COUNT(*) AS count FROM USERS WHERE email = '" + email + "'";

            sentencia = this.conexion.createStatement();

            ResultSet rs1 = sentencia.executeQuery(query1);

            while (rs1.next()) {
                count = rs1.getString("count");
                if (!(count.equals("0"))) {
                    errCode = 2;
                }
            }

            if (count.equals("0")) {
                ResultSet rs2 = sentencia.executeQuery(query2);

                while (rs2.next()) {
                    count = rs2.getString("count");
                    if (!(count.equals("0"))) {
                        errCode = 3;
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (count.equals("0")) {
            errCode = 0;
        }

        return errCode;
    }

    /**
     * Retorna els number vídeos últims afegits a la BD. Mètode cridat per la
     * view "videos.jsp".
     *
     * @param number
     * @return ResultSet amb els number últims vídeos
     */
    public ResultSet getLastVideos(int number) {
        ResultSet rs = null;

        try {
            String query = "SELECT TITLE, AUTHOR, DATE, DURATION, VIEWS, FORMAT, PATH FROM ISDCM.VIDEOS FETCH FIRST " + number + " ROWS ONLY";

            sentencia = this.conexion.createStatement();

            rs = sentencia.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;
    }

    public String searchVideoByTitle(String title) {
        String answer = "";
        ResultSet rs;

        try {
            String query = "SELECT TITLE, AUTHOR, DATE, DURATION, VIEWS, FORMAT, PATH FROM ISDCM.VIDEOS FETCH FIRST 5 ROWS ONLY";
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

    public String searchVideoByAuthor(String author) {
        return null;
    }

    public String searchVideoByYear(String author) {
        return null;
    }
}
