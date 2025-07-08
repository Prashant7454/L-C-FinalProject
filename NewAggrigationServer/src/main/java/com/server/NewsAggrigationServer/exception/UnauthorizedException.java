package com.server.NewsAggrigationServer.exception;

public class UnauthorizedException extends RuntimeException {
    private String errorCode;

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
} 