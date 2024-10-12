package by.yakovlevpavel.habittracker.service;

import by.yakovlevpavel.habittracker.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AuthenticationService {
    private static final UserService userService = new UserService();
    private static final List<User> users = new ArrayList<>();
    private static  final Map<String, Boolean> blockedUsers = UserService.getBlockedUsers();
    public void registerUser(String name, String email, String password) {
        User user = new User(name, email, password);
        users.add(user);
        userService.createUser(user);
    }
    public User loginUser(String email, String password) {
        if (blockedUsers.containsKey(email)) {
            return null;
        }
        Optional<User> foundUser = users.stream().filter(u -> u.getEmail().equals(email)).findFirst();
        if (password.equals(foundUser.get().getPassword())) {
            return foundUser.get();
        }
        return null;
    }
}
