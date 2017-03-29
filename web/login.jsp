<%-- 
    Document   : login
    Created on : 02/03/2017, 17:34:24
    Author     : claudicervello
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" type="text/css" href="style.css">

        <script>
            function validateForm() {
                var usuario = document.forms["formulario"]["usuario"].value;
                var password = document.forms["formulario"]["password1"].value;

                var valid = false;

                if ((usuario === "") || (password === "")) { // comprovació de camps buits
                    alert("Error: falta algun campo");
                } else {
                    if ((usuario.length < 3 || usuario.length > 50) ||
                            (password.length < 8 || password.length > 12)) { // comprovació de longituds
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
        <div class="box">
            <h1>Entrada usuaris</h1>
            <form name="formulario" method="post" action="servletLoginUsu"
                  onsubmit="return validateForm()">
                Nom d'usuari: <input type="text" name="usuario"><br>
                Contrasenya: <input type="password" name="password"><br>
                <br>
                <input type="submit" name="submit" value="Entrar">
            </form>
        </div>
    </body>
</html>
