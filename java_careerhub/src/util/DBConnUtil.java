package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class DBConnUtil {

    // Method to get a connection to the database
    public static Connection getConnection() throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        
        // Load database configuration from properties file
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("resources/db.properties")) {
            props.load(fis);
        } catch (IOException e) {
            throw new IOException("Error loading database properties file: " + e.getMessage(), e);
        }

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.username");
        String password = props.getProperty("db.password");

        // Load MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Make sure the MySQL driver is available in the classpath
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("MySQL JDBC driver not found.", e);
        }

        // Establish a connection to the database
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new SQLException("Failed to connect to the database: " + e.getMessage(), e);
        }

        return conn;
    }

    // Method to safely close the connection (optional)
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

