package DBservices;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabasesConnection {
    //    public static Connection getConnection() {
//        Connection con = null;
//        try {
//            Context context = new InitialContext();
//            var dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/rustershop");
//            con = dataSource.getConnection();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return con;
//    }
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rustershop", "root", "");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return con;
    }
}
