package controllers.components.user;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import global.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import models.User;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
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
    @FXML
    private JFXComboBox<String> gender;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dateOfBirth.setValue(LocalDate.of(2000, 1, 1));
        ObservableList<String> genders = FXCollections.observableArrayList();
        genders.setAll(Constants.GENDERS);
        gender.setItems(genders);
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
                date,
                gender.getValue()
        );
    }

    public void fillFields(User user) {
        firstName.setText(user.getFirstName());
        middleName.setText(user.getMiddleName());
        lastName.setText(user.getLastName());
        username.setText(user.getUsername());
        phoneNumber.setText(user.getPhone());
        LocalDate localDate = LocalDate.parse(user.getDateOfBirth(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        dateOfBirth.setValue(localDate);
        gender.setValue(user.getGender());
    }

    public HashMap<String, String> getUserFields() {
        HashMap<String, String> fields = new HashMap<>();
        fields.put("first_name", firstName.getText());
        fields.put("middle_name", middleName.getText());
        fields.put("last_name", lastName.getText());
        fields.put("username", username.getText());
        fields.put("phone", phoneNumber.getText());

        LocalDate localDate = dateOfBirth.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        fields.put("date", df.format(date));

        fields.put("gender", gender.getValue());
        return fields;
    }

    public String validateInput() {
        StringBuilder errors = new StringBuilder();
        if(gender.getValue().equals(""))                    errors.append("gender ");
        if(username.getText().equals(""))                   errors.append("username ");
        if(lastName.getText().equals(""))                   errors.append("last name ");
        if(firstName.getText().equals(""))                  errors.append("first name ");
        if(middleName.getText().equals(""))                 errors.append("middle name ");
        if(phoneNumber.getText().equals(""))                errors.append("phone number ");
        if(dateOfBirth.getValue().toString().equals(""))    errors.append("date of birth ");
        return errors.toString();
    }
}
