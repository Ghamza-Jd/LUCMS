import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import services.ViewsManager;

public class Frontend_Testing extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(ViewsManager.requestView("StudentDashboard"));
        primaryStage.setTitle("LUCMS");
        primaryStage.show();
    }
}
