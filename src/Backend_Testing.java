import controllers.repos.Students;
import controllers.repos.Users;
import models.Student;
import models.User;
import services.Session;

import java.sql.SQLException;
import java.util.Date;

public class Backend_Testing {
    public static void main(String[] args) throws SQLException {
        User user = new User(
                "Hamza",
                "Abdallah",
                "Jadid",
                "hamzajd",
                "P@ssw0rd",
                "78939614",
                new Date()
        );
        Users.getDao().createUser(user);
        Student student = new Student(
                user,
                "Computer Science"
        );
        Students.getDao().createStudent(student);
    }
}
