package controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import controllers.components.SidePanelController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import services.ViewsManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Pane sidePanel;
    @FXML
    private Pane container;
    @FXML
    private SidePanelController sidePanelController;

    private HamburgerBackArrowBasicTransition burger;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            container.getChildren().setAll(ViewsManager.requestComponent("News"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        burger = new HamburgerBackArrowBasicTransition(hamburger);
        drawer.setSidePane(sidePanel);
        burger.setRate(-1);
        toggleBurger();

        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> toggleBurger());

        sidePanelController.getHome().setOnAction(e -> {
            try {
                container.getChildren().setAll(ViewsManager.requestComponent("News"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        sidePanelController.getProfile().setOnAction(e -> {
            try {
                container.getChildren().setAll(ViewsManager.requestComponent("Profile"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        sidePanelController.getMarks().setOnAction(e -> {
            try {
                container.getChildren().setAll(ViewsManager.requestComponent("student/Marks"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
    private void toggleBurger() {
        burger.setRate(-burger.getRate());
        burger.play();
        drawer.toggle();
    }
}
