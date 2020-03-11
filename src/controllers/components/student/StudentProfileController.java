package controllers.components.student;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import models.Student;
import services.Session;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentProfileController implements Initializable {
    @FXML
    private JFXTextField major;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        major.setText(((Student) Session.getInstance().getValue("student")).getMajor());
    }

    public JFXTextField getMajor() {
        return major;
    }
}
