package controllers.components.head_of_department;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public final class HodSidePanelController {
    @FXML
    private JFXButton createNews;
    @FXML
    private JFXButton createCourse;
    @FXML
    private JFXButton createProfessor;

    public JFXButton getCreateCourse() {
        return createCourse;
    }

    public JFXButton getCreateProfessor() {
        return createProfessor;
    }

    public JFXButton getCreateNews() { return createNews; }
}
