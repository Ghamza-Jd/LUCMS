package controllers.components.head_of_department;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class CreateNews {
    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextArea body;
    @FXML
    private JFXComboBox<String> level;

    @FXML
    void postNews(ActionEvent event) {

    }
}
