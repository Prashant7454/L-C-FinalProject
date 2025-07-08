package com.server.NewsAggrigationServer.exception;

public class NewsServiceException extends RuntimeException {
    private String errorCode;
    private String operation;

    public NewsServiceException(String message) {
        super(message);
    }

    public NewsServiceException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public NewsServiceException(String message, String errorCode, String operation) {
        super(message);
        this.errorCode = errorCode;
        this.operation = operation;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getOperation() {
        return operation;
    }
} 