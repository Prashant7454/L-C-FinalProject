package Application.exception;

public class NewsAggrigationException extends Exception {
    private String errorCode;
    private String operation;

    public NewsAggrigationException(String message) {
        super(message);
    }

    public NewsAggrigationException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public NewsAggrigationException(String message, String errorCode, String operation) {
        super(message);
        this.errorCode = errorCode;
        this.operation = operation;
    }

    public NewsAggrigationException(String message, Throwable cause) {
        super(message, cause);
    }

    public NewsAggrigationException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getOperation() {
        return operation;
    }
} 