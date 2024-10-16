package by.yakovlevpavel.habittracker.service.userservice;

import by.yakovlevpavel.habittracker.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserCreationServiceImpl implements UserCreationService {
    private final Map<Integer, User> users = new HashMap<>();
    private int userCounter = 0;

    @Override
    public User createUser(User user) {
        if (users.values().stream().anyMatch(u -> u.getEmail().equals(user.getEmail()))) {
            throw new IllegalArgumentException("Пользователь с таким email уже существует.");
        }
        user.setId(userCounter++);
        users.put(user.getId(), user);
        return user;
    }
}
