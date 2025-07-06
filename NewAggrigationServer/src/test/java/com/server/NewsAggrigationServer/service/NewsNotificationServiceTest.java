package com.server.NewsAggrigationServer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NewsNotificationServiceTest {

    @Mock
    private EmailService emailService;

    @InjectMocks
    private NewsNotificationService newsNotificationService;

    @BeforeEach
    void setUp() {
        // Setup for NewsNotificationService tests
    }

    @Test
    void testNewsNotificationServiceCreation() {
        // Arrange & Act
        NewsNotificationService service = new NewsNotificationService();

        // Assert
        assertNotNull(service);
    }

    @Test
    void testSendNotificationWithValidParameters() {
        // Arrange
        String email = "test@example.com";
        String message = "New technology news available";

        when(emailService.sendEmail(anyString(), anyString(), anyString())).thenReturn(true);

        // Act
        boolean result = newsNotificationService.sendNotification(email, message);

        // Assert
        assertTrue(result);
        verify(emailService).sendEmail(email, "News Notification", message);
    }

    @Test
    void testSendNotificationWithNullEmail() {
        // Arrange
        String email = null;
        String message = "New technology news available";

        // Act
        boolean result = newsNotificationService.sendNotification(email, message);

        // Assert
        assertFalse(result);
        verify(emailService, never()).sendEmail(anyString(), anyString(), anyString());
    }

    @Test
    void testSendNotificationWithNullMessage() {
        // Arrange
        String email = "test@example.com";
        String message = null;

        // Act
        boolean result = newsNotificationService.sendNotification(email, message);

        // Assert
        assertFalse(result);
        verify(emailService, never()).sendEmail(anyString(), anyString(), anyString());
    }

    @Test
    void testSendNotificationWithEmptyEmail() {
        // Arrange
        String email = "";
        String message = "New technology news available";

        // Act
        boolean result = newsNotificationService.sendNotification(email, message);

        // Assert
        assertFalse(result);
        verify(emailService, never()).sendEmail(anyString(), anyString(), anyString());
    }

    @Test
    void testSendNotificationWithEmptyMessage() {
        // Arrange
        String email = "test@example.com";
        String message = "";

        // Act
        boolean result = newsNotificationService.sendNotification(email, message);

        // Assert
        assertFalse(result);
        verify(emailService, never()).sendEmail(anyString(), anyString(), anyString());
    }

    @Test
    void testSendNotificationWithWhitespaceEmail() {
        // Arrange
        String email = "   ";
        String message = "New technology news available";

        // Act
        boolean result = newsNotificationService.sendNotification(email, message);

        // Assert
        assertFalse(result);
        verify(emailService, never()).sendEmail(anyString(), anyString(), anyString());
    }

    @Test
    void testSendNotificationWithWhitespaceMessage() {
        // Arrange
        String email = "test@example.com";
        String message = "   ";

        // Act
        boolean result = newsNotificationService.sendNotification(email, message);

        // Assert
        assertFalse(result);
        verify(emailService, never()).sendEmail(anyString(), anyString(), anyString());
    }

    @Test
    void testSendNotificationWithEmailServiceFailure() {
        // Arrange
        String email = "test@example.com";
        String message = "New technology news available";

        when(emailService.sendEmail(anyString(), anyString(), anyString())).thenReturn(false);

        // Act
        boolean result = newsNotificationService.sendNotification(email, message);

        // Assert
        assertFalse(result);
        verify(emailService).sendEmail(email, "News Notification", message);
    }

    @Test
    void testSendNotificationWithSpecialCharacters() {
        // Arrange
        String email = "test@example.com";
        String message = "Breaking news with @#$%^&*() symbols";

        when(emailService.sendEmail(anyString(), anyString(), anyString())).thenReturn(true);

        // Act
        boolean result = newsNotificationService.sendNotification(email, message);

        // Assert
        assertTrue(result);
        verify(emailService).sendEmail(email, "News Notification", message);
    }

    @Test
    void testSendNotificationWithUnicodeCharacters() {
        // Arrange
        String email = "test@example.com";
        String message = "Breaking news with émojis 🚀";

        when(emailService.sendEmail(anyString(), anyString(), anyString())).thenReturn(true);

        // Act
        boolean result = newsNotificationService.sendNotification(email, message);

        // Assert
        assertTrue(result);
        verify(emailService).sendEmail(email, "News Notification", message);
    }

    @Test
    void testSendNotificationWithLongMessage() {
        // Arrange
        String email = "test@example.com";
        String message = "This is a very long notification message that contains many characters and should be properly handled by the email service without any issues.";

        when(emailService.sendEmail(anyString(), anyString(), anyString())).thenReturn(true);

        // Act
        boolean result = newsNotificationService.sendNotification(email, message);

        // Assert
        assertTrue(result);
        verify(emailService).sendEmail(email, "News Notification", message);
    }

    @Test
    void testSendNotificationWithNewlines() {
        // Arrange
        String email = "test@example.com";
        String message = "Breaking news:\nNew technology article\nAvailable now";

        when(emailService.sendEmail(anyString(), anyString(), anyString())).thenReturn(true);

        // Act
        boolean result = newsNotificationService.sendNotification(email, message);

        // Assert
        assertTrue(result);
        verify(emailService).sendEmail(email, "News Notification", message);
    }

    @Test
    void testSendNotificationWithNumbers() {
        // Arrange
        String email = "test@example.com";
        String message = "5 new articles available in Technology category";

        when(emailService.sendEmail(anyString(), anyString(), anyString())).thenReturn(true);

        // Act
        boolean result = newsNotificationService.sendNotification(email, message);

        // Assert
        assertTrue(result);
        verify(emailService).sendEmail(email, "News Notification", message);
    }

    @Test
    void testSendNotificationWithMixedCase() {
        // Arrange
        String email = "Test@Example.com";
        String message = "Breaking News Alert";

        when(emailService.sendEmail(anyString(), anyString(), anyString())).thenReturn(true);

        // Act
        boolean result = newsNotificationService.sendNotification(email, message);

        // Assert
        assertTrue(result);
        verify(emailService).sendEmail(email, "News Notification", message);
    }

    @Test
    void testSendNotificationWithInvalidEmailFormat() {
        // Arrange
        String email = "invalid-email-format";
        String message = "New technology news available";

        // Act
        boolean result = newsNotificationService.sendNotification(email, message);

        // Assert
        assertFalse(result);
        verify(emailService, never()).sendEmail(anyString(), anyString(), anyString());
    }

    @Test
    void testSendNotificationWithValidEmailFormats() {
        // Arrange
        String[] validEmails = {
            "test@example.com",
            "user.name@domain.com",
            "user+tag@domain.co.uk",
            "user123@domain.org",
            "user-name@domain.net"
        };

        when(emailService.sendEmail(anyString(), anyString(), anyString())).thenReturn(true);

        for (String email : validEmails) {
            // Act
            boolean result = newsNotificationService.sendNotification(email, "Test message");

            // Assert
            assertTrue(result, "Email should be valid: " + email);
            verify(emailService).sendEmail(email, "News Notification", "Test message");
        }
    }

    @Test
    void testSendNotificationWithDifferentMessages() {
        // Arrange
        String[] messages = {
            "Breaking news alert",
            "New technology article available",
            "Weather warning issued",
            "Sports update",
            "Business news",
            "Entertainment news",
            "Science discovery"
        };

        when(emailService.sendEmail(anyString(), anyString(), anyString())).thenReturn(true);

        for (String message : messages) {
            // Act
            boolean result = newsNotificationService.sendNotification("test@example.com", message);

            // Assert
            assertTrue(result, "Message should be valid: " + message);
            verify(emailService).sendEmail("test@example.com", "News Notification", message);
        }
    }

    @Test
    void testSendNotificationWithRealisticData() {
        // Arrange
        String email = "user@newsapp.com";
        String message = "New articles matching your interests:\n\n1. AI Breakthrough\n2. New Programming Language\n3. Cybersecurity Update";

        when(emailService.sendEmail(anyString(), anyString(), anyString())).thenReturn(true);

        // Act
        boolean result = newsNotificationService.sendNotification(email, message);

        // Assert
        assertTrue(result);
        verify(emailService).sendEmail(email, "News Notification", message);
    }

    @Test
    void testSendNotificationWithBoundaryValues() {
        // Test with very long strings
        String longEmail = "a".repeat(1000) + "@example.com";
        String longMessage = "b".repeat(10000);

        when(emailService.sendEmail(anyString(), anyString(), anyString())).thenReturn(true);

        // Act
        boolean result = newsNotificationService.sendNotification(longEmail, longMessage);

        // Assert
        assertTrue(result);
        verify(emailService).sendEmail(longEmail, "News Notification", longMessage);
    }

    @Test
    void testSendNotificationWithNullParameters() {
        // Test all null parameters
        assertFalse(newsNotificationService.sendNotification(null, null));
        verify(emailService, never()).sendEmail(anyString(), anyString(), anyString());
    }

    @Test
    void testSendNotificationWithEmptyParameters() {
        // Test all empty parameters
        assertFalse(newsNotificationService.sendNotification("", ""));
        verify(emailService, never()).sendEmail(anyString(), anyString(), anyString());
    }

    @Test
    void testSendNotificationWithWhitespaceParameters() {
        // Test all whitespace parameters
        assertFalse(newsNotificationService.sendNotification("   ", "   "));
        verify(emailService, never()).sendEmail(anyString(), anyString(), anyString());
    }

    @Test
    void testSendNotificationWithEmailServiceException() {
        // Arrange
        String email = "test@example.com";
        String message = "New technology news available";

        when(emailService.sendEmail(anyString(), anyString(), anyString())).thenThrow(new RuntimeException("Email service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            newsNotificationService.sendNotification(email, message);
        });

        verify(emailService).sendEmail(email, "News Notification", message);
    }
} 