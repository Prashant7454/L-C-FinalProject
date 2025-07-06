package com.server.NewsAggrigationServer.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {

    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        userDTO = new UserDTO();
    }

    @Test
    void testUserDTOCreation() {
        assertNotNull(userDTO);
    }

    @Test
    void testIdGetterAndSetter() {
        Integer expectedId = 1;
        userDTO.setId(expectedId);
        assertEquals(expectedId, userDTO.getId());
    }

    @Test
    void testUsernameGetterAndSetter() {
        String expectedUsername = "testuser";
        userDTO.setUsername(expectedUsername);
        assertEquals(expectedUsername, userDTO.getUsername());
    }

    @Test
    void testEmailGetterAndSetter() {
        String expectedEmail = "test@example.com";
        userDTO.setEmail(expectedEmail);
        assertEquals(expectedEmail, userDTO.getEmail());
    }

    @Test
    void testPasswordGetterAndSetter() {
        String expectedPassword = "password123";
        userDTO.setPassword(expectedPassword);
        assertEquals(expectedPassword, userDTO.getPassword());
    }

    @Test
    void testRoleGetterAndSetter() {
        String expectedRole = "USER";
        userDTO.setRole(expectedRole);
        assertEquals(expectedRole, userDTO.getRole());
    }

    @Test
    void testUserDTOWithAllFields() {
        Integer id = 1;
        String username = "testuser";
        String email = "test@example.com";
        String password = "password123";
        String role = "ADMIN";

        userDTO.setId(id);
        userDTO.setUsername(username);
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        userDTO.setRole(role);

        assertEquals(id, userDTO.getId());
        assertEquals(username, userDTO.getUsername());
        assertEquals(email, userDTO.getEmail());
        assertEquals(password, userDTO.getPassword());
        assertEquals(role, userDTO.getRole());
    }

    @Test
    void testUserDTOWithNullValues() {
        userDTO.setId(null);
        userDTO.setUsername(null);
        userDTO.setEmail(null);
        userDTO.setPassword(null);
        userDTO.setRole(null);

        assertNull(userDTO.getId());
        assertNull(userDTO.getUsername());
        assertNull(userDTO.getEmail());
        assertNull(userDTO.getPassword());
        assertNull(userDTO.getRole());
    }

    @Test
    void testUserDTOWithEmptyStrings() {
        userDTO.setUsername("");
        userDTO.setEmail("");
        userDTO.setPassword("");
        userDTO.setRole("");

        assertEquals("", userDTO.getUsername());
        assertEquals("", userDTO.getEmail());
        assertEquals("", userDTO.getPassword());
        assertEquals("", userDTO.getRole());
    }

    @Test
    void testUserDTOWithSpecialCharacters() {
        String usernameWithSpecial = "test_user-123";
        String emailWithSpecial = "test+user@example.com";
        String passwordWithSpecial = "pass@word#123";
        String roleWithSpecial = "SUPER_ADMIN";

        userDTO.setUsername(usernameWithSpecial);
        userDTO.setEmail(emailWithSpecial);
        userDTO.setPassword(passwordWithSpecial);
        userDTO.setRole(roleWithSpecial);

        assertEquals(usernameWithSpecial, userDTO.getUsername());
        assertEquals(emailWithSpecial, userDTO.getEmail());
        assertEquals(passwordWithSpecial, userDTO.getPassword());
        assertEquals(roleWithSpecial, userDTO.getRole());
    }
} 