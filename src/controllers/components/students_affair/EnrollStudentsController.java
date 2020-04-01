package controllers.components.students_affair;

import com.jfoenix.controls.JFXTextField;
import controllers.components.cards.CourseCardController;
import controllers.components.cards.StudentCardController;
import controllers.repos.Courses;
import controllers.repos.Enrollment;
import controllers.repos.Students;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import models.Course;
import models.Enroll;
import models.Student;
import models.User;
import services.ViewsManager;
import utils.Alerts;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public final class EnrollStudentsController implements Initializable {

    @FXML
    private JFXTextField
            fileNb,
            username,
            code,
            courseName
    ;

    @FXML
    private Pane
            studentCard,
            courseCard
    ;

    private Student student;
    private Course course;
    private Pane dashboard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        student = null;
        courseName = null;
    }

    public void setDashboard(Pane dashboard) {
        this.dashboard = dashboard;
    }

    @FXML
    private void searchStudent(ActionEvent event) throws SQLException {
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
            Alerts.createDefaultAlert("Not Found", "No such a student with the provided file number or username").showAndWait();
            return;
        }
        fillStudentCard(student);
    }

    @FXML
    private void searchCourse(ActionEvent event) throws SQLException {
        if(code.getText().equals("") && courseName.getText().equals("")) {
            Alerts.createDefaultAlert("Error", "Please enter either a course code or course name").showAndWait();
            return;
        }
        Course course = null;
        if(!code.getText().equals("")) {
            course = Courses.getInstance().retrieveCourseByCode(code.getText());
        }
        if(course == null && !courseName.getText().equals("")) {
            course = Courses.getInstance().retrieveCourseByName(courseName.getText());
        }
        if(course == null) {
            Alerts.createDefaultAlert("Not Found", "No such a course with the provided code or name").showAndWait();
            return;
        }
        fillCourseCard(course);
    }

    @FXML
    private void enroll(ActionEvent event) throws SQLException {
        if (student == null || course == null) return;
        final Enroll e = new Enroll(student, course);
        Enrollment.getInstance().create(e);
        Alerts.createSnackbar(dashboard, student.getUser().getUsername() + " enrolled to " + course.getName(), 2);
    }

    private void fillStudentCard(Student student) {
        this.student = student;
        final String
                fileNb = String.valueOf(student.getId()),
                fullName = String.format("%s %s %s",
                        student.getUser().getFirstName(),
                        student.getUser().getMiddleName(),
                        student.getUser().getLastName()),
                major = student.getMajor(),
                birth = student.getUser().getDateOfBirth()
        ;
        final ViewsManager.DetailedComponent component =
                ViewsManager.requestDetailedComponent("cards/StudentCard");
        final StudentCardController controller = component.getLoader().getController();
        controller.getFileNb().setText(fileNb);
        controller.getName().setText(fullName);
        controller.getMajor().setText(major);
        controller.getBirth().setText(birth);
        studentCard.getChildren().setAll(component.getRoot());
    }

    private void fillCourseCard(Course course) {
        this.course = course;
        final User user = course.getProfessor().getUser();
        final String
                code = course.getCode(),
                name = course.getName(),
                lang = course.getLanguage(),
                prof = String.format("%s %s %s",
                        user.getFirstName(),
                        user.getMiddleName(),
                        user.getLastName())
        ;
        final ViewsManager.DetailedComponent component =
                ViewsManager.requestDetailedComponent("cards/CourseCard");
        final CourseCardController controller = component.getLoader().getController();
        controller.getCode().setText(code);
        controller.getName().setText(name);
        controller.getLang().setText(lang);
        controller.getProf().setText(prof);
        courseCard.getChildren().setAll(component.getRoot());
    }
}
