package controllers.components.student;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public final class StudentSidePanelController {
    @FXML
    private JFXButton
            marks,
            courses
    ;

    public JFXButton getMarks() {
        return marks;
    }

    public JFXButton getCourses() {
        return courses;
    }
}
