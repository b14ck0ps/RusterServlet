package Controllers;

import DBservices.DatabaseOperations;
import Models.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Home extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        var products = DatabaseOperations.getAllProducts();
        List<Product> productsList = new ArrayList<>();
        while (true) {
            try {
                if (!products.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Product product;
            try {
                productsList.add(new Product(products.getString("ProductName"), products.getInt("CategoryId"), products.getInt("quantity"), products.getDouble("Price"), products.getString("image")));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        req.setAttribute("products", productsList);
        req.getRequestDispatcher("Home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
