<%-- 
    Document   : registroRes
    Created on : 28-feb-2017, 22:28:01
    Author     : Pau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controlador.servletRegistroUsu"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creació d'usuari</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <div class="box">
            <% if (request.getParameter("registration").equals("0")) {
                    out.println("<h1>Usuari creat!</h1>");
                } else {
                    if (request.getParameter("registration").equals("1")) {
                        out.println("<h1>Error: no es compleixen requisits de camps</h1>");
                    }
                    else {
                        if(request.getParameter("registration").equals("2")) {
                            out.println("<h1>Error: usuari ja existent</h1>");
                        }
                        else {
                            if(request.getParameter("registration").equals("3")) {
                                out.println("<h1>Error: mail ja existent</h1>");
                            }
                        }
                    }
                }
            %>

            <meta http-equiv="refresh" content="2;url=index.jsp" />

        </div>
    </body>
</html>
