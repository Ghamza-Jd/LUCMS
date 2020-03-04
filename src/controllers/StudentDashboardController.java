package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.User;
import services.Session;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentDashboardController implements Initializable {
    @FXML
    private Label username;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String title = String.format("Welcome %s!", ((User) Session.getInstance().getValue("user")).getUsername());
        username.setText(title);
    }
}
