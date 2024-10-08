package by.yakovlevpavel.habittracker.service;

import by.yakovlevpavel.habittracker.model.Habit;
import by.yakovlevpavel.habittracker.model.HabitRecord;
import by.yakovlevpavel.habittracker.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HabitService {
    private static Map<Integer, Habit> habits = new HashMap<>();
    private static int habitsCounter = 0;
    private static Map<Integer, List<HabitRecord>> habitRecords = new HashMap<>();
    private static int habitRecordCounter = 0;

    public Habit createHabit(Habit habit, int userId) {
        habit.setId(habitsCounter++);
        habit.setUserId(userId);
        habits.put(habitsCounter, habit);
        return habit;
    }

    public Habit getHabitById(int habitId) {
        return habits.get(habitId);
    }

    public void updateHabit(Habit habit) {
        if (habits.containsKey(habit.getId())) {
            habits.put(habit.getId(), habit);
        } else {
            return;
        }
    }

    public void deleteHabit(int habitId) {
        habits.remove(habitId);
    }
    public List<Habit> getHabitsByUserId(int userId) {
        List<Habit> habitsOfThisUser = new ArrayList<>();
        for (Map.Entry<Integer, Habit> entry : habits.entrySet()) {
            Habit value = entry.getValue();
            if (value.getUserId() == userId) {
                habitsOfThisUser.add(value);
            }
        }
        return habitsOfThisUser;
    }

    public void createHabitRecord(HabitRecord habitRecord) {
        if (habitRecords.containsKey(habitRecord.getHabitId())) {
            habitRecords.get(habitRecord.getHabitId()).add(habitRecord);
        } else {
            List<HabitRecord> records = new ArrayList<>();
            records.add(habitRecord);
            habitRecords.put(habitRecord.getHabitId(), records);
        }
        habitRecord.setId(habitRecordCounter++);
    }

    public List<HabitRecord> getHabitRecordsByHabitId(int habitId) {
        return habitRecords.getOrDefault(habitId, new ArrayList<>());
    }


}
