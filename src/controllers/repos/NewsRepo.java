package controllers.repos;

import com.j256.ormlite.dao.Dao;
import models.News;
import services.IModel;
import services.Persistence;

import java.sql.SQLException;
import java.util.List;

public class NewsRepo extends Persistence {
    private static NewsRepo _news;
    private Dao<IModel, String> _accessObject;

    private NewsRepo() throws SQLException {
        _accessObject = getAccessObject(News.class);
    }

    public static NewsRepo getInstance() throws SQLException {
        if(_news == null) _news = new NewsRepo();
        return _news;
    }

    @Override
    public void create(IModel model) throws SQLException {
        News news = (News) model;
        _accessObject.create(news);
    }

    @Override
    public List<IModel> retrieveAll() throws SQLException {
        return _accessObject.queryBuilder().query();
    }

    @Override
    public void update(IModel model) throws SQLException {

    }

    @Override
    public void delete(IModel model) throws SQLException {

    }
}
