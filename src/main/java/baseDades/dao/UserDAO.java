package baseDades.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import baseDades.model.User;


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

    public static void insertSampleUsers () {

        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO USERS (username, email) VALUES ('Maria', 'maria@exemple.com')";
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
    //fer al main 2 opcions de menu
    // que per afegir usuaris ho demani per text i que mostri els usuaris
    public static void insertUser (String username, String email) {

    }

    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT username, email FROM users";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()){

            while (rs.next()){
                String username = rs.getString("username");
                String email = rs.getString("email");

                User user = new User(username, email);
                users.add(user);
            }
        } catch (SQLException e){
            System.err.println("Error al llegir usuaris: "+ e.getMessage());
        }
        return users;
    }

    }
    
