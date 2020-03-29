import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import services.DatabaseHandler;
import services.ViewsManager;

public class Main extends Application {
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = ViewsManager.requestView("Login");
        primaryStage.getIcons().add(new Image("/images/lu_logo.png"));
        primaryStage.setScene(scene);
        primaryStage.setTitle("LUMS");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        DatabaseHandler.getInstance().close();
        super.stop();
    }
}
