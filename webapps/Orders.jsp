<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="Header.jsp" %>
<%@include file="CutomerNavBar.jsp" %>
<c:set var="orders" value="${requestScope.orderList}"/>
<main>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="card p-3 m-2">
                    <h1>Orders</h1>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>Order Date</th>
                            <th>Order Total</th>
                            <th>Order Details</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${orders}" var="order">
                            <tr>
                                <td>${order.id}</td>
                                <td>${order.date}</td>
                                <td>${order.totalPrice}</td>
                                <td><a href="OrderDetails.jsp?orderId=${order.id}">View Details</a></td>
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
