package controllers.repos;

import com.j256.ormlite.dao.Dao;
import models.Enroll;
import services.IModel;
import services.Persistence;

import java.sql.SQLException;
import java.util.List;

public class Enrollment extends Persistence {
    private static Enrollment _enrollment;
    private Dao<IModel, String> _enrollmentAccessObject;

    private Enrollment() throws SQLException {
        _enrollmentAccessObject = getAccessObject(Enroll.class);
    }

    public static Enrollment getInstance() throws SQLException {
        if(_enrollment == null) _enrollment = new Enrollment();
        return _enrollment;
    }

    @Override
    public void create(IModel model) throws SQLException {
        Enroll enroll = (Enroll) model;
        _enrollmentAccessObject.create(enroll);
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
