<%@ page import="Models.Product" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="DBservices.DatabaseOperations" %>
<%@include file="Header.jsp" %>
<%@include file="AdminNavBar.jsp" %>
<%@ page isELIgnored="false" %>
<main>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="card p-3 m-2">
                    <h1>Products Details</h1>
                    <%
                        Product product = (Product) request.getAttribute("product");
                        out.println("<img class='w-25' src=" + product.getImage() + " alt="+product.getName()+">");
                    %>
                    <form action="/ProductDetails" method="post">
                        <div class="mb-3">
                            <label for="ProductName" class="form-label">Product Name</label>
                            <input type="text" class="form-control" id="ProductName" aria-describedby="emailHelp"
                                   name="ProductName" value="<%= product.getName() %>">
                            <span class="text-danger">${requestScope.ProductNameError}</span>
                        </div>

                        <div class="mb-3">
                            <label for="categoryId" class="form-label">Category Id</label>
                            <select id="categoryId" class="form-select" name="categoryId">
                                <%
                                    ResultSet rs = DatabaseOperations.getAllCategories();
                                    while (rs.next()) {
                                        out.println("<option value='" + rs.getInt("id") + "'> " + rs.getString("CategoryName") + "</option>");
                                    }
                                %>
                            </select>
                            <span class="text-danger">${requestScope.categoryIdError}</span>
                        </div>
                        <div class="mb-3">
                            <label for="Price" class="form-label">Price</label>
                            <input type="number" class="form-control" id="Price" aria-describedby="emailHelp"
                                   name="Price" value="<%= product.getPrice() %>">
                            <span class="text-danger">${requestScope.PriceError}</span>
                        </div>
                        <div class="mb-3">
                            <label for="quantity" class="form-label">Quantity</label>
                            <input type="number" class="form-control" id="quantity" aria-describedby="emailHelp"
                                   name="quantity" value="<%= product.getQuantity() %>">
                            <span class="text-danger">${requestScope.quantityError}</span>
                        </div>
                        <div class="mb-3">
                            <label for="image" class="form-label">Image URL</label>
                            <input type="text" class="form-control" id="image" aria-describedby="emailHelp"
                                   name="image" value="<%= product.getImage() %>">
                            <span class="text-danger">${requestScope.imageError}</span>
                        </div>
                        <input type="hidden" name="id" value="<%= request.getAttribute("id") %>">
                        <div class="d-flex justify-content-between">
                            <button type="submit" class="btn btn-primary">Update Product</button>
                            <a href="/Admin?delete=<%= request.getAttribute("id") %>" class="btn btn-danger ml-auto">Delete</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>
<%@include file="Footer.jsp" %>
