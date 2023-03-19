package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

import static DBservices.ProductRepository.InsertProduct;
import static DBservices.ProductRepository.getAllCategories;

public class AddProduct extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AddProduct.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        var categories = getAllCategories();
        req.setAttribute("categories", categories);
        logger.info("User " + req.getSession().getAttribute("user") + " is adding a new product");
        req.getRequestDispatcher("/Views/AddProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var productName = req.getParameter("ProductName");
        var categoryId = Integer.parseInt(req.getParameter("categoryId"));
        var quantity = Integer.parseInt(req.getParameter("quantity"));
        var price = Double.parseDouble(req.getParameter("Price"));
        var image = req.getParameter("image");

        var product = new Models.Product(productName, categoryId, quantity, price, image);
        var status = InsertProduct(product);
        if (status == 1) {
            logger.info("User " + req.getSession().getAttribute("user") + " added product " + productName);
            resp.sendRedirect(req.getContextPath() + "/Admin");
        } else {
            logger.severe("User " + req.getSession().getAttribute("user") + " failed to add product " + productName);
            resp.sendRedirect(req.getContextPath() + "/AddProduct");
        }
    }
}
