import javafx.application.Application;
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
        Scene scene = ViewsManager.requestView("Login");
        primaryStage.getIcons().add(new Image("/images/lu_logo.png"));
        primaryStage.setScene(scene);
        primaryStage.setTitle("LebCoursera");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
