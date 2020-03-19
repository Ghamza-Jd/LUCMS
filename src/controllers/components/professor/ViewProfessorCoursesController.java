package controllers.components.professor;

import com.jfoenix.controls.JFXListView;
import controllers.repos.Courses;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import models.Course;
import models.Professor;
import services.Session;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewProfessorCoursesController implements Initializable {
    @FXML
    private JFXListView<String> listView;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String [] c = {"Web dev", "Node js", "Another course"};
        ArrayList<String> ss = new ArrayList<>();
        int prof_id = ((Professor) Session.getInstance().getValue("professor")).getId();
        try {
            ArrayList<Course> courses = Courses.getInstance().retrieveProfessorCourses(prof_id);
            for(Course course : courses){
                ss.add(course.printCourse());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> kkk = FXCollections.observableArrayList(ss);
        listView.setItems(kkk);
    }
}
