package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String SQLConn = "jdbc:sqlite:src/main/java/database/login-info.db";
    private static final String StatsConn = "jdbc:sqlite:src/main/java/database/Football-stats.db";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(SQLConn);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Connection getStatsConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(StatsConn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
