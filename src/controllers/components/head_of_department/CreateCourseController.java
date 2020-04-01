package controllers.components.head_of_department;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controllers.repos.Courses;
import controllers.repos.Professors;
import global.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import models.Course;
import models.Professor;
import services.IModel;
import utils.Alerts;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public final class CreateCourseController implements Initializable {
    @FXML
    private JFXTextField
            code,
            name
    ;

    @FXML
    private JFXComboBox<String>
            credits,
            lang,
            prof
    ;

    private Pane dashboard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final ObservableList<String>
                credit = FXCollections.observableArrayList(Constants.NUMBER_OF_CREDITS),
                languages = FXCollections.observableArrayList(Constants.LANGUAGES),
                profs = FXCollections.observableArrayList();
        try {
            final List<IModel> professors = Professors.getInstance().retrieveAll();
            for(IModel p : professors) {
                Professor professor = (Professor) p;
                profs.add(professor.getUser().getUsername());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        credits.setItems(credit);
        lang.setItems(languages);
        prof.setItems(profs);
    }

    @FXML
    void createCourse(ActionEvent event) throws SQLException {
        final Course course = new Course(
                code.getText(),
                name.getText(),
                Integer.parseInt(credits.getValue()),
                lang.getValue(),
                Professors.getInstance().retrieveByUsername(prof.getValue())
        );
        Courses.getInstance().create(course);
        Alerts.createSnackbar(dashboard, "Course Created", 2);
    }

    public void setDashboard(Pane pane) {
        dashboard = pane;
    }
}
