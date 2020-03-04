package controllers.repos;

import com.j256.ormlite.dao.Dao;
import models.Student;
import models.User;
import services.DatabaseHandler;

import java.sql.SQLException;

public class Students {
    private Dao<Student, String> studentDao;
    private static Students _students;
    private Students() throws SQLException {
        studentDao = DatabaseHandler.getDatabaseHandler().getStudentDao();
    }
    public static Students getDao() throws SQLException {
        if(_students == null) _students = new Students();
        return _students;
    }
    public void createStudent(Student student) throws SQLException {
        studentDao.create(student);
    }
}
