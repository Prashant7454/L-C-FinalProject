package com.server.NewsAggrigationServer.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseExceptionTest {

    @Test
    void testDatabaseExceptionWithMessageOnly() {
        String message = "Database connection failed";
        DatabaseException exception = new DatabaseException(message);
        
        assertEquals(message, exception.getMessage());
        assertNull(exception.getOperation());
        assertNull(exception.getTable());
        assertNull(exception.getErrorCode());
    }

    @Test
    void testDatabaseExceptionWithMessageAndOperation() {
        String message = "Database query failed";
        String operation = "SELECT";
        DatabaseException exception = new DatabaseException(message, operation);
        
        assertEquals(message, exception.getMessage());
        assertEquals(operation, exception.getOperation());
        assertNull(exception.getTable());
        assertNull(exception.getErrorCode());
    }

    @Test
    void testDatabaseExceptionWithMessageOperationAndTable() {
        String message = "Database insert failed";
        String operation = "INSERT";
        String table = "users";
        DatabaseException exception = new DatabaseException(message, operation, table);
        
        assertEquals(message, exception.getMessage());
        assertEquals(operation, exception.getOperation());
        assertEquals(table, exception.getTable());
        assertNull(exception.getErrorCode());
    }

    @Test
    void testDatabaseExceptionWithAllParameters() {
        String message = "Database update failed";
        String operation = "UPDATE";
        String table = "news";
        String errorCode = "DB_UPDATE_ERROR";
        DatabaseException exception = new DatabaseException(message, operation, table, errorCode);
        
        assertEquals(message, exception.getMessage());
        assertEquals(operation, exception.getOperation());
        assertEquals(table, exception.getTable());
        assertEquals(errorCode, exception.getErrorCode());
    }

    @Test
    void testDatabaseExceptionWithNullMessage() {
        DatabaseException exception = new DatabaseException(null);
        
        assertNull(exception.getMessage());
        assertNull(exception.getOperation());
        assertNull(exception.getTable());
        assertNull(exception.getErrorCode());
    }

    @Test
    void testDatabaseExceptionWithEmptyMessage() {
        String message = "";
        DatabaseException exception = new DatabaseException(message);
        
        assertEquals(message, exception.getMessage());
        assertNull(exception.getOperation());
        assertNull(exception.getTable());
        assertNull(exception.getErrorCode());
    }

    @Test
    void testDatabaseExceptionWithNullOperation() {
        String message = "Test message";
        DatabaseException exception = new DatabaseException(message, null);
        
        assertEquals(message, exception.getMessage());
        assertNull(exception.getOperation());
        assertNull(exception.getTable());
        assertNull(exception.getErrorCode());
    }

    @Test
    void testDatabaseExceptionWithNullTable() {
        String message = "Test message";
        String operation = "TEST";
        DatabaseException exception = new DatabaseException(message, operation, null);
        
        assertEquals(message, exception.getMessage());
        assertEquals(operation, exception.getOperation());
        assertNull(exception.getTable());
        assertNull(exception.getErrorCode());
    }

    @Test
    void testDatabaseExceptionWithNullErrorCode() {
        String message = "Test message";
        String operation = "TEST";
        String table = "test_table";
        DatabaseException exception = new DatabaseException(message, operation, table, null);
        
        assertEquals(message, exception.getMessage());
        assertEquals(operation, exception.getOperation());
        assertEquals(table, exception.getTable());
        assertNull(exception.getErrorCode());
    }

    @Test
    void testDatabaseExceptionInheritance() {
        String message = "Test exception";
        DatabaseException exception = new DatabaseException(message);
        
        assertTrue(exception instanceof RuntimeException);
        assertTrue(exception instanceof Exception);
    }

    @Test
    void testDatabaseExceptionWithSpecialCharacters() {
        String message = "Database error with special chars: @#$%^&*()";
        String operation = "SELECT_SPECIAL";
        String table = "test_table_123";
        String errorCode = "DB_ERROR_@#$%";
        DatabaseException exception = new DatabaseException(message, operation, table, errorCode);
        
        assertEquals(message, exception.getMessage());
        assertEquals(operation, exception.getOperation());
        assertEquals(table, exception.getTable());
        assertEquals(errorCode, exception.getErrorCode());
    }

    @Test
    void testDatabaseExceptionWithLongStrings() {
        String message = "This is a very long error message that contains many characters and should be properly handled by the DatabaseException class without any issues.";
        String operation = "VERY_LONG_OPERATION_NAME_THAT_MIGHT_BE_USED_IN_REAL_WORLD_SCENARIOS";
        String table = "very_long_table_name_that_might_exist_in_a_complex_database_schema";
        String errorCode = "VERY_LONG_ERROR_CODE_THAT_MIGHT_BE_GENERATED_BY_DATABASE_SYSTEMS";
        DatabaseException exception = new DatabaseException(message, operation, table, errorCode);
        
        assertEquals(message, exception.getMessage());
        assertEquals(operation, exception.getOperation());
        assertEquals(table, exception.getTable());
        assertEquals(errorCode, exception.getErrorCode());
    }
} 