package controllers.components.head_of_department;

import com.jfoenix.controls.JFXTextField;
import controllers.components.user.CreateUserController;
import controllers.repos.Professors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import models.Professor;
import services.ViewsManager;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateProfessorController implements Initializable {
    @FXML
    private Pane user;
    @FXML
    private JFXTextField office;

    private CreateUserController controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ViewsManager.DetailedComponent component =
                ViewsManager.requestDetailedComponent("user/CreateUser");
        controller = component.getLoader().getController();
        user.getChildren().setAll(component.getRoot());
    }

    @FXML
    public void createProfessor(ActionEvent event) throws SQLException {
        Professor professor = new Professor(controller.getUser(), Integer.parseInt(office.getText()));
        Professors.getInstance().create(professor);
    }
}
