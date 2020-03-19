package controllers.components.students_affair;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class SaSidePanelController {
    @FXML
    private JFXButton
            createStudent,
            enrollStudent;

    public JFXButton getCreateStudent() {
        return createStudent;
    }

    public JFXButton getEnrollStudent() {
        return enrollStudent;
    }
}
