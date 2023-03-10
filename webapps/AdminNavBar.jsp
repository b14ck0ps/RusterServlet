<!--nav-->
<nav class="navbar navbar-expand-lg navbar-light bg-light px-5">
    <a class="navbar-brand" href="/Admin">RusterShop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav ms-auto">
            <a class="nav-item nav-link active" href="/Admin">Home</a>
            <c:if test="${empty sessionScope.user}">
                <a class="nav-item nav-link active" href="/Login">Login</a>
                <a class="nav-item nav-link active" href="/Registration">Registration</a>
            </c:if>
            <c:if test="${not empty sessionScope.user}">
                <a class="nav-item nav-link active" href="/AddProduct">Add Products</a>
                <a class="nav-item nav-link active" href="/UserList">User List</a>
                <a class="nav-item nav-link active" href="/Profile">Profile</a>
                <a class="nav-item nav-link ms-5 text-danger" href="/Logout">Logout</a>
            </c:if>
        </div>
    </div>
</nav>