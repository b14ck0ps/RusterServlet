package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

import static DBservices.ProductRepository.getAllProducts;

public class Home extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Home.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        var productsList = getAllProducts();
        req.setAttribute("products", productsList);
        logger.info("User " + req.getSession().getAttribute("user") + " is viewing the home page");
        req.getRequestDispatcher("/Views/Home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
