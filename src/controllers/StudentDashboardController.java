package controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentDashboardController implements Initializable {
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private AnchorPane sidePanel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HamburgerBackArrowBasicTransition burger = new HamburgerBackArrowBasicTransition(hamburger);
        burger.setRate(-1);
        drawer.setSidePane(sidePanel);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            burger.setRate(-burger.getRate());
            burger.play();
            drawer.toggle();
        });
    }
}
