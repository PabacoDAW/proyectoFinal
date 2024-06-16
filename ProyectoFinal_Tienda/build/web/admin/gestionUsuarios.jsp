<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Gestión de Usuarios</title>
        <link rel="stylesheet" href="../css/gestionUsuario.css">
    </head>
    <body>
        <header class="fixed-header">
            <a class="back-button" href="../PaginaPrincipal">Volver a la tienda</a>
            <a style="margin-left: 5px" class="back-button" href="./Gestion">Volver a gestión</a>
        </header>

        <div class="main-content">
            <h1>Gestión de Usuarios</h1>
            <form action="GestionUsuarios" method="post">
                <input type="hidden" name="action" value="Crear" />

                <label for="usuario">Usuario:</label>
                <input type="text" id="usuario" name="usuario" required><br><br>

                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" required><br><br>

                <label for="apellidos">Apellidos:</label>
                <input type="text" id="apellidos" name="apellidos" required><br><br>

                <label for="contrasena">Contraseña:</label>
                <input type="password" id="contrasena" name="contrasena" required><br><br>

                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required><br><br>

                <label for="domicilio">Domicilio:</label>
                <input type="text" id="domicilio" name="domicilio" required><br><br>

                <label for="tipo">Tipo de Usuario:</label>
                <select name="tipo" id="tipo" required>
                    <option value="ADMINISTRADOR">Administrador</option>
                    <option value="SOCIO">Socio</option>
                </select><br><br>
                <input type="submit" value="Crear" />

            </form>
            <c:if test="${not empty error}">
                <div class="error">
                    ${error}
                </div>
            </c:if>
            <h2>Lista de Usuarios</h2>
            <table>
                <thead>
                    <tr>
                        <th>Usuario</th>
                        <th>Nombre</th>
                        <th>Apellidos</th>
                        <th>Contraseña</th>
                        <th>Email</th>
                        <th>Domicilio</th>
                        <th>Tipo Usuario</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${usuarios}" var="usuario">
                        <tr>
                            <td>${usuario.usuario}</td>
                            <td>${usuario.nombre}</td>
                            <td>${usuario.apellidos}</td>
                            <td>${usuario.contraseña}</td>
                            <td>${usuario.email}</td>
                            <td>${usuario.domicilio}</td>
                            <td>${usuario.tipoUsuario}</td>
                            <td>
                                <form action="EditarUsuarios" method="post" style="display:inline;">
                                    <input type="hidden" name="id" value="${usuario.id}" />
                                    <input type="submit" name="action" value="Editar" />
                                </form>
                                <form action="GestionUsuarios" method="post" style="display:inline;">
                                    <input type="hidden" name="id" value="${usuario.id}" />
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
