/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import modelo.DB;

/**
 * REST Web Service
 *
 * @author Pau
 */
@Path("generic")
public class ViewsIncrementer {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ViewsIncrementer
     */
    public ViewsIncrementer() {
    }

    /**
     * Sample of GET method
     * @param id
     * @return 
     */
    @Path("incViews")
    @GET    
    @Produces("text/html")
    public String incViews(@QueryParam("id") String id) {
        DB db = new DB();
        db.incrementView(id);
        return "<meta http-equiv=\"refresh\" content=\"0;"
                + " url=http://localhost:8080/ISDCM/player.jsp?id=" + id +
                "\" />";
    }
}
