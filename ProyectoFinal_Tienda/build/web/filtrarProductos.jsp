

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle var="mensajes" basename="internacionalizar.message"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form method="POST">
    <div class="listado-responsive">
        <div class="image-container">
            <c:forEach items="${productos}" var="producto">
                <div class="image-wrapper">
                    <a href="Producto?producto=${producto.id}"><img src="${producto.url}" alt="${producto.nombre}"/></a>
                    <c:if test="${not empty usuario}">
                        <button type="submit" name="agregar" value="${producto.id}" class="button">
                            <span class="button__text">Añadir</span>
                            <span class="button__icon"><svg xmlns="http://www.w3.org/2000/svg" width="24" viewBox="0 0 24 24" stroke-width="2" stroke-linejoin="round" stroke-linecap="round" stroke="currentColor" height="24" fill="none" class="svg"><line y2="19" y1="5" x2="12" x1="12"></line><line y2="12" y1="12" x2="19" x1="5"></line></svg></span>
                        </button>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </div>
</form>