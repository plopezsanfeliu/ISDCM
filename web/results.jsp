<%-- 
    Document   : results
    Created on : 26-abr-2017, 0:20:56
    Author     : Pau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
            <%-- start web service invocation --%><hr/>
    <%
    try {
	webservice.SearchWS_Service service = new webservice.SearchWS_Service();
	webservice.SearchWS port = service.getSearchWSPort();
	 // TODO initialize WS operation arguments here
	java.lang.String title = "a";
	// TODO process result here
	java.lang.String result = port.searchVideoByTitle(title);
	out.println("Result = "+result);
    } catch (Exception ex) {
	System.out.println("Err: " + ex.getMessage());
    }
    %>
    <%-- end web service invocation --%><hr/>

    </body>
</html>
