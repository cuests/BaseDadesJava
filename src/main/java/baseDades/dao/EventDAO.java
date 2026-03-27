package baseDades.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class EventDAO {

    public static void createTable() {
        String createTableSQL ="CREATE TABLE IF NOT EXISTS EVENTS ("+
        "id INT PRIMARY KEY,"+
        "title VARCHAR(100), "+
        "description TEXT," +
        "category VARCHAR(50),"+
        "event_date DATE,"+
        "id_creator VARCHAR(50))";
    }

    public void insertSampleEvent() {
        String sql = "INSER INTO EVENTS (id, title, description, categoy, event_date, city, id_creator)"+
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
                    
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

                //Event 1
                pstmt.setInt(1, 1);
                pstmt.setString(2, "Event del LoL");
                pstmt.setString(3, "Final regional");
                pstmt.setString(4, "GAMING");
                pstmt.setDate(5, Date.valueOf("2026-06-20"));
                pstmt.setString(6, "Tarragona");
                pstmt.setInt (7, 6);
                
            }
        catch (SQLException e) {    
            System.err.println("Error al guardar el evento en la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
