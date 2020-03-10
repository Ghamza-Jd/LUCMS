import controllers.repos.Students;
import controllers.repos.Users;
import javafx.application.Application;
import javafx.stage.Stage;
import models.User;
import services.Session;
import services.ViewsManager;

import java.sql.SQLException;

public class Frontend_Testing extends Application {
    public static void main(String[] args) throws SQLException {
        User user = Users.getInstance().retrieveSingle("hamzajd");
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
