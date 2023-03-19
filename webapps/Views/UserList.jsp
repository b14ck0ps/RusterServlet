<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="Header.jsp" %>
<%@include file="AdminNavBar.jsp" %>
<%@ page isELIgnored="false" %>
<%@ page import="java.sql.ResultSet" %>
<c:set var="products" value="${requestScope.usersList}"/>
<main>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="card p-3 m-2">
                    <h1>UserLists</h1>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Username</th>
                            <th scope="col">Email</th>
                            <th scope="col">UserType</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <th scope="row">${user.id}</th>
                                <td>${user.username}</td>
                                <td>${user.email}</td>
                                <td>${user.userType}</td>
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
