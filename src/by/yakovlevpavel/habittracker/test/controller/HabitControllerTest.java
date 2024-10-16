package by.yakovlevpavel.habittracker.test.controller;

import by.yakovlevpavel.habittracker.controller.HabitController;
import by.yakovlevpavel.habittracker.model.Habit;
import by.yakovlevpavel.habittracker.service.HabitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class HabitControllerTest {

    private HabitService habitService;
    private HabitController habitController;

    @BeforeEach
    void setUp() {
        habitService = new HabitService();
        habitController = new HabitController();
    }

    @Test
    void createHabit_shouldCreateHabitAndReturnIt() {
        String name = "Running";
        String description = "Run for 30 minutes";
        String frequency = "Daily";
        Date date = new Date();
        int userId = 1;

        Habit habit = habitController.createHabit(name, description, frequency, date, userId);

        assertNotNull(habit);
        assertEquals(name, habit.getName());
        assertEquals(description, habit.getDescription());
        assertEquals(frequency, habit.getFrequency());
        assertEquals(date, habit.getDate());
        assertEquals(userId, habit.getUserId());
    }

    @Test
    void getHabitById_shouldReturnHabitIfPresent() {
        int habitId = 1;
        Habit habit = new Habit("Running", "Run for 30 minutes", "Daily", new Date(), 1);
        habitService.createHabit(habit, habit.getUserId()); // Создание привычки в HabitService

        Habit result = habitController.getHabitById(habitId);

        assertEquals(habit, result);
    }

    @Test
    void deleteHabit_shouldDeleteHabit() {
        int habitId = 1;
        Habit habit = new Habit("Running", "Run for 30 minutes", "Daily", new Date(), 1);
        habitService.createHabit(habit, habit.getUserId()); // Создание привычки в HabitService

        assertTrue(habitController.deleteHabit(habitId));

        assertNull(habitService.getHabitById(habitId));
    }
}