package controllers.components.student;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controllers.repos.Enrollment;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import models.Course;
import models.Enroll;
import models.Student;
import services.Session;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public final class Marks implements Initializable {
    @FXML
    private JFXTreeTableView<MarkRow> table;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final double width = table.getPrefWidth();

        final JFXTreeTableColumn<MarkRow, String>
                code        = new JFXTreeTableColumn<>("Code"),
                name        = new JFXTreeTableColumn<>("Name"),
                nbCredits   = new JFXTreeTableColumn<>("Credits"),
                language    = new JFXTreeTableColumn<>("Language"),
                grade       = new JFXTreeTableColumn<>("Grade")
        ;

        code.setPrefWidth(width / 5 - 1);
        name.setPrefWidth(width / 5 - 1);
        nbCredits.setPrefWidth(width / 5 - 1);
        language.setPrefWidth(width / 5 - 1);
        grade.setPrefWidth(width / 5 - 1);

        code.setResizable(false);
        name.setResizable(false);
        nbCredits.setResizable(false);
        language.setResizable(false);
        grade.setResizable(false);

        code.setCellValueFactory(cell -> cell.getValue().getValue().code);
        name.setCellValueFactory(cell -> cell.getValue().getValue().name);
        nbCredits.setCellValueFactory(cell -> cell.getValue().getValue().numberOfCredits);
        language.setCellValueFactory(cell -> cell.getValue().getValue().language);
        grade.setCellValueFactory(cell -> cell.getValue().getValue().grade);

        final ObservableList<MarkRow> rows = FXCollections.observableArrayList();
        try {
            final ArrayList<Enroll> enrolls =
                    Enrollment
                            .getInstance()
                            .retrieveAllGradesByStudentsId(
                                    ((Student) Session
                                            .getInstance()
                                            .getValue("student")
                                    ).getId()
                            )
                    ;
            for(Enroll enroll : enrolls) {
                rows.add(
                        new MarkRow(
                                enroll.getCourse().getCode(),
                                enroll.getCourse().getName(),
                                String.valueOf(enroll.getCourse().getName()),
                                enroll.getCourse().getLanguage(),
                                String.valueOf(enroll.getGrade())
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        final TreeItem<MarkRow> root = new RecursiveTreeItem<>(rows, RecursiveTreeObject::getChildren);
        table.getColumns().setAll(code, name, nbCredits, language, grade);
        table.setRoot(root);
        table.setShowRoot(false);
    }

    private static class MarkRow extends RecursiveTreeObject<MarkRow> {
        StringProperty code;
        StringProperty name;
        StringProperty numberOfCredits;
        StringProperty language;
        StringProperty grade;

        public MarkRow(String code, String name, String numberOfCredits, String language, String grade) {
            this.code = new SimpleStringProperty(code);
            this.name = new SimpleStringProperty(name);
            this.numberOfCredits = new SimpleStringProperty(numberOfCredits);
            this.language = new SimpleStringProperty(language);
            this.grade = new SimpleStringProperty(grade);
        }
    }
}
