package by.yakovlevpavel.habittracker.service;

import by.yakovlevpavel.habittracker.model.Habit;
import by.yakovlevpavel.habittracker.model.HabitRecord;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

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

    public static Habit getHabitById(int habitId) {
        return habits.get(habitId);
    }

    public static void updateHabit(Habit habit) {
        if (habits.containsKey(habit.getId())) {
            habits.put(habit.getId(), habit);
        } else {
            return;
        }
    }

    public void deleteHabit(int habitId) {
        habits.remove(habitId);
    }

    public static List<Habit> getHabitsByUserId(int userId) {
        List<Habit> habitsOfThisUser = new ArrayList<>(habits.values());
        habitsOfThisUser.removeIf(habit -> habit.getUserId() != userId);
        habitsOfThisUser.sort(Comparator.comparing(Habit::getDate));

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

    public int getCompletionRateForPeriodForThisHabit(int habitId, LocalDate start, LocalDate end) {
        List<HabitRecord> records = getHabitRecordsByHabitId(habitId);
        List<Date> dates = records.stream()
                .map(HabitRecord::getDate)
                .toList();

        if (dates.isEmpty()) {
            return 0;
        }

        long daysInPeriod = ChronoUnit.DAYS.between(start, end) + 1;
        int completedDays = 0;

        for (Date date : dates) {
            LocalDate recordDate = LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
            if (recordDate.isAfter(start.minusDays(1)) && recordDate.isBefore(end.plusDays(1))) {
                completedDays++;
            }
        }
        //nagovnocodil, sorry <3
        return (int) Math.round(((double) completedDays / daysInPeriod) * 100);
    }

    public int getCurrentStreakForHabit(int habitId) {
        List<HabitRecord> records = getHabitRecordsByHabitId(habitId);
        if (records.isEmpty()) {
            return 0;
        }

        records.sort(Comparator.comparing(HabitRecord::getDate));

        int currentStreak = 0;
        LocalDate lastCompletedDate = null;

        for (HabitRecord record : records) {
            LocalDate recordDate = LocalDate.ofInstant(record.getDate().toInstant(), ZoneId.systemDefault());
            if (record.isCompleted()) {
                if (lastCompletedDate == null || recordDate.isEqual(lastCompletedDate.plusDays(1))) {
                    currentStreak++;
                    lastCompletedDate = recordDate;
                } else {
                    currentStreak = 1;
                    lastCompletedDate = recordDate;
                }
            } else {
                currentStreak = 0;
            }
        }
        return currentStreak;
    }
    public Map<Integer, Habit> getAllHabits() {
        return habits;
    }
}
