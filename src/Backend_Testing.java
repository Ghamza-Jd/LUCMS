import controllers.repos.Students;
import controllers.repos.Users;
import models.Student;
import models.User;

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
        Student s = new Student(
                user,
                "Computer Science"
        );
        Students.getDao().createStudent(s);

        User ru = new User(
                "Noura",
                "Amine",
                "Joudieh",
                "Rosa",
                "P@ssw0rd",
                "78887888",
                new Date()
        );
        Student rs = new Student(
                ru,
                "Computer Science"
        );
        Students.getDao().createStudent(rs);
    }
}
