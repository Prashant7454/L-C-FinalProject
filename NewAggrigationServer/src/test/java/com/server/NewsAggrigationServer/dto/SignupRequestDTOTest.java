package com.server.NewsAggrigationServer.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SignupRequestDTOTest {

    private SignupRequestDTO signupRequestDTO;

    @BeforeEach
    void setUp() {
        signupRequestDTO = new SignupRequestDTO();
    }

    @Test
    void testSignupRequestDTOCreation() {
        assertNotNull(signupRequestDTO);
    }

    @Test
    void testUsernameGetterAndSetter() {
        String expectedUsername = "newuser";
        signupRequestDTO.setUsername(expectedUsername);
        assertEquals(expectedUsername, signupRequestDTO.getUsername());
    }

    @Test
    void testEmailGetterAndSetter() {
        String expectedEmail = "newuser@example.com";
        signupRequestDTO.setEmail(expectedEmail);
        assertEquals(expectedEmail, signupRequestDTO.getEmail());
    }

    @Test
    void testPasswordGetterAndSetter() {
        String expectedPassword = "password123";
        signupRequestDTO.setPassword(expectedPassword);
        assertEquals(expectedPassword, signupRequestDTO.getPassword());
    }

    @Test
    void testConfirmPasswordGetterAndSetter() {
        String expectedConfirmPassword = "password123";
        signupRequestDTO.setConfirmPassword(expectedConfirmPassword);
        assertEquals(expectedConfirmPassword, signupRequestDTO.getConfirmPassword());
    }

    @Test
    void testSignupRequestDTOWithAllFields() {
        String username = "newuser";
        String email = "newuser@example.com";
        String password = "password123";
        String confirmPassword = "password123";

        signupRequestDTO.setUsername(username);
        signupRequestDTO.setEmail(email);
        signupRequestDTO.setPassword(password);
        signupRequestDTO.setConfirmPassword(confirmPassword);

        assertEquals(username, signupRequestDTO.getUsername());
        assertEquals(email, signupRequestDTO.getEmail());
        assertEquals(password, signupRequestDTO.getPassword());
        assertEquals(confirmPassword, signupRequestDTO.getConfirmPassword());
    }

    @Test
    void testSignupRequestDTOWithNullValues() {
        signupRequestDTO.setUsername(null);
        signupRequestDTO.setEmail(null);
        signupRequestDTO.setPassword(null);
        signupRequestDTO.setConfirmPassword(null);

        assertNull(signupRequestDTO.getUsername());
        assertNull(signupRequestDTO.getEmail());
        assertNull(signupRequestDTO.getPassword());
        assertNull(signupRequestDTO.getConfirmPassword());
    }

    @Test
    void testSignupRequestDTOWithEmptyStrings() {
        signupRequestDTO.setUsername("");
        signupRequestDTO.setEmail("");
        signupRequestDTO.setPassword("");
        signupRequestDTO.setConfirmPassword("");

        assertEquals("", signupRequestDTO.getUsername());
        assertEquals("", signupRequestDTO.getEmail());
        assertEquals("", signupRequestDTO.getPassword());
        assertEquals("", signupRequestDTO.getConfirmPassword());
    }

    @Test
    void testSignupRequestDTOWithSpecialCharacters() {
        String usernameWithSpecialChars = "user@domain.com";
        String emailWithSpecialChars = "user+tag@domain.com";
        String passwordWithSpecialChars = "pass@word#123!";
        String confirmPasswordWithSpecialChars = "pass@word#123!";

        signupRequestDTO.setUsername(usernameWithSpecialChars);
        signupRequestDTO.setEmail(emailWithSpecialChars);
        signupRequestDTO.setPassword(passwordWithSpecialChars);
        signupRequestDTO.setConfirmPassword(confirmPasswordWithSpecialChars);

        assertEquals(usernameWithSpecialChars, signupRequestDTO.getUsername());
        assertEquals(emailWithSpecialChars, signupRequestDTO.getEmail());
        assertEquals(passwordWithSpecialChars, signupRequestDTO.getPassword());
        assertEquals(confirmPasswordWithSpecialChars, signupRequestDTO.getConfirmPassword());
    }

    @Test
    void testSignupRequestDTOWithUnicodeCharacters() {
        String usernameWithUnicode = "user_émojis_🚀";
        String emailWithUnicode = "user_émojis_🚀@domain.com";
        String passwordWithUnicode = "pass_émojis_🚀_123";
        String confirmPasswordWithUnicode = "pass_émojis_🚀_123";

        signupRequestDTO.setUsername(usernameWithUnicode);
        signupRequestDTO.setEmail(emailWithUnicode);
        signupRequestDTO.setPassword(passwordWithUnicode);
        signupRequestDTO.setConfirmPassword(confirmPasswordWithUnicode);

        assertEquals(usernameWithUnicode, signupRequestDTO.getUsername());
        assertEquals(emailWithUnicode, signupRequestDTO.getEmail());
        assertEquals(passwordWithUnicode, signupRequestDTO.getPassword());
        assertEquals(confirmPasswordWithUnicode, signupRequestDTO.getConfirmPassword());
    }

    @Test
    void testSignupRequestDTOWithLongStrings() {
        String longUsername = "very_long_username_that_contains_many_characters_and_should_be_properly_handled";
        String longEmail = "very_long_email_address_that_contains_many_characters_and_should_be_properly_handled@very_long_domain_name.com";
        String longPassword = "very_long_password_that_contains_many_characters_and_should_be_properly_handled_with_numbers_123_and_symbols_@#$%";
        String longConfirmPassword = "very_long_password_that_contains_many_characters_and_should_be_properly_handled_with_numbers_123_and_symbols_@#$%";

        signupRequestDTO.setUsername(longUsername);
        signupRequestDTO.setEmail(longEmail);
        signupRequestDTO.setPassword(longPassword);
        signupRequestDTO.setConfirmPassword(longConfirmPassword);

        assertEquals(longUsername, signupRequestDTO.getUsername());
        assertEquals(longEmail, signupRequestDTO.getEmail());
        assertEquals(longPassword, signupRequestDTO.getPassword());
        assertEquals(longConfirmPassword, signupRequestDTO.getConfirmPassword());
    }

    @Test
    void testSignupRequestDTOWithWhitespace() {
        String usernameWithWhitespace = "  newuser  ";
        String emailWithWhitespace = "  newuser@example.com  ";
        String passwordWithWhitespace = "  password123  ";
        String confirmPasswordWithWhitespace = "  password123  ";

        signupRequestDTO.setUsername(usernameWithWhitespace);
        signupRequestDTO.setEmail(emailWithWhitespace);
        signupRequestDTO.setPassword(passwordWithWhitespace);
        signupRequestDTO.setConfirmPassword(confirmPasswordWithWhitespace);

        assertEquals(usernameWithWhitespace, signupRequestDTO.getUsername());
        assertEquals(emailWithWhitespace, signupRequestDTO.getEmail());
        assertEquals(passwordWithWhitespace, signupRequestDTO.getPassword());
        assertEquals(confirmPasswordWithWhitespace, signupRequestDTO.getConfirmPassword());
    }

    @Test
    void testSignupRequestDTOWithNewlines() {
        String usernameWithNewlines = "new\nuser";
        String emailWithNewlines = "new\nuser@example.com";
        String passwordWithNewlines = "pass\nword";
        String confirmPasswordWithNewlines = "pass\nword";

        signupRequestDTO.setUsername(usernameWithNewlines);
        signupRequestDTO.setEmail(emailWithNewlines);
        signupRequestDTO.setPassword(passwordWithNewlines);
        signupRequestDTO.setConfirmPassword(confirmPasswordWithNewlines);

        assertEquals(usernameWithNewlines, signupRequestDTO.getUsername());
        assertEquals(emailWithNewlines, signupRequestDTO.getEmail());
        assertEquals(passwordWithNewlines, signupRequestDTO.getPassword());
        assertEquals(confirmPasswordWithNewlines, signupRequestDTO.getConfirmPassword());
    }

    @Test
    void testSignupRequestDTOWithNumbers() {
        String usernameWithNumbers = "user123";
        String emailWithNumbers = "user123@example.com";
        String passwordWithNumbers = "123456";
        String confirmPasswordWithNumbers = "123456";

        signupRequestDTO.setUsername(usernameWithNumbers);
        signupRequestDTO.setEmail(emailWithNumbers);
        signupRequestDTO.setPassword(passwordWithNumbers);
        signupRequestDTO.setConfirmPassword(confirmPasswordWithNumbers);

        assertEquals(usernameWithNumbers, signupRequestDTO.getUsername());
        assertEquals(emailWithNumbers, signupRequestDTO.getEmail());
        assertEquals(passwordWithNumbers, signupRequestDTO.getPassword());
        assertEquals(confirmPasswordWithNumbers, signupRequestDTO.getConfirmPassword());
    }

    @Test
    void testSignupRequestDTOWithMixedCase() {
        String usernameWithMixedCase = "NewUser";
        String emailWithMixedCase = "NewUser@Example.com";
        String passwordWithMixedCase = "PaSsWoRd123";
        String confirmPasswordWithMixedCase = "PaSsWoRd123";

        signupRequestDTO.setUsername(usernameWithMixedCase);
        signupRequestDTO.setEmail(emailWithMixedCase);
        signupRequestDTO.setPassword(passwordWithMixedCase);
        signupRequestDTO.setConfirmPassword(confirmPasswordWithMixedCase);

        assertEquals(usernameWithMixedCase, signupRequestDTO.getUsername());
        assertEquals(emailWithMixedCase, signupRequestDTO.getEmail());
        assertEquals(passwordWithMixedCase, signupRequestDTO.getPassword());
        assertEquals(confirmPasswordWithMixedCase, signupRequestDTO.getConfirmPassword());
    }

    @Test
    void testSignupRequestDTOWithDifferentEmailFormats() {
        String[] emailFormats = {
            "user@domain.com",
            "user.name@domain.com",
            "user+tag@domain.com",
            "user@subdomain.domain.com",
            "user@domain.co.uk",
            "user@domain-name.com"
        };

        for (String email : emailFormats) {
            signupRequestDTO.setEmail(email);
            assertEquals(email, signupRequestDTO.getEmail());
        }
    }

    @Test
    void testSignupRequestDTOWithMismatchedPasswords() {
        String password = "password123";
        String differentConfirmPassword = "differentpassword";

        signupRequestDTO.setPassword(password);
        signupRequestDTO.setConfirmPassword(differentConfirmPassword);

        assertEquals(password, signupRequestDTO.getPassword());
        assertEquals(differentConfirmPassword, signupRequestDTO.getConfirmPassword());
        assertNotEquals(signupRequestDTO.getPassword(), signupRequestDTO.getConfirmPassword());
    }
} 