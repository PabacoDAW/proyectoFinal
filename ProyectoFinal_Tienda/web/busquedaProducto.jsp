<%-- 
    Document   : busquedaProducto
    Created on : 30 abr 2024, 13:34:46
    Author     : Rovimatica
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="img/icono.png" />
        <title>PaCoTech</title>
    </head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script>
        function filtrar() {
            var filtro = document.getElementById('filtro').value;
            console.log(filtro);
            $.ajax({
                method: "POST",
                url: "FiltrarProductos",
                data: {filtro: filtro}
            }).done(function (listado) {
                $("#listadoProductos").html(listado);
            });
        }
    </script>
    <body>
        <h1>PaCoTech</h1>
        <div>
            <input placeholder="Buscar productos..." type="text" id="filtro" onkeyup="filtrar()">
        </div>
        <div id="listadoProductos">
            <table>
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Descripción</th>
                        <th>Precio</th>
                        <th>Cantidad disponible</th>
                        <th>Marca</th>
                        <th>Categoría</th>
                        <th>Imagen</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${productos}" var="producto">
                        <tr>
                            <td>${producto.nombre}</td>
                            <td>${producto.descripcion}</td>
                            <td>${producto.precio}</td>
                            <td>${producto.stock}</td>
                            <td>
                                <c:if test="${empty producto.marcas}">
                                    Vacio
                                </c:if>
                                <c:forEach items="${producto.marcas}" var="marca">
                                    ${marca.nombre}
                                    <c:if test="${!status.last}">
                                        <br/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:if test="${empty producto.categorias}">
                                    Vacio
                                </c:if>
                                <c:forEach items="${producto.categorias}" var="categoria">
                                    ${categoria.nombre}
                                    <c:if test="${!status.last}">
                                        <br/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td><img src="" alt="alt"/></td>
                            <td><a href="Producto?producto=${producto.id}">Producto</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
