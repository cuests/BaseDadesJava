package baseDades.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:h2:./data/eventfinder_db;AUTO_SERVER=TRUE";

    public static Connection getConnection () throws SQLException {
        return DriverManager.getConnection(URL,"sa","");
    }
    
}
