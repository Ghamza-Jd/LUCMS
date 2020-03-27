package controllers.components.user;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import controllers.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import models.User;
import services.Session;
import services.ViewsManager;
import utils.Alerts;
import utils.FlashMessages;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SidePanelController implements Initializable {
    @FXML
    private Label username;
    @FXML
    private JFXButton
            profile,
            home;
    @FXML
    private Pane empty;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String greeting = String.format("Welcome %s!", ((User) Session.getInstance().getValue("user")).getUsername());
        username.setText(greeting);
    }

    @FXML
    void logout(ActionEvent event) {
        JFXAlert<String> alert = Alerts.createAlert();
        JFXDialogLayout layout = Alerts.createLayout("Logout", "Are you sure?");
        layout.setStyle("-fx-border-color: grey;");
        JFXButton logout = new JFXButton("Logout");
        logout.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        logout.setOnAction(e -> {
            try {
                FlashMessages.getInstance().sendMessage(LoginController.class, "Successfully logged out!");
                ViewsManager.getActiveStage(event).setScene(ViewsManager.requestView("Login"));
                Session.getInstance().clearSession();
            } catch (IOException ex) {
                FlashMessages.getInstance().deleteMessage(LoginController.class);
                ex.printStackTrace();
            } finally {
                alert.hideWithAnimation();
            }
        });
        JFXButton cancel = new JFXButton("Cancel");
        cancel.setOnAction(e -> {
            alert.hideWithAnimation();
        });
        cancel.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        layout.setActions(cancel, logout);
        alert.setContent(layout);
        alert.showAndWait();
    }

    public JFXButton getProfile() { return profile; }

    public JFXButton getHome() { return home; }

    public Pane getEmpty() {
        return empty;
    }
}
