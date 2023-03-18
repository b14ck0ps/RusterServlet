package Controllers;

import Models.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static DBservices.ProductRepository.deleteProduct;
import static DBservices.ProductRepository.getAllProducts;

public class Admin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        var delete = req.getParameter("delete");
        if (delete != null) {
            var id = Integer.parseInt(delete);
            deleteProduct(id);
            resp.sendRedirect("Admin");
            return;
        }
        var products = getAllProducts();
        List<Product> productsList = new ArrayList<>();
        while (true) {
            try {
                if (!products.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Product product;
            try {
                productsList.add(new Product(products.getInt("id"),products.getString("ProductName"), products.getInt("CategoryId"), products.getInt("quantity"), products.getDouble("Price"), products.getString("image")));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        req.setAttribute("products", productsList);
        req.getRequestDispatcher("AdminDashBoard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
