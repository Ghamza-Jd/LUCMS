package controllers.repos;

import com.j256.ormlite.dao.Dao;
import models.*;
import services.IModel;
import services.Persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class Enrollment extends Persistence {
    private static Enrollment   _enrollment;
    private final Dao<IModel, String> _enrollmentAccessObject;
    private final Dao<IModel, String> _professorsAccessObject;
    private final Dao<IModel, String> _coursesAccessObject;
    private final Dao<IModel, String> _usersAccessObject;
    private final Dao<IModel, String> _studentsAccessObject;

    private Enrollment() throws SQLException {
        _enrollmentAccessObject = getAccessObject(Enroll.class);
        _usersAccessObject      = Users.getInstance().getAccessObject(User.class);
        _coursesAccessObject    = Courses.getInstance().getAccessObject(Course.class);
        _studentsAccessObject   = Students.getInstance().getAccessObject(Student.class);
        _professorsAccessObject = Professors.getInstance().getAccessObject(Professor.class);
    }

    public static Enrollment getInstance() throws SQLException {
        if(_enrollment == null) _enrollment = new Enrollment();
        return _enrollment;
    }

    @Override
    public void create(IModel model) throws SQLException {
        final Enroll enroll = (Enroll) model;
        _enrollmentAccessObject.create(enroll);
    }

    @Override
    public List<IModel> retrieveAll() throws SQLException {
        return null;
    }

    @Override
    public void update(IModel model) throws SQLException {
        Enroll enroll = (Enroll) model;
        enroll.setAssigned(true);
        _enrollmentAccessObject.update(model);
    }

    @Override
    public void delete(IModel model) throws SQLException {

    }

    public ArrayList<Course> retrieveAllCoursesByStudentId(int id) throws SQLException {
        final List<IModel> enrollments =
                _enrollmentAccessObject
                        .queryBuilder()
                        .where()
                        .eq("student_id", id)
                        .query()
        ;
        if(enrollments.size() <= 0) return null;
        final ArrayList<Course> courses = new ArrayList<>();
        for(IModel model : enrollments) {
            final Enroll enroll = (Enroll) model;
            _coursesAccessObject.refresh(enroll.getCourse());
            _professorsAccessObject.refresh(enroll.getCourse().getProfessor());
            _usersAccessObject.refresh(enroll.getCourse().getProfessor().getUser());
            courses.add(enroll.getCourse());
        }
        return courses;
    }

    public ArrayList<Enroll> retrieveAllGradesByStudentsId(int id) throws SQLException {
        final List<IModel> enrollments =
                _enrollmentAccessObject
                        .queryBuilder()
                        .where()
                        .eq("student_id", id)
                        .query()
                ;
        if(enrollments.size() <= 0) return null;
        final ArrayList<Enroll> enrolls = new ArrayList<>();
        for(IModel model : enrollments) {
            final Enroll enroll = (Enroll) model;
            if(enroll.isAssigned()) {
                _coursesAccessObject.refresh(enroll.getCourse());
                enrolls.add(enroll);
            }
        }
        return enrolls;
    }

    public ArrayList<Enroll> retrieveStudentsByCourseCode(String code) throws SQLException {
        final Course course = Courses.getInstance().retrieveCourseByCode(code);
        final List<IModel> enrollments =
                _enrollmentAccessObject
                        .queryBuilder()
                        .where()
                        .eq("course_id", course.getId())
                        .query()
        ;
        if(enrollments.size() <= 0) return null;
        final ArrayList<Enroll> enrolls = new ArrayList<>();
        for(IModel model : enrollments) {
            final Enroll enroll = (Enroll) model;
            _coursesAccessObject.refresh(enroll.getCourse());
            _studentsAccessObject.refresh(enroll.getStudent());
            _usersAccessObject.refresh(enroll.getStudent().getUser());
            enrolls.add(enroll);
        }
        return enrolls;
    }

    public Enroll retrieveEnrollmentById(String id) throws SQLException {
        final List<IModel> enrollments = _enrollmentAccessObject.queryBuilder().where().eq("id", id).query();
        if(enrollments.size() > 0) {
            Enroll enroll = (Enroll) enrollments.get(0);
            _coursesAccessObject.refresh(enroll.getCourse());
            _studentsAccessObject.refresh(enroll.getStudent());
            _usersAccessObject.refresh(enroll.getStudent().getUser());
            return enroll;
        }
        return null;
    }
}
