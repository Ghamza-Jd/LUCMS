package controllers;

import controllers.repos.Professors;
import controllers.repos.Students;
import controllers.repos.StudentsAffairs;
import controllers.repos.Users;
import exceptions.InvalidCredentialsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import models.User;
import services.Session;
import services.ViewsManager;
import utils.Alerts;
import utils.FlashMessages;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Pane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String message = FlashMessages.getInstance().receiveMessages(getClass());
        if(message == null) return;
        Alerts.createSnackbar(pane, message, 1, "#080", "#FFF");
    }

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
        // TODO: Fix the roles when logging in
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
