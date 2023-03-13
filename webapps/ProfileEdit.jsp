<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="Header.jsp" %>
<c:set var="user" value="${requestScope.user}"/>

<c:if test="${user.userType == \"ADMIN\"}">
    <%@include file="AdminNavBar.jsp" %>
</c:if>
<c:if test="${user.userType == \"CUSTOMER\"}">
    <%@include file="CutomerNavBar.jsp" %>
</c:if>
<main class="mt-2">
    <div class="container d-flex flex-column justify-content-center align-items-center">
        <div class="col-md-6">
            <div class="card p-3 m-2">
                <h1>Edit Profile</h1>
                <span class="text-danger">${requestScope.error}</span>
                <hr>
                <form action="/Profile" method="post">
                    <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <input type="text" class="form-control" id="username" aria-describedby="emailHelp"
                               name="username" value="${user.username}">
                        <span class="text-danger">${requestScope.usernameError}</span>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email address</label>
                        <input type="email" class="form-control" id="email" aria-describedby="emailHelp"
                               name="email" value="${user.email}">
                        <span class="text-danger">${requestScope.emailError}</span>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">New Password</label>
                        <input type="password" class="form-control" id="password" name="password"
                               value="">
                        <span class="text-danger">${requestScope.passwordError}</span>
                    </div>
                    <div class="d-flex justify-content-between">
                        <button type="submit" class="btn btn-primary">Update</button>
                        <a href="/Profile" class="btn btn-secondary">Cancel</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</main>
<%@include file="Footer.jsp" %>
