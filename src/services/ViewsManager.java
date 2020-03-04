package services;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewsManager {
    private static ViewsManager _manager;
    private ViewsManager() { }
    public static Stage getActiveStage(ActionEvent event){
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }
    public static Scene requestView(String path) throws IOException {
        Parent root = FXMLLoader.load(ViewsManager.class.getResource(String.format("../views/%s.fxml", path)));
        return new Scene(root);
    }
}
