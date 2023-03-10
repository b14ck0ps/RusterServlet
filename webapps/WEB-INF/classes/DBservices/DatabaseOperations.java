package DBservices;

import Models.Product;
import Models.User;

import java.sql.Connection;
import java.sql.DriverManager;

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
            var ps = con.prepareStatement("INSERT INTO Product (ProductName, CategoryId, Price, quantity, image) values(?,?,?,?,?)");
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


}
