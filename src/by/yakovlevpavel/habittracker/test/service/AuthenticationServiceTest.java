package by.yakovlevpavel.habittracker.test.service;
import by.yakovlevpavel.habittracker.model.User;
import by.yakovlevpavel.habittracker.service.authenticationservice.AuthenticationService;
import by.yakovlevpavel.habittracker.service.userservice.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationServiceTest {

    private UserCreationService userCreationService;
    private UserRetrievalService userRetrievalService;
    private UserBlockingService userBlockingService;
    private UserBlockedStatusService userBlockedStatusService;
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        userCreationService = new UserCreationServiceImpl(); // Реальная реализация
        userRetrievalService = new UserRetrievalServiceImpl(new HashMap<>()); // Реальная реализация
        Map<String, Boolean> blockedUsers = new HashMap<>();
        userBlockedStatusService = new UserBlockedStatusServiceImpl(blockedUsers); // Реальная реализация

        authenticationService = new AuthenticationService(
                userCreationService,
                userRetrievalService,
                userBlockedStatusService
        );
    }

    @Test
    void loginUser_shouldReturnUserIfCredentialsAreCorrect() {
        String email = "john.doe@example.com";
        String password = "password";
        User user = new User("John Doe", email, password);
        userRetrievalService.addUser(user); // Добавляем пользователя

        User loginUser = authenticationService.loginUser(email, password);

        assertEquals(user, loginUser);
    }

    @Test
    void loginUser_shouldReturnNullIfCredentialsAreIncorrect() {
        String email = "john.doe@example.com";
        String password = "wrong_password";
        User user = new User("John Doe", email, "password");
        userRetrievalService.addUser(user); // Добавляем пользователя

        User loginUser = authenticationService.loginUser(email, password);

        assertNull(loginUser);
    }

    @Test
    void loginUser_shouldReturnNullIfUserNotFound() {
        String email = "john.doe@example.com";
        String password = "password";

        User loginUser = authenticationService.loginUser(email, password);

        assertNull(loginUser);
    }
}