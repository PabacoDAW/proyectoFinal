<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Error en la aplicación</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                text-align: center;
                padding: 50px;
            }
            .error-container {
                background-color: #fff;
                border-radius: 10px;
                box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
                padding: 20px;
                display: inline-block;
                text-align: left;
            }
            h1 {
                color: #e74c3c;
            }
            p {
                color: #333;
                font-size: 18px;
            }
            .link {
                margin-right: 50px;
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <div class="error-container">
            <h1>¡Error!</h1>
            <p>Ocurrió un problema inesperado en la aplicación.</p>
            <p>Vuelva mas tarde mientras que intentamos solucionarlo</p>
            <c:if test="${usuario.tipoUsuario eq 'ADMINISTRADOR'}">
                <a class="link" href="${pageContext.request.contextPath}/admin/Gestion">Ir a la gestión de administrador</a>
            </c:if>
            <a class="link" href="${pageContext.request.contextPath}/PaginaPrincipal">Ir a la página principal</a>
        </div>
    </body>
</html>
