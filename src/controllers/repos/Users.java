package controllers.repos;

import com.j256.ormlite.dao.Dao;
import exceptions.InvalidCredentialsException;
import models.User;
import services.DatabaseHandler;
import services.Security;

import java.sql.SQLException;
import java.util.List;

public class Users {
    private Dao<User, String> userDao;
    public static Users _users;
    private Users() throws SQLException {
        userDao = DatabaseHandler.getInstance().getUserDao();
    }
    public static Users getDao() throws SQLException {
        if(_users == null) _users = new Users();
        return _users;
    }
    public void createUser(User user) throws SQLException {
        userDao.create(user);
    }
    public User getUser(String username) throws SQLException {
        List<User> users = userDao.queryBuilder().where().eq("normalizedUsername", username).query();
        if(users.size() == 0) throw new InvalidCredentialsException();
        return users.get(0);
    }
    public boolean isUser(String username, String password) throws SQLException {
        User user = getUser(username);
        if(!Security.eqHash(password, user.getPassword())) throw new InvalidCredentialsException();
        return true;
    }
}
