package by.yakovlevpavel.habittracker.test.service;

import by.yakovlevpavel.habittracker.model.User;
import by.yakovlevpavel.habittracker.service.userservice.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceInterfaceTests {

    @Test
    void userBlockedStatusService_isUserBlocked_shouldReturnFalseByDefault() {
        UserBlockedStatusService service = new UserBlockedStatusServiceImpl(new HashMap<>());
        assertFalse(service.isUserBlocked("test@example.com"));
    }

    @Test
    void userCreationService_createUser_shouldReturnUserWithId() {
        UserCreationService service = new UserCreationServiceImpl();
        User user = new User("Test User", "test@example.com", "password");
        User createdUser = service.createUser(user);
        assertNotNull(createdUser.getId());
        assertEquals(user.getUsername(), createdUser.getUsername());
        assertEquals(user.getEmail(), createdUser.getEmail());
        assertEquals(user.getPassword(), createdUser.getPassword());
    }

    @Test
    void userDeletionService_deleteUser_shouldRemoveUserFromMap() {
        Map<Integer, User> users = new HashMap<>();
        users.put(1, new User("Test User", "test@example.com", "password"));
        UserDeletionService service = new UserDeletionServiceImpl(users);
        service.deleteUser(1);
        assertFalse(users.containsKey(1));
    }

    @Test
    void userRetrievalService_getUserById_shouldReturnUserIfPresent() {
        Map<Integer, User> users = new HashMap<>();
        users.put(1, new User("Test User", "test@example.com", "password"));
        UserRetrievalService service = new UserRetrievalServiceImpl(users);
        Optional<User> foundUser = service.getUserById(1);
        assertTrue(foundUser.isPresent());
        assertEquals(users.get(1), foundUser.get());
    }

    @Test
    void userRetrievalService_getUserById_shouldReturnEmptyOptionalIfUserNotFound() {
        Map<Integer, User> users = new HashMap<>();
        UserRetrievalService service = new UserRetrievalServiceImpl(users);
        Optional<User> foundUser = service.getUserById(1);
        assertFalse(foundUser.isPresent());
    }

    @Test
    void userRetrievalService_getUsers_shouldReturnAllUsers() {
        Map<Integer, User> users = new HashMap<>();
        users.put(1, new User("Test User", "test@example.com", "password"));
        users.put(2, new User("Test User2", "test@example.com", "password"));
        UserRetrievalService service = new UserRetrievalServiceImpl(users);
        Map<Integer, User> retrievedUsers = service.getUsers();
        assertEquals(users, retrievedUsers);
    }

    @Test
    void userRetrievalService_addUser_shouldAddAUserToMap() {
        Map<Integer, User> users = new HashMap<>();
        UserRetrievalService service = new UserRetrievalServiceImpl(users);
        User user = new User ("Test User", "test@example.com", "password");
        service.addUser(user);
        assertEquals(user, users.get(user.getId()));
    }

    @Test
    void userUnblockingService_unblockUser_shouldRemoveUserFromBlockedList() {
        Map<String, Boolean> blockedUsers = new HashMap<>();
        blockedUsers.put("test@example.com", true);
        UserUnblockingService service = new UserUnblockingServiceImpl(blockedUsers);
        service.unblockUser("test@example.com");
        assertFalse(blockedUsers.containsKey("test@example.com"));
    }

    @Test
    void userUpdateService_updateUser_shouldUpdateExistingUser() {
        Map<Integer, User> users = new HashMap<>();
        User user = new User("Test User", "test@example.com", "password");
        users.put(user.getId(), user);
        UserUpdateService service = new UserUpdateServiceImpl(users);
        User updatedUser = new User("Test User", "test@example.com", "password");
        service.updateUser(updatedUser);
        assertEquals(updatedUser, users.get(updatedUser.getId()));
    }
}