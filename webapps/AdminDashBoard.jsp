<%@ page import="java.sql.ResultSet" %>
<%@ page import="DBservices.DatabaseOperations" %>
<%@include file="Header.jsp" %>
<%@include file="AdminNavBar.jsp" %>
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
                        <%
                            ResultSet rs = DatabaseOperations.getAllProducts();
                            int i = 1;
                            while (rs.next()) {
                                out.println("<tr>");
                                out.println("<th scope=\"row\">" + i + "</th>");
                                out.println("<td>" + rs.getString("ProductName") + "</td>");
                                out.println("<td>" + rs.getString("CategoryId") + "</td>");
                                out.println("<td>" + rs.getString("Price") + "</td>");
                                out.println("<td> <a href='/ProductDetails?id="+rs.getInt("id")+"'> Details </a>  </td>");
                                out.println("</tr>");
                                i++;
                            }
                        %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</main>
<%@include file="Footer.jsp" %>
