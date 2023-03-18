package Controllers;

import Models.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import static DBservices.OrderRepository.*;
import static DBservices.ProductRepository.getProductById;
import static DBservices.UserRepository.getUserByUsername;

public class Orders extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        var session = req.getSession();
        var username = session.getAttribute("user");
        if (username == null) {
            resp.sendRedirect("Login");
            return;
        }
        var user = getUserByUsername(username.toString());
        var orders = getAllOrdersByUserId(user.getId());
        var ordersList = new java.util.ArrayList<Order>();
        Order orderList = null;
        while (true) {
            try {
                if (!orders.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                orderList = new Order(orders.getInt("id"), orders.getInt("UserId"), orders.getDouble("TotalPrice"), orders.getDate("OrderDate"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            ordersList.add(orderList);
        }
        if (req.getParameter("orderId") != null) {
            OrderDetails(req, resp);
            req.getRequestDispatcher("OrdersDetails.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("orderList", ordersList);
        req.getRequestDispatcher("Orders.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private void OrderDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var orderId = Integer.parseInt(req.getParameter("orderId"));
        var Items = getProductsByOrderId(orderId);
        var ItemsList = new java.util.ArrayList<Models.CartProduct>();
        Models.CartProduct ItemList = null;
        while (true) {
            try {
                if (!Items.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                var product = getProductById(Items.getInt("ProductId"));
                if (!product.next()) break;
                ItemList = new Models.CartProduct(product.getInt("id"), product.getString("ProductName"), Items.getInt("quantity"), product.getDouble("Price") * Items.getInt("quantity"), product.getString("Image"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            ItemsList.add(ItemList);
        }
        var order = getOrderById(orderId);
        req.setAttribute("orderId", orderId);
        req.setAttribute("orderDate", order.getDate());
        req.setAttribute("orderTotal", order.getTotalPrice());
        req.setAttribute("ItemsList", ItemsList);
    }
}
