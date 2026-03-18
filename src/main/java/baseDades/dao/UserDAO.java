package baseDades.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;


public class UserDAO {
    public static void createTable () {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS USERS (" +
                                "username VARCHAR (50) PRIMARY KEY," +
                                "email VARCHAR (70)" + 
                                ")";

        try{
            Connection conn = DatabaseConnection.getConnection();
            Statement statement = conn.createStatement();

            statement.execute(createTableSQL);
            conn.close();
            System.out.println("Database and table initialized succesfully");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void insterSampleUsers () {

        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO USERS (username, email) VALUES ('Maria', maria@exemple.com)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();

            sql = "INSERT INTO USERS (username, email) VALUES ('Pau', 'pau@exemple.com')";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("Hey ha dado error ! ! !");
        }


    }
    
}
