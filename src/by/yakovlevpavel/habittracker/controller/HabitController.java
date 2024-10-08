package by.yakovlevpavel.habittracker.controller;

import by.yakovlevpavel.habittracker.model.Habit;
import by.yakovlevpavel.habittracker.service.HabitService;

public class HabitController {

    private final HabitService habitService = new HabitService();

    public Habit createHabit(String name, String description, String frequency, int userId) {
        Habit habit = new Habit(name, description, frequency, userId);
        return habitService.createHabit(habit, userId);
    }

    public Habit getHabitById(int habitId) {
        return habitService.getHabitById(habitId);
    }

    public void updateHabit(int userId, String name, String description, String frequency) {
        Habit habit = new Habit(name, description, frequency, userId);
        habitService.updateHabit(habit);
    }

    public void deleteHabit(int habitId) {
        habitService.deleteHabit(habitId);
    }
}