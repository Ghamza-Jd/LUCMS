package controllers.components.user;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import models.User;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class CreateUserController implements Initializable {
    @FXML
    private JFXTextField
            firstName,
            middleName,
            lastName,
            username,
            phoneNumber;
    @FXML
    private JFXDatePicker dateOfBirth;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dateOfBirth.setValue(LocalDate.of(2000, 1, 1));
        phoneNumber.setOnKeyTyped(e -> {
            if(!"0123456789".contains(e.getCharacter()))
                e.consume();
        });
    }

    public User getUser() {
        LocalDate localDate = dateOfBirth.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        return new User(
                firstName.getText(),
                middleName.getText(),
                lastName.getText(),
                username.getText(),
                username.getText() + "123",
                "+961" + phoneNumber.getText(),
                date
        );
    }

    public String validateInput() {
        StringBuilder errors = new StringBuilder();
        if(username.getText().equals(""))                   errors.append("username ");
        if(lastName.getText().equals(""))                   errors.append("last name ");
        if(firstName.getText().equals(""))                  errors.append("first name ");
        if(middleName.getText().equals(""))                 errors.append("middle name ");
        if(phoneNumber.getText().equals(""))                errors.append("phone number ");
        if(dateOfBirth.getValue().toString().equals(""))    errors.append("date of birth ");
        return errors.toString();
    }
}
