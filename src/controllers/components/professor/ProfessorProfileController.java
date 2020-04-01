package controllers.components.professor;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import models.Professor;
import services.Session;

import java.net.URL;
import java.util.ResourceBundle;

public final class ProfessorProfileController implements Initializable {
    @FXML
    private JFXTextField office;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        office.setEditable(false);
        office.setText(String.valueOf(((Professor) Session.getInstance().getValue("professor")).getOfficeNumber()));
    }
}
