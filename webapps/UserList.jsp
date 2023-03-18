<%@include file="Header.jsp" %>
<%@include file="AdminNavBar.jsp" %>
<%@ page isELIgnored="false" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="DBservices.DatabasesConnection" %>
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
                        <%
                            ResultSet rs = DatabasesConnection.getAllUsers();
                            int i = 1;
                            while (rs.next()) {
                                out.println("<tr>");
                                out.println("<th scope=\"row\">" + i + "</th>");
                                out.println("<td>" + rs.getString("username") + "</td>");
                                out.println("<td>" + rs.getString("email") + "</td>");
                                out.println("<td>" + rs.getString("userType") + "</td>");
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
