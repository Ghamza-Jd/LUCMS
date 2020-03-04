package utils;

import javafx.scene.control.Alert;

public class Alerts {
    public static Alert createAlert(String title, String msg, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        return alert;
    }
    public static Alert warningAlert(String title, String msg) {
        return createAlert(title, msg, Alert.AlertType.WARNING);
    }
}
