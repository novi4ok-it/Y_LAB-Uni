package by.yakovlevpavel.habittracker.service;

import by.yakovlevpavel.habittracker.model.User;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthenticationService {
    private final UserService userService = new UserService();
    private static final List<User> users = new ArrayList<>();

    public void registerUser(String name, String email, String password) {
        User user = new User(name, email, password);
        users.add(user);
        userService.createUser(user);
    }
    public User loginUser(String email, String password) {
        Optional<User> foundUser = users.stream().filter(u -> u.getEmail().equals(email)).findFirst();
        if (password.equals(foundUser.get().getPassword())) {
            return foundUser.get();
        }
        return null;
    }
}
