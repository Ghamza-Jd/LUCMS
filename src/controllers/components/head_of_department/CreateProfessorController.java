package controllers.components.head_of_department;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controllers.repos.Students;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import models.Student;
import models.User;

import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class CreateProfessorController {
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
    }
}
