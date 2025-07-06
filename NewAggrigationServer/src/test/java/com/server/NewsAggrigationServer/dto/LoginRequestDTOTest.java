package com.server.NewsAggrigationServer.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginRequestDTOTest {

    private LoginRequestDTO loginRequestDTO;

    @BeforeEach
    void setUp() {
        loginRequestDTO = new LoginRequestDTO();
    }

    @Test
    void testLoginRequestDTOCreation() {
        assertNotNull(loginRequestDTO);
    }

    @Test
    void testUsernameGetterAndSetter() {
        String expectedUsername = "testuser";
        loginRequestDTO.setUsername(expectedUsername);
        assertEquals(expectedUsername, loginRequestDTO.getUsername());
    }

    @Test
    void testPasswordGetterAndSetter() {
        String expectedPassword = "password123";
        loginRequestDTO.setPassword(expectedPassword);
        assertEquals(expectedPassword, loginRequestDTO.getPassword());
    }

    @Test
    void testLoginRequestDTOWithAllFields() {
        String username = "testuser";
        String password = "password123";

        loginRequestDTO.setUsername(username);
        loginRequestDTO.setPassword(password);

        assertEquals(username, loginRequestDTO.getUsername());
        assertEquals(password, loginRequestDTO.getPassword());
    }

    @Test
    void testLoginRequestDTOWithNullValues() {
        loginRequestDTO.setUsername(null);
        loginRequestDTO.setPassword(null);

        assertNull(loginRequestDTO.getUsername());
        assertNull(loginRequestDTO.getPassword());
    }

    @Test
    void testLoginRequestDTOWithEmptyStrings() {
        loginRequestDTO.setUsername("");
        loginRequestDTO.setPassword("");

        assertEquals("", loginRequestDTO.getUsername());
        assertEquals("", loginRequestDTO.getPassword());
    }

    @Test
    void testLoginRequestDTOWithSpecialCharacters() {
        String usernameWithSpecialChars = "user@domain.com";
        String passwordWithSpecialChars = "pass@word#123!";

        loginRequestDTO.setUsername(usernameWithSpecialChars);
        loginRequestDTO.setPassword(passwordWithSpecialChars);

        assertEquals(usernameWithSpecialChars, loginRequestDTO.getUsername());
        assertEquals(passwordWithSpecialChars, loginRequestDTO.getPassword());
    }

    @Test
    void testLoginRequestDTOWithUnicodeCharacters() {
        String usernameWithUnicode = "user_émojis_🚀";
        String passwordWithUnicode = "pass_émojis_🚀_123";

        loginRequestDTO.setUsername(usernameWithUnicode);
        loginRequestDTO.setPassword(passwordWithUnicode);

        assertEquals(usernameWithUnicode, loginRequestDTO.getUsername());
        assertEquals(passwordWithUnicode, loginRequestDTO.getPassword());
    }

    @Test
    void testLoginRequestDTOWithLongStrings() {
        String longUsername = "very_long_username_that_contains_many_characters_and_should_be_properly_handled";
        String longPassword = "very_long_password_that_contains_many_characters_and_should_be_properly_handled_with_numbers_123_and_symbols_@#$%";

        loginRequestDTO.setUsername(longUsername);
        loginRequestDTO.setPassword(longPassword);

        assertEquals(longUsername, loginRequestDTO.getUsername());
        assertEquals(longPassword, loginRequestDTO.getPassword());
    }

    @Test
    void testLoginRequestDTOWithWhitespace() {
        String usernameWithWhitespace = "  testuser  ";
        String passwordWithWhitespace = "  password123  ";

        loginRequestDTO.setUsername(usernameWithWhitespace);
        loginRequestDTO.setPassword(passwordWithWhitespace);

        assertEquals(usernameWithWhitespace, loginRequestDTO.getUsername());
        assertEquals(passwordWithWhitespace, loginRequestDTO.getPassword());
    }

    @Test
    void testLoginRequestDTOWithNewlines() {
        String usernameWithNewlines = "test\nuser";
        String passwordWithNewlines = "pass\nword";

        loginRequestDTO.setUsername(usernameWithNewlines);
        loginRequestDTO.setPassword(passwordWithNewlines);

        assertEquals(usernameWithNewlines, loginRequestDTO.getUsername());
        assertEquals(passwordWithNewlines, loginRequestDTO.getPassword());
    }

    @Test
    void testLoginRequestDTOWithNumbers() {
        String usernameWithNumbers = "user123";
        String passwordWithNumbers = "123456";

        loginRequestDTO.setUsername(usernameWithNumbers);
        loginRequestDTO.setPassword(passwordWithNumbers);

        assertEquals(usernameWithNumbers, loginRequestDTO.getUsername());
        assertEquals(passwordWithNumbers, loginRequestDTO.getPassword());
    }

    @Test
    void testLoginRequestDTOWithMixedCase() {
        String usernameWithMixedCase = "TestUser";
        String passwordWithMixedCase = "PaSsWoRd123";

        loginRequestDTO.setUsername(usernameWithMixedCase);
        loginRequestDTO.setPassword(passwordWithMixedCase);

        assertEquals(usernameWithMixedCase, loginRequestDTO.getUsername());
        assertEquals(passwordWithMixedCase, loginRequestDTO.getPassword());
    }
} 