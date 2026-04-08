package baseDades.controllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import baseDades.dao.DataBase.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class EventFormController {

    // La etiqueta @FXML "conecta" estas variables con los fx:id de tu archivo FXML
    @FXML
    private TextField titleField;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private TextField categoryField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField cityField;

    // Este método se ejecuta al pulsar el botón "Guardar Evento"
    @FXML
    private void guardarEvento() {
        // 1. Recoger los datos de la interfaz
        String title = titleField.getText();
        String description = descriptionArea.getText();
        String category = categoryField.getText();
        LocalDate localDate = datePicker.getValue();
        String city = cityField.getText();

        // 2. Validar que los campos obligatorios (los que pusiste NOT NULL en SQL) no estén vacíos
        if (title == null || title.trim().isEmpty() || localDate == null || city == null || city.trim().isEmpty()) {
            mostrarAlerta(AlertType.WARNING, "Campos incompletos", "El título, la fecha y la ciudad son obligatorios.");
            return;
        }

        // 3. Preparar la consulta SQL (usamos ? por seguridad, para evitar inyección SQL)
        String sql = "INSERT INTO events (title, description, category, event_date, city) VALUES (?, ?, ?, ?, ?)";

        // 4. Conectar a la base de datos y ejecutar el INSERT
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.setString(3, category);
            // Convertimos la fecha de JavaFX (LocalDate) a fecha de SQL (java.sql.Date)
            pstmt.setDate(4, Date.valueOf(localDate));
            pstmt.setString(5, city);

            pstmt.executeUpdate(); // Ejecutamos la consulta

            // Si llegamos aquí, se guardó correctamente
            mostrarAlerta(AlertType.INFORMATION, "Éxito", "El evento se ha guardado correctamente en la base de datos.");
            limpiarFormulario(); // Vaciamos los campos para el siguiente evento

        } catch (Exception e) {
            mostrarAlerta(AlertType.ERROR, "Error", "No se pudo guardar el evento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método de ayuda para mostrar ventanas emergentes (pop-ups)
    private void mostrarAlerta(AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    // Método para dejar el formulario en blanco después de guardar
    private void limpiarFormulario() {
        titleField.clear();
        descriptionArea.clear();
        categoryField.clear();
        datePicker.setValue(null);
        cityField.clear();
    }
}