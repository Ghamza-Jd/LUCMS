package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import services.IModel;

@DatabaseTable(tableName = "enroll")
public class Enroll implements IModel {
    @DatabaseField(id = true)
    private String id;
    @DatabaseField(canBeNull = false, foreign = true)
    private Student student;
    @DatabaseField(canBeNull = false, foreign = true)
    private Course course;
    @DatabaseField
    private float grade;
    @DatabaseField
    private boolean assigned;

    public Enroll() { }

    public Enroll(Student student, Course course) {
        this.id = String.format("s%dc%d", student.getId(), course.getId());
        this.course = course;
        this.student = student;
        this.grade = 0;
        this.assigned = false;
    }

    public String getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }
}
