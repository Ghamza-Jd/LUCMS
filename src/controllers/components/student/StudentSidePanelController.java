package controllers.components.student;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class StudentSidePanelController {
    @FXML
    private JFXButton marks;

    public JFXButton getMarks() {
        return marks;
    }
}
