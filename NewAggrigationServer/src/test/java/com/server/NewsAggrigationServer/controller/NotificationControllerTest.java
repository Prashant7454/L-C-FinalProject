package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.dto.NotificationDTO;
import com.server.NewsAggrigationServer.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationControllerTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    private NotificationDTO notificationDTO1;
    private NotificationDTO notificationDTO2;
    private List<NotificationDTO> notificationList;

    @BeforeEach
    void setUp() {
        notificationDTO1 = new NotificationDTO();
        notificationDTO1.setId(1);
        notificationDTO1.setUserId(1);
        notificationDTO1.setMessage("Test notification 1");
        notificationDTO1.setIsRead(0);
        notificationDTO1.setCreatedAt(LocalDateTime.now());

        notificationDTO2 = new NotificationDTO();
        notificationDTO2.setId(2);
        notificationDTO2.setUserId(1);
        notificationDTO2.setMessage("Test notification 2");
        notificationDTO2.setIsRead(1);
        notificationDTO2.setCreatedAt(LocalDateTime.now());

        notificationList = Arrays.asList(notificationDTO1, notificationDTO2);
    }

    @Test
    void testGetNotificationsByUserId() {
        // Arrange
        when(notificationService.getNotificationsByUserId(1)).thenReturn(notificationList);

        // Act
        ResponseEntity<List<NotificationDTO>> result = notificationController.getNotificationsByUserId(1);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
        assertEquals(1, result.getBody().get(0).getId());
        assertEquals(2, result.getBody().get(1).getId());
        verify(notificationService).getNotificationsByUserId(1);
    }

    @Test
    void testGetNotificationsByUserIdWithEmptyList() {
        // Arrange
        when(notificationService.getNotificationsByUserId(1)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<NotificationDTO>> result = notificationController.getNotificationsByUserId(1);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().isEmpty());
        verify(notificationService).getNotificationsByUserId(1);
    }

    @Test
    void testGetNotificationsByUserIdWithNullUserId() {
        // Arrange
        when(notificationService.getNotificationsByUserId(null)).thenThrow(new IllegalArgumentException("User ID cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationController.getNotificationsByUserId(null);
        });
        verify(notificationService).getNotificationsByUserId(null);
    }

    @Test
    void testGetNotificationsByUserIdWithServiceException() {
        // Arrange
        when(notificationService.getNotificationsByUserId(1)).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            notificationController.getNotificationsByUserId(1);
        });
        verify(notificationService).getNotificationsByUserId(1);
    }

    @Test
    void testMarkNotificationAsRead() {
        // Arrange
        when(notificationService.markNotificationAsRead(1)).thenReturn(notificationDTO1);

        // Act
        ResponseEntity<NotificationDTO> result = notificationController.markNotificationAsRead(1);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(1, result.getBody().getId());
        assertEquals("Test notification 1", result.getBody().getMessage());
        verify(notificationService).markNotificationAsRead(1);
    }

    @Test
    void testMarkNotificationAsReadWithZeroId() {
        // Arrange
        when(notificationService.markNotificationAsRead(0)).thenThrow(new IllegalArgumentException("Invalid notification ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationController.markNotificationAsRead(0);
        });
        verify(notificationService).markNotificationAsRead(0);
    }

    @Test
    void testMarkNotificationAsReadWithNegativeId() {
        // Arrange
        when(notificationService.markNotificationAsRead(-1)).thenThrow(new IllegalArgumentException("Invalid notification ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationController.markNotificationAsRead(-1);
        });
        verify(notificationService).markNotificationAsRead(-1);
    }

    @Test
    void testMarkNotificationAsReadWithServiceException() {
        // Arrange
        when(notificationService.markNotificationAsRead(1)).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            notificationController.markNotificationAsRead(1);
        });
        verify(notificationService).markNotificationAsRead(1);
    }

    @Test
    void testDeleteNotification() {
        // Arrange
        doNothing().when(notificationService).deleteNotification(1);

        // Act
        ResponseEntity<String> result = notificationController.deleteNotification(1);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Notification deleted successfully", result.getBody());
        verify(notificationService).deleteNotification(1);
    }

    @Test
    void testDeleteNotificationWithZeroId() {
        // Arrange
        doThrow(new IllegalArgumentException("Invalid notification ID")).when(notificationService).deleteNotification(0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationController.deleteNotification(0);
        });
        verify(notificationService).deleteNotification(0);
    }

    @Test
    void testDeleteNotificationWithNegativeId() {
        // Arrange
        doThrow(new IllegalArgumentException("Invalid notification ID")).when(notificationService).deleteNotification(-1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationController.deleteNotification(-1);
        });
        verify(notificationService).deleteNotification(-1);
    }

    @Test
    void testDeleteNotificationWithServiceException() {
        // Arrange
        doThrow(new RuntimeException("Service error")).when(notificationService).deleteNotification(1);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            notificationController.deleteNotification(1);
        });
        verify(notificationService).deleteNotification(1);
    }

    @Test
    void testGetUnreadNotificationsByUserId() {
        // Arrange
        when(notificationService.getUnreadNotificationsByUserId(1)).thenReturn(Collections.singletonList(notificationDTO1));

        // Act
        ResponseEntity<List<NotificationDTO>> result = notificationController.getUnreadNotificationsByUserId(1);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(1, result.getBody().size());
        assertEquals(1, result.getBody().get(0).getId());
        assertEquals(0, result.getBody().get(0).getIsRead());
        verify(notificationService).getUnreadNotificationsByUserId(1);
    }

    @Test
    void testGetUnreadNotificationsByUserIdWithEmptyList() {
        // Arrange
        when(notificationService.getUnreadNotificationsByUserId(1)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<NotificationDTO>> result = notificationController.getUnreadNotificationsByUserId(1);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().isEmpty());
        verify(notificationService).getUnreadNotificationsByUserId(1);
    }

    @Test
    void testGetUnreadNotificationsByUserIdWithNullUserId() {
        // Arrange
        when(notificationService.getUnreadNotificationsByUserId(null)).thenThrow(new IllegalArgumentException("User ID cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationController.getUnreadNotificationsByUserId(null);
        });
        verify(notificationService).getUnreadNotificationsByUserId(null);
    }

    @Test
    void testGetUnreadNotificationsByUserIdWithServiceException() {
        // Arrange
        when(notificationService.getUnreadNotificationsByUserId(1)).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            notificationController.getUnreadNotificationsByUserId(1);
        });
        verify(notificationService).getUnreadNotificationsByUserId(1);
    }

    @Test
    void testGetNotificationsByUserIdWithZeroUserId() {
        // Arrange
        when(notificationService.getNotificationsByUserId(0)).thenThrow(new IllegalArgumentException("Invalid user ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationController.getNotificationsByUserId(0);
        });
        verify(notificationService).getNotificationsByUserId(0);
    }

    @Test
    void testGetNotificationsByUserIdWithNegativeUserId() {
        // Arrange
        when(notificationService.getNotificationsByUserId(-1)).thenThrow(new IllegalArgumentException("Invalid user ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationController.getNotificationsByUserId(-1);
        });
        verify(notificationService).getNotificationsByUserId(-1);
    }

    @Test
    void testGetNotificationsByUserIdWithLargeUserId() {
        // Arrange
        when(notificationService.getNotificationsByUserId(Integer.MAX_VALUE)).thenReturn(notificationList);

        // Act
        ResponseEntity<List<NotificationDTO>> result = notificationController.getNotificationsByUserId(Integer.MAX_VALUE);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
        verify(notificationService).getNotificationsByUserId(Integer.MAX_VALUE);
    }

    @Test
    void testMarkNotificationAsReadWithLargeId() {
        // Arrange
        when(notificationService.markNotificationAsRead(Integer.MAX_VALUE)).thenReturn(notificationDTO1);

        // Act
        ResponseEntity<NotificationDTO> result = notificationController.markNotificationAsRead(Integer.MAX_VALUE);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(1, result.getBody().getId());
        verify(notificationService).markNotificationAsRead(Integer.MAX_VALUE);
    }

    @Test
    void testDeleteNotificationWithLargeId() {
        // Arrange
        doNothing().when(notificationService).deleteNotification(Integer.MAX_VALUE);

        // Act
        ResponseEntity<String> result = notificationController.deleteNotification(Integer.MAX_VALUE);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Notification deleted successfully", result.getBody());
        verify(notificationService).deleteNotification(Integer.MAX_VALUE);
    }

    @Test
    void testGetUnreadNotificationsByUserIdWithZeroUserId() {
        // Arrange
        when(notificationService.getUnreadNotificationsByUserId(0)).thenThrow(new IllegalArgumentException("Invalid user ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationController.getUnreadNotificationsByUserId(0);
        });
        verify(notificationService).getUnreadNotificationsByUserId(0);
    }

    @Test
    void testGetUnreadNotificationsByUserIdWithNegativeUserId() {
        // Arrange
        when(notificationService.getUnreadNotificationsByUserId(-1)).thenThrow(new IllegalArgumentException("Invalid user ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationController.getUnreadNotificationsByUserId(-1);
        });
        verify(notificationService).getUnreadNotificationsByUserId(-1);
    }

    @Test
    void testGetUnreadNotificationsByUserIdWithLargeUserId() {
        // Arrange
        when(notificationService.getUnreadNotificationsByUserId(Integer.MAX_VALUE)).thenReturn(notificationList);

        // Act
        ResponseEntity<List<NotificationDTO>> result = notificationController.getUnreadNotificationsByUserId(Integer.MAX_VALUE);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
        verify(notificationService).getUnreadNotificationsByUserId(Integer.MAX_VALUE);
    }
} 