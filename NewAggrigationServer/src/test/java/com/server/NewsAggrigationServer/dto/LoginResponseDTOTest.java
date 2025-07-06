package com.server.NewsAggrigationServer.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginResponseDTOTest {

    private LoginResponseDTO loginResponseDTO;

    @BeforeEach
    void setUp() {
        loginResponseDTO = new LoginResponseDTO();
    }

    @Test
    void testLoginResponseDTOCreation() {
        assertNotNull(loginResponseDTO);
    }

    @Test
    void testSuccessGetterAndSetter() {
        Boolean expectedSuccess = true;
        loginResponseDTO.setSuccess(expectedSuccess);
        assertEquals(expectedSuccess, loginResponseDTO.getSuccess());
    }

    @Test
    void testMessageGetterAndSetter() {
        String expectedMessage = "Login successful";
        loginResponseDTO.setMessage(expectedMessage);
        assertEquals(expectedMessage, loginResponseDTO.getMessage());
    }

    @Test
    void testUserIdGetterAndSetter() {
        Integer expectedUserId = 1;
        loginResponseDTO.setUserId(expectedUserId);
        assertEquals(expectedUserId, loginResponseDTO.getUserId());
    }

    @Test
    void testUsernameGetterAndSetter() {
        String expectedUsername = "testuser";
        loginResponseDTO.setUsername(expectedUsername);
        assertEquals(expectedUsername, loginResponseDTO.getUsername());
    }

    @Test
    void testRoleGetterAndSetter() {
        String expectedRole = "USER";
        loginResponseDTO.setRole(expectedRole);
        assertEquals(expectedRole, loginResponseDTO.getRole());
    }

    @Test
    void testLoginResponseDTOWithAllFields() {
        Boolean success = true;
        String message = "Login successful";
        Integer userId = 1;
        String username = "testuser";
        String role = "ADMIN";

        loginResponseDTO.setSuccess(success);
        loginResponseDTO.setMessage(message);
        loginResponseDTO.setUserId(userId);
        loginResponseDTO.setUsername(username);
        loginResponseDTO.setRole(role);

        assertEquals(success, loginResponseDTO.getSuccess());
        assertEquals(message, loginResponseDTO.getMessage());
        assertEquals(userId, loginResponseDTO.getUserId());
        assertEquals(username, loginResponseDTO.getUsername());
        assertEquals(role, loginResponseDTO.getRole());
    }

    @Test
    void testLoginResponseDTOWithNullValues() {
        loginResponseDTO.setSuccess(null);
        loginResponseDTO.setMessage(null);
        loginResponseDTO.setUserId(null);
        loginResponseDTO.setUsername(null);
        loginResponseDTO.setRole(null);

        assertNull(loginResponseDTO.getSuccess());
        assertNull(loginResponseDTO.getMessage());
        assertNull(loginResponseDTO.getUserId());
        assertNull(loginResponseDTO.getUsername());
        assertNull(loginResponseDTO.getRole());
    }

    @Test
    void testLoginResponseDTOWithEmptyStrings() {
        loginResponseDTO.setMessage("");
        loginResponseDTO.setUsername("");
        loginResponseDTO.setRole("");

        assertEquals("", loginResponseDTO.getMessage());
        assertEquals("", loginResponseDTO.getUsername());
        assertEquals("", loginResponseDTO.getRole());
    }

    @Test
    void testLoginResponseDTOWithZeroValues() {
        loginResponseDTO.setUserId(0);

        assertEquals(0, loginResponseDTO.getUserId());
    }

    @Test
    void testLoginResponseDTOWithNegativeValues() {
        loginResponseDTO.setUserId(-1);

        assertEquals(-1, loginResponseDTO.getUserId());
    }

    @Test
    void testLoginResponseDTOWithLargeValues() {
        Integer largeUserId = Integer.MAX_VALUE;

        loginResponseDTO.setUserId(largeUserId);

        assertEquals(largeUserId, loginResponseDTO.getUserId());
    }

    @Test
    void testLoginResponseDTOWithSpecialCharacters() {
        String messageWithSpecialChars = "Login failed with error @#$%^&*()";
        String usernameWithSpecialChars = "user@domain.com";
        String roleWithSpecialChars = "ADMIN@TEST";

        loginResponseDTO.setMessage(messageWithSpecialChars);
        loginResponseDTO.setUsername(usernameWithSpecialChars);
        loginResponseDTO.setRole(roleWithSpecialChars);

        assertEquals(messageWithSpecialChars, loginResponseDTO.getMessage());
        assertEquals(usernameWithSpecialChars, loginResponseDTO.getUsername());
        assertEquals(roleWithSpecialChars, loginResponseDTO.getRole());
    }

    @Test
    void testLoginResponseDTOWithUnicodeCharacters() {
        String messageWithUnicode = "Login message with émojis 🚀";
        String usernameWithUnicode = "user_émojis_🚀";
        String roleWithUnicode = "ADMIN_émojis_🚀";

        loginResponseDTO.setMessage(messageWithUnicode);
        loginResponseDTO.setUsername(usernameWithUnicode);
        loginResponseDTO.setRole(roleWithUnicode);

        assertEquals(messageWithUnicode, loginResponseDTO.getMessage());
        assertEquals(usernameWithUnicode, loginResponseDTO.getUsername());
        assertEquals(roleWithUnicode, loginResponseDTO.getRole());
    }

    @Test
    void testLoginResponseDTOWithLongStrings() {
        String longMessage = "This is a very long login response message that contains many characters and should be properly handled by the getter and setter methods without any issues.";
        String longUsername = "very_long_username_that_contains_many_characters_and_should_be_properly_handled";
        String longRole = "very_long_role_name_that_contains_many_characters_and_should_be_properly_handled";

        loginResponseDTO.setMessage(longMessage);
        loginResponseDTO.setUsername(longUsername);
        loginResponseDTO.setRole(longRole);

        assertEquals(longMessage, loginResponseDTO.getMessage());
        assertEquals(longUsername, loginResponseDTO.getUsername());
        assertEquals(longRole, loginResponseDTO.getRole());
    }

    @Test
    void testLoginResponseDTOWithBooleanValues() {
        // Test true
        loginResponseDTO.setSuccess(true);
        assertEquals(true, loginResponseDTO.getSuccess());

        // Test false
        loginResponseDTO.setSuccess(false);
        assertEquals(false, loginResponseDTO.getSuccess());
    }

    @Test
    void testLoginResponseDTOWithDifferentRoles() {
        String[] roles = {"USER", "ADMIN", "MODERATOR", "GUEST"};

        for (String role : roles) {
            loginResponseDTO.setRole(role);
            assertEquals(role, loginResponseDTO.getRole());
        }
    }

    @Test
    void testLoginResponseDTOWithWhitespace() {
        String messageWithWhitespace = "  Login message  ";
        String usernameWithWhitespace = "  testuser  ";
        String roleWithWhitespace = "  USER  ";

        loginResponseDTO.setMessage(messageWithWhitespace);
        loginResponseDTO.setUsername(usernameWithWhitespace);
        loginResponseDTO.setRole(roleWithWhitespace);

        assertEquals(messageWithWhitespace, loginResponseDTO.getMessage());
        assertEquals(usernameWithWhitespace, loginResponseDTO.getUsername());
        assertEquals(roleWithWhitespace, loginResponseDTO.getRole());
    }

    @Test
    void testLoginResponseDTOWithNewlines() {
        String messageWithNewlines = "Login\nmessage";
        String usernameWithNewlines = "test\nuser";
        String roleWithNewlines = "AD\nMIN";

        loginResponseDTO.setMessage(messageWithNewlines);
        loginResponseDTO.setUsername(usernameWithNewlines);
        loginResponseDTO.setRole(roleWithNewlines);

        assertEquals(messageWithNewlines, loginResponseDTO.getMessage());
        assertEquals(usernameWithNewlines, loginResponseDTO.getUsername());
        assertEquals(roleWithNewlines, loginResponseDTO.getRole());
    }

    @Test
    void testLoginResponseDTOWithNumbers() {
        String usernameWithNumbers = "user123";
        String roleWithNumbers = "USER123";

        loginResponseDTO.setUsername(usernameWithNumbers);
        loginResponseDTO.setRole(roleWithNumbers);

        assertEquals(usernameWithNumbers, loginResponseDTO.getUsername());
        assertEquals(roleWithNumbers, loginResponseDTO.getRole());
    }

    @Test
    void testLoginResponseDTOWithMixedCase() {
        String messageWithMixedCase = "Login Message";
        String usernameWithMixedCase = "TestUser";
        String roleWithMixedCase = "AdMiN";

        loginResponseDTO.setMessage(messageWithMixedCase);
        loginResponseDTO.setUsername(usernameWithMixedCase);
        loginResponseDTO.setRole(roleWithMixedCase);

        assertEquals(messageWithMixedCase, loginResponseDTO.getMessage());
        assertEquals(usernameWithMixedCase, loginResponseDTO.getUsername());
        assertEquals(roleWithMixedCase, loginResponseDTO.getRole());
    }
} 