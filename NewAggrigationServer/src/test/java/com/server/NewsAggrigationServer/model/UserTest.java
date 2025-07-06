package com.server.NewsAggrigationServer.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testUserCreation() {
        assertNotNull(user);
    }

    @Test
    void testIdGetterAndSetter() {
        Integer expectedId = 1;
        user.setId(expectedId);
        assertEquals(expectedId, user.getId());
    }

    @Test
    void testUsernameGetterAndSetter() {
        String expectedUsername = "testuser";
        user.setUsername(expectedUsername);
        assertEquals(expectedUsername, user.getUsername());
    }

    @Test
    void testEmailGetterAndSetter() {
        String expectedEmail = "test@example.com";
        user.setEmail(expectedEmail);
        assertEquals(expectedEmail, user.getEmail());
    }

    @Test
    void testPasswordGetterAndSetter() {
        String expectedPassword = "password123";
        user.setPassword(expectedPassword);
        assertEquals(expectedPassword, user.getPassword());
    }

    @Test
    void testRoleGetterAndSetter() {
        String expectedRole = "USER";
        user.setRole(expectedRole);
        assertEquals(expectedRole, user.getRole());
    }

    @Test
    void testUserWithAllFields() {
        Integer id = 1;
        String username = "testuser";
        String email = "test@example.com";
        String password = "password123";
        String role = "USER";

        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);

        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(role, user.getRole());
    }

    @Test
    void testUserWithNullValues() {
        user.setId(null);
        user.setUsername(null);
        user.setEmail(null);
        user.setPassword(null);
        user.setRole(null);

        assertNull(user.getId());
        assertNull(user.getUsername());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
        assertNull(user.getRole());
    }
} 