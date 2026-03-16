package baseDades.dao;

import java.sql.Connection;
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


    }
    
}
