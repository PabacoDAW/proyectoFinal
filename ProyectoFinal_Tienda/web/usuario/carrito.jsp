
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PaCoTech</title>
    </head>
    <body>
        <h1>Carrito</h1>
        <p>Coste del carrito: ${precio}€</p>
        <table>
            <thead>
                <tr>
                    <td>Nombre</td>
                    <td>Descripcion</td>
                    <td>Precio</td>
                    <td>Stock</td>
                    <td>Imagen</td>
                </tr>
            </thead>
            <c:forEach items="${productos}" var="producto">
                <tr>
                    <td>${producto.nombre}</td>
                    <td>${producto.descripcion}</td>
                    <td>${producto.precio}</td>
                    <td>${producto.stock}</td>
                    <td><img src="${producto.url}" alt="${producto.nombre}"/></td>
                    <form>
                        <input type="submit" name="id" value="${producto.id}" />
                    </form>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
