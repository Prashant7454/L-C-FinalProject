package com.server.NewsAggrigationServer.exception;

public class ValidationException extends RuntimeException {
    private String field;
    private String errorCode;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, String field) {
        super(message);
        this.field = field;
    }

    public ValidationException(String message, String field, String errorCode) {
        super(message);
        this.field = field;
        this.errorCode = errorCode;
    }

    public String getField() {
        return field;
    }

    public String getErrorCode() {
        return errorCode;
    }
} 