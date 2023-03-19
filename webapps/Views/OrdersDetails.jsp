<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="Header.jsp" %>
<%@include file="CutomerNavBar.jsp" %>
<c:set var="items" value="${requestScope.ItemsList}"/>
<main>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="card p-3 m-2">
                    <h1>Order #${requestScope.orderId}</h1>
                    <p>${requestScope.orderDate}</p>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th></th>
                            <th>Item Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${items}" var="item">
                            <tr>
                                <td><img src="${item.image}" alt="Image" width="50"></td>
                                <td>${item.productName}</td>
                                <td>${item.quantity}</td>
                                <td>${item.price}</td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td></td>
                            <td></td>
                            <td>Total</td>
                            <td>${requestScope.orderTotal}</td>
                        </tr>
                        </tbody>
                    </table>
                    <a href="/Orders">Back</a>
                </div>
            </div>
        </div>
    </div>
</main>
<%@include file="Footer.jsp" %>
