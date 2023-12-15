package ConnUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingletonHelper {
    private static Connection conn;
    private ConnectionSingletonHelper() {
    }

    public static Connection getConnection(String dsn) {
        if (conn != null) {
            return conn;
        }
        try {
            if (dsn.equals("mysql")) {
                Class.forName("org.gjt.mm.mysql.Driver");
                conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/encore",
                        "root", "maria");
            } else if (dsn.equals("oracle")) {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conn = DriverManager.getConnection(
                        "jdbc:oracle:thin:@hostname:port:orcl",
                        "root", "maria");
            } else if (dsn.equals("mariadb")) {
                Class.forName("org.mariadb.jdbc.Driver");
                conn = DriverManager.getConnection(
                        "jdbc:mariadb://localhost:3306/encore",
                        "root", "maria");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return conn;

        }

    }

    public static void close() throws SQLException {
        if (conn != null) {
            if (!conn.isClosed()) {
                conn.close();
            }
        }

    }

}
