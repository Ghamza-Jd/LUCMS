package controllers;

import controllers.repos.Users;
import exceptions.InvalidCredentialsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.ViewsManager;
import utils.Alerts;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    Button loginBtn;
    @FXML
    TextField username;
    @FXML
    PasswordField password;

    @Override
    public void initialize(URL location, ResourceBundle resources) { }

    @FXML
    public void login(ActionEvent event) throws SQLException, IOException {
        String username = this.username.getText();
        String password = this.password.getText();
        try {
            if (Users.getDao().isUser(username, password)){
                ViewsManager.getActiveStage(event).setScene(ViewsManager.requestView("StudentDashboard"));
            }
        } catch (InvalidCredentialsException e) {
            Alerts.warningAlert("Invalid Credentials", "Incorrect username or password").showAndWait();
            this.password.setText("");
        }
    }
}
