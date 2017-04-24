<%-- 
    Document   : videos
    Created on : 21-mar-2017, 12:06:24
    Author     : Pau
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="modelo.DB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="style.css">

        <style>
            table {
                border-collapse: collapse;
            }

            th, td {
                padding: 2px;
            }

            tr:nth-child(even){background-color: #f2f2f2}

            th {
                background-color: #4CAF50;
                color: white;
            }
        </style>

        <script>
            function validateForm1() {
                var name = document.forms["formulario1"]["name"].value;
                var valid = false;
                if (name === "") { // comprovació de camps buits
                    alert("Error: s'han de completar els camps del formulari");
                } else {
                    if ((name.length < 3) || (name.length > 50)) {
                        // comprovació de longituds
                        alert("Error: no es compleixen longituds de camps");
                    } else {
                        valid = true;
                    }
                }
                return valid;
            }

            function validateForm2() {
                var title = document.forms["formulario2"]["title"].value;
                var author = document.forms["formulario2"]["author"].value;
                var date = document.forms["formulario2"]["date"].value;
                var duration = document.forms["formulario2"]["duration"].value;
                var description = document.forms["formulario2"]["description"].value;
                var format = document.forms["formulario2"]["format"].value;
                var path = document.forms["formulario2"]["path"].value;
                var valid = false;
                if ((title === "") || (author === "") || (date === "") ||
                        (duration === "") || (description === "") ||
                        (description === "") || (path === "")) {
                    alert("Error: se tienen que completar todos los campos del formulario");
                } else {
                    if ((title.length < 3 || title.length > 100) ||
                            (author.length < 2 || author.length > 100) ||
                            (date.length !== 10) || (duration.length !== 8) ||
                            (description.length < 25 || description.length > 255) ||
                            (format.length < 2 || format.length > 5) ||
                            (path.length < 5 || path.length > 300)) {
                        alert("No se cumplen los requisitos de longitud de campos");
                    } else {
                        valid = true;
                    }
                }
                return valid;
            }
        </script>
    </head>
    <body>
        <%
            if (session.getAttribute("username") == null) {
                out.println("session.getAttribute(\"<meta http-equiv=\"refresh\" content=\"0;url=index.jsp\" />");
            } else {
                out.println(session.getAttribute("username"));
                out.println("<a href=\"logout.jsp\">Log out</a>");
            }
        %>

        <div class="bigbox">
            <h1>Cercador de vídeos</h1>
            <form name="Test" method="post" action="SearchWSServlet"
                  onsubmit="return validateForm1()">
                <p>Cerca per nom:
                    <input type="text" name="name">
                    <input type="submit" value="Cercar nom" name="spellcheckbutton">
                </p>
            </form>

            <h1>Últims vídeos</h1>
            <table style="margin: 0 auto;">
                <tr>
                    <th>Títol</th>
                    <th>Autor</th>
                    <th>Duració</th>
                    <th>Data</th>
                    <th>Visualitzacions</th>
                    <th>Format</th>
                </tr>
                <%
                    DB db = new DB();
                    ResultSet rs = db.getLastVideos(5);

                    while (rs.next()) {
                        out.println("<tr>");
                        out.println("<td><a href=\"" + rs.getString("path") + "\">" + rs.getString("title") + "</a></td>");
                        out.println("<td>" + rs.getString("author") + "</td>");
                        out.println("<td>" + rs.getString("duration") + "</td>");
                        out.println("<td>" + rs.getString("date") + "</td>");
                        out.println("<td>" + rs.getString("views") + "</td>");
                        out.println("<td>" + rs.getString("format") + "</td>");
                        out.println("</tr>");
                    }
                %>
            </table>

            <h1>Registrar vídeo</h1>
            <form name="formulario2" method="post" action="servletRegistroVid"
                  onsubmit="return validateForm2()">
                Títol: <input type="text" name="title"><br>
                Autor: <input type="text" name="author"><br>
                Data de llançament(dd/mm/aaaa): <input type="date" name="date"><br>
                Duració (hh:mm:ss): <input type="text" name="duration" value="hh:mm:ss"><br>
                Descripció: <input type="text" name="description">
                Format: <input type="text" name="format">
                Ruta: <input type="text" name="path">
                <br>
                <input type="submit" name="submit" value="Registrar vídeo">
            </form>
        </div>
    </body>
</html>