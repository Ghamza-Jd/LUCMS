package controllers.components.professor;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewProfessorCoursesController implements Initializable {
    @FXML
    private JFXListView<String> listView;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String [] c = {"Web dev", "Node js", "Another course"};
        ObservableList<String> courses = FXCollections.observableArrayList(c);
        listView.setItems(courses);
    }
}
