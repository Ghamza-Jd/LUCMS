package controllers.repos;

import com.j256.ormlite.dao.Dao;
import exceptions.InvalidCredentialsException;
import models.User;
import services.IModel;
import services.Persistence;
import services.Security;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.SQLException;
import java.util.List;

public class Users extends Persistence {
    private static Users _users;
    private Dao<IModel, String> _accessObject;

    private Users() throws SQLException {
        _accessObject = getAccessObject(User.class);
    }

    public static Users getInstance() throws SQLException {
        if(_users == null) _users = new Users();
        return _users;
    }

    public boolean isUser(String username, String password) throws SQLException {
        if(!exists(username)) throw new InvalidCredentialsException();
        User user = retrieveSingle(username);
        if(!Security.eqHash(password, user.getPassword())) throw new InvalidCredentialsException();
        return true;
    }

    @Override
    public void create(IModel model) throws SQLException {
        User user = (User) model;
        _accessObject.create(user);
    }

    @Override
    public List<IModel> retrieveAll() throws SQLException {
        return _accessObject.queryBuilder().query();
    }

    @Override
    public void update(IModel model) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void delete(IModel model) throws SQLException {
        throw new NotImplementedException();
    }

    public boolean exists(String username) throws SQLException {
        List<IModel> users =
                _accessObject
                    .queryBuilder()
                    .where()
                    .eq("normalizedUsername", username)
                    .query();
        return users.size() > 0;
    }

    public User retrieveSingle(String username) throws SQLException {
        List<IModel> users =
                _accessObject
                        .queryBuilder()
                        .where()
                        .eq("normalizedUsername", username)
                        .query();
        return (users.size() > 0 ? (User) users.get(0) : null);
    }
}
