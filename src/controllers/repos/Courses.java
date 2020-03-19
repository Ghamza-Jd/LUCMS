package controllers.repos;

import com.j256.ormlite.dao.Dao;
import models.Course;
import models.Professor;
import services.IModel;
import services.Persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Courses extends Persistence {
    private static Courses _courses;
    private Dao<IModel, String> _coursesAccessObject;
    private Dao<IModel, String> _professorsAccessObject;

    private Courses() throws SQLException {
        _coursesAccessObject = getAccessObject(Course.class);
        _professorsAccessObject = Professors.getInstance().getAccessObject(Professor.class);
    }

    public static Courses getInstance() throws SQLException {
        if(_courses == null) _courses = new Courses();
        return _courses;
    }

    @Override
    public void create(IModel model) throws SQLException {
        Course course = (Course) model;
        _coursesAccessObject.create(course);
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

    public ArrayList<Course> retrieveProfessorCourses(int id) throws SQLException {
        List<IModel> models = _coursesAccessObject.queryBuilder().where().eq("professor_id", id).query();
        ArrayList<Course> result = new ArrayList<>();
        for(IModel m : models) result.add((Course) m);
        return result;
    }
}
