package services;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewsManager {
    public static Stage getActiveStage(ActionEvent event) {
        return (Stage) getActiveScene(event).getWindow();
    }

    public static Scene getActiveScene(ActionEvent event) {
        return ((Node) event.getSource()).getScene();
    }

    public static Scene requestView(String path) throws IOException {
        Parent root = FXMLLoader.load(ViewsManager.class.getResource(String.format("../views/%s.fxml", path)));
        return new Scene(root);
    }

    public static Parent requestComponent(String path) {
        try {
            return FXMLLoader.load(ViewsManager.class.getResource(String.format("../views/components/%s.fxml", path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DetailedComponent requestDetailedComponent(String path) {
        FXMLLoader loader = new FXMLLoader(ViewsManager.class.getResource(String.format("../views/components/%s.fxml", path)));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new DetailedComponent(root, loader);
    }

    public static class DetailedComponent {
        private Parent root;
        private FXMLLoader loader;

        DetailedComponent(Parent root, FXMLLoader loader){
            this.root = root;
            this.loader = loader;
        }

        public FXMLLoader getLoader() { return loader; }

        public Parent getRoot() { return root; }
    }
}
