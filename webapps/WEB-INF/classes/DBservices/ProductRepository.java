package DBservices;

import Models.Product;

import java.sql.Connection;
import java.sql.ResultSet;

import static DBservices.DatabasesConnection.getConnection;

public class ProductRepository {
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
}
