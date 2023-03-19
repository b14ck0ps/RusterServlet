package DBservices;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.logging.Logger;

public class DatabasesConnection {
    private static final Logger logger = Logger.getLogger(DatabasesConnection.class.getName());

    public static Connection getConnection() {
        Connection con = null;
        try {
            Context context = new InitialContext();
            var dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/rustershop");
            con = dataSource.getConnection();
            logger.info("Connection to database established");
        } catch (Exception e) {
            logger.severe("Connection to database failed");
            System.out.println(e.getMessage());
        }
        return con;
    }
}
