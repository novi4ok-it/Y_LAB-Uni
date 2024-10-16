package by.yakovlevpavel.habittracker.service.userservice;

import by.yakovlevpavel.habittracker.model.User;

import java.util.Map;
import java.util.Optional;

public class UserRetrievalServiceImpl implements UserRetrievalService {
    private final Map<Integer, User> users;
    public Map<Integer, User> getUsers() {
        return users;
    }
    public UserRetrievalServiceImpl(Map<Integer, User> users) {
        this.users = users;
    }

    @Override
    public Optional<User> getUserById(int userId) {
        return Optional.ofNullable(users.get(userId));
    }

    public void addUser(User user) {
        // Проверяем, есть ли уже пользователь с таким же ID
        if (users.containsKey(user.getId())) {
            throw new IllegalArgumentException("Пользователь с таким ID уже существует.");
        }

        users.put(user.getId(), user);
    }
}
