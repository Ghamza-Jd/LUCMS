package controllers.components.students_affair;

import com.jfoenix.controls.JFXButton;
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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import services.ViewsManager;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewStudentsController implements Initializable {
    @FXML
    private JFXTreeTableView<StudentRow> table;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        double width = table.getPrefWidth();

        JFXTreeTableColumn<StudentRow, String>
                fileNumber        = new JFXTreeTableColumn<>("File #"),
                fullName          = new JFXTreeTableColumn<>("Full Name"),
                username          = new JFXTreeTableColumn<>("Username"),
                actions           = new JFXTreeTableColumn<>("Actions")
        ;

        fileNumber.setPrefWidth(width / 4 - 1);
        fullName.setPrefWidth(width / 4 - 1);
        username.setPrefWidth(width / 4 - 1);
        actions.setPrefWidth(width / 4 - 1);

        fileNumber.setResizable(false);
        fullName.setResizable(false);
        username.setResizable(false);
        actions.setResizable(false);

        fileNumber.setCellValueFactory(cell -> cell.getValue().getValue().fileNumber);
        fullName.setCellValueFactory(cell -> cell.getValue().getValue().fullName);
        username.setCellValueFactory(cell -> cell.getValue().getValue().username);

        Callback<TreeTableColumn<StudentRow, String>, TreeTableCell<StudentRow, String>> cellFactory =
                new Callback<TreeTableColumn<StudentRow, String>, TreeTableCell<StudentRow, String>>() {
                    @Override
                    public TreeTableCell<StudentRow, String> call(TreeTableColumn<StudentRow, String> param) {
                        return new TreeTableCell<StudentRow, String>() {
                            final Parent actions = ViewsManager.requestComponent("user/Actions");
                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if(empty) {
                                    setGraphic(null);
                                }
                                else {
                                    setGraphic(actions);
                                    setAlignment(Pos.CENTER);
                                }
                                setText(null);
                            }
                        };
                    }
                }
        ;
        actions.setCellFactory(cellFactory);

        ObservableList<StudentRow> rows = FXCollections.observableArrayList();

        rows.add(new StudentRow("86611", "Hamza Abdallah Jadid", "hamzajd"));
        rows.add(new StudentRow("87259", "Noura Amine Joudieh", "razarizo"));

        final TreeItem<StudentRow> root = new RecursiveTreeItem<>(rows, RecursiveTreeObject::getChildren);
        table.getColumns().setAll(fileNumber, fullName, username, actions);
        table.setRoot(root);
        table.setShowRoot(false);
    }

    private static class StudentRow extends RecursiveTreeObject<StudentRow> {
        StringProperty fileNumber;
        StringProperty fullName;
        StringProperty username;
        JFXButton edit;

        public StudentRow(String fileNumber, String fullName, String username) {
            this.fileNumber = new SimpleStringProperty(fileNumber);
            this.fullName = new SimpleStringProperty(fullName);
            this.username = new SimpleStringProperty(username);
            edit = new JFXButton("edit");
        }
    }
}
