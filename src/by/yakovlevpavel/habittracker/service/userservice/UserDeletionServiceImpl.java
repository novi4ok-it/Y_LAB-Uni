package by.yakovlevpavel.habittracker.service.userservice;

import by.yakovlevpavel.habittracker.model.User;

import java.util.Map;

public class UserDeletionServiceImpl implements UserDeletionService {
    private final Map<Integer, User> users;

    public UserDeletionServiceImpl(Map<Integer, User> users) {
        this.users = users;
    }

    @Override
    public void deleteUser(int userId) {
        users.remove(userId);
    }
}
