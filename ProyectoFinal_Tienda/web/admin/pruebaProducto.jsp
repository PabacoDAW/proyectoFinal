<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Productos</title>
    <link rel="stylesheet" href="../css/gestionUsuario.css">
</head>
<body>
    <header class="fixed-header">
        <a class="back-button" href="../PaginaPrincipal">Volver a la tienda</a>
        <a style="margin-left: 5px" class="back-button" href="./Gestion">Volver a gestion</a>
    </header>

    <div class="main-content">
        <h1>Gestión de Productos</h1>
        <form action="Pp" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" id="id" />
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre"><br><br>

            <label for="descripcion">Descripción:</label>
            <input type="text" id="descripcion" name="descripcion"><br><br>

            <label for="precio">Precio:</label>
            <input type="number" id="precio" name="precio" step="0.01"><br><br>

            <label for="file">Imagen:</label>
            <input type="file" id="file" name="file"><br><br>

            <label for="marcas">Marcas:</label>
            <select name="marcas" id="marcas" multiple>
                <c:forEach items="${marcas}" var="marca">
                    <option value="${marca.id}">${marca.nombre}</option>
                </c:forEach>
            </select><br><br>

            <label for="categorias">Categorías:</label>
            <select name="categorias" id="categorias" multiple>
                <c:forEach items="${categorias}" var="categoria">
                    <option value="${categoria.id}">${categoria.nombre}</option>
                </c:forEach>
            </select><br><br>

            <input type="submit" name="action" value="Crear" id="crear" />
            <c:if test="${not empty error}">
                <div class="error">
                    ${error}
                </div>
            </c:if>
        </form>

        <h2>Lista de Productos</h2>
        <table>
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Precio</th>
                    <th>Imagen</th>
                    <th>Marcas</th>
                    <th>Categorías</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${productos}" var="producto">
                    <tr>
                        <td>${producto.nombre}</td>
                        <td>${producto.descripcion}</td>
                        <td>${producto.precio}</td>
                        <td><img src="../${producto.url}" alt="${producto.nombre}" style="max-width: 100px; max-height: 100px;"></td>
                        <td>
                            <c:forEach items="${producto.marcas}" var="marca">
                                ${marca.nombre}<br>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach items="${producto.categorias}" var="categoria">
                                ${categoria.nombre}<br>
                            </c:forEach>
                        </td>
                        <td>
                            <form action="EditarProductos" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="${producto.id}" />
                                <input type="submit" name="action" value="Editar" />
                            </form>
                            <form action="GestionProductos" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="${producto.id}" />
                                <input type="submit" name="action" value="Eliminar" />
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
