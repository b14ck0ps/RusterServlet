package DBservices;

import Models.Product;
import Models.User;
import Models.UserType;

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
}
