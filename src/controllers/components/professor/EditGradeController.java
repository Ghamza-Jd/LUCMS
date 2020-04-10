package controllers.components.professor;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EditGradeController {
    @FXML
    private Label student;
    @FXML
    private Label course;
    @FXML
    private JFXTextField grade;
    @FXML
    private JFXButton assign;

    public Label getStudent() {
        return student;
    }

    public Label getCourse() {
        return course;
    }

    public JFXTextField getGrade() {
        return grade;
    }

    public JFXButton getAssign() {
        return assign;
    }
}
