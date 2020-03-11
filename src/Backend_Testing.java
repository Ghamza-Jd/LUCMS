import controllers.repos.Professors;
import controllers.repos.Students;
import models.Professor;
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
    }
}
