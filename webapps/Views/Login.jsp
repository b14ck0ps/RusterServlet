<%@include file="Header.jsp" %>
<%@include file="CutomerNavBar.jsp" %>
<%@ page isELIgnored="false" %>
<main class="mt-5">
    <div class="container d-flex flex-column justify-content-center align-items-center">
        <div class="col-md-6">
            <div class="card p-3 m-2">
                <h1>Login</h1>
                <span class="text-danger">${requestScope.invalid}</span>
                <hr>
                <form action="/Login" method="post">
                    <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <input type="text" class="form-control" id="username"
                               name="username" value="${requestScope.oldUsername}">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div>
                    <button type="submit" class="btn btn-primary">Login</button>
                </form>
            </div>
        </div>
    </div>
</main>
<%@include file="Footer.jsp" %>
