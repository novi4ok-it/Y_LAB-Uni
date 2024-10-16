package by.yakovlevpavel.habittracker.service;

import by.yakovlevpavel.habittracker.model.Habit;
import by.yakovlevpavel.habittracker.model.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

//public class ReminderService {
//
//    private final HabitService habitService;
//    private final LocalTime reminderTime;
////    private final UserService userService;
////
////    public ReminderService(HabitService habitService, LocalTime reminderTime, UserService userService) {
////        this.habitService = habitService;
////        this.reminderTime = reminderTime;
////       this.userService = userService;
////    }
//
//    public void sendReminders(LocalDate currentDate) {
//        Map<Integer, Habit> allHabits = habitService.getAllHabits();
//
//        allHabits.values().forEach(habit -> {
//            if (shouldSendReminder(habit)) {
//                User user = userService.getUserById(habit.getUserId());
//                sendConsoleReminder(user, habit, currentDate);
//            }
//        });
//    }
//
//    private boolean shouldSendReminder(Habit habit) {
//        return habit.getFrequency().equals("Daily") && LocalTime.now().equals(reminderTime);
//    }
//
//    private void sendConsoleReminder(User user, Habit habit, LocalDate currentDate) {
//        System.out.println("Напоминание для " + user.getUsername() + ":");
//        System.out.println("Не забудьте выполнить привычку: " + habit.getDescription() +
//                " сегодня, " + currentDate + ".");
//    }
//}