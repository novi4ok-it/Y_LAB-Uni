package by.yakovlevpavel.habittracker.test.service;

import by.yakovlevpavel.habittracker.model.Habit;
import by.yakovlevpavel.habittracker.model.HabitRecord;
import by.yakovlevpavel.habittracker.service.HabitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HabitServiceTest {

    private HabitService habitService;

    @BeforeEach
    void setUp() {
        habitService = new HabitService();
    }

    @Test
    void getCurrentStreakForHabit_shouldReturnZeroIfNoRecords() {
        int habitId = 1;

        int streak = habitService.getCurrentStreakForHabit(habitId);

        assertEquals(0, streak);
    }

    @Test
    void getCurrentStreakForHabit_shouldReturnCorrectStreakForConsecutiveCompletedRecords() {
        int habitId = 1;

        // Записи: 26.10, 27.10, 28.10 (завершённые)
        Date recordDate1 = Date.from(LocalDate.of(2023, 10, 26).atStartOfDay(ZoneId.systemDefault()).toInstant());
        HabitRecord record1 = new HabitRecord(habitId,true, recordDate1);
        habitService.createHabitRecord(record1);

        Date recordDate2 = Date.from(LocalDate.of(2023, 10, 27).atStartOfDay(ZoneId.systemDefault()).toInstant());
        HabitRecord record2 = new HabitRecord(habitId,true, recordDate2);
        habitService.createHabitRecord(record2);

        Date recordDate3 = Date.from(LocalDate.of(2023, 10, 28).atStartOfDay(ZoneId.systemDefault()).toInstant());
        HabitRecord record3 = new HabitRecord(habitId,true, recordDate3);
        habitService.createHabitRecord(record3);

        int streak = habitService.getCurrentStreakForHabit(habitId);

        assertEquals(3, streak);
    }

    @Test
    void getCurrentStreakForHabit_shouldReturnOneIfStreakBroken() {
        int habitId = 1;

        // Записи: 26.10, 27.10 (завершённые), 29.10 (не завершённая), 30.10 (завершённая)
        Date recordDate1 = Date.from(LocalDate.of(2023, 10, 26).atStartOfDay(ZoneId.systemDefault()).toInstant());
        HabitRecord record1 = new HabitRecord(habitId,true, recordDate1);
        habitService.createHabitRecord(record1);

        Date recordDate2 = Date.from(LocalDate.of(2023, 10, 27).atStartOfDay(ZoneId.systemDefault()).toInstant());
        HabitRecord record2 = new HabitRecord(habitId,true, recordDate2);
        habitService.createHabitRecord(record2);

        Date recordDate3 = Date.from(LocalDate.of(2023, 10, 29).atStartOfDay(ZoneId.systemDefault()).toInstant());
        HabitRecord record3 = new HabitRecord(habitId,false, recordDate3); // Не завершенная
        habitService.createHabitRecord(record3);

        Date recordDate4 = Date.from(LocalDate.of(2023, 10, 30).atStartOfDay(ZoneId.systemDefault()).toInstant());
        HabitRecord record4 = new HabitRecord(habitId,true, recordDate4);
        habitService.createHabitRecord(record4);

        int streak = habitService.getCurrentStreakForHabit(habitId);

        assertEquals(1, streak);
    }

    @Test
    void getAllHabits_shouldReturnAllHabits() {
        int userId = 1;
        Habit habit1 = new Habit("Running", "Run for 30 minutes", "Daily", new Date(), userId);
        habitService.createHabit(habit1, userId);
        Habit habit2 = new Habit("Reading", "Read for 1 hour", "Daily", new Date(), userId);
        habitService.createHabit(habit2, userId);

        List<Habit> habits = habitService.getAllHabits().values().stream().toList();

        assertEquals(2, habits.size());
        assertTrue(habits.contains(habit1));
        assertTrue(habits.contains(habit2));
    }
}
