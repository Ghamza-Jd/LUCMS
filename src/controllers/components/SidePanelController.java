package controllers.components;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import models.User;
import services.Session;
import services.ViewsManager;

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
    void logout(ActionEvent event) throws IOException {
        ViewsManager.getActiveStage(event).setScene(ViewsManager.requestView("Login"));
        Session.getInstance().clearSession();
    }

    public JFXButton getProfile() { return profile; }

    public JFXButton getHome() { return home; }

    public Pane getEmpty() {
        return empty;
    }
}
