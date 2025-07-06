package com.server.NewsAggrigationServer.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NotificationDTOTest {

    private NotificationDTO notificationDTO;

    @BeforeEach
    void setUp() {
        notificationDTO = new NotificationDTO();
    }

    @Test
    void testNotificationDTOCreation() {
        assertNotNull(notificationDTO);
    }

    @Test
    void testIdGetterAndSetter() {
        Integer expectedId = 1;
        notificationDTO.setId(expectedId);
        assertEquals(expectedId, notificationDTO.getId());
    }

    @Test
    void testUserIdGetterAndSetter() {
        Integer expectedUserId = 1;
        notificationDTO.setUserId(expectedUserId);
        assertEquals(expectedUserId, notificationDTO.getUserId());
    }

    @Test
    void testMessageGetterAndSetter() {
        String expectedMessage = "New article available";
        notificationDTO.setMessage(expectedMessage);
        assertEquals(expectedMessage, notificationDTO.getMessage());
    }

    @Test
    void testIsReadGetterAndSetter() {
        Integer expectedIsRead = 0;
        notificationDTO.setIsRead(expectedIsRead);
        assertEquals(expectedIsRead, notificationDTO.getIsRead());
    }

    @Test
    void testNotificationDTOWithAllFields() {
        Integer id = 1;
        Integer userId = 2;
        String message = "Breaking news alert";
        Integer isRead = 0;

        notificationDTO.setId(id);
        notificationDTO.setUserId(userId);
        notificationDTO.setMessage(message);
        notificationDTO.setIsRead(isRead);

        assertEquals(id, notificationDTO.getId());
        assertEquals(userId, notificationDTO.getUserId());
        assertEquals(message, notificationDTO.getMessage());
        assertEquals(isRead, notificationDTO.getIsRead());
    }

    @Test
    void testNotificationDTOWithNullValues() {
        notificationDTO.setId(null);
        notificationDTO.setUserId(null);
        notificationDTO.setMessage(null);
        notificationDTO.setIsRead(null);

        assertNull(notificationDTO.getId());
        assertNull(notificationDTO.getUserId());
        assertNull(notificationDTO.getMessage());
        assertNull(notificationDTO.getIsRead());
    }

    @Test
    void testNotificationDTOWithEmptyString() {
        notificationDTO.setMessage("");
        assertEquals("", notificationDTO.getMessage());
    }

    @Test
    void testNotificationDTOWithZeroValues() {
        notificationDTO.setId(0);
        notificationDTO.setUserId(0);
        notificationDTO.setIsRead(0);

        assertEquals(0, notificationDTO.getId());
        assertEquals(0, notificationDTO.getUserId());
        assertEquals(0, notificationDTO.getIsRead());
    }

    @Test
    void testNotificationDTOWithNegativeValues() {
        notificationDTO.setId(-1);
        notificationDTO.setUserId(-2);
        notificationDTO.setIsRead(-1);

        assertEquals(-1, notificationDTO.getId());
        assertEquals(-2, notificationDTO.getUserId());
        assertEquals(-1, notificationDTO.getIsRead());
    }

    @Test
    void testNotificationDTOWithLargeValues() {
        Integer largeId = Integer.MAX_VALUE;
        Integer largeUserId = Integer.MAX_VALUE - 1;
        Integer largeIsRead = Integer.MAX_VALUE - 2;

        notificationDTO.setId(largeId);
        notificationDTO.setUserId(largeUserId);
        notificationDTO.setIsRead(largeIsRead);

        assertEquals(largeId, notificationDTO.getId());
        assertEquals(largeUserId, notificationDTO.getUserId());
        assertEquals(largeIsRead, notificationDTO.getIsRead());
    }

    @Test
    void testNotificationDTOWithSpecialCharacters() {
        String messageWithSpecialChars = "Alert: News with @#$%^&*() symbols";
        notificationDTO.setMessage(messageWithSpecialChars);
        assertEquals(messageWithSpecialChars, notificationDTO.getMessage());
    }

    @Test
    void testNotificationDTOWithUnicodeCharacters() {
        String messageWithUnicode = "Notification with émojis 🚀 and symbols ©®™";
        notificationDTO.setMessage(messageWithUnicode);
        assertEquals(messageWithUnicode, notificationDTO.getMessage());
    }

    @Test
    void testNotificationDTOWithLongString() {
        String longMessage = "This is a very long notification message that contains many characters and should be properly handled by the getter and setter methods without any issues.";
        notificationDTO.setMessage(longMessage);
        assertEquals(longMessage, notificationDTO.getMessage());
    }

    @Test
    void testNotificationDTOWithWhitespace() {
        String messageWithWhitespace = "  Notification message  ";
        notificationDTO.setMessage(messageWithWhitespace);
        assertEquals(messageWithWhitespace, notificationDTO.getMessage());
    }

    @Test
    void testNotificationDTOWithNewlines() {
        String messageWithNewlines = "Notification\nmessage";
        notificationDTO.setMessage(messageWithNewlines);
        assertEquals(messageWithNewlines, notificationDTO.getMessage());
    }

    @Test
    void testNotificationDTOWithNumbers() {
        String messageWithNumbers = "Alert 123: New article available";
        notificationDTO.setMessage(messageWithNumbers);
        assertEquals(messageWithNumbers, notificationDTO.getMessage());
    }

    @Test
    void testNotificationDTOWithMixedCase() {
        String messageWithMixedCase = "Breaking News Alert";
        notificationDTO.setMessage(messageWithMixedCase);
        assertEquals(messageWithMixedCase, notificationDTO.getMessage());
    }

    @Test
    void testNotificationDTOWithReadStatus() {
        // Test unread (0)
        notificationDTO.setIsRead(0);
        assertEquals(0, notificationDTO.getIsRead());

        // Test read (1)
        notificationDTO.setIsRead(1);
        assertEquals(1, notificationDTO.getIsRead());
    }

    @Test
    void testNotificationDTOWithDifferentMessages() {
        String[] messages = {
            "New article available",
            "Breaking news alert",
            "Your saved article has been updated",
            "New category added",
            "System maintenance scheduled"
        };

        for (String message : messages) {
            notificationDTO.setMessage(message);
            assertEquals(message, notificationDTO.getMessage());
        }
    }

    @Test
    void testNotificationDTOWithRealisticValues() {
        // Test with realistic values that might be used in a real application
        Integer[] realisticIds = {1, 5, 10, 25, 50, 100, 500, 1000};

        for (Integer id : realisticIds) {
            notificationDTO.setId(id);
            notificationDTO.setUserId(id * 2);
            notificationDTO.setIsRead(id % 2); // Alternate between 0 and 1

            assertEquals(id, notificationDTO.getId());
            assertEquals(id * 2, notificationDTO.getUserId());
            assertEquals(id % 2, notificationDTO.getIsRead());
        }
    }
} 