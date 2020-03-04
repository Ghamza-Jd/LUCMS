import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import services.ViewsManager;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.getIcons().add(new Image("/images/lu_logo.png"));
        primaryStage.setScene(ViewsManager.requestView("Login"));
        primaryStage.setTitle("LUCMS");
        primaryStage.show();
    }
}
