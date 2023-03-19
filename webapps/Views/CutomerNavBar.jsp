<%@ page import="java.util.List" %>
<%@ page import="Models.CartProduct" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--nav-->
<nav class="navbar navbar-expand-lg navbar-light bg-light px-5">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">RusterShop</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
            aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav ms-auto">
            <a class="nav-item nav-link active mx-2" href="${pageContext.request.contextPath}/"><i class="bi bi-house-fill"></i> Home</a>
            <c:if test="${empty sessionScope.user}">
                <a class="nav-item nav-link active mx-2" href="${pageContext.request.contextPath}/Login"><i class="bi bi-box-arrow-right"></i> Login</a>
                <a class="nav-item nav-link active mx-2" href="${pageContext.request.contextPath}/Registration"><i class="bi bi-pencil-square"></i> Registration</a>
            </c:if>
            <c:if test="${not empty sessionScope.user}">
                <a class="nav-item nav-link active mx-2" href="${pageContext.request.contextPath}/Profile"><i class="bi bi-person-circle"></i> Profile</a>
                <a class="nav-item nav-link active mx-2" href="${pageContext.request.contextPath}/Orders"><i class="bi bi-bag-fill"></i> Orders</a>
                <a href="${pageContext.request.contextPath}/Cart" class="nav-item nav-link active mx-2 position-relative">
                    <i class="bi bi-cart-plus-fill"></i> Cart
                    <c:if test="${sessionScope.cartProducts.size() > 0}">
                        <span class="position-absolute top-2 start-100 translate-middle badge rounded-pill bg-warning">
                            <c:out value="${sessionScope.cartProducts.size()}"/></span>
                    </c:if>
                </a>
                <a class="nav-item nav-link ms-5 text-danger md-mx-2" href="${pageContext.request.contextPath}/Logout"><i class="bi bi-box-arrow-left"></i> Logout</a>
            </c:if>
        </div>
    </div>
</nav>
