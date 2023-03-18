package DBservices;

import Models.Order;
import Models.OrderProduct;

import java.sql.Connection;
import java.sql.ResultSet;

import static DBservices.DatabasesConnection.getConnection;

public class OrderRepository {
    //store order to database
    public static int storeOrder(Order order) {
        int status = 0;
        try {
            Connection con = getConnection();
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
        try {
            Connection con = getConnection();
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
        try {
            Connection con = getConnection();
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
    public static ResultSet getAllOrdersByUserId(int userId) {
        ResultSet rs = null;
        try {
            Connection con = getConnection();
            var ps = con.prepareStatement("SELECT * FROM Orders WHERE UserId = ?");
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            return rs;
        } catch (Exception e) {
            return rs;
        }
    }

    //getProductsByOrderId
    public static ResultSet getProductsByOrderId(int orderId) {
        ResultSet rs = null;
        try {
            Connection con = getConnection();
            var ps = con.prepareStatement("SELECT * FROM productsorders WHERE OrderID = ?");
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            return rs;
        } catch (Exception e) {
            return rs;
        }
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
