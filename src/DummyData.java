import controllers.repos.*;
import models.*;

import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

public class DummyData {
    public static void main(String[] args) throws SQLException {
        User noura = new User(
                "Noura",
                "Amine",
                "Joudieh",
                "N.Joudieh",
                "P@ssw0rd",
                generatePhoneNumber(),
                generateDate(true),
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
                generatePhoneNumber(),
                generateDate(true),
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
                generatePhoneNumber(),
                generateDate(true),
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
                generatePhoneNumber(),
                generateDate(true),
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
                generatePhoneNumber(),
                generateDate(true),
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
                generatePhoneNumber(),
                generateDate(true),
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
                generatePhoneNumber(),
                generateDate(true),
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
                generatePhoneNumber(),
                generateDate(true),
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
                generatePhoneNumber(),
                generateDate(false),
                "Female"
        );
        Professor p1 = new Professor(siba, generateOfficeNumber());
        Professors.getInstance().create(p1);

        User mdbouk = new User(
                "Mohammad",
                "Hussien",
                "Dbouk",
                "M.Dbouk",
                "P@ssw0rd",
                generatePhoneNumber(),
                generateDate(false),
                "Male"
        );
        Professor p2 = new Professor(mdbouk, generateOfficeNumber());
        Professors.getInstance().create(p2);

        User sbiety = new User(
                "Ihab",
                "Hussien",
                "Sbiety",
                "I.Sbeity",
                "P@ssw0rd",
                generatePhoneNumber(),
                generateDate(false),
                "Male"
        );
        Professor p3 = new Professor(sbiety, generateOfficeNumber());
        Professors.getInstance().create(p3);

        User bs = new User(
                "Bilal",
                "Adham",
                "Said",
                "B.Said",
                "P@ssw0rd",
                generatePhoneNumber(),
                generateDate(false),
                "Male"
        );
        Professor p4 = new Professor(bs, generateOfficeNumber());
        Professors.getInstance().create(p4);

        User kamal = new User(
                "Kamal",
                "Jamal",
                "Beydoun",
                "K.Beydoun",
                "P@ssw0rd",
                generatePhoneNumber(),
                generateDate(false),
                "Male"
        );
        Professor p5 = new Professor(kamal, generateOfficeNumber());
        Professors.getInstance().create(p5);

        Course c1 = new Course("I3350", "Mobile Dev", 5, "English", p1);
        Courses.getInstance().create(c1);

        User ali = new User(
                "Ali",
                "Hussien",
                "Hajj Hassan",
                "A.HajjHassan",
                "P@ssw0rd",
                generatePhoneNumber(),
                generateDate(false),
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
                generatePhoneNumber(),
                generateDate(false),
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

    public static String generatePhoneNumber() {
        final String [] prefixes = {"03", "70", "71", "76", "78"};
        final Random random = new Random();
        final StringBuilder s = new StringBuilder(prefixes[random.nextInt(prefixes.length)]);
        for(int i = 0; i < 6; i++) s.append(random.nextInt(10));
        return s.toString();
    }

    public static int generateOfficeNumber() {
        final Random random = new Random();
        return random.nextInt(10) + random.nextInt(10) * 10 + (random.nextInt(6) + 1) * 100;
    }

    public static Date generateDate(boolean isYoung) {
        final Random random = new Random();
        final int day = random.nextInt(28) + 1;
        final int month = random.nextInt(12) + 1;
        final int year = random.nextInt(6) + (isYoung ? 1995 : 1980);
        final LocalDate localDate = LocalDate.of(year, month, day);
        final Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        return Date.from(instant);
    }
}
