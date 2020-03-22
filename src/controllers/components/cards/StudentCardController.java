package controllers.components.cards;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class StudentCardController {
    @FXML
    private JFXTextField
            name,
            fileNb,
            major,
            birth;

    public JFXTextField getName() { return name; }

    public JFXTextField getFileNb() { return fileNb; }

    public JFXTextField getMajor() { return major; }

    public JFXTextField getBirth() { return birth; }
}
