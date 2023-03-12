<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="Header.jsp" %>
<%@include file="CutomerNavBar.jsp" %>
<c:set var="cartProducts" value="${sessionScope.cartProducts}"/>
<main>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="card p-3 m-2">
                    <div class="d-flex justify-content-between align-items-center">
                        <h1>Cart</h1>
                        <p>Total Items: ${cartProducts.size().toString()}</p>
                    </div>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col">Product Name</th>
                            <th scope="col">Price</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Total</th>
                            <th scope="col">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${cartProducts}" var="cartProduct">
                            <tr>
                                <td><img width="50px" src="${cartProduct.image}" alt="${cartProduct.productName}"/></td>
                                <td>${cartProduct.productName}</td>
                                <td>
                                    <a href="/Cart?action=deleteSingle&id=${cartProduct.id}&quantity=1">-</a>
                                        ${cartProduct.quantity}
                                    <a href="/Cart?action=add&id=${cartProduct.id}&quantity=1">+</a>
                                </td>
                                <td>${cartProduct.price}</td>
                                <td>
                                    <a href="/Cart?action=delete&id=${cartProduct.id}"
                                       class="btn btn-danger">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</main>
<%@include file="Footer.jsp" %>
