package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

import static DBservices.OrderRepository.*;
import static DBservices.UserRepository.getUserByUsername;

public class Orders extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Orders.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        var session = req.getSession();
        var username = session.getAttribute("user");
        var user = getUserByUsername(username.toString());
        var ordersList = getAllOrdersByUserId(user.getId());
        if (req.getParameter("orderId") != null) {
            OrderDetails(req, resp);
            logger.info("User " + username + " is viewing order details");
            req.getRequestDispatcher("/Views/OrdersDetails.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("orderList", ordersList);
        req.getRequestDispatcher("/Views/Orders.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private void OrderDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var orderId = Integer.parseInt(req.getParameter("orderId"));
        var ItemsList = getCartProductsByOrderId(orderId);
        var order = getOrderById(orderId);
        req.setAttribute("orderId", orderId);
        req.setAttribute("orderDate", order.getDate());
        req.setAttribute("orderTotal", order.getTotalPrice());
        req.setAttribute("ItemsList", ItemsList);
    }
}
