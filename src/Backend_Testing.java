import controllers.repos.Professors;
import controllers.repos.Students;
import controllers.repos.StudentsAffairs;
import models.Professor;
import models.Student;
import models.StudentsAffair;
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
        Student student = new Student(user, "Comp science");
        Students.getInstance().create(student);

        User user2 = new User(
                "Noura",
                "Amine",
                "Joudieh",
                "rosa",
                "P@ssw0rd",
                "78939614",
                new Date()
        );
        Professor professor = new Professor(user2, 2214);
        Professors.getInstance().create(professor);

        User user3 = new User(
                "Sara",
                "Abdallah",
                "Jadid",
                "sarajd",
                "P@ssw0rd",
                "78939614",
                new Date()
        );
        StudentsAffair sa = new StudentsAffair(user3, "SJ@gmail.com");
        StudentsAffairs.getInstance().create(sa);
    }
}
