<%-- 
    Document   : index
    Created on : 03-mar-2017, 11:13:40
    Author     : Pau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="style.css">

        <script>
            function validateForm() {
                var nombre = document.forms["formulario"]["nombre"].value;
                var apellidos = document.forms["formulario"]["apellidos"].value;
                var correo = document.forms["formulario"]["correo"].value;
                var usuario = document.forms["formulario"]["usuario"].value;
                var password1 = document.forms["formulario"]["password1"].value;
                var password2 = document.forms["formulario"]["password2"].value;

                var valid = false;

                if ((nombre === "") || (apellidos === "") || (correo === "") ||
                        (usuario === "") || (password1 === "") ||
                        (password2 === "")) { // comprovació de camps buits
                    alert("Error: se tienen que completar todos los campos del formulario");
                } else {
                    if (password1 !== password2) { // comprovació d'igualtat de
                        // passwords
                        alert("Las contraseñas no coinciden");
                    } else {
                        if ((nombre.length < 2 || nombre.length > 50) ||
                                (apellidos.length < 2 || apellidos.length > 100) ||
                                (correo.length < 6 || correo.length > 300) ||
                                (usuario.length < 3 || usuario.length > 50) ||
                                (password1.length < 8 || password1.length > 12)) { // comprovació de longituds
                            alert("No se cumplen los requisitos de longitud de campos");
                        } else {
                            valid = true;
                        }
                    }
                }
                return valid;
            }
        </script>

    </head>
    <body>
        <%
            if(session.getAttribute("username") == null) {
                out.println("<a href=\"login.jsp\">Log in</a>");
            }
            else {
                out.println(session.getAttribute("username"));
                out.println("<a href=\"logout.jsp\">Log out</a>");
                out.println("session.getAttribute(\"<meta http-equiv=\"refresh\" content=\"0;url=videos.jsp\" />");
            }
        %>
        
        <div class="box">
            <h1>Registre usuaris</h1>
            <form name="formulario" method="post" action="servletRegistroUsu"
                  onsubmit="return validateForm()">
                Nom: <input type="text" name="nombre"><br>
                Cognoms: <input type="text" name="apellidos"><br>
                Correu electrònic: <input type="text" name="correo"><br>
                Nom d'usuari: <input type="text" name="usuario"><br>
                Contrasenya: <input type="password" name="password1"><br>
                Repetir contrasenya: <input type="password" name="password2">
                <br>
                <input type="submit" name="submit" value="Registrar usuari">
            </form>
        </div>

    </body>
</html>
