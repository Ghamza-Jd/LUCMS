package services;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;
import config.Config;
import models.Student;
import models.User;

import java.sql.SQLException;

public class DatabaseHandler {
    private static DatabaseHandler _db;

    private Dao<User, String> userDao;
    private Dao<Student, String> studentDao;

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
    }

    public static DatabaseHandler getInstance() throws SQLException {
        if(_db == null) _db = new DatabaseHandler();
        return _db;
    }

    public Dao<User, String> getUserDao() {
        return userDao;
    }

    public Dao<Student, String> getStudentDao() {
        return studentDao;
    }
}
