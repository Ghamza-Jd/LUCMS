package controllers.components.head_of_department;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import controllers.repos.NewsRepo;
import global.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import models.News;
import utils.Alerts;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public final class CreateNews implements Initializable {
    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextArea body;
    @FXML
    private JFXComboBox<String> level;
    @FXML
    private Label counter;

    private Pane dashboard;

    private int count;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final ObservableList<String> levelOption = FXCollections.observableArrayList();
        levelOption.setAll(Constants.WARNING_LEVELS);
        level.setItems(levelOption);
        body.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            count = body.getText().length() + 1;
            if(body.getText().length() >= 255 && !(e.getCode() == KeyCode.BACK_SPACE)) {
                e.consume();
                body.setText(body.getText().substring(0, 255));
                body.end();
                return;
            }
            if(e.getCode() == KeyCode.BACK_SPACE) count -= 2;
            if(count < 0) count = 0;
            counter.setText(String.format("%d / 255", count));
        });
    }

    @FXML
    void postNews(ActionEvent event) throws SQLException {
        NewsRepo.getInstance().create(new News(title.getText(), body.getText(), level.getValue()));
        Alerts.createSnackbar(dashboard, "Your news post have been uploaded!", 2);
    }

    public void setDashboard(Pane pane) {
        dashboard = pane;
    }
}
