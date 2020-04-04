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
        Professor pSiba = new Professor(siba, generateOfficeNumber());
        Professors.getInstance().create(pSiba);

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
        Professor pdbouk = new Professor(mdbouk, generateOfficeNumber());
        Professors.getInstance().create(pdbouk);

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
        Professor psbeity = new Professor(sbiety, generateOfficeNumber());
        Professors.getInstance().create(psbeity);

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
        Professor psaid = new Professor(bs, generateOfficeNumber());
        Professors.getInstance().create(psaid);

        User bassem = new User(
                "Bassem",
                "Melhem",
                "Haidar",
                "B.Haidar",
                "P@ssw0rd",
                generatePhoneNumber(),
                generateDate(false),
                "Male"
        );
        Professor pbassem = new Professor(bassem, generateOfficeNumber());
        Professors.getInstance().create(pbassem);

        User antoun = new User(
                "Antoun",
                "Jouseph",
                "Yaccoub",
                "A.Yaccoub",
                "P@ssw0rd",
                generatePhoneNumber(),
                generateDate(false),
                "Male"
        );
        Professor pantoun = new Professor(antoun, generateOfficeNumber());
        Professors.getInstance().create(pantoun);

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
        Professor pkamal = new Professor(kamal, generateOfficeNumber());
        Professors.getInstance().create(pkamal);

        User zein = new User(
                "Zein",
                "Marwan",
                "Ibrain",
                "Z.Ibrahim",
                "P@ssw0rd",
                generatePhoneNumber(),
                generateDate(false),
                "Male"
        );
        Professor pzein = new Professor(zein, generateOfficeNumber());
        Professors.getInstance().create(pzein);

        User rami = new User(
                "Rami",
                "Sami",
                "Baida",
                "R.Baida",
                "P@ssw0rd",
                generatePhoneNumber(),
                generateDate(false),
                "Male"
        );
        Professor prami = new Professor(rami, generateOfficeNumber());
        Professors.getInstance().create(prami);

        User fadlallah = new User(
                "Ahmad",
                "Youssef",
                "Fadlallah",
                "A.Fadlallah",
                "P@ssw0rd",
                generatePhoneNumber(),
                generateDate(false),
                "Male"
        );
        Professor pfadlallah = new Professor(fadlallah, generateOfficeNumber());
        Professors.getInstance().create(pfadlallah);

        User aladin = new User(
                "Hussien",
                "Aladin",
                "Aladin",
                "H.Aladin",
                "P@ssw0rd",
                generatePhoneNumber(),
                generateDate(false),
                "Male"
        );
        Professor paladin = new Professor(aladin, generateOfficeNumber());
        Professors.getInstance().create(paladin);

        User may = new User(
                "May",
                "Atef",
                "Dhayne",
                "M.Dhayne",
                "P@ssw0rd",
                generatePhoneNumber(),
                generateDate(false),
                "Female"
        );
        Professor pmay = new Professor(may, generateOfficeNumber());
        Professors.getInstance().create(pmay);

        User hassan = new User(
                "Hassan",
                "Mohammad",
                "Tout",
                "H.Tout",
                "P@ssw0rd",
                generatePhoneNumber(),
                generateDate(false),
                "Male"
        );
        Professor phassan = new Professor(hassan, generateOfficeNumber());
        Professors.getInstance().create(phassan);

        User makke = new User(
                "Rani",
                "Rami",
                "Makke",
                "R.Makke",
                "P@ssw0rd",
                generatePhoneNumber(),
                generateDate(false),
                "Male"
        );
        Professor pmakke = new Professor(makke, generateOfficeNumber());
        Professors.getInstance().create(pmakke);

        User ghrayeb = new User(
                "Ali",
                "Mohammad",
                "Ghourayeb",
                "A.Ghourayeb",
                "P@ssw0rd",
                generatePhoneNumber(),
                generateDate(false),
                "Male"
        );
        Professor pghrayeb = new Professor(ghrayeb, generateOfficeNumber());
        Professors.getInstance().create(pghrayeb);

        User hamze = new User(
                "Mohammad",
                "Hassan",
                "Hamzet",
                "M.Hamzet",
                "P@ssw0rd",
                generatePhoneNumber(),
                generateDate(false),
                "Male"
        );
        Professor phamze = new Professor(hamze, generateOfficeNumber());
        Professors.getInstance().create(phamze);

        User safadi = new User(
                "Abed",
                "Hassan",
                "Safadi",
                "A.Safadi",
                "P@ssw0rd",
                generatePhoneNumber(),
                generateDate(false),
                "Male"
        );
        Professor psafadi = new Professor(safadi, generateOfficeNumber());
        Professors.getInstance().create(psafadi);

        User faour = new User(
                "Ahmad",
                "Mahmoud",
                "Faour",
                "A.Faour",
                "P@ssw0rd",
                generatePhoneNumber(),
                generateDate(false),
                "Male"
        );
        Professor pfaour = new Professor(faour, generateOfficeNumber());
        Professors.getInstance().create(pfaour);

        Course i3350 = new Course(
                "I3350",
                "Mobile Dev",
                5,
                "English",
                pSiba
        );
        Courses.getInstance().create(i3350);

        Course i2208 = new Course(
                "I2208",
                "Network 1",
                4,
                "English",
                pbassem
        );
        Courses.getInstance().create(i2208);

        Course i2210 = new Course(
                "I2210",
                "Databases 1",
                5,
                "English",
                pdbouk
        );
        Courses.getInstance().create(i2210);

        Course i2209 = new Course(
                "I2209",
                "Prolog",
                4,
                "English",
                pSiba
        );
        Courses.getInstance().create(i2209);

        Course i2206 = new Course(
                "I2206",
                "Data Structures",
                5,
                "English",
                pantoun
        );
        Courses.getInstance().create(i2206);

        Course i2211 = new Course(
                "I2211",
                "Object Oriented",
                5,
                "English",
                psbeity
        );
        Courses.getInstance().create(i2211);

        Course i2207 = new Course(
                "I2207",
                "Assembly",
                4,
                "English",
                pSiba
        );
        Courses.getInstance().create(i2207);

        Course i3307 = new Course(
                "I3307",
                "Theory of Computation",
                4,
                "English",
                psaid
        );
        Courses.getInstance().create(i3307);

        Course i3340 = new Course(
                "I3340",
                "Parallel Programming",
                4,
                "English",
                psaid
        );
        Courses.getInstance().create(i3340);

        Course i3344 = new Course(
                "I3344",
                "Modeling",
                6,
                "English",
                pSiba
        );
        Courses.getInstance().create(i3344);

        Course i3330 = new Course(
                "I3330",
                "Project Management",
                3,
                "English",
                pSiba
        );
        Courses.getInstance().create(i3330);

        Course i3333 = new Course(
                "I3333",
                "Image Synthesis",
                3,
                "English",
                pSiba
        );
        Courses.getInstance().create(i3333);

        Course i3341 = new Course(
                "I3341",
                "Advanced Algorithms",
                4,
                "English",
                pantoun
        );
        Courses.getInstance().create(i3341);

        Course i3332 = new Course(
                "I3332",
                "ASP.NET",
                3,
                "English",
                pkamal
        );
        Courses.getInstance().create(i3332);

        Course i3331 = new Course(
                "I3331",
                "Computer and Society",
                3,
                "English",
                pSiba
        );
        Courses.getInstance().create(i3331);

        Course i3306 = new Course(
                "I3306",
                "Databases 2",
                3,
                "English",
                pdbouk
        );
        Courses.getInstance().create(i3306);

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
