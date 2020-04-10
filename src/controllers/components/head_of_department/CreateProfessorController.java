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
import utils.Alerts;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public final class CreateProfessorController implements Initializable {
    @FXML
    private Pane user;
    @FXML
    private JFXTextField office;

    private CreateUserController controller;
    private Pane dashboard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final ViewsManager.DetailedComponent component =
                ViewsManager.requestDetailedComponent("user/CreateUser");
        controller = component.getLoader().getController();
        user.getChildren().setAll(component.getRoot());
    }

    @FXML
    public void createProfessor(ActionEvent event) throws SQLException {
        if(!validateInput().equals("")){
            Alerts.createSnackbar(
                    dashboard,
                    "Please fill of the fields first",
                    4, "#D00", "#FFF");
            return;
        }
        final Professor professor = new Professor(controller.getUser(), Integer.parseInt(office.getText()));
        Professors.getInstance().create(professor);
        Alerts.createSnackbar(dashboard, "Professor created successfully", 2);
    }

    private String validateInput() {
        final StringBuilder errors = new StringBuilder(controller.validateInput());
        if(office.getText().equals("")) errors.append("office ");
        return errors.toString();
    }

    public void setDashboard(Pane pane) {
        dashboard = pane;
    }
}
