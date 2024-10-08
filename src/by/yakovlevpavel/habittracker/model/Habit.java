package by.yakovlevpavel.habittracker.model;

public class Habit {
    private int id;
    private String name;
    private String description;
    private String frequency;
    private int userId;

    public Habit(String name, String description, String frequency, int userId) {
        this.name = name;
        this.description = description;
        this.frequency = frequency;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
