package DBservices;

import Models.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DatabaseOperations {
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rustershop", "root", "");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public static int InsertProduct(Product p) {
        int status = 0;
        try {
            Connection con = getConnection();
            var ps = con.prepareStatement("INSERT INTO Products (ProductName, CategoryId, Price, quantity, image) values(?,?,?,?,?)");
            ps.setString(1, p.getName());
            ps.setInt(2, p.getCategoryId());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getQuantity());
            ps.setString(5, p.getImage());
            ps.executeUpdate();
            status = 1;
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    public static int RegisterUser(User p) {
        int status = 0;
        try {
            Connection con = getConnection();
            var ps = con.prepareStatement("INSERT INTO Users (username, password, email, userType) values(?,?,?,?)");
            ps.setString(1, p.getUsername());
            ps.setString(2, p.getPassword());
            ps.setString(3, p.getEmail());
            ps.setString(4, p.getUserType().toString());
            ps.executeUpdate();
            status = 1;
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    public static int LoginUser(String username, String password) {
        int status = 0;
        try {
            Connection con = getConnection();
            var ps = con.prepareStatement("SELECT * FROM Users WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            var rs = ps.executeQuery();
            if (rs.next()) {
                status = 1;
            }
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    public static ResultSet getAllUsers() {
        ResultSet rs = null;
        try {
            Connection con = getConnection();
            var ps = con.prepareStatement("SELECT * FROM Users");
            rs = ps.executeQuery();
            return rs;
        } catch (Exception e) {
            return rs;
        }
    }

    public static ResultSet getAllProducts() {
        ResultSet rs = null;
        try {
            Connection con = getConnection();
            var ps = con.prepareStatement("SELECT * FROM Products");
            rs = ps.executeQuery();
            return rs;
        } catch (Exception e) {
            return rs;
        }
    }

    public static ResultSet getProductById(int id) {
        ResultSet rs = null;
        try {
            Connection con = getConnection();
            var ps = con.prepareStatement("SELECT * FROM Products WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            return rs;
        } catch (Exception e) {
            return rs;
        }
    }

    public static ResultSet getAllCategories() {
        ResultSet rs = null;
        try {
            Connection con = getConnection();
            var ps = con.prepareStatement("SELECT * FROM category");
            rs = ps.executeQuery();
            return rs;
        } catch (Exception e) {
            return rs;
        }
    }

    public static int deleteProduct(int id) {
        int status = 0;
        try {
            Connection con = getConnection();
            var ps = con.prepareStatement("DELETE FROM Products WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            status = 1;
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    //update product
    public static int updateProduct(int id, Product p) {
        int status = 0;
        try {
            Connection con = getConnection();
            var ps = con.prepareStatement("UPDATE Products SET ProductName = ?, CategoryId = ?, Price = ?, quantity = ?, image = ? WHERE id = ?");
            ps.setString(1, p.getName());
            ps.setInt(2, p.getCategoryId());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getQuantity());
            ps.setString(5, p.getImage());
            ps.setInt(6, id);
            ps.executeUpdate();
            status = 1;
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    //get user type by username
    public static UserType getUserTypeByUsername(String username) {
        UserType userType = null;
        try {
            Connection con = getConnection();
            var ps = con.prepareStatement("SELECT userType FROM Users WHERE username = ?");
            ps.setString(1, username);
            var rs = ps.executeQuery();
            if (rs.next()) {
                userType = UserType.valueOf(rs.getString("userType"));
            }
            return userType;
        } catch (Exception e) {
            return userType;
        }
    }

    //get user by username
    public static User getUserByUsername(String username) {
        User user = null;
        try {
            Connection con = getConnection();
            var ps = con.prepareStatement("SELECT * FROM Users WHERE username = ?");
            ps.setString(1, username);
            var rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("email"), UserType.valueOf(rs.getString("userType")));
            }
            return user;
        } catch (Exception e) {
            return user;
        }
    }

    //update user
    public static int updateUser(User p) {
        int status = 0;
        try {
            Connection con = getConnection();
            var ps = con.prepareStatement("UPDATE Users SET username = ?, password = ?, email = ?, userType = ? WHERE id = ?");
            ps.setString(1, p.getUsername());
            ps.setString(2, p.getPassword());
            ps.setString(3, p.getEmail());
            ps.setString(4, p.getUserType().toString());
            ps.setInt(5, p.getId());
            ps.executeUpdate();
            status = 1;
            return status;
        } catch (Exception e) {
            return status;
        }
    }

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

    //get user by email
    public static User getUserByEmail(String email) {
        User user = null;
        try {
            Connection con = getConnection();
            var ps = con.prepareStatement("SELECT * FROM Users WHERE email = ?");
            ps.setString(1, email);
            var rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("email"), UserType.valueOf(rs.getString("userType")));
            }
            return user;
        } catch (Exception e) {
            return user;
        }
    }
}
