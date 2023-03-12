package Controllers;

import Models.CartProduct;
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
                    return;
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
}
