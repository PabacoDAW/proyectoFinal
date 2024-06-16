<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Editar Producto</title>
        <link rel="stylesheet" href="../css/gestionUsuario.css">
    </head>
    <body>
        <header class="fixed-header">
            <a class="back-button" href="../PaginaPrincipal">Volver a la tienda</a>
            <a style="margin-left: 5px" class="back-button" href="./Gestion">Volver a gestion</a>
        </header>

        <div class="main-content">
            <h1>Editar Producto</h1>
            <form method="post" action="EditarProductos" enctype="multipart/form-data">
                <input type="hidden" name="id" value="${producto.id}" />
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" value="${producto.nombre}"><br><br>

                <label for="descripcion">Descripción:</label>
                <input type="text" id="descripcion" name="descripcion" value="${producto.descripcion}"><br><br>

                <label for="precio">Precio:</label>
                <input type="number" id="precio" name="precio" step="0.01" value="${producto.precio}"><br><br>

                <label for="imagen">Imagen:</label>
                <input type="file" id="imagen" name="imagen"><br><br> <!-- Campo de subida de imagen -->

                <label for="marcas">Marcas:</label>
                <select name="marcas" id="marcas" multiple>
                    <c:forEach items="${marcas}" var="marca">
                        <option value="${marca.id}" <c:if test="${producto.marcas.contains(marca)}">selected</c:if>>${marca.nombre}</option>
                    </c:forEach>
                </select><br><br>

                <label for="categorias">Categorías:</label>
                <select name="categorias" id="categorias" multiple>
                    <c:forEach items="${categorias}" var="categoria">
                        <option value="${categoria.id}" <c:if test="${producto.categorias.contains(categoria)}">selected</c:if>>${categoria.nombre}</option>
                    </c:forEach>
                </select><br><br>

                <input name="actualizar" type="submit" value="Actualizar" />
            </form>
        </div>
    </body>
</html>
