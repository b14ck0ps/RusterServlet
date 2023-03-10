<%@ page import="java.util.List" %>
<%@ page import="Models.Product" %>
<%@include file="Header.jsp" %>
<%@include file="CutomerNavBar.jsp" %>
<%var products = (List<Product>) request.getAttribute("products");%>
<main>
    <div class="container d-flex flex-wrap">
        <%for (Product product : products) {%>
        <div class="card m-3" style="width: 18rem;">
            <img src="<%=product.getImage()%>" class="card-img-top p-2 m-2" alt="<%=product.getName()%>" style="width: 150px; height:150px" >
            <div class="card-body">
                <h5 class="card-title"><%=product.getName()%>
                </h5>
                <p class="card-text">Price : <%=product.getPrice()%>
                </p>
                <p class="card-text">Stock : <%=product.getQuantity()%>
                </p>
                <a href="#" class="btn btn-primary">Add to Cart</a>
            </div>
        </div>
        <%}%>
    </div>
</main>
<%@include file="Footer.jsp" %>
