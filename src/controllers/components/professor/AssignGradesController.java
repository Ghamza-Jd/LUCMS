package controllers.components.professor;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controllers.components.students_affair.ViewStudentsController;
import controllers.components.user.ActionsController;
import controllers.repos.Courses;
import controllers.repos.Enrollment;
import controllers.repos.Students;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import models.Course;
import models.Enroll;
import models.Professor;
import models.Student;
import services.Session;
import services.ViewsManager;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AssignGradesController implements Initializable {
    @FXML
    private JFXTreeTableView<AssignmentRow> table;
    @FXML
    private JFXComboBox<String> courses;
    @FXML
    private JFXTextField search;

    private ObservableList<AssignmentRow> rows;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rows = FXCollections.observableArrayList();
        double width = table.getPrefWidth();

        JFXTreeTableColumn<AssignmentRow, String>
                fileNumber  = new JFXTreeTableColumn<>("File #"),
                fullName    = new JFXTreeTableColumn<>("Full Name"),
                grade       = new JFXTreeTableColumn<>("Grade"),
                actions     = new JFXTreeTableColumn<>("Actions")
        ;

        fileNumber.setPrefWidth(width / 4 - 1);
        fullName.setPrefWidth(width / 4 - 1);
        grade.setPrefWidth(width / 4 - 1);
        actions.setPrefWidth(width / 4 - 1);

        fileNumber.setResizable(false);
        fullName.setResizable(false);
        grade.setResizable(false);
        actions.setResizable(false);

        fileNumber.setCellValueFactory(cell -> cell.getValue().getValue().fileNumber);
        fullName.setCellValueFactory(cell -> cell.getValue().getValue().fullName);
        grade.setCellValueFactory(cell -> cell.getValue().getValue().grade);

        Callback<TreeTableColumn<AssignmentRow, String>, TreeTableCell<AssignmentRow, String>> actionsCell =
                new Callback<TreeTableColumn<AssignmentRow, String>, TreeTableCell<AssignmentRow, String>>() {
                    @Override
                    public TreeTableCell<AssignmentRow, String> call(TreeTableColumn<AssignmentRow, String> param) {
                        return new TreeTableCell<AssignmentRow, String>() {
                            ViewsManager.DetailedComponent component =
                                    ViewsManager.requestDetailedComponent("user/Actions");
                            ActionsController controller = component.getLoader().getController();
                            {
                                controller.getEdit().setOnAction(e -> { });
                                controller.getDetails().setOnAction(e -> { });
                                controller.getDelete().setVisible(false);
                                controller.getDelete().setPrefWidth(0);
                            }
                            final Parent root = component.getRoot();
                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if(empty) {
                                    setGraphic(null);
                                }
                                else {
                                    setGraphic(root);
                                    setAlignment(Pos.CENTER);
                                }
                                setText(null);
                            }
                        };
                    }
                }
        ;
        actions.setCellFactory(actionsCell);

        courses.getSelectionModel().selectedItemProperty().addListener((option, oldVal, newVale) -> {
            try {
                String code = newVale.split(" ")[0];
                populateTable("Info3350");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        try {
            populateTable("Info3350");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        populateComboBox();

        final TreeItem<AssignmentRow> root = new RecursiveTreeItem<>(rows, RecursiveTreeObject::getChildren);
        table.getColumns().setAll(fileNumber, fullName, grade, actions);
        table.setRoot(root);
        table.setShowRoot(false);
    }

    private void populateTable(String courseCode) throws SQLException {
        if(courseCode.equals("")) return;
        rows.clear();
        ArrayList<Enroll> enrolls = Enrollment.getInstance().retrieveStudentsByCourseCode(courseCode);
        for(Enroll enroll : enrolls) {
            rows.add(
                    new AssignmentRow(
                            String.valueOf(enroll.getStudent().getId()),
                            String.format(
                                    "%s %s %s",
                                    enroll.getStudent().getUser().getFirstName(),
                                    enroll.getStudent().getUser().getMiddleName(),
                                    enroll.getStudent().getUser().getLastName()
                            ),
                            String.valueOf(enroll.getGrade())
                    )
            );
        }
    }

    private void populateComboBox() {
        ArrayList<String> coursesInfo = new ArrayList<>();
        int prof_id = ((Professor) Session.getInstance().getValue("professor")).getId();
        try {
            ArrayList<Course> courses = Courses.getInstance().retrieveProfessorCourses(prof_id);
            for(Course course : courses){
                coursesInfo.add(course.printCourse());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> coursesItems = FXCollections.observableArrayList(coursesInfo);
        courses.setItems(coursesItems);
    }

    private static class AssignmentRow extends RecursiveTreeObject<AssignmentRow> {
        StringProperty fileNumber;
        StringProperty fullName;
        StringProperty grade;

        public AssignmentRow(String fileNumber, String fullName, String grade) {
            this.fileNumber = new SimpleStringProperty(fileNumber);
            this.fullName = new SimpleStringProperty(fullName);
            this.grade = new SimpleStringProperty(grade);
        }
    }
}
