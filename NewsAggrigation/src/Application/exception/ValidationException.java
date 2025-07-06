package Application.exception;

public class ValidationException extends NewsAggrigationException {
    private String field;

    public ValidationException(String message) {
        super(message, "VALIDATION_ERROR");
    }

    public ValidationException(String message, String field) {
        super(message, "VALIDATION_ERROR");
        this.field = field;
    }

    public ValidationException(String message, String field, String errorCode) {
        super(message, errorCode);
        this.field = field;
    }

    public ValidationException(String message, Throwable cause) {
        super(message, "VALIDATION_ERROR", cause);
    }

    public String getField() {
        return field;
    }
} 