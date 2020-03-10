package services;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;
import config.Config;
import models.*;

import java.sql.SQLException;

public class DatabaseHandler {
    private static DatabaseHandler _db;

    private Dao<User, String> userDao;
    private Dao<Student, String> studentDao;
    private Dao<Professor, String> professorDao;
    private Dao<Course, String> courseDao;
    private Dao<News, String> newsDao;

    private DatabaseHandler() throws SQLException {
        JdbcPooledConnectionSource connectionSource = new JdbcPooledConnectionSource(
                    String.format("jdbc:mysql://%s/%s",
                            Config.DATABASE_URL,
                            Config.DATABASE_NAME),
                    Config.DATABASE_USERNAME,
                    Config.DATABASE_PASSWORD
        );

        userDao = DaoManager.createDao(connectionSource, User.class);
        TableUtils.createTableIfNotExists(connectionSource, User.class);

        studentDao = DaoManager.createDao(connectionSource, Student.class);
        TableUtils.createTableIfNotExists(connectionSource, Student.class);

        professorDao = DaoManager.createDao(connectionSource, Professor.class);
        TableUtils.createTableIfNotExists(connectionSource, Professor.class);

        courseDao = DaoManager.createDao(connectionSource, Course.class);
        TableUtils.createTableIfNotExists(connectionSource, Course.class);

        newsDao = DaoManager.createDao(connectionSource, News.class);
        TableUtils.createTableIfNotExists(connectionSource, News.class);
    }

    public static DatabaseHandler getInstance() throws SQLException {
        if(_db == null) _db = new DatabaseHandler();
        return _db;
    }

    public Dao<User, String> getUserDao() { return userDao; }

    public Dao<Student, String> getStudentDao() { return studentDao; }

    public Dao<Professor, String> getProfessorDao() { return professorDao; }

    public Dao<Course, String> getCourseDao() { return courseDao; }

    public Dao<News, String> getNewsDao() { return newsDao; }
}
