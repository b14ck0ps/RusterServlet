package Controllers;

import Models.CartProduct;
import Models.Order;
import Models.OrderProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Cart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        var action = req.getParameter("action");
        if (action != null && action.equals("add")) {
            try {
                AddToCart(req, resp);
                req.getSession().setAttribute("cartMessage", "Product added to cart successfully");
                //check if param single is true
                var single = req.getParameter("single");
                if (single != null && single.equals("true")) {
                    resp.sendRedirect("/Cart");
                } else {
                    resp.sendRedirect("/Home");
                }
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (action != null && action.equals("delete")) {
            try {
                DeleteFromCart(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (action != null && action.equals("deleteSingle")) {
            try {
                DeleteSingleProductFromCart(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (action != null && action.equals("clear")) {
            try {
                clearCart(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (action != null && action.equals("checkout")) {
            try {
                checkout(req, resp);
                req.getSession().setAttribute("OrderMessage", "Order placed successfully");
                resp.sendRedirect("/Orders");
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        //calculate total price
        List<CartProduct> cartProducts = (List<CartProduct>) req.getSession().getAttribute("cartProducts");
        var totalPrice = 0.0;
        if (cartProducts != null) {
            for (CartProduct cartProduct : cartProducts) {
                totalPrice += cartProduct.getPrice();
            }
        }
        if (cartProducts == null || cartProducts.size() == 0) {
            cartProducts = new java.util.ArrayList<>();
            req.getSession().setAttribute("cartProducts", cartProducts);
        }
        req.getSession().setAttribute("totalPrice", totalPrice);
        req.getRequestDispatcher("Cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private void AddToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        var id = req.getParameter("id");
        var quantity = req.getParameter("quantity");
        List<CartProduct> cartProducts = (List<CartProduct>) req.getSession().getAttribute("cartProducts");
        if (cartProducts == null) {
            cartProducts = new java.util.ArrayList<>();
        }
        if (id != null && quantity != null) {
            var product = DBservices.DatabaseOperations.getProductById(Integer.parseInt(id));
            //check if product already exists in cart
            for (var i = 0; i < cartProducts.size(); i++) {
                if (cartProducts.get(i).getId() == Integer.parseInt(id)) {
                    cartProducts.get(i).setQuantity(cartProducts.get(i).getQuantity() + Integer.parseInt(quantity));
                    if (product.next()) {
                        var ItemPrice = product.getDouble("Price");
                        cartProducts.get(i).setPrice(ItemPrice * cartProducts.get(i).getQuantity());
                    }
                    req.getSession().setAttribute("cartProducts", cartProducts);
                }
            }
            if (product.next()) {
                var name = product.getString("ProductName");
                var price = product.getDouble("Price");
                var image = product.getString("image");
                cartProducts.add(new CartProduct(Integer.parseInt(id), name, Integer.parseInt(quantity), Integer.parseInt(quantity) * price, image));
            }
            req.getSession().setAttribute("cartProducts", cartProducts);
        }
    }

    private void DeleteFromCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        var id = req.getParameter("id");
        List<CartProduct> cartProducts = (List<CartProduct>) req.getSession().getAttribute("cartProducts");
        if (cartProducts == null) {
            cartProducts = new java.util.ArrayList<>();
        }
        if (id != null) {
            for (var i = 0; i < cartProducts.size(); i++) {
                if (cartProducts.get(i).getId() == Integer.parseInt(id)) {
                    cartProducts.remove(i);
                    break;
                }
            }
            req.getSession().setAttribute("cartProducts", cartProducts);
        }
    }

    private void DeleteSingleProductFromCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        var id = req.getParameter("id");
        List<CartProduct> cartProducts = (List<CartProduct>) req.getSession().getAttribute("cartProducts");
        if (cartProducts == null) {
            cartProducts = new java.util.ArrayList<>();
        }
        if (id != null) {
            for (var i = 0; i < cartProducts.size(); i++) {
                if (cartProducts.get(i).getId() == Integer.parseInt(id)) {
                    if (cartProducts.get(i).getQuantity() > 1) {
                        cartProducts.get(i).setQuantity(cartProducts.get(i).getQuantity() - 1);
                        var product = DBservices.DatabaseOperations.getProductById(Integer.parseInt(id));
                        if (product.next()) {
                            var ItemPrice = product.getDouble("Price");
                            cartProducts.get(i).setPrice(cartProducts.get(i).getPrice() - ItemPrice);
                        }
                    } else {
                        cartProducts.remove(i);
                    }
                    break;
                }
            }
            req.getSession().setAttribute("cartProducts", cartProducts);
        }
    }

    private void clearCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<CartProduct> cartProducts = (List<CartProduct>) req.getSession().getAttribute("cartProducts");
        if (cartProducts == null) {
            cartProducts = new java.util.ArrayList<>();
        }
        cartProducts.clear();
        req.getSession().setAttribute("cartProducts", cartProducts);
    }

    private void checkout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<CartProduct> cartProducts = (List<CartProduct>) req.getSession().getAttribute("cartProducts");
        if (cartProducts == null) {
            cartProducts = new java.util.ArrayList<>();
        }
        var totalPrice = req.getSession().getAttribute("totalPrice");
        var date = new java.sql.Date(new java.util.Date().getTime());
        var user = DBservices.DatabaseOperations.getUserByUsername(req.getSession().getAttribute("user").toString());
        var order = new Order(user.getId(), (Double) totalPrice, date);
        if (DBservices.DatabaseOperations.storeOrder(order) == 1) {
            ////get last order id from database and set it to order object to be used in orderproduct table as foreign key
            order.setId(DBservices.DatabaseOperations.getLastOrderId());
            //save orderproducts to database
            for (CartProduct cartProduct : cartProducts) {
                var orderProduct = new OrderProduct(order.getId(), cartProduct.getId(), cartProduct.getQuantity());
                DBservices.DatabaseOperations.storeOrderProduct(orderProduct);
            }
            cartProducts.clear();
        }
        req.getSession().setAttribute("cartProducts", cartProducts);
    }
}
