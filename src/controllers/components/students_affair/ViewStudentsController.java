package controllers.components.students_affair;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controllers.components.user.ActionsController;
import controllers.components.user.ProfileController;
import controllers.repos.Students;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.util.Callback;
import models.Student;
import services.ViewsManager;
import utils.Alerts;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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

        Callback<TreeTableColumn<StudentRow, String>, TreeTableCell<StudentRow, String>> actionsCell =
                new Callback<TreeTableColumn<StudentRow, String>, TreeTableCell<StudentRow, String>>() {
                    @Override
                    public TreeTableCell<StudentRow, String> call(TreeTableColumn<StudentRow, String> param) {
                        return new TreeTableCell<StudentRow, String>() {
                            ViewsManager.DetailedComponent component =
                                    ViewsManager.requestDetailedComponent("user/Actions");
                            ActionsController controller = component.getLoader().getController();
                            {
                                controller.getEdit().setOnAction(e -> {});
                                controller.getDetails().setOnAction(e -> {
                                    try {
                                        String id  = this.getTreeTableRow().getTreeItem().getValue().fileNumber.getValue();
                                        Student student = Students.getInstance().retrieveSingleByFileNb(Integer.parseInt(id));
                                        displayStudentDetails(student);
                                    } catch (SQLException ex) {
                                        ex.printStackTrace();
                                    }
                                });
                                controller.getDelete().setOnAction(e -> {});
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

        ObservableList<StudentRow> rows = FXCollections.observableArrayList();

        try {
            ArrayList<Student> students = Students.getInstance().retrieveAllStudents();
            for(Student s : students) {
                rows.add(
                        new StudentRow(
                                String.valueOf(s.getId()),
                                String.format(
                                        "%s %s %s",
                                        s.getUser().getFirstName(),
                                        s.getUser().getMiddleName(),
                                        s.getUser().getLastName()),
                                s.getUser().getUsername()
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        final TreeItem<StudentRow> root = new RecursiveTreeItem<>(rows, RecursiveTreeObject::getChildren);
        table.getColumns().setAll(fileNumber, fullName, username, actions);
        table.setRoot(root);
        table.setShowRoot(false);
    }

    private void displayStudentDetails(Student student) {
        JFXAlert<String> alert = Alerts.createAlert();
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setStyle("-fx-border-color: grey;");

        JFXButton exit = new JFXButton("Exit");
        exit.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        exit.setOnAction(e -> alert.hideWithAnimation());
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        HBox heading = new HBox(new Label("Details"), region, exit);
        layout.setHeading(heading);

        ViewsManager.DetailedComponent component = ViewsManager.requestDetailedComponent("user/Profile");
        ProfileController controller = component.getLoader().getController();
        controller.fillFields(student.getUser());

        layout.setBody(component.getRoot());
        alert.setContent(layout);
        alert.showAndWait();
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
