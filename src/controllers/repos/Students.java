package controllers.repos;

import com.j256.ormlite.dao.Dao;
import models.Student;
import models.User;
import services.DatabaseHandler;

import java.sql.SQLException;
import java.util.List;

public class Students {
    private Dao<User, String> userDao;
    private Dao<Student, String> studentDao;
    private static Students _students;
    private Students() throws SQLException {
        userDao = DatabaseHandler.getInstance().getUserDao();
        studentDao = DatabaseHandler.getInstance().getStudentDao();
    }
    public static Students getDao() throws SQLException {
        if(_students == null) _students = new Students();
        return _students;
    }
    public void createStudent(Student student) throws SQLException {
        User user = student.getUser();
        user.setRole("STUDENT");
        student.setUser(user);
        userDao.create(user);
        studentDao.create(student);
    }
    public Student getStudent(User user) throws SQLException {
        List<Student> students = studentDao.queryBuilder().where().eq("user_id", user).query();
        if(students.size() > 0) {
            Student student = students.get(0);
            userDao.refresh(student.getUser());
            return student;
        }
        return null;
    }
}
