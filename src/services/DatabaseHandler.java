package services;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;
import config.Config;

import java.sql.SQLException;
import java.util.HashMap;

public class DatabaseHandler {
    private static DatabaseHandler _db;

    private HashMap<String, Object> accessObjects;
    private JdbcPooledConnectionSource connectionSource;

    private DatabaseHandler() throws SQLException {
        accessObjects = new HashMap<>();
        connectionSource = new JdbcPooledConnectionSource(
                    String.format("jdbc:mysql://%s/%s",
                            Config.DATABASE_URL,
                            Config.DATABASE_NAME),
                    Config.DATABASE_USERNAME,
                    Config.DATABASE_PASSWORD
        );
    }

    public static DatabaseHandler getInstance() throws SQLException {
        if(_db == null) _db = new DatabaseHandler();
        return _db;
    }

    public <T extends IModel> Dao<T, String> getDao(Class<T> clazz) throws SQLException {
        if(accessObjects.containsKey(clazz.getName())) return (Dao<T, String>) accessObjects.get(clazz.getName());
        Dao<T, String> dao = DaoManager.createDao(connectionSource, clazz);
        TableUtils.createTableIfNotExists(connectionSource, clazz);
        accessObjects.put(clazz.getName(), dao);
        return dao;
    }
}
