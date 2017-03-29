<%-- 
    Document   : videoRes
    Created on : 22-mar-2017, 17:49:48
    Author     : Pau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creació de video</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <div class="box">
            <% if (request.getParameter("registration").equals("0")) {
                    out.println("<h1>Vídeo creat!</h1>");
                } else {
                        out.println("<h1>Error: no es compleixen requisits de camps</h1>");
                }
            %>

            <meta http-equiv="refresh" content="2;url=videos.jsp" />

        </div>
    </body>
</html>
