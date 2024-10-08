package by.yakovlevpavel.habittracker.service;

import by.yakovlevpavel.habittracker.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private static Map<Integer, User> users = new HashMap<>();
    private static List<String> usersEmail = new ArrayList<>();
    private static List<String> usersPasswords = new ArrayList<>();
    private static int userCounter = 0;

    public User createUser(User user) {
        if (usersEmail.contains(user.getEmail())) {
            throw new IllegalArgumentException("Пользователь с таким email уже существует.");
        }
        if (usersPasswords.contains(user.getPassword())) {
            throw new IllegalArgumentException("Придумайте другой пароль");
        }
        user.setId(userCounter++);
        users.put(user.getId(), user);
        return user;
    }

    public User getUserById(int userId) {
        return users.get(userId);
    }

    public void deleteUser(int userId) {
        users.remove(userId);
    }

    public void updateUser(User user) {
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
        }
        else {
            return;
        }
    }
}
