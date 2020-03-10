package controllers.repos;

import com.j256.ormlite.dao.Dao;
import models.News;

public class NewsRepo {
    private static NewsRepo _newsRepo;
    private Dao<News, String> newsDao;
}
