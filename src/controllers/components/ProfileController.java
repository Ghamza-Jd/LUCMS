package controllers.components;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import models.User;
import services.Session;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    @FXML
    private JFXTextField
            firstName,
            middleName,
            lastName,
            username,
            phoneNumber,
            dateOfBirth;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User user = (User) Session.getInstance().getValue("user");
        firstName.setText(user.getFirstName());
        middleName.setText(user.getMiddleName());
        lastName.setText(user.getLastName());
        username.setText(user.getUsername());
        phoneNumber.setText(user.getPhone());
        dateOfBirth.setText(user.getDateOfBirth());
    }
}
