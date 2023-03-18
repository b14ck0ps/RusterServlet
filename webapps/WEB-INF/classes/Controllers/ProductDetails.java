package Controllers;

import Models.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static DBservices.ProductRepository.*;

public class ProductDetails extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        var id = Integer.parseInt(req.getParameter("id"));
        var product = getProductById(id);
        req.setAttribute("id", id);
        req.setAttribute("product", product);
        var categories = getAllCategories();
        req.setAttribute("categories", categories);
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
        var status = updateProduct(id, product);
        if (status == 1) {
            resp.sendRedirect("Admin");
        } else {
            resp.sendRedirect("ProductDetails?id=" + id);
        }

    }
}
