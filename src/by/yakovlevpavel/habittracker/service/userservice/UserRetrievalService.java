package by.yakovlevpavel.habittracker.service.userservice;

import by.yakovlevpavel.habittracker.model.User;

import java.util.Map;
import java.util.Optional;

public interface UserRetrievalService {
    Optional<User> getUserById(int userId);
    Map<Integer, User> getUsers();
    void addUser(User user);
}
