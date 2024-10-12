package by.yakovlevpavel.habittracker.controller;

import by.yakovlevpavel.habittracker.model.Habit;
import by.yakovlevpavel.habittracker.service.HabitService;

import java.util.Date;

public class HabitController {

    public static final HabitService habitService = new HabitService();

    public static Habit createHabit(String name, String description, String frequency, Date date, int userId) {
        Habit habit = new Habit(name, description, frequency, date, userId);
        return habitService.createHabit(habit, userId);
    }

    public Habit getHabitById(int habitId) {
        return habitService.getHabitById(habitId);
    }

    public void updateHabit(int userId, Date date, String name, String description, String frequency) {
        Habit habit = new Habit(name, description, frequency, date, userId);
        HabitService.updateHabit(habit);
    }

    public static boolean deleteHabit(int habitId) {
        habitService.deleteHabit(habitId);
        return true;
    }
}