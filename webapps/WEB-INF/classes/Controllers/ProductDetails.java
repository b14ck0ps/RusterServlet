package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class ProductDetails extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        var id = Integer.parseInt(req.getParameter("id"));
        var rs = DBservices.DatabaseOperations.getProductById(id);
        req.setAttribute("id", id);
        try {
            if (rs.next()) {
                var product = new Models.Product(rs.getString("ProductName"), rs.getInt("CategoryId"), rs.getInt("quantity"), rs.getDouble("Price"), rs.getString("image"));
                req.setAttribute("product", product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("ProductDetails.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var id = Integer.parseInt(req.getParameter("id"));
        var productName = req.getParameter("ProductName");
        var categoryId = Integer.parseInt(req.getParameter("categoryId"));
        var quantity = Integer.parseInt(req.getParameter("quantity"));
        var price = Double.parseDouble(req.getParameter("Price"));
        var image = req.getParameter("image");

       //update product
        var product = new Models.Product(productName, categoryId, quantity, price, image);
        var status = DBservices.DatabaseOperations.updateProduct(id, product);
        if (status == 1) {
            resp.sendRedirect("Admin");
        } else {
            resp.sendRedirect("ProductDetails?id=" + id);
        }

    }
}
