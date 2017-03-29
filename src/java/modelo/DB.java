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

    public void createDBVideo(String title, String author, String date, String duration,
            String description, String format, String path) {

        String query = "INSERT INTO VIDEOS(title, author, date, duration, views, description, format, path)"
                + "VALUES(\'" + title + "\',\'" + author + "\',\'" + date + "\',\'" + duration + "\'," + 0 + ",\'" + description + "\',\'" + format + "\',\'" + path + "\')";

        try {
            sentencia = this.conexion.createStatement();
            sentencia.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createDBUser(String name, String surname, String email, String username, String password) {
        String query = "INSERT INTO USERS(name, surname, email, username, password)"
                + "VALUES(\'" + name + "\',\'" + surname + "\',\'" + email + "\',\'" + username + "\',\'" + password + "\')";
        
        try {
            sentencia = this.conexion.createStatement();
            sentencia.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
}
