package controllers.components.student;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentCoursesController implements Initializable {
    @FXML
    private TreeTableView<CourseRow> table;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        double width = table.getPrefWidth();

        JFXTreeTableColumn<CourseRow, String>
                code        = new JFXTreeTableColumn<>("Code"),
                name        = new JFXTreeTableColumn<>("Name"),
                professor   = new JFXTreeTableColumn<>("Professor"),
                credits     = new JFXTreeTableColumn<>("Credits");

        code.setPrefWidth(width / 4 - 1);
        name.setPrefWidth(width / 4 - 1);
        professor.setPrefWidth(width / 4 - 1);
        credits.setPrefWidth(width / 4 - 1);

        code.setResizable(false);
        name.setResizable(false);
        professor.setResizable(false);
        credits.setResizable(false);

        code.setCellValueFactory(cell -> cell.getValue().getValue().code);
        name.setCellValueFactory(cell -> cell.getValue().getValue().name);
        professor.setCellValueFactory(cell -> cell.getValue().getValue().professor);
        credits.setCellValueFactory(cell -> cell.getValue().getValue().credits);

        ObservableList<CourseRow> rows = FXCollections.observableArrayList();
        /*
        TODO: Query the Enrollment and fill the List above ^
         */
        rows.add(new CourseRow("I2210", "Database", "5", "EN"));
        rows.add(new CourseRow("I2209", "Prolog", "4", "EN"));
        rows.add(new CourseRow("I2208", "Networking", "5", "EN"));
        rows.add(new CourseRow("I2206", "Data structure", "5", "EN"));
        rows.add(new CourseRow("I2210", "OOP", "5", "EN"));

        final TreeItem<CourseRow> root = new RecursiveTreeItem<>(rows, RecursiveTreeObject::getChildren);
        table.getColumns().setAll(code, name, professor, credits);
        table.setRoot(root);
        table.setShowRoot(false);
    }

    private static class CourseRow extends RecursiveTreeObject<StudentCoursesController.CourseRow> {
        StringProperty code;
        StringProperty name;
        StringProperty professor;
        StringProperty credits;

        public CourseRow(String code, String name, String professor, String credits) {
            this.code = new SimpleStringProperty(code);
            this.name = new SimpleStringProperty(name);
            this.professor = new SimpleStringProperty(professor);
            this.credits = new SimpleStringProperty(credits);
        }
    }
}
