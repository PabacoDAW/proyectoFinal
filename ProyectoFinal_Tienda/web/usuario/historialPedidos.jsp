<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PaCoTech</title>
    </head>
    <body>
        <h1>Historial de Pedidos</h1>
        <table>
            <thead>
                <tr>
                    <td>Productos</td>
                    <td>Estado</td>
                    <td>Entrega a domicilio</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${pedidos}" var="pedido">
                    <tr>
                        <td>
                            <c:if test="${empty pedido.productos}">
                                Vacio
                            </c:if>
                            <c:forEach items="${pedido.productos}" var="producto">
                                ${producto.nombre}
                            </c:forEach>
                        </td>
                        <td>${pedido.estado}</td>
                        <td>
                            <c:if test="${pedido.entregaADomicilio}">
                                Entrega a domicilio
                            </c:if>
                            <c:otherwise>
                                A recoger en una de nuestras tiendas
                            </c:otherwise>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
