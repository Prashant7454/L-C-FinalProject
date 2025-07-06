package Application.auth.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {

    private LoginRequest loginRequest;

    @BeforeEach
    void setUp() {
        loginRequest = new LoginRequest();
    }

    @Test
    void testLoginRequestCreation() {
        assertNotNull(loginRequest);
    }

    @Test
    void testUsernameGetterAndSetter() {
        String expectedUsername = "testuser";
        loginRequest.setUsername(expectedUsername);
        assertEquals(expectedUsername, loginRequest.getUsername());
    }

    @Test
    void testPasswordGetterAndSetter() {
        String expectedPassword = "password123";
        loginRequest.setPassword(expectedPassword);
        assertEquals(expectedPassword, loginRequest.getPassword());
    }

    @Test
    void testLoginRequestWithAllFields() {
        String username = "testuser";
        String password = "password123";

        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        assertEquals(username, loginRequest.getUsername());
        assertEquals(password, loginRequest.getPassword());
    }

    @Test
    void testLoginRequestWithNullValues() {
        loginRequest.setUsername(null);
        loginRequest.setPassword(null);

        assertNull(loginRequest.getUsername());
        assertNull(loginRequest.getPassword());
    }

    @Test
    void testLoginRequestWithEmptyStrings() {
        loginRequest.setUsername("");
        loginRequest.setPassword("");

        assertEquals("", loginRequest.getUsername());
        assertEquals("", loginRequest.getPassword());
    }

    @Test
    void testLoginRequestWithSpecialCharacters() {
        String usernameWithSpecial = "test_user-123";
        String passwordWithSpecial = "pass@word#123!";

        loginRequest.setUsername(usernameWithSpecial);
        loginRequest.setPassword(passwordWithSpecial);

        assertEquals(usernameWithSpecial, loginRequest.getUsername());
        assertEquals(passwordWithSpecial, loginRequest.getPassword());
    }

    @Test
    void testLoginRequestWithWhitespace() {
        loginRequest.setUsername("  testuser  ");
        loginRequest.setPassword("  password123  ");

        assertEquals("  testuser  ", loginRequest.getUsername());
        assertEquals("  password123  ", loginRequest.getPassword());
    }

    @Test
    void testLoginRequestWithLongCredentials() {
        String longUsername = "very_long_username_that_might_be_used_in_real_world_scenarios";
        String longPassword = "very_long_password_with_many_characters_and_symbols_123!@#$%^&*()";

        loginRequest.setUsername(longUsername);
        loginRequest.setPassword(longPassword);

        assertEquals(longUsername, loginRequest.getUsername());
        assertEquals(longPassword, loginRequest.getPassword());
    }

    @Test
    void testLoginRequestWithUnicodeCharacters() {
        String usernameWithUnicode = "testuser_émojis_🚀";
        String passwordWithUnicode = "pass@word_émojis_🚀_123";

        loginRequest.setUsername(usernameWithUnicode);
        loginRequest.setPassword(passwordWithUnicode);

        assertEquals(usernameWithUnicode, loginRequest.getUsername());
        assertEquals(passwordWithUnicode, loginRequest.getPassword());
    }
} 