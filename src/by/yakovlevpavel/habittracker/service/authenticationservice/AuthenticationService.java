package by.yakovlevpavel.habittracker.service.authenticationservice;

import by.yakovlevpavel.habittracker.model.User;
import by.yakovlevpavel.habittracker.service.userservice.UserBlockedStatusService;
import by.yakovlevpavel.habittracker.service.userservice.UserCreationService;
import by.yakovlevpavel.habittracker.service.userservice.UserRetrievalService;
import java.util.Map;
import java.util.Optional;

public class AuthenticationService {
    private final UserCreationService userCreationService;
    private final UserRetrievalService userRetrievalService;
    private final UserBlockedStatusService userBlockedStatusService;

    public AuthenticationService(UserCreationService userCreationService,
                                 UserRetrievalService userRetrievalService,
                                 UserBlockedStatusService userBlockedStatusService) {
        this.userCreationService = userCreationService;
        this.userRetrievalService = userRetrievalService;
        this.userBlockedStatusService = userBlockedStatusService;
    }

    public void registerUser(String name, String email, String password) {
        User user = new User(name, email, password);
        user = userCreationService.createUser(user);
        userRetrievalService.addUser(user);
    }

    public User loginUser(String email, String password) {
        if (userBlockedStatusService.isUserBlocked(email)) {
            return null;
        }
        Map<Integer, User> users = userRetrievalService.getUsers();
        Optional<User> foundUser = users.values().stream().filter(u -> u.getEmail().equals(email)).findFirst();
        if (password.equals(foundUser.get().getPassword())) {
            return foundUser.get();
        }
        return null;
    }
}
