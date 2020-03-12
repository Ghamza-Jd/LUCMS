package controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import controllers.components.SidePanelController;
import controllers.components.student.StudentSidePanelController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import models.User;
import services.Session;
import services.ViewsManager;

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
        container.getChildren().setAll(ViewsManager.requestComponent("News"));
        burger = new HamburgerBackArrowBasicTransition(hamburger);
        drawer.setSidePane(sidePanel);
        burger.setRate(-1);
        toggleBurger();
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> toggleBurger());
        sidePanelController.getHome().setOnAction(e ->
                container.getChildren().setAll(ViewsManager.requestComponent("News"))
        );
        sidePanelController.getProfile().setOnAction(e ->
                container.getChildren().setAll(ViewsManager.requestComponent("Profile"))
        );
        roleSetup(((User) Session.getInstance().getValue("user")).getRole());
    }

    private void roleSetup(String role) {
        if(role.equals("STUDENT")) {
            ViewsManager.DetailedComponent component =
                    ViewsManager.requestDetailedComponent("student/StudentSidePanel");
            StudentSidePanelController controller = component.getLoader().getController();
            controller.getMarks().setOnAction(e ->
                    container.getChildren().setAll(ViewsManager.requestComponent("student/Marks"))
            );
            sidePanelController.getEmpty().getChildren().setAll(component.getRoot());
        }
        if(role.equals("PROFESSOR")) {

        }
    }

    private void toggleBurger() {
        burger.setRate(-burger.getRate());
        burger.play();
        drawer.toggle();
    }
}
