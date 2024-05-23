<%-- 
    Document   : paginaPrincipal
    Created on : 13 abr 2024, 16:52:41
    Author     : Cooler Master
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<fmt:setBundle var="mensajes" basename="internacionalizar.message"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="img/icono.png"/>
        <link rel="stylesheet" href="css/listado-responsive.css"/>
        <link rel="stylesheet" href="css/boton-carrito.css"/>
        <link rel="stylesheet" href="css/prueba-boton.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <title>PaCoTech</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg bg-body-secondary">
            <div class="container-fluid">
                <a class="navbar-brand fs-2 text" href="./PaginaPrincipal">PaCotech</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <c:if test="${not empty usuario}">
                            <li class="nav-item">
                                <a class="nav-link active fs-4 text" aria-current="page" href="./usuario/HistorialPedidos">Historial</a>
                            </li>
                        </c:if>
                        <li class="nav-item">
                            <a class="nav-link active fs-4 text" href="./BusquedaProducto">Buscador</a>
                        </li>
                        <c:if test="${not empty usuario}">
                            <li class="nav-item">
                                <a class="nav-link active" href="./usuario/Carrito">
                                    <button data-quantity="4" class="shopping-cart-btn">
                                        <svg class="shopping-cart-icon" viewBox="0 0 24.38 30.52" height="30.52" width="24.38"
                                             xmlns="http://www.w3.org/2000/svg">
                                        <path transform="translate(-3.62 -0.85)"
                                              d="M28,27.3,26.24,7.51a.75.75,0,0,0-.76-.69h-3.7a6,6,0,0,0-12,0H6.13a.76.76,0,0,0-.76.69L3.62,27.3v.07a4.29,4.29,0,0,0,4.52,4H23.48a4.29,4.29,0,0,0,4.52-4ZM15.81,2.37a4.47,4.47,0,0,1,4.46,4.45H11.35a4.47,4.47,0,0,1,4.46-4.45Zm7.67,27.48H8.13a2.79,2.79,0,0,1-3-2.45L6.83,8.34h3V11a.76.76,0,0,0,1.52,0V8.34h8.92V11a.76.76,0,0,0,1.52,0V8.34h3L26.48,27.4a2.79,2.79,0,0,1-3,2.44Zm0,0">
                                        </path>
                                        </svg>
                                        <span class="item-quantity"><c:out value="${nproducto}"/></span>
                                    </button>
                                </a>
                            </li>
                        </c:if>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <img class="usuario" src="img/user.png" alt="icono usuario">
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="./Login">Ir al login</a></li>
                                <li><a class="dropdown-item" href="./Registro">Registrarse</a></li>
                                    <c:if test="${not empty usuario}">
                                    <li><a class="dropdown-item" href="#">Cerrar Sesión</a></li>
                                    </c:if>
                            </ul>
                        </li>

                    </ul>
                </div>
            </div>
        </nav>

        <form method="POST">
            <div class="listado-responsive">
                <div class="image-container">
                    <c:forEach items="${productos}" var="producto">
                        <div class="image-wrapper">
                            <a href="Producto?producto=${producto.id}"><img src="${producto.url}" alt="${producto.nombre}"/></a>
                                <c:if test="${not empty usuario}">
                                <button type="submit" name="agregar" value="${producto.id}" class="button-1 image-button">
                                    <span class="button__text">Add Item</span>
                                    <span class="button__icon"><svg xmlns="http://www.w3.org/2000/svg" width="24" viewBox="0 0 24 24"
                                                                    stroke-width="2" stroke-linejoin="round" stroke-linecap="round" stroke="currentColor" height="24"
                                                                    fill="none" class="svg">
                                        <line y2="19" y1="5" x2="12" x1="12"></line>
                                        <line y2="12" y1="12" x2="19" x1="5"></line>
                                        </svg></span>
                                </button>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </form>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
                integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
        crossorigin="anonymous"></script>
    </body>
</html>
