package controllers.components;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class NewsCardController {
    @FXML
    private Text title;
    @FXML
    private TextFlow body;

    public Text getTitle() {
        return title;
    }

    public TextFlow getBody() {
        return body;
    }
}
