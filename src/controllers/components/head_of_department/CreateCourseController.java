package controllers.components.head_of_department;

import com.jfoenix.controls.JFXButton;
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
import models.Course;
import models.Professor;
import services.IModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CreateCourseController implements Initializable {
    @FXML
    private JFXTextField
            code,
            name,
            lang;
    @FXML
    private JFXComboBox<String>
            credits,
            prof;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> credit = FXCollections.observableArrayList(Constants.NUMBER_OF_CREDITS);
        ObservableList<String> profs = FXCollections.observableArrayList();
        credits.setItems(credit);
        try {
            List<IModel> professors = Professors.getInstance().retrieveAll();
            for(IModel p : professors) {
                Professor professor = (Professor) p;
                profs.add(professor.getUser().getUsername());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        prof.setItems(profs);
    }

    @FXML
    void createCourse(ActionEvent event) throws SQLException {
        Course course = new Course(
                code.getText(),
                name.getText(),
                Integer.parseInt(credits.getValue()),
                lang.getText(),
                Professors.getInstance().retrieveByUsername(prof.getValue())
        );
        Courses.getInstance().create(course);
    }
}
