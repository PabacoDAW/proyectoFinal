<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Gestión de Pedidos</title>
        <link rel="stylesheet" href="../css/gestionUsuario.css">
    </head>
    <body>
        <header class="fixed-header">
            <a class="back-button" href="../PaginaPrincipal">Volver a la tienda</a>>
            <a style="margin-left: 5px" class="back-button" href="./Gestion">Volver a gestion</a>
        </header>

        <div class="main-content">
            <h1>Gestión de Pedidos</h1>

            <h2>Lista de Pedidos</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Usuario</th>
                        <th>Estado</th>
                        <th>Fecha</th>
                        <th>Entrega a Domicilio</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="pedido" items="${pedidos}">
                        <tr>
                            <td><c:out value="${pedido.id}"/></td>
                            <td><c:out value="${pedido.usuario.usuario}"/></td>
                            <td><c:out value="${pedido.estado}"/></td>
                            <td><c:out value="${pedido.fecha}"/></td>
                            <td><c:out value="${pedido.entregaADomicilio ? 'Sí' : 'No'}"/></td>
                            <td>
                                <!-- Formulario para editar pedido -->
                                <form method="post" style="display:inline;">
                                    <input type="hidden" name="action" value="editar">
                                    <input type="hidden" name="id" value="${pedido.id}">
                                    <input type="text" name="estado" value="${pedido.estado}" required>
                                    <label>Entrega a Domicilio:</label>
                                    <input type="checkbox" name="entregaADomicilio" <c:if test="${pedido.entregaADomicilio}">checked</c:if>>
                                        <input type="submit" value="Editar">
                                    </form>
                                </td>
                            </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
