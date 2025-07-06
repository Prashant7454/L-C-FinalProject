package Application.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExceptionHandler {
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public static void handleException(NewsAggrigationException exception) {
        String timestamp = LocalDateTime.now().format(formatter);
        String errorCode = exception.getErrorCode() != null ? exception.getErrorCode() : "UNKNOWN_ERROR";
        String operation = exception.getOperation() != null ? exception.getOperation() : "UNKNOWN_OPERATION";
        
        System.err.println("=== EXCEPTION HANDLED ===");
        System.err.println("Timestamp: " + timestamp);
        System.err.println("Error Code: " + errorCode);
        System.err.println("Operation: " + operation);
        System.err.println("Message: " + exception.getMessage());
        
        if (exception.getCause() != null) {
            System.err.println("Cause: " + exception.getCause().getMessage());
        }
        
        // Handle specific exception types
        if (exception instanceof AuthenticationException) {
            handleAuthenticationException((AuthenticationException) exception);
        } else if (exception instanceof NetworkException) {
            handleNetworkException((NetworkException) exception);
        } else if (exception instanceof ValidationException) {
            handleValidationException((ValidationException) exception);
        }
        
        System.err.println("========================");
    }
    
    private static void handleAuthenticationException(AuthenticationException exception) {
        System.err.println("Authentication Error Details:");
        System.err.println("- This is an authentication-related error");
        System.err.println("- User should check their credentials");
        System.err.println("- Consider re-authenticating");
    }
    
    private static void handleNetworkException(NetworkException exception) {
        System.err.println("Network Error Details:");
        System.err.println("- URL: " + (exception.getUrl() != null ? exception.getUrl() : "N/A"));
        System.err.println("- Status Code: " + exception.getStatusCode());
        System.err.println("- Check network connectivity");
        System.err.println("- Verify server is running");
    }
    
    private static void handleValidationException(ValidationException exception) {
        System.err.println("Validation Error Details:");
        System.err.println("- Field: " + (exception.getField() != null ? exception.getField() : "N/A"));
        System.err.println("- Input validation failed");
        System.err.println("- Check input format and requirements");
    }
    
    public static void handleGenericException(Exception exception) {
        String timestamp = LocalDateTime.now().format(formatter);
        
        System.err.println("=== UNEXPECTED EXCEPTION ===");
        System.err.println("Timestamp: " + timestamp);
        System.err.println("Type: " + exception.getClass().getSimpleName());
        System.err.println("Message: " + exception.getMessage());
        
        if (exception.getCause() != null) {
            System.err.println("Cause: " + exception.getCause().getMessage());
        }
        
        System.err.println("Stack Trace:");
        exception.printStackTrace();
        System.err.println("============================");
    }
    
    public static void logError(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.err.println("[" + timestamp + "] ERROR: " + message);
    }
    
    public static void logWarning(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.err.println("[" + timestamp + "] WARNING: " + message);
    }
    
    public static void logInfo(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.println("[" + timestamp + "] INFO: " + message);
    }
} 