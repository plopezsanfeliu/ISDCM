/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

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
    
    
    private DB db;

    /**
     * Cerca de vídeos per títol
     * @param title
     * @return 
     */
    
    @WebMethod(operationName = "searchVideoByTitle")
    public String searchVideoByTitle(@WebParam(name = "title") String title) {
        //TODO write your implementation code here:
        return db.searchVideoByTitle(title);
    }

    /**
     * Cerca de vídeos per autor
     * @param author
     * @return 
     */
    
    @WebMethod(operationName = "searchVideoByAuthor")
    public String searchVideoByAuthor(@WebParam(name = "author") String author) {
        //TODO write your implementation code here:
        return db.searchVideoByAuthor(author);
    }

    /**
     * Cerca de vídeos per any
     * @param year
     * @return 
     */
    
    @WebMethod(operationName = "searchVideoByYear")
    public String searchVideoByYear(@WebParam(name = "year") String year) {
        //TODO write your implementation code here:
        return db.searchVideoByYear(year);
    }

}
