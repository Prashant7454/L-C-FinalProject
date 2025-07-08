package com.server.NewsAggrigationServer.exception;

public class DatabaseException extends RuntimeException {
    private String operation;
    private String table;
    private String errorCode;

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, String operation) {
        super(message);
        this.operation = operation;
    }

    public DatabaseException(String message, String operation, String table) {
        super(message);
        this.operation = operation;
        this.table = table;
    }

    public DatabaseException(String message, String operation, String table, String errorCode) {
        super(message);
        this.operation = operation;
        this.table = table;
        this.errorCode = errorCode;
    }

    public String getOperation() {
        return operation;
    }

    public String getTable() {
        return table;
    }

    public String getErrorCode() {
        return errorCode;
    }
} 