<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Usuario</title>
    <link rel="stylesheet" href="../css/gestionUsuario.css">
</head>
<body>
    <header class="fixed-header">
        <a class="back-button" href="../PaginaPrincipal">Volver a la tienda</a>
        <a style="margin-left: 5px" class="back-button" href="./Gestion">Volver a gestion</a>
    </header>

    <div class="main-content">
        <h1>Editar Usuario</h1>
        <form method="post" action="EditarUsuarios">
            <input type="hidden" name="id" value="${usuario.id}" />
            <label for="usuario">Usuario:</label>
            <input type="text" id="usuario" name="usuario" value="${usuario.usuario}"><br><br>

            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" value="${usuario.nombre}"><br><br>

            <label for="apellidos">Apellidos:</label>
            <input type="text" id="apellidos" name="apellidos" value="${usuario.apellidos}"><br><br>

            <label for="contrasena">Contraseña:</label>
            <input type="password" id="contrasena" name="contrasena" value="${usuario.contraseña}"><br><br>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${usuario.email}"><br><br>

            <label for="domicilio">Domicilio:</label>
            <input type="text" id="domicilio" name="domicilio" value="${usuario.domicilio}"><br><br>

            <label for="tipo">Tipo de Usuario:</label>
            <select name="tipo" id="tipo">
                <option value="ADMINISTRADOR" <c:if test="${usuario.tipoUsuario == 'ADMINISTRADOR'}">selected</c:if>>Administrador</option>
                <option value="SOCIO" <c:if test="${usuario.tipoUsuario == 'SOCIO'}">selected</c:if>>Socio</option>
            </select><br><br>
            <input name="actualizar" type="submit" value="Actualizar" />
        </form>
    </div>
</body>
</html>
