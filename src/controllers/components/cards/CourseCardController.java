package controllers.components.cards;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public final class CourseCardController {
    @FXML
    private JFXTextField
            name,
            code,
            lang,
            prof
    ;

    public JFXTextField getName() { return name; }

    public JFXTextField getCode() { return code; }

    public JFXTextField getLang() { return lang; }

    public JFXTextField getProf() { return prof; }
}
