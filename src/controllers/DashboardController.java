package controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import controllers.components.head_of_department.CreateProfessorController;
import controllers.components.head_of_department.HodSidePanelController;
import controllers.components.professor.ProfessorSidePanelController;
import controllers.components.student.StudentSidePanelController;
import controllers.components.students_affair.SaSidePanelController;
import controllers.components.user.SidePanelController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import models.User;
import services.Session;
import services.ViewsManager;
import utils.Alerts;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private Text title;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Pane
            sidePanel,
            container,
            dashboard
    ;
    @FXML
    private SidePanelController sidePanelController;

    private HamburgerBackArrowBasicTransition burger;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTitleText("Home");
        Alerts.createSnackbar(dashboard, "Successfully logged in.", 1, "#0A0", "#FFF");
        container.getChildren().setAll(ViewsManager.requestComponent("news/News"));
        burger = new HamburgerBackArrowBasicTransition(hamburger);
        drawer.setSidePane(sidePanel);
        burger.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> toggleBurger());
        sidePanelController.getHome().setOnAction(e -> {
            container.getChildren().setAll(ViewsManager.requestComponent("news/News"));
            setTitleText("Home");
        });
        sidePanelController.getProfile().setOnAction(e -> {
            container.getChildren().setAll(ViewsManager.requestComponent("user/Profile"));
            setTitleText("My Profile");
        });
        roleSetup(((User) Session.getInstance().getValue("user")).getRole());
    }

    private void roleSetup(String role) {
        if(role.equals("STUDENT")) {
            ViewsManager.DetailedComponent component =
                    ViewsManager.requestDetailedComponent("student/StudentSidePanel");
            StudentSidePanelController controller = component.getLoader().getController();
            controller.getMarks().setOnAction(e -> {
                container.getChildren().setAll(ViewsManager.requestComponent("student/Marks"));
                setTitleText("Marks");
            });
            sidePanelController.getEmpty().getChildren().setAll(component.getRoot());
        }

        if(role.equals("PROFESSOR")) {
            // TODO: Give each student a grade
            ViewsManager.DetailedComponent component =
                    ViewsManager.requestDetailedComponent("professor/ProfessorSidePanel");
            ProfessorSidePanelController controller = component.getLoader().getController();
            controller.getCourses().setOnAction(e -> {
                container.getChildren().setAll(ViewsManager.requestComponent("professor/ViewProfessorCourses"));
                setTitleText("My Courses");
            });
            controller.getGrades().setOnAction(e -> {
                container.getChildren().setAll(ViewsManager.requestComponent("professor/AssignGrades"));
                setTitleText("Grades");
            });
            sidePanelController.getEmpty().getChildren().setAll(component.getRoot());
        }

        if(role.equals("STUDENT_AFFAIR")){
            // TODO: View all students
            ViewsManager.DetailedComponent component =
                    ViewsManager.requestDetailedComponent("students_affair/SaSidePanel");
            SaSidePanelController controller = component.getLoader().getController();
            controller.getCreateStudent().setOnAction(e -> {
                container.getChildren().setAll(ViewsManager.requestComponent("students_affair/CreateStudent"));
                setTitleText("New Student");
            });
            controller.getEnrollStudent().setOnAction(e -> {
                container.getChildren().setAll(ViewsManager.requestComponent("students_affair/EnrollStudents"));
                setTitleText("Enroll a Student");
            });
            sidePanelController.getEmpty().getChildren().setAll(component.getRoot());
        }

        if(role.equals(("HEAD_OF_DEPARTMENT"))){
            // TODO: View all course
            // TODO: View all doctors
            ViewsManager.DetailedComponent component =
                    ViewsManager.requestDetailedComponent(("head_of_department/HodSidePanel"));
            HodSidePanelController controller = component.getLoader().getController();
            controller.getCreateProfessor().setOnAction(e -> {
                ViewsManager.DetailedComponent comp =
                        ViewsManager.requestDetailedComponent("head_of_department/CreateProfessor");
                CreateProfessorController cont = comp.getLoader().getController();
                cont.setDashboard(dashboard);
                container.getChildren().setAll(comp.getRoot());
                setTitleText("New Professor");
            });
            controller.getCreateCourse().setOnAction(e ->{
                container.getChildren().setAll(ViewsManager.requestComponent("head_of_department/CreateCourse"));
                setTitleText("New Course");
            });
            controller.getCreateNews().setOnAction(e ->{
                container.getChildren().setAll(ViewsManager.requestComponent("head_of_department/CreateNews"));
                setTitleText("Post News");
            });
            sidePanelController.getEmpty().getChildren().setAll(component.getRoot());
        }

        // TODO: Add admin role
    }

    private void toggleBurger() {
        burger.setRate(-burger.getRate());
        burger.play();
        drawer.toggle();
    }

    private void setTitleText(String text) { title.setText(text); }
}
