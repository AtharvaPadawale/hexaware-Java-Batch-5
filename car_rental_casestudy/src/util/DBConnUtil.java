//package util;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DBConnUtil {
//
//    private static Connection connection = null;
//
//    public static Connection getConnection() {
//        if (connection == null) {
//            try {
//                // Get connection string from DBPropertyUtil
//                String connectionString = DBPropertyUtil.getPropertyString();
//
//                if (!connectionString.isEmpty()) {
//                    connection = DriverManager.getConnection(connectionString);
//                    System.out.println("Database connection established successfully.");
//                } else {
//                    System.out.println("Connection string is empty. Please check db.properties file.");
//                }
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return connection;
//    }
//}


package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class DBConnUtil {

    public static Connection getConnection() throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;

        // Load database configuration from resources using classloader
        Properties props = new Properties();
        try (InputStream input = DBConnUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new IOException("db.properties file not found in classpath!");
            }
            props.load(input);
        }

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.username");
        String password = props.getProperty("db.password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("MySQL JDBC driver not found.", e);
        } catch (SQLException e) {
            throw new SQLException("Failed to connect to the database: " + e.getMessage(), e);
        }

        return conn;
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing the database connection: " + e.getMessage());
            }
        }
    }
}
