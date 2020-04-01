package controllers.components.news;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public final class NewsCardController {
    @FXML
    private Text title, date;
    @FXML
    private JFXTextArea body;
    @FXML
    private Rectangle level;

    public Text getTitle() {
        return title;
    }

    public JFXTextArea getBody() {
        return body;
    }

    public void setDate(String date) { this.date.setText(String.format("Posted on: %s", date)); }

    public void setCardColor(String color) { level.setFill(Paint.valueOf(color)); }
}
