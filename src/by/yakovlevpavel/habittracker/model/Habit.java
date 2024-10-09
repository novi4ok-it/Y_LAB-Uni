package by.yakovlevpavel.habittracker.model;

import java.util.Date;

public class Habit {
    private int id;
    private String name;
    private String description;
    private String frequency;
    private Date date;
    private int userId;

    public Habit(String name, String description, String frequency, Date date, int userId) {
        this.name = name;
        this.description = description;
        this.frequency = frequency;
        this.userId = userId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
