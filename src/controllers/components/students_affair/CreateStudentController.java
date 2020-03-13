package controllers.components.students_affair;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import global.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateStudentController implements Initializable {
    @FXML
    private JFXTextField
            firstName,
            middleName,
            lastName,
            username,
            phoneNumber;
    @FXML
    private JFXDatePicker dateOfBirth;
    @FXML
    private JFXComboBox<String> major;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        phoneNumber.setOnKeyTyped(e -> {
            if(!"0123456789".contains(e.getCharacter()))
                e.consume();
        });
        ObservableList<String> majors = FXCollections.observableArrayList(Constants.MAJORS);
        major.setItems(majors);
    }
}
