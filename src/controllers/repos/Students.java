package controllers.repos;

import com.j256.ormlite.dao.Dao;
import models.Student;
import models.User;
import services.IModel;
import services.Persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class Students extends Persistence {
    private static Students _students;
    private final Dao<IModel, String> _studentsAccessObject;
    private final Dao<IModel, String> _usersAccessObject;

    private Students() throws SQLException {
        _studentsAccessObject = getAccessObject(Student.class);
        _usersAccessObject = Users.getInstance().getAccessObject(User.class);
    }

    public static Students getInstance() throws SQLException {
        if(_students == null) _students = new Students();
        return _students;
    }

    @Override
    public void create(IModel model) throws SQLException {
        final Student student = (Student) model;
        final User user = student.getUser();
        user.setRole("STUDENT");
        student.setUser(user);
        _usersAccessObject.create(user);
        _studentsAccessObject.create(student);
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
        final Student student = (Student) model;
        _studentsAccessObject.delete(student);
        _usersAccessObject.delete(student.getUser());
    }

    public Student retrieveSingle(User user) throws SQLException {
        final List<IModel> students =
                _studentsAccessObject
                        .queryBuilder()
                        .where()
                        .eq("user_id", user)
                        .query()
        ;
        if(students.size() > 0) {
            final IModel student = students.get(0);
            _usersAccessObject.refresh(((Student) student).getUser());
            return (Student) student;
        }
        return null;
    }

    public Student retrieveSingleByFileNb(int fileNb) throws SQLException {
        final List<IModel> students =
                _studentsAccessObject
                        .queryBuilder()
                        .where()
                        .eq("id", fileNb)
                        .query()
        ;
        if(students.size() > 0) {
            final IModel student = students.get(0);
            _usersAccessObject.refresh(((Student) student).getUser());
            return (Student) student;
        }
        return null;
    }

    public Student retrieveSingleByUsername(String username) throws SQLException {
        final List<IModel> users =
                _usersAccessObject
                        .queryBuilder()
                        .where()
                        .eq("normalizedUsername", username.toUpperCase())
                        .query()
        ;
        if(users.size() > 0) {
            final User user = (User) users.get(0);
            return user.getRole().equals("STUDENT") ? retrieveSingleByFileNb((user).getId()) : null;
        }
        return null;
    }

    public ArrayList<Student> retrieveAllStudents() throws SQLException {
        final List<IModel> models = _studentsAccessObject.queryBuilder().query();
        final ArrayList<Student> students = new ArrayList<>();
        for(IModel m : models) {
            final Student student = (Student) m;
            _usersAccessObject.refresh(student.getUser());
            students.add(student);
        }
        return students;
    }
}
