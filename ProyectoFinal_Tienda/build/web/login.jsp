

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <div class="container">
            <h1>Inicio de Sesión</h1>
            <br>
            <br>
            <form method="post">
                <label>E-mail</label>
                <input type="text" name="email" required="">
                <br>
                <label>Contraseña</label>
                <input type="password" name="password" required="">
                <br>
                <c:if test="${not empty error}">
                    <div>
                        ${error}
                    </div>
                </c:if>
                <br>
                <input type="submit" value="Iniciar Sesión">
            </form>
        </div>
        <br/>
        <a href="./PaginaPrincipal">Entrar como invitado</a>
        <br/>
        <a href="./Registro">Registrarse</a>
    </body>
</html>
