<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../css/prueba-login.css"/>
        <link rel="stylesheet" href="../css/boton-carrito.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="../css/historial.css"/>
        <title>PaCoTech</title>
        <style>
            .usuario{
                -webkit-text-stroke: 1px black;
                text-stroke: 2px black;
                color: white;
                filter: drop-shadow(0 0 2px white);
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg bg-dark ">
            <div class="container-fluid d-flex justify-content-center">
                <a class="navbar-brand fs-2 text text-white mx-auto" href="../PaginaPrincipal">PaCotech</a>
                <button class="navbar-toggler" type="button" data-bs-theme="dark"  data-bs-toggle="collapse" data-bs-target="#navbarNav"
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
                    <ul class="navbar-nav mx-auto">
                        <c:if test="${not empty usuario}">
                            <li class="nav-item">
                                <a class="nav-link active fs-4 text text-white" aria-current="page" href="./HistorialPedidos">Historial</a>
                            </li>
                        </c:if>
                        <li class="nav-item">
                            <a class="nav-link active fs-4 text text-white" href="../BusquedaProducto">Buscador</a>
                        </li>
                        <c:if test="${not empty usuario}">
                            <li class="nav-item">
                                <a class="nav-link active" href="./Carrito">
                                    <button data-quantity="4" class="shopping-cart-btn">
                                        <svg class="shopping-cart-icon" viewBox="0 0 24.38 30.52" height="30.52" width="24.38"
                                             xmlns="http://www.w3.org/2000/svg">
                                        <path transform="translate(-3.62 -0.85)"
                                              d="M28,27.3,26.24,7.51a.75.75,0,0,0-.76-.69h-3.7a6,6,0,0,0-12,0H6.13a.76.76,0,0,0-.76.69L3.62,27.3v.07a4.29,4.29,0,0,0,4.52,4H23.48a4.29,4.29,0,0,0,4.52-4ZM15.81,2.37a4.47,4.47,0,0,1,4.46,4.45H11.35a4.47,4.47,0,0,1,4.46-4.45Zm7.67,27.48H8.13a2.79,2.79,0,0,1-3-2.45L6.83,8.34h3V11a.76.76,0,0,0,1.52,0V8.34h8.92V11a.76.76,0,0,0,1.52,0V8.34h3L26.48,27.4a2.79,2.79,0,0,1-3,2.44Zm0,0">
                                        </path>
                                        </svg>
                                        <span class="item-quantity">
                                            <c:out value="${nproducto}"/>
                                        </span>
                                    </button>
                                </a>
                            </li>
                        </c:if>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <img class="usuario" src="../img/user.png" alt="icono usuario">
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="../Login">Ir al login</a></li>
                                <li><a class="dropdown-item" href="../Registro">Registrarse</a></li>
                                    <c:if test="${not empty usuario}">
                                    <li><a class="dropdown-item" href="./EditarUsuario">Editar Usuario</a></li>
                                    <li><a class="dropdown-item" href="../CerrarSesion">Cerrar Sesión</a></li>
                                    </c:if>
                                    <c:if test="${usuario.tipoUsuario eq 'ADMINISTRADOR'}">
                                    <li><a class="dropdown-item" href="../admin/Gestion">Panel de Administración</a></li>
                                    </c:if>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <section class="order-history">
            <h2>Historial de Pedidos</h2>
            <c:forEach items="${pedidos}" var="pedido">
                <div class="order">
                    <h3>Nº de pedido: ${pedido.id}</h3>
                    <p>Fecha: ${pedido.getFecha()}</p>
                    <p>Total: ${pedido.getPrecio()} €</p>
                    <p>Estado: ${pedido.estado}</p>
                    <p>Entrega: <c:choose>
                            <c:when test="${pedido.entregaADomicilio}">
                                A domicilio
                            </c:when>
                            <c:otherwise>
                                A recoger en una de nuestras tiendas
                            </c:otherwise>
                        </c:choose>
                    </p>
                </div>
            </c:forEach>
        </section>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
                integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
        crossorigin="anonymous"></script>
    </body>
</html>
