package com.server.NewsAggrigationServer.exception;

public class ExternalApiException extends RuntimeException {
    private String apiName;
    private int statusCode;
    private String errorCode;

    public ExternalApiException(String message) {
        super(message);
    }

    public ExternalApiException(String message, String apiName) {
        super(message);
        this.apiName = apiName;
    }

    public ExternalApiException(String message, String apiName, int statusCode) {
        super(message);
        this.apiName = apiName;
        this.statusCode = statusCode;
    }

    public ExternalApiException(String message, String apiName, int statusCode, String errorCode) {
        super(message);
        this.apiName = apiName;
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    }

    public String getApiName() {
        return apiName;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
} 