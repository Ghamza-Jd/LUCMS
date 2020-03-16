package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import services.IModel;

@DatabaseTable(tableName = "student_affairs")
public class StudentsAffair implements IModel {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(canBeNull = false, foreign = true)
    private User user;
    @DatabaseField
    private String email;

    public StudentsAffair() { }

    public StudentsAffair(User user, String email) {
        this.user = user;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
