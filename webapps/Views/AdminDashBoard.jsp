<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="Header.jsp" %>
<%@include file="AdminNavBar.jsp" %>
<%@ page isELIgnored="false" %>
<c:set var="products" value="${requestScope.products}" />

<main>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="card p-3 m-2">
                    <h1>Products</h1>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Name</th>
                            <th scope="col">Category</th>
                            <th scope="col">Price</th>
                            <th scope="col" >Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="product" items="${products}">
                            <tr>
                                <th scope="row"><c:out value="${product.id}"/></th>
                                <td><c:out value="${product.name}"/></td>
                                <td><c:out value="${product.categoryId}"/></td>
                                <td><c:out value="${product.price}"/></td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/ProductDetails?id=<c:out value="${product.id}"/>">Details</a>
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
