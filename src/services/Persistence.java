package services;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Persistence Class
 * An abstract class that facilitates the database CRUD operations
 * for all subclasses that implements IModel
 */
public abstract class Persistence {
    private Dao<IModel, String> _accessObject;

    private <T extends IModel> Dao<T, String> getDao(Class<T> clazz) throws SQLException {
        if(_accessObject == null) _accessObject = DatabaseHandler.getInstance().getDao((Class<IModel>) clazz);
        return (Dao<T, String>) _accessObject;
    }

    public Dao<IModel, String> getAccessObject(Class<? extends IModel> clazz) throws SQLException {
        return getDao((Class<IModel>) clazz);
    }

    public abstract void create(IModel model) throws SQLException;
    public abstract List<IModel> retrieveAll() throws SQLException;
    public abstract void update(IModel model) throws SQLException;
    public abstract void delete(IModel model) throws SQLException;
}
