<%-- 
    Document   : registro
    Created on : 23 abr 2024, 11:58:36
    Author     : Rovimatica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
    </head>
    <body>
        <h1>Registro</h1>
        <form method="post">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre"><br><br>

            <label for="apellidos">Apellidos:</label>
            <input type="text" id="apellidos" name="apellidos"><br><br>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required ><br><br>
            
            <label for="nombreUsu">Usuario:</label>
            <input type="text" id="nombreUsu" name="nombreUsu" required ><br><br>

            <label for="contrasena">Contraseña:</label>
            <input type="password" id="contrasena" name="contrasena" required ><br><br>

            <label for="domicilio">Domicilio:</label>
            <input type="text" id="domicilio" name="domicilio"><br><br>

            <input type="submit" value="Registrar">
            <br/>
            <a href="./Login">Logearse</a>
        </form>
    </body>
</html>
