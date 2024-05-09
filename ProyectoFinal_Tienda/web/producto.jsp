<%-- 
    Document   : producto
    Created on : 26 abr 2024, 11:07:14
    Author     : usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${producto.nombre}</title>
    </head>
    <body>
        <h1>${producto.nombre}</h1>
        <img src="${producto.url}" alt="${producto.nombre}"/>
        <p>${producto.descripcion}</p>
    </body>
</html>
