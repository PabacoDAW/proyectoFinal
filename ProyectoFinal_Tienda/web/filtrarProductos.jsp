

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle var="mensajes" basename="internacionalizar.message"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            </tr>
        </c:forEach>
    </tbody>
</table>
