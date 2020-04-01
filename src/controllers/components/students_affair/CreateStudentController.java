package controllers.components.students_affair;

import com.jfoenix.controls.JFXComboBox;
import controllers.components.user.CreateUserController;
import controllers.repos.Students;
import global.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import models.Student;
import services.ViewsManager;
import utils.Alerts;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public final class CreateStudentController implements Initializable {
    @FXML
    private Pane user;
    @FXML
    private JFXComboBox<String> major;

    private CreateUserController controller;

    private Pane dashboard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final ViewsManager.DetailedComponent component =
                ViewsManager.requestDetailedComponent("user/CreateUser");
        controller = component.getLoader().getController();
        user.getChildren().setAll(component.getRoot());
        final ObservableList<String> majors = FXCollections.observableArrayList(Constants.MAJORS);
        major.setItems(majors);
    }

    @FXML
    public void createStudent(ActionEvent event) throws SQLException {
        final Student student = new Student(controller.getUser(), major.getValue());
        Students.getInstance().create(student);
        Alerts.createSnackbar(dashboard, "Student Created Successfully", 2);
    }

    public void setDashboardPane(Pane pane) {
        dashboard = pane;
    }
}
