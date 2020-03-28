package controllers.components.user;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tooltip;

import java.net.URL;
import java.util.ResourceBundle;

public class ActionsController implements Initializable {
    @FXML
    private JFXButton
            edit,
            details,
            delete
    ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        edit.setTooltip(new Tooltip("Edit"));
        details.setTooltip(new Tooltip("View Details"));
        delete.setTooltip(new Tooltip("Delete"));
    }

    public JFXButton getEdit() {
        return edit;
    }

    public JFXButton getDetails() {
        return details;
    }

    public JFXButton getDelete() {
        return delete;
    }
}
