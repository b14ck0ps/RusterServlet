<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="Header.jsp" %>
<%@include file="AdminNavBar.jsp" %>
<%@ page isELIgnored="false" %>
<c:set var="categories" value="${requestScope.categories}"/>
<main>
    <div class="container d-flex flex-column justify-content-center align-items-center">
        <div class="col-md-6">
            <div class="card p-3 m-2">
                <h1>Add New Product</h1>
                <hr>
                <form action="${pageContext.request.contextPath}/AddProduct" method="post">
                    <div class="mb-3">
                        <label for="ProductName" class="form-label">Product Name</label>
                        <input type="text" class="form-control" id="ProductName" aria-describedby="emailHelp"
                               name="ProductName" value="${requestScope.oldProductName}">
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
                               name="Price" value="${requestScope.oldPrice}">
                        <span class="text-danger">${requestScope.PriceError}</span>
                    </div>
                    <div class="mb-3">
                        <label for="quantity" class="form-label">Quantity</label>
                        <input type="number" class="form-control" id="quantity" aria-describedby="emailHelp"
                               name="quantity" value="${requestScope.oldquantity}">
                        <span class="text-danger">${requestScope.quantityError}</span>
                    </div>
                    <div class="mb-3">
                        <label for="image" class="form-label">Image URL</label>
                        <input type="text" class="form-control" id="image" aria-describedby="emailHelp"
                               name="image" value="${requestScope.oldimage}">
                        <span class="text-danger">${requestScope.imageError}</span>
                    </div>
                    <button type="submit" class="btn btn-primary">Add Product</button>
                </form>
            </div>
        </div>
    </div>
</main>
<%@include file="Footer.jsp" %>
