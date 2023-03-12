<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@include file="Header.jsp" %>
<%@include file="CutomerNavBar.jsp" %>
<c:set var="products" value="${requestScope.products}"/>
<main>
    <div class="toast align-items-center" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Hello, world! This is a toast message.
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
    <div class="container d-flex flex-wrap">
        <c:forEach var="product" items="${products}">
            <div class="card m-3" style="width: 18rem;">
                <img src="${product.image}" class="card-img-top p-2 m-2" alt="${product.name}"
                     style="width: 150px; height:150px">
                <div class="card-body">
                    <h5 class="card-title">${product.name}</h5>
                    <p class="card-text">Price: ${product.price}</p>
                    <p class="card-text">Stock: ${product.quantity}</p>
                    <a href="/Cart?action=add&id=${product.id}&quantity=1" class="btn btn-primary">Add to Cart</a>
                </div>
            </div>
        </c:forEach>
    </div>
</main>
<%@include file="Footer.jsp" %>
