package services;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;
import config.Config;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Dao Factory
 */
public class DatabaseHandler {
    private static DatabaseHandler _db;
    private JdbcPooledConnectionSource connectionSource;

    /**
     * HashMap that has the name of the class as a key
     * and the Dao as value
     */
    private HashMap<String, Object> accessObjects;

    private DatabaseHandler() throws SQLException {
        accessObjects = new HashMap<>();
        connectionSource = new JdbcPooledConnectionSource(
                    String.format("jdbc:%s://%s/%s?%s",
                            Config.DATABASE_ENGINE,
                            Config.DATABASE_URL,
                            Config.DATABASE_NAME,
                            Config.DATABASE_CONFIG),
                    Config.DATABASE_USERNAME,
                    Config.DATABASE_PASSWORD
        );
    }

    public static DatabaseHandler getInstance() throws SQLException {
        if(_db == null) _db = new DatabaseHandler();
        return _db;
    }

    public void close() throws IOException {
        if(_db != null) connectionSource.close();
    }

    /**
     * @param clazz class
     * @param <T> extends IModel
     * @return Database access object
     * @throws SQLException SQLException
     */
    public <T extends IModel> Dao<T, String> getDao(Class<T> clazz) throws SQLException {
        if(accessObjects.containsKey(clazz.getName())) return (Dao<T, String>) accessObjects.get(clazz.getName());
        Dao<T, String> dao = DaoManager.createDao(connectionSource, clazz);
        TableUtils.createTableIfNotExists(connectionSource, clazz);
        accessObjects.put(clazz.getName(), dao);
        return dao;
    }
}
