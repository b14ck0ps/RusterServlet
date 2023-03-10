<%@include  file="Header.html" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>
<%@ page isELIgnored="false" %>
<main>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="card p-3 m-2">
                    <h1>Registration</h1>
                    <hr>
                    <form action="/Registration" method="post">
                        <div class="mb-3">
                            <label for="username" class="form-label">Username</label>
                            <input type="text" class="form-control" id="username" aria-describedby="emailHelp" name="username" value="${requestScope.oldUsername}">
                            <span class="text-danger">${requestScope.usernameError}</span>
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email address</label>
                            <input type="email" class="form-control" id="email" aria-describedby="emailHelp" name="email" value="${requestScope.oldEmail}">
                            <span class="text-danger">${requestScope.emailError}</span>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" id="password" name="password"  value="${requestScope.oldPassword}">
                            <span class="text-danger">${requestScope.passwordError}</span>
                        </div>
                        <button type="submit" class="btn btn-primary">Register</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>
<%@include  file="Footer.html" %>
