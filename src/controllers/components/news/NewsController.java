package controllers.components.news;

import controllers.repos.NewsRepo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import models.News;
import services.IModel;
import services.ViewsManager;
import utils.Cards;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class NewsController implements Initializable {
    @FXML
    private Pane pane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            List<IModel> news = NewsRepo.getInstance().retrieveAll();
            for (IModel m : news) {
                News n = (News) m;
                ViewsManager.DetailedComponent component =
                        ViewsManager.requestDetailedComponent("news/NewsCard");
                pane.getChildren().add(component.getRoot());
                NewsCardController controller = component.getLoader().getController();
                controller.getTitle().setText(n.getTitle());
                controller.getBody().setText(n.getBody());
                controller.setCardColor(Cards.getColor(n.getLevel()));
                controller.setDate(n.getDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
