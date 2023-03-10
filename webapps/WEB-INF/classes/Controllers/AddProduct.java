package Controllers;

import DBservices.DatabaseOperations;
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

public class AddProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        ResultSet rsc = DatabaseOperations.getAllCategories();
        List<Category> categories = new ArrayList<Category>();
        while (true) {
            try {
                if (!rsc.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Category category = new Category();
            try {
                category.setId(rsc.getInt("id"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                category.setName(rsc.getString("CategoryName"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            categories.add(category);
        }
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("AddProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var productName = req.getParameter("ProductName");
        var categoryId = Integer.parseInt(req.getParameter("categoryId"));
        var quantity = Integer.parseInt(req.getParameter("quantity"));
        var price = Double.parseDouble(req.getParameter("Price"));
        var image = req.getParameter("image");

        var product = new Models.Product(productName, categoryId, quantity, price, image);
        var status = DBservices.DatabaseOperations.InsertProduct(product);
        if (status == 1) {
            resp.sendRedirect("Admin");
        } else {
            resp.sendRedirect("AddProduct");
        }
    }
}
