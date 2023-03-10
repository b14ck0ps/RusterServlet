<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="Header.jsp" %>
<%@include file="AdminNavBar.jsp" %>
<%@ page isELIgnored="false" %>
<c:set var="product" value="${requestScope.product}"/>
<c:set var="categories" value="${requestScope.categories}"/>
<main>
    <div class="container d-flex flex-column justify-content-center align-items-center">
        <div class="col-md-6">
            <div class="card p-3 m-2">
                <h1>Products Details</h1>
                <img class="w-50 m-auto" src="${product.image}" alt="${product.name}">
                <form action="/ProductDetails" method="post">
                    <div class="mb-3">
                        <label for="ProductName" class="form-label">Product Name</label>
                        <input type="text" class="form-control" id="ProductName" aria-describedby="emailHelp"
                               name="ProductName" value="${product.name}">
                        <span class="text-danger">${requestScope.ProductNameError}</span>
                    </div>

                    <div class="mb-3">
                        <label for="categoryId" class="form-label">Category Id</label>
                        <select id="categoryId" class="form-select" name="categoryId">
                            <c:forEach var="category" items="${categories}">
                                <option value="${category.id}">${category.name}</option>
                            </c:forEach>
                        </select>
                        <span class="text-danger">${requestScope.categoryIdError}</span>
                    </div>
                    <div class="mb-3">
                        <label for="Price" class="form-label">Price</label>
                        <input type="number" class="form-control" id="Price" aria-describedby="emailHelp"
                               name="Price" value="${product.price}">
                        <span class="text-danger">${requestScope.PriceError}</span>
                    </div>
                    <div class="mb-3">
                        <label for="quantity" class="form-label">Quantity</label>
                        <input type="number" class="form-control" id="quantity" aria-describedby="emailHelp"
                               name="quantity" value="${product.quantity}">
                        <span class="text-danger">${requestScope.quantityError}</span>
                    </div>
                    <div class="mb-3">
                        <label for="image" class="form-label">Image URL</label>
                        <input type="text" class="form-control" id="image" aria-describedby="emailHelp"
                               name="image" value="${product.image}">
                        <span class="text-danger">${requestScope.imageError}</span>
                    </div>
                    <input type="hidden" name="id" value="<%= request.getAttribute("id") %>">
                    <div class="d-flex justify-content-between">
                        <button type="submit" class="btn btn-primary">Update Product</button>
                        <a href="/Admin?delete=${requestScope.id}" class="btn btn-danger ml-auto">Delete</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</main>
<%@include file="Footer.jsp" %>
