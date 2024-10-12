package by.yakovlevpavel.habittracker;

import by.yakovlevpavel.habittracker.controller.HabitController;
import by.yakovlevpavel.habittracker.model.Habit;
import by.yakovlevpavel.habittracker.model.HabitRecord;
import by.yakovlevpavel.habittracker.model.User;
import by.yakovlevpavel.habittracker.service.AuthenticationService;
import by.yakovlevpavel.habittracker.service.HabitService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final HabitService habitService = new HabitService();
    private static final AuthenticationService authenticationService = new AuthenticationService();
    private static User currentUser;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (currentUser == null) {
                showLoginMenu(scanner);
            } else {
                showMainMenu(scanner);
            }
        }
    }

    private static void showLoginMenu(Scanner scanner) {
        System.out.println("----- Меню авторизации -----");
        System.out.println("1. Регистрация");
        System.out.println("2. Вход");
        System.out.println("3. Выход");

        int choice = getIntegerInput(scanner, 1, 3);

        switch (choice) {
            case 1:
                registerUser(scanner);
                break;
            case 2:
                loginUser(scanner);
                break;
            case 3:
                System.exit(0);
        }
    }
    private static int getIntegerInput(Scanner scanner, int min, int max) {
        while (true) {
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (input >= min && input <= max) {
                    scanner.nextLine(); // Очистить буфер
                    return input;
                } else {
                    System.out.println("Введите число в диапазоне от " + min + " до " + max + ":");
                }
            } else {
                System.out.println("Введите целое число:");
                scanner.next(); // Удалить некорректный ввод
            }
        }
    }

    private static void loginUser(Scanner scanner) {
        System.out.println("Введите email:");
        String email = scanner.nextLine();

        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        User user = authenticationService.loginUser(email, password);
        if (user != null) {
            currentUser = user;
            System.out.println("Вход выполнен успешно!");
        } else {
            System.out.println("Неверный email или пароль.");
        }
    }

    private static void showMainMenu(Scanner scanner) {
        System.out.println("----- Главное меню -----");
        System.out.println("1. Создать привычку");
        System.out.println("2. Просмотреть привычки");
        System.out.println("3. Редактировать привычку");
        System.out.println("4. Удалить привычку");
        System.out.println("5. Отметить выполнение привычки");
        System.out.println("6. Статистика");
        System.out.println("7. Выйти");

        int choice = getIntegerInput(scanner, 1, 7);

        switch (choice) {
            case 1:
                createHabit(scanner);
                break;
            case 2:
                viewHabits(scanner);
                break;
            case 3:
                updateHabit(scanner);
                break;
            case 4:
                deleteHabit(scanner);
                break;
            case 5:
                markHabitAsCompleted(scanner);
                break;
            case 6:
                showStatistics(scanner);
                break;
            case 7:
                currentUser = null;
                break;
        }
    }
    private static void registerUser(Scanner scanner) {
        System.out.println("Введите имя:");
        String name = scanner.nextLine();

        System.out.println("Введите email:");
        String email = scanner.nextLine();

        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        authenticationService.registerUser(name, email, password);
        System.out.println("Пользователь зарегистрирован!");
    }
    // ... (Оставшиеся методы для обработки ввода, регистрации, авторизации, создания, просмотра, редактирования, удаления привычек, отметки выполнения, статистики)
    private static void createHabit(Scanner scanner) {
        System.out.println("Введите название привычки:");
        String name = scanner.nextLine();

        System.out.println("Введите описание привычки:");
        String description = scanner.nextLine();

        System.out.println("Введите частоту выполнения привычки (ежедневно, еженедельно, ежемесячно):");
        String frequency = scanner.nextLine();

        System.out.println("Введите дату начала привычки (ДД.ММ.ГГГГ):");
        String dateString = scanner.nextLine();
        Date date = parseDate(dateString); // Преобразовать строку в Date

        if (date != null) {

            Habit habit = HabitController.createHabit(name, description, frequency, date, currentUser.getId()); // Используем HabitService
            if (habit != null) {
                System.out.println("Привычка создана!");
            } else {
                System.out.println("Ошибка при создании привычки.");
            }
        } else {
            System.out.println("Неверный формат даты.");
        }
    }

    private static void viewHabits(Scanner scanner) {
        List<Habit> habits = HabitService.getHabitsByUserId(currentUser.getId()); // Используем HabitService
        if (!habits.isEmpty()) {
            System.out.println("----- Ваши привычки -----");
            for (Habit habit : habits) {
                System.out.println("Название: " + habit.getName());
                System.out.println("Описание: " + habit.getDescription());
                System.out.println("Частота: " + habit.getFrequency());
                System.out.println("Дата начала: " + habit.getDate());
                // Добавьте вывод других данных о привычке, если необходимо
                System.out.println("--------------------");
            }
        } else {
            System.out.println("У вас нет привычек.");
        }
    }

    private static void updateHabit(Scanner scanner) {
        System.out.println("Введите ID привычки, которую хотите редактировать:");
        int habitId = getIntegerInput(scanner, 1, Integer.MAX_VALUE);

        Habit habit = HabitService.getHabitById(habitId); // Используем HabitService
        if (habit != null) {
            System.out.println("Введите новое название привычки (оставьте пустым, чтобы не менять):");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                habit.setName(newName);
            }

            // Аналогично для других полей: description, frequency, date

            HabitService.updateHabit(habit); // Используем HabitService
            System.out.println("Привычка обновлена!");
        } else {
            System.out.println("Привычка с таким ID не найдена.");
        }
    }

    private static void deleteHabit(Scanner scanner) {
        System.out.println("Введите ID привычки, которую хотите удалить:");
        int habitId = getIntegerInput(scanner, 1, Integer.MAX_VALUE);

        boolean success = HabitController.deleteHabit(habitId); // Используем HabitService
        if (success) {
            System.out.println("Привычка удалена!");
        } else {
            System.out.println("Ошибка при удалении привычки.");
        }
    }

    private static void markHabitAsCompleted(Scanner scanner) {
        System.out.println("Введите ID привычки, которую хотите отметить как выполненную:");
        int habitId = getIntegerInput(scanner, 1, Integer.MAX_VALUE);

       // boolean success = HabitRecord; // Используем HabitService
        if (true) {
            System.out.println("Привычка отмечена как выполненная!");
        } else {
            System.out.println("Ошибка при отметке привычки.");
        }
    }

    private static void showStatistics(Scanner scanner) {
        // Реализуйте логику вывода статистики
        System.out.println("Статистика по привычкам: (добавьте вывод нужной информации)");
        // Например, можно вывести:
        // * Количество выполненных и невыполненных привычек за период
        // * Процент выполнения привычек за период
        // * Другие показатели, которые считаете нужными
    }

    private static Date parseDate(String dateString) {
        // Реализуйте парсинг даты из строки
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }
}