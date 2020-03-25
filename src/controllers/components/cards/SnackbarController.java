package controllers.components.cards;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class SnackbarController {
    @FXML
    private Text text;
    @FXML
    private Pane pane;

    public void setText(String text) {
        this.text.setText(text);
    }

    public void setTextColor(String color) {
        this.text.setStyle(String.format("-fx-fill: %s ;", color));
    }

    public void setBackgroundColor(String color) {
        this.pane.setStyle(String.format("-fx-background-color: %s ;", color));
    }
}
