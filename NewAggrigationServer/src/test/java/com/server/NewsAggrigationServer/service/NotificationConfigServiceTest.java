package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.model.NotificationConfig;
import com.server.NewsAggrigationServer.repository.NotificationConfigRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationConfigServiceTest {

    @Mock
    private NotificationConfigRepository notificationConfigRepository;

    @InjectMocks
    private NotificationConfigService notificationConfigService;

    private NotificationConfig config1;
    private NotificationConfig config2;

    @BeforeEach
    void setUp() {
        config1 = new NotificationConfig();
        config1.setId(1);
        config1.setUserId(1);
        config1.setCategoryId(1);
        config1.setIsEnabled(1);

        config2 = new NotificationConfig();
        config2.setId(2);
        config2.setUserId(1);
        config2.setCategoryId(2);
        config2.setIsEnabled(0);
    }

    @Test
    void testGetConfigsByUserId() {
        // Arrange
        List<NotificationConfig> expectedConfigs = Arrays.asList(config1, config2);
        when(notificationConfigRepository.findByUserId(1)).thenReturn(expectedConfigs);

        // Act
        List<NotificationConfig> result = notificationConfigService.getConfigsByUserId(1);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getUserId());
        assertEquals(1, result.get(0).getCategoryId());
        assertEquals(1, result.get(0).getIsEnabled());
        assertEquals(1, result.get(1).getUserId());
        assertEquals(2, result.get(1).getCategoryId());
        assertEquals(0, result.get(1).getIsEnabled());
        verify(notificationConfigRepository).findByUserId(1);
    }

    @Test
    void testGetConfigsByUserIdEmptyList() {
        // Arrange
        when(notificationConfigRepository.findByUserId(1)).thenReturn(Collections.emptyList());

        // Act
        List<NotificationConfig> result = notificationConfigService.getConfigsByUserId(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(notificationConfigRepository).findByUserId(1);
    }

    @Test
    void testGetConfigsByUserIdWithZero() {
        // Arrange
        when(notificationConfigRepository.findByUserId(0)).thenReturn(Collections.emptyList());

        // Act
        List<NotificationConfig> result = notificationConfigService.getConfigsByUserId(0);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(notificationConfigRepository).findByUserId(0);
    }

    @Test
    void testGetConfigsByUserIdWithNegativeValue() {
        // Arrange
        when(notificationConfigRepository.findByUserId(-1)).thenReturn(Collections.emptyList());

        // Act
        List<NotificationConfig> result = notificationConfigService.getConfigsByUserId(-1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(notificationConfigRepository).findByUserId(-1);
    }

    @Test
    void testGetConfigsByUserIdWithLargeValue() {
        // Arrange
        when(notificationConfigRepository.findByUserId(Integer.MAX_VALUE)).thenReturn(Collections.emptyList());

        // Act
        List<NotificationConfig> result = notificationConfigService.getConfigsByUserId(Integer.MAX_VALUE);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(notificationConfigRepository).findByUserId(Integer.MAX_VALUE);
    }

    @Test
    void testGetConfigsByUserIdWithRepositoryException() {
        // Arrange
        when(notificationConfigRepository.findByUserId(1)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            notificationConfigService.getConfigsByUserId(1);
        });
        verify(notificationConfigRepository).findByUserId(1);
    }

    @Test
    void testGetConfigsByUserIdWithRealisticValues() {
        // Test with realistic user IDs that might be used in a real application
        Integer[] realisticUserIds = {1, 5, 10, 25, 50, 100, 500, 1000};
        for (Integer userId : realisticUserIds) {
            when(notificationConfigRepository.findByUserId(userId)).thenReturn(Arrays.asList(config1));
            List<NotificationConfig> result = notificationConfigService.getConfigsByUserId(userId);
            assertNotNull(result);
            assertEquals(1, result.size());
            verify(notificationConfigRepository).findByUserId(userId);
        }
    }

    @Test
    void testGetConfigsByUserIdWithBoundaryValues() {
        // Test minimum value
        when(notificationConfigRepository.findByUserId(0)).thenReturn(Collections.emptyList());
        List<NotificationConfig> minResult = notificationConfigService.getConfigsByUserId(0);
        assertTrue(minResult.isEmpty());
        // Test maximum value
        when(notificationConfigRepository.findByUserId(Integer.MAX_VALUE)).thenReturn(Collections.emptyList());
        List<NotificationConfig> maxResult = notificationConfigService.getConfigsByUserId(Integer.MAX_VALUE);
        assertTrue(maxResult.isEmpty());
        verify(notificationConfigRepository).findByUserId(0);
        verify(notificationConfigRepository).findByUserId(Integer.MAX_VALUE);
    }
} 