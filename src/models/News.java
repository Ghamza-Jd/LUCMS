package models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import javafx.fxml.FXML;
import services.IModel;

import java.text.SimpleDateFormat;
import java.util.Date;

@DatabaseTable(tableName = "news")
public class News implements IModel {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String title;
    @DatabaseField
    private String body;
    @DatabaseField
    private String level;
    @DatabaseField(dataType = DataType.DATE)
    private Date date;

    public News() { }

    public News(String title, String body, String level) {
        this.title = title;
        this.body = body;
        this.level = level;
        this.date = new Date();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDate() {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(date);
    }
}
