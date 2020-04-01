package controllers.repos;

import com.j256.ormlite.dao.Dao;
import models.Professor;
import models.User;
import services.IModel;
import services.Persistence;

import java.sql.SQLException;
import java.util.List;

public final class Professors extends Persistence {
    private static Professors _professor;
    private final Dao<IModel, String> _professorsAccessObject;
    private final Dao<IModel, String> _usersAccessObject;

    private Professors() throws SQLException {
        _professorsAccessObject = getAccessObject(Professor.class);
        _usersAccessObject = Users.getInstance().getAccessObject(User.class);
    }

    public static Professors getInstance() throws SQLException {
        if(_professor == null) _professor = new Professors();
        return _professor;
    }

    @Override
    public void create(IModel model) throws SQLException {
        final Professor professor = (Professor) model;
        final User user = professor.getUser();
        user.setRole("PROFESSOR");
        professor.setUser(user);
        _usersAccessObject.create(user);
        _professorsAccessObject.create(professor);
    }

    @Override
    public List<IModel> retrieveAll() throws SQLException {
        final List<IModel> professors = _professorsAccessObject.queryBuilder().query();
        for(IModel m : professors) {
            final Professor professor = (Professor) m;
            _usersAccessObject.refresh(professor.getUser());
        }
        return professors;
    }

    @Override
    public void update(IModel model) throws SQLException {

    }

    @Override
    public void delete(IModel model) throws SQLException {

    }

    public Professor retrieveSingle(User user) throws SQLException {
        final List<IModel> professors =
                _professorsAccessObject
                        .queryBuilder()
                        .where()
                        .eq("user_id", user)
                        .query()
        ;
        if(professors.size() > 0) {
            final IModel professor = professors.get(0);
            _usersAccessObject.refresh(((Professor) professor).getUser());
            return (Professor) professor;
        }
        return null;
    }

    public Professor retrieveByUsername(String username) throws SQLException {
        final List<IModel> users = _usersAccessObject
                .queryBuilder()
                .where().
                eq("normalizedUsername", username.toUpperCase())
                .query()
        ;
        return users.size() > 0 ? retrieveSingle((User) users.get(0)) : null;
    }
}
