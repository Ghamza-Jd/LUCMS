package controllers.repos;

import com.j256.ormlite.dao.Dao;
import models.Course;
import models.Professor;
import models.User;
import services.IModel;
import services.Persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class Courses extends Persistence {
    private static Courses _courses;
    private final Dao<IModel, String> _coursesAccessObject;
    private final Dao<IModel, String> _professorsAccessObject;
    private final Dao<IModel, String> _userAccessObject;

    private Courses() throws SQLException {
        _coursesAccessObject = getAccessObject(Course.class);
        _professorsAccessObject = Professors.getInstance().getAccessObject(Professor.class);
        _userAccessObject = Users.getInstance().getAccessObject(User.class);
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
        final List<IModel> models =
                _coursesAccessObject
                        .queryBuilder()
                        .where()
                        .eq("professor_id", id)
                        .query()
        ;
        final ArrayList<Course> result = new ArrayList<>();
        for(IModel m : models) result.add((Course) m);
        return result;
    }

    public Course retrieveCourseByCode(String code) throws SQLException {
        final List<IModel> models =
                _coursesAccessObject
                        .queryBuilder()
                        .where()
                        .eq("code", code)
                        .query()
        ;
        if(models.size() > 0) {
            final Course course = (Course) models.get(0);
            _professorsAccessObject.refresh(course.getProfessor());
            _userAccessObject.refresh(course.getProfessor().getUser());
            return course;
        }
        return null;
    }

    public Course retrieveCourseByName(String name) {
        return null;
    }
}
