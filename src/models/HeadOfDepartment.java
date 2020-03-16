package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import services.IModel;

@DatabaseTable(tableName = "head_of_department")
public class HeadOfDepartment implements IModel {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(canBeNull = false, foreign = true)
    private User user;
    @DatabaseField
    private String department;

    public HeadOfDepartment() { }

    public HeadOfDepartment(User user, String department) {
        this.user = user;
        this.department = department;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
