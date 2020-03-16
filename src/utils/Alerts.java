package utils;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Alerts {
    public static JFXAlert<String> createDefaultAlert(String title, String msg){
        JFXAlert<String> alert = new JFXAlert<>();
        alert.initModality(Modality.APPLICATION_MODAL);
        ((Stage) alert.getDialogPane().getScene().getWindow()).initStyle(StageStyle.UNDECORATED);
        alert.setOverlayClose(false);

        JFXDialogLayout layout = createLayout(title, msg);

        JFXButton okBtn = new JFXButton("OK");
        okBtn.setDefaultButton(true);
        okBtn.setOnAction(addEvent -> alert.hideWithAnimation());
        layout.setActions(okBtn);

        alert.setContent(layout);
        return alert;
    }
    public static JFXAlert<String> createAlert() {
        JFXAlert<String> alert = new JFXAlert<>();
        alert.initModality(Modality.APPLICATION_MODAL);
        ((Stage) alert.getDialogPane().getScene().getWindow()).initStyle(StageStyle.UNDECORATED);
        alert.setOverlayClose(false);
        return alert;
    }

    public static JFXDialogLayout createLayout(String title, String msg) {
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Label(title));
        layout.setBody(new VBox(new Label(msg)));
        return layout;
    }
}
