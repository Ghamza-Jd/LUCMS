package controllers.components.professor;

import com.jfoenix.controls.JFXListView;
import controllers.repos.Courses;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import models.Course;
import models.Professor;
import services.Session;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public final class ViewProfessorCoursesController implements Initializable {
    @FXML
    private JFXListView<String> listView;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setCellFactory(stringListView -> new CenteredListViewCell());
        final ArrayList<String> coursesInfo = new ArrayList<>();
        final int prof_id = ((Professor) Session.getInstance().getValue("professor")).getId();
        try {
            final ArrayList<Course> courses = Courses.getInstance().retrieveProfessorCourses(prof_id);
            for(Course course : courses){
                coursesInfo.add(course.printCourse());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        final ObservableList<String> coursesItems = FXCollections.observableArrayList(coursesInfo);
        listView.setItems(coursesItems);
    }

    static final class CenteredListViewCell extends ListCell<String> {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                final HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER);
                hBox.setPrefHeight(20);

                final Label label = new Label(item);
                label.setAlignment(Pos.CENTER);
                label.setFont(Font.font("System", FontWeight.NORMAL, 14));

                hBox.getChildren().add(label);
                setGraphic(hBox);
            }
        }
    }
}
