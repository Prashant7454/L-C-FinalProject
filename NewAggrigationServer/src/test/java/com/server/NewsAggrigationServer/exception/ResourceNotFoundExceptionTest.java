package com.server.NewsAggrigationServer.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ResourceNotFoundExceptionTest {

    @Test
    void testResourceNotFoundExceptionWithMessage() {
        String message = "Resource not found";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);
        
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testResourceNotFoundExceptionWithNullMessage() {
        ResourceNotFoundException exception = new ResourceNotFoundException(null);
        
        assertNull(exception.getMessage());
    }

    @Test
    void testResourceNotFoundExceptionWithEmptyMessage() {
        String message = "";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);
        
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testResourceNotFoundExceptionWithSpecialCharacters() {
        String message = "Resource not found with special chars: @#$%^&*()";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);
        
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testResourceNotFoundExceptionWithUnicodeCharacters() {
        String message = "Resource not found with émojis 🚀 and symbols ©®™";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);
        
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testResourceNotFoundExceptionWithLongMessage() {
        String message = "This is a very long error message that contains many characters and should be properly handled by the ResourceNotFoundException class without any issues.";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);
        
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testResourceNotFoundExceptionInheritance() {
        String message = "Test exception";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);
        
        assertTrue(exception instanceof RuntimeException);
        assertTrue(exception instanceof Exception);
        assertTrue(exception instanceof Throwable);
    }

    @Test
    void testResourceNotFoundExceptionWithWhitespace() {
        String message = "  Resource not found  ";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);
        
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testResourceNotFoundExceptionWithNewlines() {
        String message = "Resource\nnot\nfound";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);
        
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testResourceNotFoundExceptionWithTabs() {
        String message = "Resource\tnot\tfound";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);
        
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testResourceNotFoundExceptionWithNumbers() {
        String message = "Resource 123 not found";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);
        
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testResourceNotFoundExceptionWithSymbols() {
        String message = "Resource !@#$%^&*() not found";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);
        
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testResourceNotFoundExceptionWithSingleCharacter() {
        String message = "A";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);
        
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testResourceNotFoundExceptionWithVeryLongMessage() {
        StringBuilder longMessage = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            longMessage.append("a");
        }
        ResourceNotFoundException exception = new ResourceNotFoundException(longMessage.toString());
        
        assertEquals(longMessage.toString(), exception.getMessage());
    }

    @Test
    void testResourceNotFoundExceptionWithEmojiMessage() {
        String message = "🚀";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);
        
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testResourceNotFoundExceptionWithUnicodeSymbols() {
        String message = "©®™";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);
        
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testResourceNotFoundExceptionWithMixedCase() {
        String message = "ReSoUrCe NoT fOuNd";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);
        
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testResourceNotFoundExceptionWithUnderscores() {
        String message = "Resource_not_found";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);
        
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testResourceNotFoundExceptionWithHyphens() {
        String message = "Resource-not-found";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);
        
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testResourceNotFoundExceptionWithSpaces() {
        String message = "Resource not found";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);
        
        assertEquals(message, exception.getMessage());
    }
} 