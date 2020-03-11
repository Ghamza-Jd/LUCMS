package controllers.repos;

import com.j256.ormlite.dao.Dao;
import models.Student;
import models.User;
import services.IModel;
import services.Persistence;

import java.sql.SQLException;
import java.util.List;

public class Students extends Persistence {
    private static Students _students;
    private Dao<IModel, String> _studentsAccessObject;
    private Dao<IModel, String> _usersAccessObject;

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
        Student student = (Student) model;
        User user = student.getUser();
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

    }

    public Student retrieveSingle(User user) throws SQLException {
        List<IModel> students =
                _studentsAccessObject
                        .queryBuilder()
                        .where()
                        .eq("user_id", user)
                        .query();
        if(students.size() > 0) {
            IModel student = students.get(0);
            _usersAccessObject.refresh(((Student) student).getUser());
            return (Student) student;
        }
        return null;
    }
}
