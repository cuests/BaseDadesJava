package baseDades.dao.DataBase;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInit {

    public static void init() {
        try (InputStream is = DatabaseInit.class.getResourceAsStream("/schema.sql")) {
            
            if (is == null) {
                throw new RuntimeException("No se pudo encontrar el archivo /schema.sql en el classpath.");
            }

            String sql = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            try (Connection conn = DatabaseConnection.getConnection();
                 Statement stmt = conn.createStatement()) {
                
                stmt.execute(sql);
                System.out.println("Base de datos inicializada correctamente.");
                
            }

        } catch (Exception e) {
            System.err.println("Error crítico al inicializar la base de datos:");
            e.printStackTrace();
        }
    }
}