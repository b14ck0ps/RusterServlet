package DBservices;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class DatabasesConnection {
    public static Connection getConnection() {
        Connection con = null;
        try {
            Context context = new InitialContext();
            var dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/rustershop");
            con = dataSource.getConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return con;
    }
}
