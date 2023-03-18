package DBservices;

import Models.CartProduct;
import Models.Order;
import Models.OrderProduct;
import Models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static DBservices.DatabasesConnection.getConnection;
import static DBservices.ProductRepository.getProductById;

public class OrderRepository {
    //store order to database
    public static int storeOrder(Order order) {
        int status = 0;
        try (Connection con = getConnection()) {
            var ps = con.prepareStatement("INSERT INTO Orders (UserId, TotalPrice, OrderDate) values(?,?,?)");
            ps.setInt(1, order.getUserId());
            ps.setDouble(2, order.getTotalPrice());
            ps.setDate(3, order.getDate());
            ps.executeUpdate();
            status = 1;
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    //save order OrderProduct to database
    public static int storeOrderProduct(OrderProduct orderProduct) {
        int status = 0;
        try (Connection con = getConnection()) {
            var ps = con.prepareStatement("INSERT INTO productsorders (OrderID, ProductID, quantity) values(?,?,?)");
            ps.setInt(1, orderProduct.getOrderId());
            ps.setInt(2, orderProduct.getProductId());
            ps.setInt(3, orderProduct.getQuantity());
            ps.executeUpdate();
            status = 1;
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    //get last order id
    public static int getLastOrderId() {
        int id = 0;
        try (Connection con = getConnection()) {
            var ps = con.prepareStatement("SELECT MAX(id) as id FROM Orders");
            var rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
            return id;
        } catch (Exception e) {
            return id;
        }
    }

    //get all orders of a user
    public static List<Order> getAllOrdersByUserId(int userId) {
        List<Order> ordersList = new ArrayList<>();
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement("SELECT * FROM Orders WHERE UserId = ?");) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ordersList.add(new Order(rs.getInt("id"), rs.getInt("UserId"), rs.getDouble("TotalPrice"), rs.getDate("OrderDate")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ordersList;
    }

    //getProductsByOrderId
    public static List<CartProduct> getCartProductsByOrderId(int orderId) {
        List<CartProduct> cartItemList = new ArrayList<>();
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement("SELECT * FROM productsorders WHERE OrderID = ?");) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int productId = rs.getInt("ProductId");
                    int quantity = rs.getInt("Quantity");
                    Product product = getProductById(productId);
                    if (product != null) {
                        String productName = product.getName();
                        double price = product.getPrice();
                        String image = product.getImage();
                        CartProduct cartItem = new CartProduct(productId, productName, quantity, price * quantity, image);
                        cartItemList.add(cartItem);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cartItemList;
    }

    //getOrderbyId
    public static Order getOrderById(int orderId) {
        Order order = null;
        try {
            Connection con = getConnection();
            var ps = con.prepareStatement("SELECT * FROM Orders WHERE id = ?");
            ps.setInt(1, orderId);
            var rs = ps.executeQuery();
            if (rs.next()) {
                order = new Order(rs.getInt("id"), rs.getInt("UserId"), rs.getDouble("TotalPrice"), rs.getDate("OrderDate"));
            }
            return order;
        } catch (Exception e) {
            return order;
        }
    }
}
