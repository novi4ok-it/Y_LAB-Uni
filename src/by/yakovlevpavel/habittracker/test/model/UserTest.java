package by.yakovlevpavel.habittracker.test.model;

import by.yakovlevpavel.habittracker.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void testUserConstructor() {
        String username = "JohnDoe";
        String email = "john.doe@example.com";
        String password = "password123";

        User user = new User(username, email, password);

        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
    }

    @Test
    void testSettersAndGetters() {
        User user = new User("JaneDoe", "jane.doe@example.com", "secret123");

        user.setId(1);
        assertEquals(1, user.getId());

        user.setUsername("JaneSmith");
        assertEquals("JaneSmith", user.getUsername());

        user.setEmail("jane.smith@example.com");
        assertEquals("jane.smith@example.com", user.getEmail());

        user.setPassword("newpassword");
        assertEquals("newpassword", user.getPassword());
    }
}
