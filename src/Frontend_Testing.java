import controllers.repos.Students;
import controllers.repos.Users;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.Student;
import models.User;
import services.Session;
import services.ViewsManager;

import java.sql.SQLException;

public class Frontend_Testing extends Application {
    public static void main(String[] args) throws SQLException {
        User user = Users.getDao().getUser("hamzajd");
        Session.getInstance().addToSession("user", user);
        Session.getInstance().addToSession("student", Students.getDao().getStudent(user));
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(ViewsManager.requestView("Dashboard"));
        primaryStage.setTitle("LUCMS");
        primaryStage.show();
    }
}
