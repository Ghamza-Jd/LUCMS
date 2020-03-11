package controllers.repos;

import com.j256.ormlite.dao.Dao;
import models.HeadOfDepartment;
import models.StudentsAffair;
import models.User;
import services.IModel;
import services.Persistence;

import java.sql.SQLException;
import java.util.List;

public class HoDs extends Persistence {
    private static HoDs _hods;
    private Dao<IModel, String> _hodsAccessObject;
    private Dao<IModel, String> _usersAccessObject;

    private HoDs() throws SQLException {
        _hodsAccessObject = getAccessObject(HeadOfDepartment.class);
        _usersAccessObject = Users.getInstance().getAccessObject(User.class);
    }

    public static HoDs getInstance() throws SQLException {
        if(_hods == null) _hods = new HoDs();
        return _hods;
    }

    @Override
    public void create(IModel model) throws SQLException {
        HeadOfDepartment hod = (HeadOfDepartment) model;
        hod.getUser().setRole("HEAD_OF_DEPARTMENT");
        _usersAccessObject.create(hod.getUser());
        _hodsAccessObject.create(hod);
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
}
