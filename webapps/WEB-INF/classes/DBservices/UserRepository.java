package DBservices;

import Models.User;
import Models.UserType;

import java.sql.Connection;

import static DBservices.DatabasesConnection.getConnection;

public class UserRepository {
    public static int RegisterUser(User p) {
        int status = 0;
        try (Connection con = getConnection()) {
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
        try (Connection con = getConnection()) {
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

    //get user type by username
    public static UserType getUserTypeByUsername(String username) {
        UserType userType = null;
        try (Connection con = getConnection()) {
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
        try (Connection con = getConnection()) {
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
        try (Connection con = getConnection()) {
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

    //get user by email
    public static User getUserByEmail(String email) {
        User user = null;
        try (Connection con = getConnection()) {
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
