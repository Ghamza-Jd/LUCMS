package controllers;

import controllers.repos.Students;
import controllers.repos.Users;
import exceptions.InvalidCredentialsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.Student;
import models.User;
import services.Session;
import services.ViewsManager;
import utils.Alerts;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button loginBtn;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @Override
    public void initialize(URL location, ResourceBundle resources) { }

    @FXML
    public void loginBtnHandler(ActionEvent event) throws SQLException, IOException {
        String username = this.username.getText();
        String password = this.password.getText();
        try {
            if (Users.getDao().isUser(username, password)) {
                login(username);
                ViewsManager.getActiveStage(event).setScene(ViewsManager.requestView("test"));
            }
        } catch (InvalidCredentialsException e) {
            Alerts.warningAlert("Invalid Credentials", "Incorrect username or password").showAndWait();
            this.password.setText("");
        }
    }

    private void login(String username) throws SQLException {
        Session.getInstance().addToSession("user", Users.getDao().getUser(username));
        User user = (User) Session.getInstance().getValue("user");
        if(user.getRole().equals("STUDENT")) {
            Session.getInstance().addToSession("student", Students.getDao().getStudent(user));
            System.out.println(Session.getInstance().getValue("student"));
        }
    }
}
