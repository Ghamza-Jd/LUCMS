import controllers.repos.Users;
import models.User;

import java.sql.SQLException;
import java.util.Date;

public class Testing {
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
    }
}
