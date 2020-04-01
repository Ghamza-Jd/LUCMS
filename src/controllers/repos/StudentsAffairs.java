package controllers.repos;

import com.j256.ormlite.dao.Dao;
import models.StudentsAffair;
import models.User;
import services.IModel;
import services.Persistence;

import java.sql.SQLException;
import java.util.List;

public final class StudentsAffairs extends Persistence {
    private static StudentsAffairs _affairs;
    private final Dao<IModel, String> _affairsAccessObject;
    private final Dao<IModel, String> _usersAccessObject;

    private StudentsAffairs() throws SQLException {
        _affairsAccessObject = getAccessObject(StudentsAffair.class);
        _usersAccessObject = Users.getInstance().getAccessObject(User.class);
    }

    public static StudentsAffairs getInstance() throws SQLException {
        if(_affairs == null) _affairs = new StudentsAffairs();
        return _affairs;
    }

    @Override
    public void create(IModel model) throws SQLException {
        final StudentsAffair affair = (StudentsAffair) model;
        affair.getUser().setRole("STUDENT_AFFAIR");
        _usersAccessObject.create(affair.getUser());
        _affairsAccessObject.create(affair);
    }

    @Override
    public List<IModel> retrieveAll() throws SQLException {
        return null;
    }

    @Override
    public void update(IModel model) throws SQLException {

    }

    @Override
    public void delete(IModel model) throws SQLException {

    }

    public StudentsAffair retrieveAll(User user) throws SQLException {
        return null;
    }
}
