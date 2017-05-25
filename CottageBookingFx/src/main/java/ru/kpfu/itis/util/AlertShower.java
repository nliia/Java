package ru.kpfu.itis.util;

import javafx.scene.control.Alert;

/**
 * @author Liia
 */
public class AlertShower {
    public static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Fields");
        alert.setHeaderText("Please correct invalid fields");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
