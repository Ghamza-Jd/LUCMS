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
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
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

import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class AssignGradesController implements Initializable {
    @FXML
    private JFXTreeTableView<AssignmentRow> table;
    @FXML
    private JFXComboBox<String> courses;
    @FXML
    private JFXTextField search;

    private ObservableList<AssignmentRow> rows;
    private ObservableList<AssignmentRow> displayedRows;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rows = FXCollections.observableArrayList();
        displayedRows = FXCollections.observableArrayList();
        double width = table.getPrefWidth();

        final JFXTreeTableColumn<AssignmentRow, String>
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

        final Callback<TreeTableColumn<AssignmentRow, String>, TreeTableCell<AssignmentRow, String>> actionsCell =
                new Callback<>() {
                    @Override
                    public TreeTableCell<AssignmentRow, String> call(TreeTableColumn<AssignmentRow, String> param) {
                        return new TreeTableCell<>() {
                            final ViewsManager.DetailedComponent component =
                                    ViewsManager.requestDetailedComponent("user/Actions");
                            final ActionsController controller = component.getLoader().getController();

                            {
                                controller.getEdit().setOnAction(e -> {
                                });
                                controller.getDetails().setOnAction(e -> {
                                });
                                controller.getDelete().setVisible(false);
                                controller.getDelete().setPrefWidth(0);
                            }

                            final Parent root = component.getRoot();

                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
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
                final String code = newVale.split(" ")[0];
                populateTable(code);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        search.setOnKeyReleased(e -> searchTable(search.getText()));

        populateComboBox();

        final TreeItem<AssignmentRow> root = new RecursiveTreeItem<>(displayedRows, RecursiveTreeObject::getChildren);
        table.getColumns().setAll(fileNumber, fullName, grade, actions);
        table.setRoot(root);
        table.setShowRoot(false);
    }

    private void populateTable(String courseCode) throws SQLException {
        if(courseCode.equals("")) return;
        rows.clear();
        final ArrayList<Enroll> enrolls = Enrollment.getInstance().retrieveStudentsByCourseCode(courseCode);
        if (enrolls != null) {
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
        displayedRows.clear();
        displayedRows.setAll(rows);
    }

    private void populateComboBox() {
        final int prof_id = ((Professor) Session.getInstance().getValue("professor")).getId();
        final ArrayList<String> coursesInfo = new ArrayList<>();
        try {
            final ArrayList<Course> courses = Courses.getInstance().retrieveProfessorCourses(prof_id);
            for(Course course : courses){
                coursesInfo.add(course.printCourse());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        final ObservableList<String> coursesItems = FXCollections.observableArrayList(coursesInfo);
        courses.setItems(coursesItems);
    }

    private void searchTable(String regex) {
        final ObservableList<AssignmentRow> newRows = FXCollections.observableArrayList();
        for(AssignmentRow assignmentRow : rows) {
            final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            final Matcher
                    fullNameMatcher = pattern.matcher(assignmentRow.fullName.getValue()),
                    fileNumberMatcher = pattern.matcher(assignmentRow.fileNumber.getValue()),
                    gradeMatcher = pattern.matcher(assignmentRow.grade.getValue())
            ;
            if(fullNameMatcher.find() || fileNumberMatcher.find() || gradeMatcher.find()) newRows.add(assignmentRow);
        }
        displayedRows.clear();
        displayedRows.setAll(newRows);
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
