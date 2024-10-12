package by.yakovlevpavel.habittracker.service.userservice;

import by.yakovlevpavel.habittracker.model.User;
import by.yakovlevpavel.habittracker.service.userservice.UserUpdateService;

import java.util.*;

// Реализация сервиса обновления пользователя
class UserUpdateServiceImpl implements UserUpdateService {
    private final Map<Integer, User> users;

    public UserUpdateServiceImpl(Map<Integer, User> users) {
        this.users = users;
    }

    @Override
    public void updateUser(User user) {
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
        }
    }
}

