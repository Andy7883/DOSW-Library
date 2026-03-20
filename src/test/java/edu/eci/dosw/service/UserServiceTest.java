package edu.eci.dosw.service;

import edu.eci.dosw.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void shouldAddAndRetrieveUser() {
        User user = new User("U1", "Alice");

        userService.addUser(user);

        assertEquals(1, userService.getAllUsers().size());
        assertEquals(user, userService.getUserById("U1"));
    }

    @Test
    void shouldReturnNullWhenUserDoesNotExist() {
        User result = userService.getUserById("NON_EXISTENT");

        assertNull(result);
    }
}