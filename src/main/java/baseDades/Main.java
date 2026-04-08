package baseDades;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.h2.tools.Server;
import baseDades.dao.DataBase.DatabaseInit;

// 1. Añadimos "extends Application" para decirle a Java que esto es una ventana de JavaFX
public class Main extends Application {

    // 2. Este método "start" es el que "dibuja" la ventana al arrancar
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Leemos tu archivo FXML (asegúrate de que está suelto en src/main/resources/)
        Parent root = FXMLLoader.load(getClass().getResource("/event-form.fxml"));
        
        // Creamos la "escena" (el contenido) con un tamaño inicial de 400x500 píxeles
        Scene scene = new Scene(root, 400, 500);
        
        // Le ponemos un título a la ventana y la mostramos
        primaryStage.setTitle("Buscador de Eventos");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        try {
            // A. Iniciamos la base de datos H2 como ya hacíamos antes
            Server webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
            System.out.println("El link de la base de datos es = " + webServer.getURL());
            DatabaseInit.init();
            
        } catch (Exception e) {
            System.err.println("Error al iniciar la base de datos:");
            e.printStackTrace();
        }

        // B. Arrancamos la interfaz gráfica (esto llama al método start de arriba)
        launch(args);
    }
}