<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Gestión de Categorías</title>
        <link rel="stylesheet" href="../css/gestionUsuario.css">
    </head>
    <body>
        <header class="fixed-header">
            <a class="back-button" href="../PaginaPrincipal">Volver a la tienda</a>
            <a style="margin-left: 5px" class="back-button" href="./Gestion">Volver a gestion</a>
        </header>

        <div class="main-content">
            <h1>Gestión de Categorías</h1>

            <!-- Formulario para crear nueva categoría -->
            <form method="post">
                <input type="hidden" name="action" value="crear">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" required>
                <input type="submit" value="Crear Categoría">
            </form>
            <c:if test="${not empty error}">
                <div>
                    ${error}
                </div>
            </c:if>
            <h2>Lista de Categorías</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="categoria" items="${categorias}">
                        <tr>
                            <td><c:out value="${categoria.id}"/></td>
                            <td><c:out value="${categoria.nombre}"/></td>
                            <td>
                                <!-- Formulario para editar categoría -->
                                <form method="post" style="display:inline;">
                                    <input type="hidden" name="action" value="editar">
                                    <input type="hidden" name="id" value="${categoria.id}">
                                    <input type="text" name="nombre" value="${categoria.nombre}" required>
                                    <input type="submit" value="Editar">
                                </form>

                                <!-- Formulario para eliminar categoría -->
                                <form method="post" style="display:inline;">
                                    <input type="hidden" name="action" value="eliminar">
                                    <input type="hidden" name="id" value="${categoria.id}">
                                    <input type="submit" value="Eliminar">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
