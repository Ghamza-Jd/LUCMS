import controllers.repos.*;
import models.*;

import java.sql.SQLException;
import java.util.Date;

public class DummyData {
    public static void main(String[] args) throws SQLException {
        User noura = new User(
                "Noura",
                "Amine",
                "Joudieh",
                "N.Joudieh",
                "P@ssw0rd",
                "78939614",
                new Date(),
                "Female"
        );
        Student s1 = new Student(noura, "Computer science");
        Students.getInstance().create(s1);

        User zeina = new User(
                "Zeina",
                "Amine",
                "Joudieh",
                "Z.Joudieh",
                "P@ssw0rd",
                "78939614",
                new Date(),
                "Female"
        );
        Student s2 = new Student(zeina, "Geology");
        Students.getInstance().create(s2);

        User hamza = new User(
                "Hamza",
                "Abdallah",
                "Jadid",
                "H.Jadid",
                "P@ssw0rd",
                "78939614",
                new Date(),
                "Male"
        );
        Student s3 = new Student(hamza, "Computer science");
        Students.getInstance().create(s3);

        User batoul = new User(
                "Batoul",
                "Husien",
                "Amhaz",
                "B.Amhaz",
                "P@ssw0rd",
                "78939614",
                new Date(),
                "Female"
        );
        Student s4 = new Student(batoul, "Biology");
        Students.getInstance().create(s4);

        User hadi = new User(
                "Hadi",
                "Ahmad",
                "Nasser",
                "H.Nasser",
                "P@ssw0rd",
                "78939614",
                new Date(),
                "Male"
        );
        Student s5 = new Student(hadi, "Computer science");
        Students.getInstance().create(s5);

        User mohammad = new User(
                "Mohammad",
                "Masoud",
                "Bareq",
                "M.Bareq",
                "P@ssw0rd",
                "78939614",
                new Date(),
                "Male"
        );
        Student s6 = new Student(mohammad, "Computer science");
        Students.getInstance().create(s6);

        User reem = new User(
                "Reem",
                "Ali",
                "Atwi",
                "R.Atwi",
                "P@ssw0rd",
                "78544789",
                new Date(),
                "Female"
        );
        Student s7 = new Student(reem, "Computer science");
        Students.getInstance().create(s7);

        User noor = new User(
                "Noor",
                "Ali",
                "Choukair",
                "N.Choukair",
                "P@ssw0rd",
                "78544789",
                new Date(),
                "Female"
        );
        Student s8 = new Student(noor, "Computer science");
        Students.getInstance().create(s8);

        User siba = new User(
                "Siba",
                "Mahmoud",
                "Haidar",
                "S.Haidar",
                "P@ssw0rd",
                "78939614",
                new Date(),
                "Female"
        );
        Professor p1 = new Professor(siba, 214);
        Professors.getInstance().create(p1);

        User mdbouk = new User(
                "Mohammad",
                "Hussien",
                "Dbouk",
                "M.Dbouk",
                "P@ssw0rd",
                "78939614",
                new Date(),
                "Male"
        );
        Professor p2 = new Professor(mdbouk, 214);
        Professors.getInstance().create(p2);

        User sbiety = new User(
                "Ihab",
                "Hussien",
                "Sbiety",
                "I.Sbeity",
                "P@ssw0rd",
                "78939614",
                new Date(),
                "Male"
        );
        Professor p3 = new Professor(sbiety, 214);
        Professors.getInstance().create(p3);


        User ali = new User(
                "Ali",
                "Hussien",
                "Hajj Hassan",
                "A.HajjHassan",
                "P@ssw0rd",
                "78939614",
                new Date(),
                "Male"
        );
        StudentsAffair sa = new StudentsAffair(ali, "hajjhassan@gmail.com");
        StudentsAffairs.getInstance().create(sa);

        User raafat = new User(
                "Raafat",
                "Karim",
                "Tarraf",
                "R.Tarraf",
                "P@ssw0rd",
                "78939614",
                new Date(),
                "Male"
        );
        HeadOfDepartment hod = new HeadOfDepartment(raafat, "Applied Math");
        HoDs.getInstance().create(hod);

        News dangerNews = new News(
                "Postponing Semester 2!",
                "The administration of the Lebanese University decided to postpone the second semester " +
                        "because of the pandemic of corona virus. Stay at home and take care",
                "Urgent");
        News warningNews = new News(
                "Evaluate your Professors",
                "In order to check your marks you have to evaluate the professor that taught you a specific course.",
                "Important");
        News infoNews = new News(
                "Congrats for LU!",
                "The Lebanese University wins third place in IBA-2020 competition in Bahrain.",
                "Info");
        NewsRepo.getInstance().create(dangerNews);
        NewsRepo.getInstance().create(warningNews);
        NewsRepo.getInstance().create(infoNews);
    }
}
