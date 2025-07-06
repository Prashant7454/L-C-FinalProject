package Application.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthenticationExceptionTest {

    @Test
    void testAuthenticationExceptionWithMessageOnly() {
        String message = "Authentication failed";
        AuthenticationException exception = new AuthenticationException(message);
        
        assertEquals(message, exception.getMessage());
        assertEquals("AUTH_ERROR", exception.getErrorCode());
        assertNull(exception.getOperation());
        assertNull(exception.getCause());
    }

    @Test
    void testAuthenticationExceptionWithMessageAndErrorCode() {
        String message = "Invalid credentials";
        String errorCode = "INVALID_CREDENTIALS";
        AuthenticationException exception = new AuthenticationException(message, errorCode);
        
        assertEquals(message, exception.getMessage());
        assertEquals(errorCode, exception.getErrorCode());
        assertNull(exception.getOperation());
        assertNull(exception.getCause());
    }

    @Test
    void testAuthenticationExceptionWithMessageErrorCodeAndOperation() {
        String message = "Login failed";
        String errorCode = "LOGIN_FAILED";
        String operation = "LOGIN";
        AuthenticationException exception = new AuthenticationException(message, errorCode, operation);
        
        assertEquals(message, exception.getMessage());
        assertEquals(errorCode, exception.getErrorCode());
        assertEquals(operation, exception.getOperation());
        assertNull(exception.getCause());
    }

    @Test
    void testAuthenticationExceptionWithMessageAndCause() {
        String message = "Authentication error";
        Throwable cause = new RuntimeException("Underlying cause");
        AuthenticationException exception = new AuthenticationException(message, cause);
        
        assertEquals(message, exception.getMessage());
        assertEquals("AUTH_ERROR", exception.getErrorCode());
        assertNull(exception.getOperation());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testAuthenticationExceptionWithNullMessage() {
        AuthenticationException exception = new AuthenticationException(null);
        
        assertNull(exception.getMessage());
        assertEquals("AUTH_ERROR", exception.getErrorCode());
        assertNull(exception.getOperation());
        assertNull(exception.getCause());
    }

    @Test
    void testAuthenticationExceptionWithEmptyMessage() {
        String message = "";
        AuthenticationException exception = new AuthenticationException(message);
        
        assertEquals(message, exception.getMessage());
        assertEquals("AUTH_ERROR", exception.getErrorCode());
        assertNull(exception.getOperation());
        assertNull(exception.getCause());
    }

    @Test
    void testAuthenticationExceptionWithNullErrorCode() {
        String message = "Test message";
        AuthenticationException exception = new AuthenticationException(message, null);
        
        assertEquals(message, exception.getMessage());
        assertNull(exception.getErrorCode());
        assertNull(exception.getOperation());
        assertNull(exception.getCause());
    }

    @Test
    void testAuthenticationExceptionWithNullOperation() {
        String message = "Test message";
        String errorCode = "TEST_ERROR";
        AuthenticationException exception = new AuthenticationException(message, errorCode, null);
        
        assertEquals(message, exception.getMessage());
        assertEquals(errorCode, exception.getErrorCode());
        assertNull(exception.getOperation());
        assertNull(exception.getCause());
    }

    @Test
    void testAuthenticationExceptionInheritance() {
        String message = "Test exception";
        AuthenticationException exception = new AuthenticationException(message);
        
        assertTrue(exception instanceof NewsAggrigationException);
        assertTrue(exception instanceof Exception);
        assertTrue(exception instanceof Throwable);
    }

    @Test
    void testAuthenticationExceptionWithSpecialCharacters() {
        String message = "Authentication error with special chars: @#$%^&*()";
        String errorCode = "AUTH_ERROR_@#$%";
        String operation = "LOGIN_SPECIAL";
        AuthenticationException exception = new AuthenticationException(message, errorCode, operation);
        
        assertEquals(message, exception.getMessage());
        assertEquals(errorCode, exception.getErrorCode());
        assertEquals(operation, exception.getOperation());
        assertNull(exception.getCause());
    }

    @Test
    void testAuthenticationExceptionWithLongStrings() {
        String message = "This is a very long authentication error message that contains many characters and should be properly handled by the AuthenticationException class without any issues.";
        String errorCode = "VERY_LONG_ERROR_CODE_THAT_MIGHT_BE_GENERATED_BY_AUTHENTICATION_SYSTEMS";
        String operation = "VERY_LONG_OPERATION_NAME_THAT_MIGHT_BE_USED_IN_REAL_WORLD_SCENARIOS";
        AuthenticationException exception = new AuthenticationException(message, errorCode, operation);
        
        assertEquals(message, exception.getMessage());
        assertEquals(errorCode, exception.getErrorCode());
        assertEquals(operation, exception.getOperation());
        assertNull(exception.getCause());
    }

    @Test
    void testAuthenticationExceptionWithUnicodeCharacters() {
        String message = "Authentication error with émojis 🚀 and symbols ©®™";
        String errorCode = "AUTH_ERROR_émojis_🚀";
        String operation = "LOGIN_émojis_🚀";
        AuthenticationException exception = new AuthenticationException(message, errorCode, operation);
        
        assertEquals(message, exception.getMessage());
        assertEquals(errorCode, exception.getErrorCode());
        assertEquals(operation, exception.getOperation());
        assertNull(exception.getCause());
    }

    @Test
    void testAuthenticationExceptionWithNullCause() {
        String message = "Test message";
        AuthenticationException exception = new AuthenticationException(message, null);
        
        assertEquals(message, exception.getMessage());
        assertEquals("AUTH_ERROR", exception.getErrorCode());
        assertNull(exception.getOperation());
        assertNull(exception.getCause());
    }
} 