<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--nav-->
<nav class="navbar navbar-expand-lg navbar-light bg-light px-5">
    <a class="navbar-brand" href="/">RusterShop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
            aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav ms-auto">
            <a class="nav-item nav-link active" href="/">Home</a>
            <c:if test="${empty sessionScope.user}">
                <a class="nav-item nav-link" href="/Login">Login</a>
                <a class="nav-item nav-link" href="/Registration">Registration</a>
            </c:if>
            <c:if test="${not empty sessionScope.user}">
                <a class="nav-item nav-link active" href="/Profile">Profile</a>
                <a class="nav-item nav-link active" href="/Orders">Orders</a>
                <a class="nav-item nav-link active" href="/Cart">Cart</a>
                <a class="nav-item nav-link ms-5 text-danger" href="/Logout">Logout</a>
            </c:if>
        </div>
    </div>
</nav>
