package by.yakovlevpavel.habittracker.service;

import by.yakovlevpavel.habittracker.model.Habit;
import by.yakovlevpavel.habittracker.model.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class ReminderService {

    private final HabitService habitService;
    private final LocalTime reminderTime;// Время отправки напоминания
    private final UserService userService;

    public ReminderService(HabitService habitService, LocalTime reminderTime, UserService userService) {
        this.habitService = habitService;
        this.reminderTime = reminderTime; // Храним время отправки напоминания
        this.userService = userService;
    }

    public void sendReminders(LocalDate currentDate) {
        // 1. Получаем список привычек для всех пользователей
        Map<Integer, Habit> allHabits = habitService.getAllHabits();

        // 2. Итерируем по привычкам
        allHabits.values().forEach(habit -> {
            // 3. Проверяем, нужно ли отправлять напоминание для этой привычки
            if (shouldSendReminder(habit)) {
                // 4. Получаем пользователя, которому нужно отправить напоминание
                User user = userService.getUserById(habit.getUserId());

                // 5. Отправляем напоминание в консоль
                sendConsoleReminder(user, habit, currentDate);
            }
        });
    }

    private boolean shouldSendReminder(Habit habit) {
        // Проверяем, нужно ли отправить напоминание для этой привычки
        // Напоминание отправляется только один раз в день в заданное время
        return habit.getFrequency().equals("Daily") && LocalTime.now().equals(reminderTime);
    }

    private void sendConsoleReminder(User user, Habit habit, LocalDate currentDate) {
        // Отправляем напоминание в консоль
        System.out.println("Напоминание для " + user.getUsername() + ":");
        System.out.println("Не забудьте выполнить привычку: " + habit.getDescription() +
                " сегодня, " + currentDate + ".");
    }
}