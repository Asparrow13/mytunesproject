package gui.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertHelper {
    /**
     * Shows an error dialog with the given message.
     * @param str the message to display in the dialog.
     */
    public static void alertDialogBuilder(String str) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText(null);
        alert.setContentText(str);
        alert.showAndWait();
    }
    /**
     * Shows a success dialog with the given message.
     * @param str the message to display in the dialog.
     */
    public static void successDialogBuilder(String str) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText(str);
        alert.showAndWait();
    }
}
