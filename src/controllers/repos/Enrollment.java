package controllers.repos;

import services.IModel;
import services.Persistence;

import java.sql.SQLException;
import java.util.List;

public class Enrollment extends Persistence {
    private static Enrollment _enrollment;

    private Enrollment() {

    }

    public static Enrollment getInstance() {
        if(_enrollment == null) _enrollment = new Enrollment();
        return _enrollment;
    }

    @Override
    public void create(IModel model) throws SQLException {

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
