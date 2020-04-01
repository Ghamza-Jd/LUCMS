package controllers.components.students_affair;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controllers.components.user.ActionsController;
import controllers.components.user.CreateUserController;
import controllers.components.user.ProfileController;
import controllers.repos.Students;
import controllers.repos.Users;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Callback;
import models.Student;
import models.User;
import services.ViewsManager;
import utils.Alerts;

import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

public final class ViewStudentsController implements Initializable {
    @FXML
    private JFXTreeTableView<StudentRow> table;
    private ObservableList<StudentRow> rows;
    private Pane dashboard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rows = FXCollections.observableArrayList();
        final double width = table.getPrefWidth();

        final JFXTreeTableColumn<StudentRow, String>
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

        final Callback<TreeTableColumn<StudentRow, String>, TreeTableCell<StudentRow, String>> actionsCell =
                new Callback<>() {
                    @Override
                    public TreeTableCell<StudentRow, String> call(TreeTableColumn<StudentRow, String> param) {
                        return new TreeTableCell<>() {
                            final ViewsManager.DetailedComponent component =
                                    ViewsManager.requestDetailedComponent("user/Actions");
                            final ActionsController controller = component.getLoader().getController();

                            {
                                controller.getEdit().setOnAction(e -> {
                                    try {
                                        final String id = this.getTreeTableRow().getTreeItem().getValue().fileNumber.getValue();
                                        final Student student = Students.getInstance().retrieveSingleByFileNb(Integer.parseInt(id));
                                        displayStudentEdit(student);
                                    } catch (SQLException ex) {
                                        ex.printStackTrace();
                                    }
                                });
                                controller.getDetails().setOnAction(e -> {
                                    try {
                                        final String id = this.getTreeTableRow().getTreeItem().getValue().fileNumber.getValue();
                                        final Student student = Students.getInstance().retrieveSingleByFileNb(Integer.parseInt(id));
                                        displayStudentDetails(student);
                                    } catch (SQLException ex) {
                                        ex.printStackTrace();
                                    }
                                });
                                controller.getDelete().setOnAction(e -> {
                                    try {
                                        final String id = this.getTreeTableRow().getTreeItem().getValue().fileNumber.getValue();
                                        final Student student = Students.getInstance().retrieveSingleByFileNb(Integer.parseInt(id));
                                        displayDeleteDialog(student, getIndex());
                                    } catch (SQLException ex) {
                                        ex.printStackTrace();
                                    }
                                });
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

        try {
            populateTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        final TreeItem<StudentRow> root = new RecursiveTreeItem<>(rows, RecursiveTreeObject::getChildren);
        table.getColumns().setAll(fileNumber, fullName, username, actions);
        table.setRoot(root);
        table.setShowRoot(false);
    }

    public void setDashboard(Pane dashboard) {
        this.dashboard = dashboard;
    }

    private void populateTable() throws SQLException {
        final ArrayList<Student> students = Students.getInstance().retrieveAllStudents();
        for(Student s : students) {
            rows.add(
                    new StudentRow(
                            String.valueOf(s.getId()),
                            String.format(
                                    "%s %s %s",
                                    s.getUser().getFirstName(),
                                    s.getUser().getMiddleName(),
                                    s.getUser().getLastName()
                            ),
                            s.getUser().getUsername()
                    )
            );
        }
    }

    private void displayStudentDetails(Student student) {
        final JFXAlert<String> alert = Alerts.createAlert();
        final JFXDialogLayout layout = new JFXDialogLayout();
        final JFXButton exit = new JFXButton("");
        final Region region = new Region();
        final HBox heading = new HBox(new Label("Details"), region, exit);
        final ImageView exitIcon = new ImageView(new Image("/icons/close.png"));

        exitIcon.setFitHeight(32);
        exitIcon.setFitWidth(32);
        exit.setGraphic(exitIcon);
        exit.setOnAction(e -> alert.hideWithAnimation());

        HBox.setHgrow(region, Priority.ALWAYS);

        layout.setStyle("-fx-border-color: grey;");
        layout.setHeading(heading);

        final ViewsManager.DetailedComponent readOnlyComponent = ViewsManager.requestDetailedComponent("user/Profile");
        final ProfileController readOnlyController = readOnlyComponent.getLoader().getController();
        readOnlyController.fillFields(student.getUser());

        final ViewsManager.DetailedComponent editableComponent = ViewsManager.requestDetailedComponent("user/CreateUser");
        final CreateUserController editableController = editableComponent.getLoader().getController();
        editableController.fillFields(student.getUser());
        editableComponent.getRoot().setVisible(false);

        final JFXButton edit    = new JFXButton("Edit");
        final JFXButton update  = new JFXButton("Update");
        final JFXButton cancel  = new JFXButton("Cancel");

        final ImageView editIcon = new ImageView(new Image("/icons/edit.png"));
        editIcon.setFitWidth(24);
        editIcon.setFitHeight(24);
        edit.setGraphic(editIcon);

        final ImageView updateIcon = new ImageView(new Image("/icons/update.png"));
        updateIcon.setFitWidth(24);
        updateIcon.setFitHeight(24);
        update.setGraphic(updateIcon);

        final ImageView cancelIcon = new ImageView(new Image("/icons/cancel.png"));
        cancelIcon.setFitWidth(24);
        cancelIcon.setFitHeight(24);
        cancel.setGraphic(cancelIcon);

        final HBox actions = new HBox(cancel, update);
        final StackPane buttonsStackPane = new StackPane(edit, actions);
        final StackPane viewsStackPane = new StackPane(readOnlyComponent.getRoot(), editableComponent.getRoot());
        final VBox body = new VBox(viewsStackPane, buttonsStackPane);

        actions.setAlignment(Pos.CENTER);
        actions.setSpacing(30);
        actions.setVisible(false);

        body.setAlignment(Pos.CENTER);
        body.setSpacing(10);

        edit.setOnAction(e -> {
            readOnlyComponent.getRoot().setVisible(false);
            editableComponent.getRoot().setVisible(true);
            actions.setVisible(true);
            edit.setVisible(false);
        });

        update.setOnAction(e -> {
            try {
                updateStudent(getStudentFields(editableController, student));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        cancel.setOnAction(e -> {
            editableComponent.getRoot().setVisible(false);
            readOnlyComponent.getRoot().setVisible(true);
            actions.setVisible(false);
            edit.setVisible(true);
        });

        layout.setBody(body);
        alert.setContent(layout);
        alert.showAndWait();
    }

    private void displayStudentEdit(Student student) {
        final JFXAlert<String> alert = Alerts.createAlert();
        final JFXDialogLayout layout = new JFXDialogLayout();
        layout.setStyle("-fx-border-color: grey;");

        final JFXButton exit = new JFXButton("");
        final ImageView exitIcon = new ImageView(new Image("/icons/close.png"));
        exitIcon.setFitHeight(32);
        exitIcon.setFitWidth(32);
        exit.setGraphic(exitIcon);

        exit.setOnAction(e -> alert.hideWithAnimation());
        final Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        final HBox heading = new HBox(new Label("Details"), region, exit);
        layout.setHeading(heading);

        final ViewsManager.DetailedComponent component = ViewsManager.requestDetailedComponent("user/CreateUser");
        final CreateUserController controller = component.getLoader().getController();
        controller.fillFields(student.getUser());

        final JFXButton update = new JFXButton("Update");
        final ImageView updateIcon = new ImageView(new Image("/icons/update.png"));
        updateIcon.setFitWidth(24);
        updateIcon.setFitHeight(24);
        update.setGraphic(updateIcon);

        final JFXButton undoChanges = new JFXButton("Undo Changes");
        final ImageView undoChangesIcon = new ImageView(new Image("/icons/cancel.png"));
        undoChangesIcon.setFitWidth(24);
        undoChangesIcon.setFitHeight(24);
        undoChanges.setGraphic(undoChangesIcon);


        final VBox body = new VBox(component.getRoot(), update, undoChanges);
        body.setAlignment(Pos.CENTER);

        update.setOnAction(e -> {
            try {
                updateStudent(getStudentFields(controller, student));
                Alerts.createSnackbar(dashboard, "Update the student wit File # " + student.getId(), 2);
                alert.hideWithAnimation();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        undoChanges.setOnAction(e -> {
            controller.fillFields(student.getUser());
        });

        layout.setBody(body);
        alert.setContent(layout);
        alert.showAndWait();
    }

    public void displayDeleteDialog(Student student, int index) {
        final JFXAlert<String> alert = Alerts.createAlert();
        final JFXDialogLayout layout = Alerts.createLayout("Remove Student", String.format(
                "Are you sure you want to remove %s %s %s having file# %d",
                student.getUser().getFirstName(),
                student.getUser().getMiddleName(),
                student.getUser().getLastName(),
                student.getId())
        );
        final JFXButton yes = new JFXButton("");

        final JFXButton no = new JFXButton("");

        final ImageView yesIcon = new ImageView(new Image("/icons/ok.png"));
        yesIcon.setFitWidth(32);
        yesIcon.setFitHeight(32);
        yes.setGraphic(yesIcon);

        final ImageView noIcon = new ImageView(new Image("/icons/close.png"));
        noIcon.setFitWidth(32);
        noIcon.setFitHeight(32);
        no.setGraphic(noIcon);

        yes.setOnAction(e -> {
            try {
                deleteStudent(student);
                rows.remove(index);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                alert.hideWithAnimation();
            }
        });
        no.setOnAction(e -> {
            alert.hideWithAnimation();
        });

        layout.setActions(yes, no);
        alert.setContent(layout);
        alert.showAndWait();
    }

    public User getStudentFields(CreateUserController controller, Student student) {
        final HashMap<String, String> fields = controller.getUserFields();
        student.getUser().setFirstName(fields.get("first_name"));
        student.getUser().setMiddleName(fields.get("middle_name"));
        student.getUser().setLastName(fields.get("last_name"));
        student.getUser().setUsername(fields.get("username"));

        final LocalDate localDate = LocalDate.parse(fields.get("date"), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        final Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        final Date date = Date.from(instant);
        student.getUser().setDateOfBirth(date);

        student.getUser().setGender(fields.get("gender"));
        return student.getUser();
    }

    private void updateStudent(User user) throws SQLException {
        Users.getInstance().update(user);
    }

    private void deleteStudent(Student student) throws SQLException {
        Students.getInstance().delete(student);
    }

    private static class StudentRow extends RecursiveTreeObject<StudentRow> {
        StringProperty fileNumber;
        StringProperty fullName;
        StringProperty username;

        public StudentRow(String fileNumber, String fullName, String username) {
            this.fileNumber = new SimpleStringProperty(fileNumber);
            this.fullName = new SimpleStringProperty(fullName);
            this.username = new SimpleStringProperty(username);
        }
    }
}
