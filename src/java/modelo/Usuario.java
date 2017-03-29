/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 *
 * @author Pau
 */
public class Usuario {

    private final String name;
    private final String surname;
    private final String mail;
    private final String username;
    private final String password;
    private final DB db;

    public Usuario(String name, String surname, String mail, String username,
            String password) throws NoSuchAlgorithmException, SQLException, ClassNotFoundException {
        SHA1 sha = new SHA1();
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.username = username;
        this.password = sha.getHash(password);
        this.db = new DB();        
    }

    public Usuario(String username, String password) throws
            NoSuchAlgorithmException, SQLException, ClassNotFoundException {
        SHA1 sha = new SHA1();
        this.name = null;
        this.surname = null;
        this.mail = null;
        this.username = username;
        this.password = sha.getHash(password);
        this.db = new DB();
    }

    private boolean validateUser() {
        boolean valid = false;

        if (this.name.length() > 1 && this.name.length() < 51
                && this.surname.length() > 1 && this.surname.length() < 101
                && this.mail.length() > 5 && this.mail.length() < 301
                && this.username.length() > 2 && this.name.length() < 51
                && this.password.length() > 7 && this.name.length() < 13) {
            valid = true;
        }
        return valid;
    }

    public int createDBUser() {
        int errCode;
        
        if (this.validateUser()) {
            errCode = db.securityCheck(this.username, this.mail);
            if (errCode == 0) {
                db.createDBUser(this.name, this.surname, this.mail, 
                        this.username, this.password);
            }
        } else {
            errCode = 1;
        }
        return errCode;
    }

    public boolean validateDBUser() {
        return db.validateDBUser(this.username, this.password);
    }
}
