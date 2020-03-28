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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
                                controller.getEdit().setOnAction(e -> {
                                    try {
                                        String id  = this.getTreeTableRow().getTreeItem().getValue().fileNumber.getValue();
                                        Student student = Students.getInstance().retrieveSingleByFileNb(Integer.parseInt(id));
                                        displayStudentEdit(student);
                                    } catch (SQLException ex) {
                                        ex.printStackTrace();
                                    }
                                });
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
        final JFXAlert<String> alert = Alerts.createAlert();
        final JFXDialogLayout layout = new JFXDialogLayout();

        final JFXButton exit = new JFXButton("Exit");
        final Region region = new Region();
        final HBox heading = new HBox(new Label("Details"), region, exit);

        exit.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        exit.setOnAction(e -> alert.hideWithAnimation());

        HBox.setHgrow(region, Priority.ALWAYS);

        layout.setStyle("-fx-border-color: grey;");
        layout.setHeading(heading);

        ViewsManager.DetailedComponent component = ViewsManager.requestDetailedComponent("user/Profile");
        ProfileController controller = component.getLoader().getController();
        switchToDetails(controller);
        controller.fillFields(student.getUser());

        final JFXButton edit = new JFXButton("");
        final JFXButton update = new JFXButton("");
        final JFXButton cancel = new JFXButton("");

        ImageView editIcon = new ImageView(new Image("/views/components/icons/edit.png"));
        editIcon.setFitWidth(32);
        editIcon.setFitHeight(32);
        edit.setGraphic(editIcon);

        ImageView updateIcon = new ImageView(new Image("/views/components/icons/update.png"));
        updateIcon.setFitWidth(32);
        updateIcon.setFitHeight(32);
        update.setGraphic(updateIcon);

        ImageView cancelIcon = new ImageView(new Image("/views/components/icons/cancel.png"));
        cancelIcon.setFitWidth(32);
        cancelIcon.setFitHeight(32);
        cancel.setGraphic(cancelIcon);



        final HBox actions = new HBox(cancel, update);
        final StackPane stackPane = new StackPane(edit, actions);
        final VBox body = new VBox(component.getRoot(), stackPane);

        actions.setAlignment(Pos.CENTER);
        actions.setSpacing(10);
        actions.setVisible(false);

        body.setAlignment(Pos.CENTER);
        body.setSpacing(10);

        edit.setOnAction(e -> {
            switchToEdit(controller);
            actions.setVisible(true);
            edit.setVisible(false);
        });

        update.setOnAction(e -> {

        });

        cancel.setOnAction(e -> {
            switchToDetails(controller);
            actions.setVisible(false);
            edit.setVisible(true);
        });

        layout.setBody(body);
        alert.setContent(layout);
        alert.showAndWait();
    }

    private void displayStudentEdit(Student student) {
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

    private void switchToEdit(ProfileController controller) {
        for(JFXTextField tf : controller.getAllTextFields()) {
            tf.setDisable(false);
            tf.setEditable(true);
        }
    }

    private void switchToDetails(ProfileController controller) {
        for(JFXTextField tf : controller.getAllTextFields()) {
            tf.setDisable(true);
            tf.setEditable(false);
        }
    }

    private void editAction(ActionEvent event) {

    }

    private void updateAction(ActionEvent event) {

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
