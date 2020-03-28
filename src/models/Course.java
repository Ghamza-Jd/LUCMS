package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import services.IModel;

@DatabaseTable(tableName = "course")
public class Course implements IModel {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(foreign = true, uniqueIndex = true)
    private Professor professor;
    @DatabaseField
    private String code;
    @DatabaseField
    private String name;
    @DatabaseField
    private int numberOfCredits;
    @DatabaseField
    private String language;

    public Course() { }

    public Course(String code, String name, int numberOfCredits, String language, Professor professor) {
        this.code = code;
        this.name = name;
        this.numberOfCredits = numberOfCredits;
        this.language = language;
        this.professor = professor;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    public void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String printCourse() {
        return String.format("%s %s %s", getCode(), getName(), getLanguage());
    }
}
