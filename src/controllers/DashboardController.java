package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import controllers.components.head_of_department.CreateCourseController;
import controllers.components.head_of_department.CreateNews;
import controllers.components.head_of_department.CreateProfessorController;
import controllers.components.head_of_department.HodSidePanelController;
import controllers.components.professor.ProfessorSidePanelController;
import controllers.components.student.StudentSidePanelController;
import controllers.components.students_affair.CreateStudentController;
import controllers.components.students_affair.EnrollStudentsController;
import controllers.components.students_affair.SaSidePanelController;
import controllers.components.students_affair.ViewStudentsController;
import controllers.components.user.SidePanelController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import models.User;
import services.Session;
import services.ViewsManager;
import utils.Alerts;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public final class DashboardController implements Initializable {
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
    private JFXButton
            home,
            profile,
            logout
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
        drawer.setOnDrawerClosed(event -> {
            drawer.setVisible(false);
        });
        drawer.setVisible(false);
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

        home.setOnAction(sidePanelController.getHome().getOnAction());
        profile.setOnAction(sidePanelController.getProfile().getOnAction());
        logout.setOnAction(e -> {
            sidePanelController.logout(e);
        });

        final User user = (User) Session.getInstance().getValue("user");

        if (user != null && user.getGender().toLowerCase().equals("female"))
            sidePanelController.setProfilePic("/icons/femaleUser.png");

        if (user != null)
            roleSetup(user.getRole());
        else {
            try {
                ViewsManager.getActiveStage(new ActionEvent()).setScene(ViewsManager.requestView("Login"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void roleSetup(String role) {
        final String path = role.toLowerCase();
        if(role.equals("STUDENT")) {
            final ViewsManager.DetailedComponent component =
                    ViewsManager.requestDetailedComponent(path + "/StudentSidePanel");
            final StudentSidePanelController controller = component.getLoader().getController();
            controller.getMarks().setOnAction(e -> {
                container.getChildren().setAll(ViewsManager.requestComponent(path + "/Marks"));
                setTitleText("Marks");
            });
            controller.getCourses().setOnAction(e -> {
                container.getChildren().setAll(ViewsManager.requestComponent(path + "/StudentCourses"));
                setTitleText("My Courses");
            });
            sidePanelController.getEmpty().getChildren().setAll(component.getRoot());
        }

        if(role.equals("PROFESSOR")) {
            // TODO: Assign grades to students
            final ViewsManager.DetailedComponent component =
                    ViewsManager.requestDetailedComponent(path + "/ProfessorSidePanel");
            final ProfessorSidePanelController controller = component.getLoader().getController();
            controller.getCourses().setOnAction(e -> {
                container.getChildren().setAll(ViewsManager.requestComponent(path + "/ViewProfessorCourses"));
                setTitleText("My Courses");
            });
            controller.getGrades().setOnAction(e -> {
                container.getChildren().setAll(ViewsManager.requestComponent(path + "/AssignGrades"));
                setTitleText("Grades");
            });
            sidePanelController.getEmpty().getChildren().setAll(component.getRoot());
        }

        if(role.equals("STUDENT_AFFAIR")) {
            final ViewsManager.DetailedComponent component =
                    ViewsManager.requestDetailedComponent(path + "/SaSidePanel");
            final SaSidePanelController controller = component.getLoader().getController();
            controller.getCreateStudent().setOnAction(e -> {
                final ViewsManager.DetailedComponent comp =
                        ViewsManager.requestDetailedComponent(path + "/CreateStudent");
                final CreateStudentController cont = comp.getLoader().getController();
                cont.setDashboardPane(dashboard);
                container.getChildren().setAll(comp.getRoot());
                setTitleText("New Student");
            });
            controller.getEnrollStudent().setOnAction(e -> {
                final ViewsManager.DetailedComponent comp =
                        ViewsManager.requestDetailedComponent(path + "/EnrollStudents");
                final EnrollStudentsController cont = comp.getLoader().getController();
                cont.setDashboard(dashboard);
                container.getChildren().setAll(comp.getRoot());
                setTitleText("Enroll a Student");
            });
            controller.getViewStudents().setOnAction(e -> {
                final ViewsManager.DetailedComponent comp =
                        ViewsManager.requestDetailedComponent(path + "/ViewStudents");
                final ViewStudentsController cont = comp.getLoader().getController();
                cont.setDashboard(dashboard);
                container.getChildren().setAll(comp.getRoot());
                setTitleText("Students");
            });
            sidePanelController.getEmpty().getChildren().setAll(component.getRoot());
        }

        if(role.equals(("HEAD_OF_DEPARTMENT"))) {
            // TODO: View all course
            // TODO: View all professors
            final ViewsManager.DetailedComponent component =
                    ViewsManager.requestDetailedComponent(path + "/HodSidePanel");
            final HodSidePanelController controller = component.getLoader().getController();
            controller.getCreateProfessor().setOnAction(e -> {
                final ViewsManager.DetailedComponent comp =
                        ViewsManager.requestDetailedComponent(path + "/CreateProfessor");
                final CreateProfessorController cont = comp.getLoader().getController();
                cont.setDashboard(dashboard);
                container.getChildren().setAll(comp.getRoot());
                setTitleText("New Professor");
            });
            controller.getCreateCourse().setOnAction(e ->{
                final ViewsManager.DetailedComponent comp =
                        ViewsManager.requestDetailedComponent(path + "/CreateCourse");
                final CreateCourseController cont = comp.getLoader().getController();
                cont.setDashboard(dashboard);
                container.getChildren().setAll(comp.getRoot());
                setTitleText("New Course");
            });
            controller.getCreateNews().setOnAction(e ->{
                final ViewsManager.DetailedComponent comp =
                        ViewsManager.requestDetailedComponent(path + "/CreateNews");
                final CreateNews cont = comp.getLoader().getController();
                cont.setDashboard(dashboard);
                container.getChildren().setAll(comp.getRoot());
                setTitleText("Post News");
            });
            sidePanelController.getEmpty().getChildren().setAll(component.getRoot());
        }
        // TODO: Add admin role
    }

    private void toggleBurger() {
        if(burger.getRate() < 0) drawer.setVisible(true);
        burger.setRate(-burger.getRate());
        burger.play();
        drawer.toggle();
    }

    private void setTitleText(String text) { title.setText(text); }
}
