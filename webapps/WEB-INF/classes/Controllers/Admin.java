package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

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
        var productsList = getAllProducts();
        req.setAttribute("products", productsList);
        req.getRequestDispatcher("AdminDashBoard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
