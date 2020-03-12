package controllers.components.student;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;

import java.net.URL;
import java.util.ResourceBundle;

public class Marks implements Initializable {
    @FXML
    private JFXTreeTableView<MarkRow> table;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        double width = table.getPrefWidth();

        JFXTreeTableColumn<MarkRow, String> code = new JFXTreeTableColumn<>("Code");
        JFXTreeTableColumn<MarkRow, String> name = new JFXTreeTableColumn<>("Name");
        JFXTreeTableColumn<MarkRow, String> numberOfCredits = new JFXTreeTableColumn<>("Credits");
        JFXTreeTableColumn<MarkRow, String> language = new JFXTreeTableColumn<>("Language");
        JFXTreeTableColumn<MarkRow, String> grade = new JFXTreeTableColumn<>("Grade");

        code.setPrefWidth(width / 5 - 1);
        name.setPrefWidth(width / 5 - 1);
        numberOfCredits.setPrefWidth(width / 5 - 1);
        language.setPrefWidth(width / 5 - 1);
        grade.setPrefWidth(width / 5 - 1);

        code.setCellValueFactory(cell -> cell.getValue().getValue().code);
        name.setCellValueFactory(cell -> cell.getValue().getValue().name);
        numberOfCredits.setCellValueFactory(cell -> cell.getValue().getValue().numberOfCredits);
        language.setCellValueFactory(cell -> cell.getValue().getValue().language);
        grade.setCellValueFactory(cell -> cell.getValue().getValue().grade);

        ObservableList<MarkRow> rows = FXCollections.observableArrayList();
        /*
        TODO: Query the Enrollment and fill the List above ^
         */
        rows.add(new MarkRow("I2210", "Database", "5", "EN", "60"));
        rows.add(new MarkRow("I2209", "Prolog", "4", "EN", "38"));
        rows.add(new MarkRow("I2208", "Networking", "5", "EN", "70"));
        rows.add(new MarkRow("I2206", "Data structure", "5", "EN", "80"));
        rows.add(new MarkRow("I2210", "OOP", "5", "EN", "90"));

        final TreeItem<MarkRow> root = new RecursiveTreeItem<>(rows, RecursiveTreeObject::getChildren);
        table.getColumns().setAll(code, name, numberOfCredits, language, grade);
        table.setRoot(root);
        table.setShowRoot(false);
    }

    static class MarkRow extends RecursiveTreeObject<MarkRow> {
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
