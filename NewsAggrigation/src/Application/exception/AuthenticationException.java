package Application.exception;

public class AuthenticationException extends NewsAggrigationException {
    
    public AuthenticationException(String message) {
        super(message, "AUTH_ERROR");
    }

    public AuthenticationException(String message, String errorCode) {
        super(message, errorCode);
    }

    public AuthenticationException(String message, String errorCode, String operation) {
        super(message, errorCode, operation);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, "AUTH_ERROR", cause);
    }
} 