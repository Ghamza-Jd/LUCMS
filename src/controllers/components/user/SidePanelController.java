package controllers.components.user;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import controllers.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML
    private ImageView profilePic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String greeting = String.format("Welcome %s!", ((User) Session.getInstance().getValue("user")).getUsername());
        username.setText(greeting);
    }

    @FXML
    public void logout(ActionEvent event) {
        final JFXAlert<String> alert = Alerts.createAlert();
        final JFXDialogLayout layout = Alerts.createLayout("Logout", "Are you sure?");

        final JFXButton logout = new JFXButton("");
        final ImageView logoutIcon = new ImageView(new Image("/icons/ok.png"));

        logoutIcon.setFitWidth(32);
        logoutIcon.setFitHeight(32);
        logout.setGraphic(logoutIcon);

        final JFXButton cancel = new JFXButton("");
        final ImageView cancelIcon = new ImageView(new Image("/icons/close.png"));
        cancelIcon.setFitWidth(32);
        cancelIcon.setFitHeight(32);
        cancel.setGraphic(cancelIcon);

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

        cancel.setOnAction(e -> {
            alert.hideWithAnimation();
        });

        layout.setActions(cancel, logout);
        alert.setContent(layout);
        alert.showAndWait();
    }

    public JFXButton getProfile() { return profile; }

    public JFXButton getHome() { return home; }

    public Pane getEmpty() {
        return empty;
    }

    public void setProfilePic(String path) {
        profilePic.setImage(new Image(path));
    }
}
