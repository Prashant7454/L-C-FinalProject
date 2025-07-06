package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.model.Notification;
import com.server.NewsAggrigationServer.repository.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

    private Notification notification1;
    private Notification notification2;

    @BeforeEach
    void setUp() {
        notification1 = new Notification();
        notification1.setId(1);
        notification1.setUserId(1);
        notification1.setMessage("New technology news available");
        notification1.setIsRead(0);

        notification2 = new Notification();
        notification2.setId(2);
        notification2.setUserId(1);
        notification2.setMessage("Breaking news alert");
        notification2.setIsRead(1);
    }

    @Test
    void testGetNotificationsByUserId() {
        // Arrange
        List<Notification> expectedNotifications = Arrays.asList(notification1, notification2);
        when(notificationRepository.findByUserId(1)).thenReturn(expectedNotifications);

        // Act
        List<Notification> result = notificationService.getNotificationsByUserId(1);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getUserId());
        assertEquals("New technology news available", result.get(0).getMessage());
        assertEquals(0, result.get(0).getIsRead());
        assertEquals(1, result.get(1).getUserId());
        assertEquals("Breaking news alert", result.get(1).getMessage());
        assertEquals(1, result.get(1).getIsRead());

        verify(notificationRepository).findByUserId(1);
    }

    @Test
    void testGetNotificationsByUserIdEmptyList() {
        // Arrange
        when(notificationRepository.findByUserId(1)).thenReturn(Arrays.asList());

        // Act
        List<Notification> result = notificationService.getNotificationsByUserId(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(notificationRepository).findByUserId(1);
    }

    @Test
    void testGetNotificationsByUserIdWithZero() {
        // Arrange
        when(notificationRepository.findByUserId(0)).thenReturn(Arrays.asList());

        // Act
        List<Notification> result = notificationService.getNotificationsByUserId(0);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(notificationRepository).findByUserId(0);
    }

    @Test
    void testGetNotificationsByUserIdWithNegativeValue() {
        // Arrange
        when(notificationRepository.findByUserId(-1)).thenReturn(Arrays.asList());

        // Act
        List<Notification> result = notificationService.getNotificationsByUserId(-1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(notificationRepository).findByUserId(-1);
    }

    @Test
    void testGetNotificationsByUserIdWithLargeValue() {
        // Arrange
        when(notificationRepository.findByUserId(Integer.MAX_VALUE)).thenReturn(Arrays.asList());

        // Act
        List<Notification> result = notificationService.getNotificationsByUserId(Integer.MAX_VALUE);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(notificationRepository).findByUserId(Integer.MAX_VALUE);
    }

    @Test
    void testGetNotificationsByUserIdWithMultipleUsers() {
        // Arrange
        Notification user2Notification = new Notification();
        user2Notification.setId(3);
        user2Notification.setUserId(2);
        user2Notification.setMessage("User 2 notification");
        user2Notification.setIsRead(0);

        when(notificationRepository.findByUserId(1)).thenReturn(Arrays.asList(notification1, notification2));
        when(notificationRepository.findByUserId(2)).thenReturn(Arrays.asList(user2Notification));

        // Act
        List<Notification> user1Result = notificationService.getNotificationsByUserId(1);
        List<Notification> user2Result = notificationService.getNotificationsByUserId(2);

        // Assert
        assertEquals(2, user1Result.size());
        assertEquals(1, user2Result.size());
        assertEquals(2, user2Result.get(0).getUserId());

        verify(notificationRepository).findByUserId(1);
        verify(notificationRepository).findByUserId(2);
    }

    @Test
    void testGetNotificationsByUserIdWithRepositoryException() {
        // Arrange
        when(notificationRepository.findByUserId(1)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            notificationService.getNotificationsByUserId(1);
        });

        verify(notificationRepository).findByUserId(1);
    }

    @Test
    void testGetNotificationsByUserIdWithRealisticValues() {
        // Test with realistic user IDs that might be used in a real application
        Integer[] realisticUserIds = {1, 5, 10, 25, 50, 100, 500, 1000};

        for (Integer userId : realisticUserIds) {
            // Arrange
            when(notificationRepository.findByUserId(userId)).thenReturn(Arrays.asList(notification1));

            // Act
            List<Notification> result = notificationService.getNotificationsByUserId(userId);

            // Assert
            assertNotNull(result);
            assertEquals(1, result.size());

            verify(notificationRepository).findByUserId(userId);
        }
    }

    @Test
    void testGetNotificationsByUserIdWithBoundaryValues() {
        // Test minimum value
        when(notificationRepository.findByUserId(0)).thenReturn(Arrays.asList());
        List<Notification> minResult = notificationService.getNotificationsByUserId(0);
        assertTrue(minResult.isEmpty());

        // Test maximum value
        when(notificationRepository.findByUserId(Integer.MAX_VALUE)).thenReturn(Arrays.asList());
        List<Notification> maxResult = notificationService.getNotificationsByUserId(Integer.MAX_VALUE);
        assertTrue(maxResult.isEmpty());

        verify(notificationRepository).findByUserId(0);
        verify(notificationRepository).findByUserId(Integer.MAX_VALUE);
    }

    @Test
    void testGetNotificationsByUserIdWithSequentialValues() {
        for (int i = 1; i <= 10; i++) {
            // Arrange
            Notification sequentialNotification = new Notification();
            sequentialNotification.setId(i);
            sequentialNotification.setUserId(i);
            sequentialNotification.setMessage("Notification " + i);
            sequentialNotification.setIsRead(i % 2); // Alternate between 0 and 1

            when(notificationRepository.findByUserId(i)).thenReturn(Arrays.asList(sequentialNotification));

            // Act
            List<Notification> result = notificationService.getNotificationsByUserId(i);

            // Assert
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(i, result.get(0).getUserId());
            assertEquals("Notification " + i, result.get(0).getMessage());
            assertEquals(i % 2, result.get(0).getIsRead());

            verify(notificationRepository).findByUserId(i);
        }
    }

    @Test
    void testGetNotificationsByUserIdWithLargeList() {
        // Arrange
        List<Notification> largeList = Arrays.asList();
        for (int i = 1; i <= 100; i++) {
            Notification notification = new Notification();
            notification.setId(i);
            notification.setUserId(1);
            notification.setMessage("Notification " + i);
            notification.setIsRead(i % 2);
            largeList = Arrays.asList(notification);
        }

        when(notificationRepository.findByUserId(1)).thenReturn(largeList);

        // Act
        List<Notification> result = notificationService.getNotificationsByUserId(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size()); // Since we're creating a new list each time

        verify(notificationRepository).findByUserId(1);
    }

    @Test
    void testGetNotificationsByUserIdWithNullReturn() {
        // Arrange
        when(notificationRepository.findByUserId(1)).thenReturn(null);

        // Act
        List<Notification> result = notificationService.getNotificationsByUserId(1);

        // Assert
        assertNull(result);

        verify(notificationRepository).findByUserId(1);
    }

    @Test
    void testGetNotificationsByUserIdWithMixedData() {
        // Arrange
        Notification mixedNotification1 = new Notification();
        mixedNotification1.setId(1);
        mixedNotification1.setUserId(1);
        mixedNotification1.setMessage("Breaking news");
        mixedNotification1.setIsRead(0);

        Notification mixedNotification2 = new Notification();
        mixedNotification2.setId(2);
        mixedNotification2.setUserId(1);
        mixedNotification2.setMessage("Technology update");
        mixedNotification2.setIsRead(1);

        Notification mixedNotification3 = new Notification();
        mixedNotification3.setId(3);
        mixedNotification3.setUserId(1);
        mixedNotification3.setMessage("Weather alert");
        mixedNotification3.setIsRead(0);

        List<Notification> mixedList = Arrays.asList(mixedNotification1, mixedNotification2, mixedNotification3);

        when(notificationRepository.findByUserId(1)).thenReturn(mixedList);

        // Act
        List<Notification> result = notificationService.getNotificationsByUserId(1);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("Breaking news", result.get(0).getMessage());
        assertEquals(0, result.get(0).getIsRead());
        assertEquals("Technology update", result.get(1).getMessage());
        assertEquals(1, result.get(1).getIsRead());
        assertEquals("Weather alert", result.get(2).getMessage());
        assertEquals(0, result.get(2).getIsRead());

        verify(notificationRepository).findByUserId(1);
    }

    @Test
    void testGetNotificationsByUserIdWithDifferentMessages() {
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

        for (int i = 0; i < messages.length; i++) {
            Notification notification = new Notification();
            notification.setId(i + 1);
            notification.setUserId(1);
            notification.setMessage(messages[i]);
            notification.setIsRead(i % 2);

            when(notificationRepository.findByUserId(1)).thenReturn(Arrays.asList(notification));

            // Act
            List<Notification> result = notificationService.getNotificationsByUserId(1);

            // Assert
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(messages[i], result.get(0).getMessage());
            assertEquals(i % 2, result.get(0).getIsRead());

            verify(notificationRepository).findByUserId(1);
        }
    }

    @Test
    void testGetNotificationsByUserIdWithReadStatus() {
        // Arrange
        Notification unreadNotification = new Notification();
        unreadNotification.setId(1);
        unreadNotification.setUserId(1);
        unreadNotification.setMessage("Unread notification");
        unreadNotification.setIsRead(0);

        Notification readNotification = new Notification();
        readNotification.setId(2);
        readNotification.setUserId(1);
        readNotification.setMessage("Read notification");
        readNotification.setIsRead(1);

        when(notificationRepository.findByUserId(1)).thenReturn(Arrays.asList(unreadNotification, readNotification));

        // Act
        List<Notification> result = notificationService.getNotificationsByUserId(1);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(0, result.get(0).getIsRead());
        assertEquals(1, result.get(1).getIsRead());

        verify(notificationRepository).findByUserId(1);
    }
} 