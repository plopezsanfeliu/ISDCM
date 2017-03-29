<%-- 
    Document   : logout
    Created on : 03-mar-2017, 12:36:04
    Author     : Pau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% session.invalidate();  %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <div class="box">
            <h1>Log out realitzat!</h1>
        </div>
        <meta http-equiv="refresh" content="2;url=index.jsp" />
    </body>
</html>
