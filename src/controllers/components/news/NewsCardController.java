package controllers.components.news;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class NewsCardController {
    @FXML
    private Text title;
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

    public void setCardColor(String color) { level.setFill(Paint.valueOf(color)); }
}
