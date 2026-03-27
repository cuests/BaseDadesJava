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
            //en cas de voler fer selects, es fa executeQuery() i no executeUpdate()
            pstmt.executeUpdate();

            sql = "INSERT INTO USERS (username, email) VALUES ('Miquel', 'miquel@exemple.com')";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();

            sql = "INSERT INTO USERS (username, email) VALUES ('Albert', 'albert@exemple.com')";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("Hey ha dado error ! ! !");
        }

    }
    //fer al main 2 opcions de menu
    // que per afegir usuaris ho demani per text i que mostri els usuaris
    public static boolean insertUser (String usuari, String email) {
        String sql = "INSERT INTO USERS (username, email) VALUES (?, ?)";
        try(Connection conn = DatabaseConnection.getConnection();) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, usuari);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error al inserir usuari manualment: " + e.getMessage());
        }
        return false;
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


    public static boolean updateUser (User user){
        String sql = "UPDATE users SET email = ? WHERE username = ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getUsername());

            int rowsUpdated = pstmt.executeUpdate();

            return rowsUpdated > 0;
        }
        catch(Exception e) {
            System.err.println("Error al actualitzar l'usuari" +e.getMessage());
            return false;
        }
    }

    public static boolean deleteUser (User user){
        String sql = "DELETE from users WHERE username = ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
            
        } catch (Exception e) {
            System.err.println("Error al eliminar l'usuari" +e.getMessage());
            return false;
        }
    }

    public static boolean searchUser (User user){
        String sql = "SELECT username, email from users WHERE username = ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());

            ResultSet rs = pstmt.executeQuery();
            //rs.next() --- vol dir que si existeis un registre amb aquest username (en aquest cas) doncs que faci X.
            if (rs.next()){
                //per cridar a les dades que ens ha donat amb rs
                System.out.println("Nom de l'usuari: " + rs.getString("username"));
                System.out.println("Mail de l'usuari: " + rs.getString("email"));
                return true;   
            }
            else {
                System.out.println("No s'ha trobat el usuari.");
                return false;
            }
            
        } catch (Exception e) {
            System.err.println("Error al trobar l'usuari" +e.getMessage());
            return false;
        }
    }
}
    
