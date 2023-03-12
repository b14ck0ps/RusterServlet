<!--nav-->
<nav class="navbar navbar-expand-lg navbar-light bg-light px-5">
    <a class="navbar-brand" href="/Admin">RusterShop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav ms-auto">
            <a class="nav-item nav-link active mx-2" href="/Admin"><i class="bi bi-house-fill"></i> Home</a>
            <c:if test="${empty sessionScope.user}">
                <a class="nav-item nav-link active mx-2" href="/Login"><i class="bi bi-box-arrow-right"></i> Login</a>
                <a class="nav-item nav-link active mx-2" href="/Registration"><i class="bi bi-pencil-square"></i> Registration</a>
            </c:if>
            <c:if test="${not empty sessionScope.user}">
                <a class="nav-item nav-link active mx-2" href="/AddProduct"><i class="bi bi-plus-square-fill"></i> Add Products</a>
                <a class="nav-item nav-link active mx-2" href="/UserList"><i class="bi bi-person-lines-fill"></i> User List</a>
                <a class="nav-item nav-link active mx-2" href="/Profile"><i class="bi bi-person-circle"></i> Profile</a>
                <a class="nav-item nav-link ms-5 text-danger mx-2" href="/Logout"><i class="bi bi-box-arrow-left"></i> Logout</a>
            </c:if>
        </div>
    </div>
</nav>