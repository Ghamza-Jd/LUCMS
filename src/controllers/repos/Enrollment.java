package controllers.repos;

import com.j256.ormlite.dao.Dao;
import models.*;
import services.IModel;
import services.Persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Enrollment extends Persistence {
    private static Enrollment   _enrollment;
    private Dao<IModel, String> _enrollmentAccessObject;
    private Dao<IModel, String> _professorsAccessObject;
    private Dao<IModel, String> _coursesAccessObject;
    private Dao<IModel, String> _usersAccessObject;

    private Enrollment() throws SQLException {
        _enrollmentAccessObject = getAccessObject(Enroll.class);
        _usersAccessObject      = Users.getInstance().getAccessObject(User.class);
        _coursesAccessObject    = Courses.getInstance().getAccessObject(Course.class);
        _professorsAccessObject = Professors.getInstance().getAccessObject(Professor.class);
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

    public ArrayList<Course> retrieveAllCoursesByStudentId(int id) throws SQLException {
        List<IModel> enrollments =
                _enrollmentAccessObject
                        .queryBuilder()
                        .where()
                        .eq("student_id", id)
                        .query()
        ;
        if(enrollments.size() <= 0) return null;
        ArrayList<Course> courses = new ArrayList<>();
        for(IModel model : enrollments) {
            Enroll enroll = (Enroll) model;
            _coursesAccessObject.refresh(enroll.getCourse());
            _professorsAccessObject.refresh(enroll.getCourse().getProfessor());
            _usersAccessObject.refresh(enroll.getCourse().getProfessor().getUser());
            courses.add(enroll.getCourse());
        }
        return courses;
    }
}
