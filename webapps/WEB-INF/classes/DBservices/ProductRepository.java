package DBservices;

import Models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static DBservices.DatabasesConnection.getConnection;

public class ProductRepository {
    public static int InsertProduct(Product p) {
        int status = 0;
        try (Connection con = getConnection()) {

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

    public static List<Product> getAllProducts() {
        List<Product> productsList = new ArrayList<>();
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement("SELECT * FROM Products"); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                productsList.add(new Product(rs.getInt("id"), rs.getString("ProductName"), rs.getInt("CategoryId"), rs.getInt("quantity"), rs.getDouble("Price"), rs.getString("image")));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return productsList;
    }


    public static Product getProductById(int id) {
        Product product = null;
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement("SELECT * FROM Products WHERE id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    product = new Product(rs.getInt("id"), rs.getString("ProductName"), rs.getInt("CategoryId"), rs.getInt("quantity"), rs.getDouble("Price"), rs.getString("image"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    public static ResultSet getAllCategories() {
        ResultSet rs = null;
        try (Connection con = getConnection()) {

            var ps = con.prepareStatement("SELECT * FROM category");
            rs = ps.executeQuery();
            return rs;
        } catch (Exception e) {
            return rs;
        }
    }

    public static int deleteProduct(int id) {
        int status = 0;
        try (Connection con = getConnection()) {

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
        try (Connection con = getConnection()) {
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
