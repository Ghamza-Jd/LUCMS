package controllers.components.professor;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class ProfessorSidePanelController {
    @FXML
    private JFXButton
            grades,
            courses;

    public JFXButton getCourses() {
        return courses;
    }

    public JFXButton getGrades() {
        return grades;
    }
}
