package controllers;

import controllers.repos.Professors;
import controllers.repos.Students;
import controllers.repos.StudentsAffairs;
import controllers.repos.Users;
import exceptions.InvalidCredentialsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.User;
import services.Session;
import services.ViewsManager;
import utils.Alerts;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    public void loginBtnHandler(ActionEvent event) throws SQLException, IOException {
        String username = this.username.getText();
        String password = this.password.getText();
        try {
            if (Users.getInstance().isUser(username, password)) {
                login(username);
                ViewsManager.getActiveStage(event).setScene(ViewsManager.requestView("Dashboard"));
            }
        } catch (InvalidCredentialsException e) {
            Alerts.createDefaultAlert("Invalid Credentials", "Incorrect username or password").showAndWait();
            this.password.setText("");
        }
    }

    private void login(String username) throws SQLException {
        Session.getInstance().addToSession("user", Users.getInstance().retrieveSingle(username));
        User user = (User) Session.getInstance().getValue("user");
        if(user.getRole().equals("STUDENT")) {
            Session.getInstance().addToSession("student", Students.getInstance().retrieveSingle(user));
        }
        if(user.getRole().equals("PROFESSOR")) {
            Session.getInstance().addToSession("professor", Professors.getInstance().retrieveSingle(user));
        }
        if(user.getRole().equals("STUDENT_AFFAIR")) {
            Session.getInstance().addToSession("student_affair", StudentsAffairs.getInstance().retrieveAll());
        }
    }
}
