package controllers.components;

import controllers.repos.NewsRepo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import models.News;
import services.IModel;
import services.ViewsManager;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class NewsController implements Initializable {
    @FXML
    private Pane pane;
    @FXML
    private ScrollPane scroll;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            List<IModel> news = NewsRepo.getInstance().retrieveAll();
            for (IModel m : news) {
                News n = (News) m;
                ViewsManager.DetailedComponent component =
                        ViewsManager.requestDetailedComponent("NewsCard");
                pane.getChildren().add(component.getRoot());
                NewsCardController controller = component.getLoader().getController();
                controller.getTitle().setText(n.getTitle());
                controller.getBody().setText(n.getBody());
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
