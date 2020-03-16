package controllers.components.students_affair;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controllers.repos.Students;
import global.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import models.Student;
import models.User;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
        dateOfBirth.setValue(LocalDate.of(2000, 1, 1));
        phoneNumber.setOnKeyTyped(e -> {
            if(!"0123456789".contains(e.getCharacter()))
                e.consume();
        });
        ObservableList<String> majors = FXCollections.observableArrayList(Constants.MAJORS);
        major.setItems(majors);
    }

    @FXML
    void createStudent(ActionEvent event) throws SQLException {
        LocalDate localDate = dateOfBirth.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        User user = new User(
                firstName.getText(),
                middleName.getText(),
                lastName.getText(),
                username.getText(),
                username.getText() + "123",
                "+961" + phoneNumber.getText(),
                date
        );
        Student student = new Student(user, major.getValue());
        Students.getInstance().create(student);
    }
}
