<%-- 
    Document   : loginRes
    Created on : 03-mar-2017, 12:49:07
    Author     : Pau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <div class="box">
            <% if (request.getParameter("login").equals("true")) {
                    out.println("<h1>Login realitzat correctament!</h1>");
                } else {
                    out.println("<h1>Error en el procès, torna-ho a provar</h1>");
                }
            %>           
            <meta http-equiv="refresh" content="2;url=index.jsp" />     
        </div>
    </body>
</html>
