package Application.auth;

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
    void testUserWithValidCredentials() {
        user.setUsername("testuser");
        user.setPassword("password123");
        user.setEmail("test@example.com");
        user.setRole("USER");

        assertEquals("testuser", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("USER", user.getRole());
    }

    @Test
    void testUserWithNullValues() {
        user.setUsername(null);
        user.setPassword(null);
        user.setEmail(null);
        user.setRole(null);

        assertNull(user.getUsername());
        assertNull(user.getPassword());
        assertNull(user.getEmail());
        assertNull(user.getRole());
    }

    @Test
    void testUserWithEmptyStrings() {
        user.setUsername("");
        user.setPassword("");
        user.setEmail("");
        user.setRole("");

        assertEquals("", user.getUsername());
        assertEquals("", user.getPassword());
        assertEquals("", user.getEmail());
        assertEquals("", user.getRole());
    }

    @Test
    void testUserWithSpecialCharacters() {
        String usernameWithSpecial = "test_user-123";
        String passwordWithSpecial = "pass@word#123";
        String emailWithSpecial = "test+user@example.com";
        String roleWithSpecial = "SUPER_ADMIN";

        user.setUsername(usernameWithSpecial);
        user.setPassword(passwordWithSpecial);
        user.setEmail(emailWithSpecial);
        user.setRole(roleWithSpecial);

        assertEquals(usernameWithSpecial, user.getUsername());
        assertEquals(passwordWithSpecial, user.getPassword());
        assertEquals(emailWithSpecial, user.getEmail());
        assertEquals(roleWithSpecial, user.getRole());
    }

    @Test
    void testUserEquality() {
        User user1 = new User();
        user1.setUsername("testuser");
        user1.setPassword("password123");
        user1.setEmail("test@example.com");
        user1.setRole("USER");

        User user2 = new User();
        user2.setUsername("testuser");
        user2.setPassword("password123");
        user2.setEmail("test@example.com");
        user2.setRole("USER");

        // Test equality based on username and email
        assertEquals(user1.getUsername(), user2.getUsername());
        assertEquals(user1.getEmail(), user2.getEmail());
    }

    @Test
    void testUserWithDifferentRoles() {
        user.setRole("USER");
        assertEquals("USER", user.getRole());

        user.setRole("ADMIN");
        assertEquals("ADMIN", user.getRole());

        user.setRole("MODERATOR");
        assertEquals("MODERATOR", user.getRole());
    }

    @Test
    void testUserWithLongCredentials() {
        String longUsername = "very_long_username_that_might_be_used_in_real_world_scenarios";
        String longPassword = "very_long_password_with_many_characters_and_symbols_123!@#$%^&*()";
        String longEmail = "very_long_email_address_that_might_be_used_in_real_world_scenarios@example.com";

        user.setUsername(longUsername);
        user.setPassword(longPassword);
        user.setEmail(longEmail);

        assertEquals(longUsername, user.getUsername());
        assertEquals(longPassword, user.getPassword());
        assertEquals(longEmail, user.getEmail());
    }

    @Test
    void testUserWithWhitespace() {
        user.setUsername("  testuser  ");
        user.setPassword("  password123  ");
        user.setEmail("  test@example.com  ");

        assertEquals("  testuser  ", user.getUsername());
        assertEquals("  password123  ", user.getPassword());
        assertEquals("  test@example.com  ", user.getEmail());
    }
} 