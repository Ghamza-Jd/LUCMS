package controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import controllers.components.professor.ProfessorSidePanelController;
import controllers.components.user.SidePanelController;
import controllers.components.head_of_department.HodSidePanelController;
import controllers.components.student.StudentSidePanelController;
import controllers.components.students_affair.SaSidePanelController;
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
        container.getChildren().setAll(ViewsManager.requestComponent("news/News"));
        burger = new HamburgerBackArrowBasicTransition(hamburger);
        drawer.setSidePane(sidePanel);
        burger.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> toggleBurger());
        sidePanelController.getHome().setOnAction(e ->
                container.getChildren().setAll(ViewsManager.requestComponent("news/News"))
        );
        sidePanelController.getProfile().setOnAction(e ->
                container.getChildren().setAll(ViewsManager.requestComponent("user/Profile"))
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
            ViewsManager.DetailedComponent component =
                    ViewsManager.requestDetailedComponent("professor/ProfessorSidePanel");
            ProfessorSidePanelController controller = component.getLoader().getController();
            controller.getCourses().setOnAction(e -> {
                container.getChildren().setAll(ViewsManager.requestComponent("professor/ViewProfessorCourses"));
            });
            controller.getGrades().setOnAction(e -> {
                container.getChildren().setAll(ViewsManager.requestComponent("professor/AssignGrades"));
            });
            sidePanelController.getEmpty().getChildren().setAll(component.getRoot());
        }

        if(role.equals("STUDENT_AFFAIR")){
            ViewsManager.DetailedComponent component =
                    ViewsManager.requestDetailedComponent("students_affair/SaSidePanel");
            SaSidePanelController controller = component.getLoader().getController();
            controller.getCreateStudent().setOnAction(e -> {
                container.getChildren().setAll(ViewsManager.requestComponent("students_affair/CreateStudent"));
            });
            controller.getEnrollStudent().setOnAction(e -> {
                container.getChildren().setAll(ViewsManager.requestComponent("students_affair/EnrollStudents"));
            });
            sidePanelController.getEmpty().getChildren().setAll(component.getRoot());
        }

        if(role.equals(("HEAD_OF_DEPARTMENT"))){
            ViewsManager.DetailedComponent component =
                    ViewsManager.requestDetailedComponent(("head_of_department/HodSidePanel"));
            HodSidePanelController controller = component.getLoader().getController();
            controller.getCreateProfessor().setOnAction(e -> {
                container.getChildren().setAll(ViewsManager.requestComponent("head_of_department/CreateProfessor"));
            });
            controller.getCreateCourse().setOnAction(e -> {
                container.getChildren().setAll(ViewsManager.requestComponent("head_of_department/CreateCourse"));
            });
            sidePanelController.getEmpty().getChildren().setAll(component.getRoot());
        }
    }

    private void toggleBurger() {
        burger.setRate(-burger.getRate());
        burger.play();
        drawer.toggle();
    }
}
