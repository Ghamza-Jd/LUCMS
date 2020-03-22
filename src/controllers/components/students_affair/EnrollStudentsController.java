package controllers.components.students_affair;

import com.jfoenix.controls.JFXTextField;
import controllers.components.cards.CourseCardController;
import controllers.components.cards.StudentCardController;
import controllers.repos.Courses;
import controllers.repos.Students;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import models.Course;
import models.Student;
import models.User;
import services.ViewsManager;
import utils.Alerts;

import java.sql.SQLException;

public class EnrollStudentsController {

    @FXML
    private JFXTextField
            fileNb,
            username,
            code,
            courseName;

    @FXML
    private Pane
            studentCard,
            courseCard
    ;

    @FXML
    void searchStudent(ActionEvent event) throws SQLException {
        if(fileNb.getText().equals("") && username.getText().equals("")) {
            Alerts.createDefaultAlert("Error", "Please enter either a file number or username").showAndWait();
            return;
        }
        Student student = null;
        if(!fileNb.getText().equals("")) {
            student = Students.getInstance().retrieveSingleByFileNb(Integer.parseInt(fileNb.getText()));
        }
        if(student == null && !username.getText().equals("")) {
            student = Students.getInstance().retrieveSingleByUsername(username.getText());
        }
        if(student == null) {
            Alerts.createDefaultAlert("Not Found", "No such a student with the provided file number and username").showAndWait();
            return;
        }
        fillStudentCard(student);
    }

    @FXML
    void searchCourse(ActionEvent event) throws SQLException {
        if(code.getText().equals("") && courseName.getText().equals("")) {
            Alerts.createDefaultAlert("Error", "Please enter either a course code or course name").showAndWait();
            return;
        }
        Course course = null;
        if(!code.getText().equals("")) {
            course = Courses.getInstance().retrieveCourseByCode(code.getText());
        }

        fillCourseCard(course);
    }

    @FXML
    void enroll(ActionEvent event) {

    }

    private void fillStudentCard(Student student) {
        String
                fileNb = String.valueOf(student.getId()),
                fullName = String.format("%s %s %s",
                        student.getUser().getFirstName(),
                        student.getUser().getMiddleName(),
                        student.getUser().getLastName()),
                major = student.getMajor(),
                birth = student.getUser().getDateOfBirth()
        ;
        ViewsManager.DetailedComponent component =
                ViewsManager.requestDetailedComponent("cards/studentCard");
        StudentCardController controller = component.getLoader().getController();
        controller.getFileNb().setText(fileNb);
        controller.getName().setText(fullName);
        controller.getMajor().setText(major);
        controller.getBirth().setText(birth);
        studentCard.getChildren().setAll(component.getRoot());
    }

    private void fillCourseCard(Course course) {
        User user = course.getProfessor().getUser();
        String
                code = course.getCode(),
                name = course.getName(),
                lang = course.getLanguage(),
                prof = String.format("%s %s %s",
                        user.getFirstName(),
                        user.getMiddleName(),
                        user.getLastName())
        ;
        ViewsManager.DetailedComponent component =
                ViewsManager.requestDetailedComponent("cards/courseCard");
        CourseCardController controller = component.getLoader().getController();
        controller.getCode().setText(code);
        controller.getName().setText(name);
        controller.getLang().setText(lang);
        controller.getProf().setText(prof);
        courseCard.getChildren().setAll(component.getRoot());
    }
}
