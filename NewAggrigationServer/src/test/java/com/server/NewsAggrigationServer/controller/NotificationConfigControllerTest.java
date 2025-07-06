package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.dto.NotificationConfigDTO;
import com.server.NewsAggrigationServer.service.NotificationConfigService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationConfigControllerTest {

    @Mock
    private NotificationConfigService configService;

    @InjectMocks
    private NotificationConfigController notificationConfigController;

    private NotificationConfigDTO configDTO1;
    private NotificationConfigDTO configDTO2;
    private List<NotificationConfigDTO> configList;

    @BeforeEach
    void setUp() {
        configDTO1 = new NotificationConfigDTO();
        configDTO1.setId(1);
        configDTO1.setUserId(1);
        configDTO1.setCategoryId(1);
        configDTO1.setIsEnabled(1);

        configDTO2 = new NotificationConfigDTO();
        configDTO2.setId(2);
        configDTO2.setUserId(1);
        configDTO2.setCategoryId(2);
        configDTO2.setIsEnabled(0);

        configList = Arrays.asList(configDTO1, configDTO2);
    }

    @Test
    void testSaveConfig() {
        // Arrange
        when(configService.saveConfig(any(NotificationConfigDTO.class))).thenReturn(configDTO1);

        // Act
        NotificationConfigDTO result = notificationConfigController.saveConfig(configDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(1, result.getUserId());
        assertEquals(1, result.getCategoryId());
        assertEquals(1, result.getIsEnabled());
        verify(configService).saveConfig(configDTO1);
    }

    @Test
    void testSaveConfigWithNullInput() {
        // Arrange
        when(configService.saveConfig(null)).thenThrow(new IllegalArgumentException("Config cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationConfigController.saveConfig(null);
        });
        verify(configService).saveConfig(null);
    }

    @Test
    void testSaveConfigWithServiceException() {
        // Arrange
        when(configService.saveConfig(any(NotificationConfigDTO.class))).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            notificationConfigController.saveConfig(configDTO1);
        });
        verify(configService).saveConfig(configDTO1);
    }

    @Test
    void testGetNotificationConfigurationsByUserId() {
        // Arrange
        when(configService.getNotificationConfigurationsByUserId(1)).thenReturn(configList);

        // Act
        List<NotificationConfigDTO> result = notificationConfigController.getNotificationConfigurationsByUserId(1);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
        assertEquals(1, result.get(0).getUserId());
        assertEquals(1, result.get(1).getUserId());
        verify(configService).getNotificationConfigurationsByUserId(1);
    }

    @Test
    void testGetNotificationConfigurationsByUserIdWithEmptyList() {
        // Arrange
        when(configService.getNotificationConfigurationsByUserId(1)).thenReturn(Collections.emptyList());

        // Act
        List<NotificationConfigDTO> result = notificationConfigController.getNotificationConfigurationsByUserId(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(configService).getNotificationConfigurationsByUserId(1);
    }

    @Test
    void testGetNotificationConfigurationsByUserIdWithNullUserId() {
        // Arrange
        when(configService.getNotificationConfigurationsByUserId(null)).thenThrow(new IllegalArgumentException("User ID cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationConfigController.getNotificationConfigurationsByUserId(null);
        });
        verify(configService).getNotificationConfigurationsByUserId(null);
    }

    @Test
    void testGetNotificationConfigurationsByUserIdWithServiceException() {
        // Arrange
        when(configService.getNotificationConfigurationsByUserId(1)).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            notificationConfigController.getNotificationConfigurationsByUserId(1);
        });
        verify(configService).getNotificationConfigurationsByUserId(1);
    }

    @Test
    void testGetNotificationConfigurationByUserIdAndCategoryId() {
        // Arrange
        when(configService.getNotificationConfigurationByUserIdAndCategoryId(1, 1)).thenReturn(configDTO1);

        // Act
        NotificationConfigDTO result = notificationConfigController.getNotificationConfigurationByUserIdAndCategoryId(1, 1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(1, result.getUserId());
        assertEquals(1, result.getCategoryId());
        assertEquals(1, result.getIsEnabled());
        verify(configService).getNotificationConfigurationByUserIdAndCategoryId(1, 1);
    }

    @Test
    void testGetNotificationConfigurationByUserIdAndCategoryIdWithNullUserId() {
        // Arrange
        when(configService.getNotificationConfigurationByUserIdAndCategoryId(null, 1)).thenThrow(new IllegalArgumentException("User ID cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationConfigController.getNotificationConfigurationByUserIdAndCategoryId(null, 1);
        });
        verify(configService).getNotificationConfigurationByUserIdAndCategoryId(null, 1);
    }

    @Test
    void testGetNotificationConfigurationByUserIdAndCategoryIdWithNullCategoryId() {
        // Arrange
        when(configService.getNotificationConfigurationByUserIdAndCategoryId(1, null)).thenThrow(new IllegalArgumentException("Category ID cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationConfigController.getNotificationConfigurationByUserIdAndCategoryId(1, null);
        });
        verify(configService).getNotificationConfigurationByUserIdAndCategoryId(1, null);
    }

    @Test
    void testGetNotificationConfigurationByUserIdAndCategoryIdWithServiceException() {
        // Arrange
        when(configService.getNotificationConfigurationByUserIdAndCategoryId(1, 1)).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            notificationConfigController.getNotificationConfigurationByUserIdAndCategoryId(1, 1);
        });
        verify(configService).getNotificationConfigurationByUserIdAndCategoryId(1, 1);
    }

    @Test
    void testUpdateNotificationConfiguration() {
        // Arrange
        when(configService.updateNotificationConfiguration(any(NotificationConfigDTO.class))).thenReturn(configDTO1);

        // Act
        NotificationConfigDTO result = notificationConfigController.updateNotificationConfiguration(configDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(1, result.getUserId());
        assertEquals(1, result.getCategoryId());
        assertEquals(1, result.getIsEnabled());
        verify(configService).updateNotificationConfiguration(configDTO1);
    }

    @Test
    void testUpdateNotificationConfigurationWithNullInput() {
        // Arrange
        when(configService.updateNotificationConfiguration(null)).thenThrow(new IllegalArgumentException("Config cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationConfigController.updateNotificationConfiguration(null);
        });
        verify(configService).updateNotificationConfiguration(null);
    }

    @Test
    void testUpdateNotificationConfigurationWithServiceException() {
        // Arrange
        when(configService.updateNotificationConfiguration(any(NotificationConfigDTO.class))).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            notificationConfigController.updateNotificationConfiguration(configDTO1);
        });
        verify(configService).updateNotificationConfiguration(configDTO1);
    }

    @Test
    void testDeleteNotificationConfiguration() {
        // Arrange
        doNothing().when(configService).deleteNotificationConfiguration(1, 1);

        // Act
        notificationConfigController.deleteNotificationConfiguration(1, 1);

        // Assert
        verify(configService).deleteNotificationConfiguration(1, 1);
    }

    @Test
    void testDeleteNotificationConfigurationWithNullUserId() {
        // Arrange
        doThrow(new IllegalArgumentException("User ID cannot be null")).when(configService).deleteNotificationConfiguration(null, 1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationConfigController.deleteNotificationConfiguration(null, 1);
        });
        verify(configService).deleteNotificationConfiguration(null, 1);
    }

    @Test
    void testDeleteNotificationConfigurationWithNullCategoryId() {
        // Arrange
        doThrow(new IllegalArgumentException("Category ID cannot be null")).when(configService).deleteNotificationConfiguration(1, null);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationConfigController.deleteNotificationConfiguration(1, null);
        });
        verify(configService).deleteNotificationConfiguration(1, null);
    }

    @Test
    void testDeleteNotificationConfigurationWithServiceException() {
        // Arrange
        doThrow(new RuntimeException("Service error")).when(configService).deleteNotificationConfiguration(1, 1);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            notificationConfigController.deleteNotificationConfiguration(1, 1);
        });
        verify(configService).deleteNotificationConfiguration(1, 1);
    }

    @Test
    void testSaveConfigWithZeroValues() {
        // Arrange
        configDTO1.setUserId(0);
        configDTO1.setCategoryId(0);
        when(configService.saveConfig(configDTO1)).thenThrow(new IllegalArgumentException("Invalid IDs"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationConfigController.saveConfig(configDTO1);
        });
        verify(configService).saveConfig(configDTO1);
    }

    @Test
    void testSaveConfigWithNegativeValues() {
        // Arrange
        configDTO1.setUserId(-1);
        configDTO1.setCategoryId(-1);
        when(configService.saveConfig(configDTO1)).thenThrow(new IllegalArgumentException("Invalid IDs"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationConfigController.saveConfig(configDTO1);
        });
        verify(configService).saveConfig(configDTO1);
    }

    @Test
    void testSaveConfigWithLargeValues() {
        // Arrange
        configDTO1.setUserId(Integer.MAX_VALUE);
        configDTO1.setCategoryId(Integer.MAX_VALUE);
        when(configService.saveConfig(configDTO1)).thenReturn(configDTO1);

        // Act
        NotificationConfigDTO result = notificationConfigController.saveConfig(configDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(Integer.MAX_VALUE, result.getUserId());
        assertEquals(Integer.MAX_VALUE, result.getCategoryId());
        verify(configService).saveConfig(configDTO1);
    }

    @Test
    void testGetNotificationConfigurationsByUserIdWithZeroUserId() {
        // Arrange
        when(configService.getNotificationConfigurationsByUserId(0)).thenThrow(new IllegalArgumentException("Invalid user ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationConfigController.getNotificationConfigurationsByUserId(0);
        });
        verify(configService).getNotificationConfigurationsByUserId(0);
    }

    @Test
    void testGetNotificationConfigurationsByUserIdWithNegativeUserId() {
        // Arrange
        when(configService.getNotificationConfigurationsByUserId(-1)).thenThrow(new IllegalArgumentException("Invalid user ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationConfigController.getNotificationConfigurationsByUserId(-1);
        });
        verify(configService).getNotificationConfigurationsByUserId(-1);
    }

    @Test
    void testGetNotificationConfigurationsByUserIdWithLargeUserId() {
        // Arrange
        when(configService.getNotificationConfigurationsByUserId(Integer.MAX_VALUE)).thenReturn(configList);

        // Act
        List<NotificationConfigDTO> result = notificationConfigController.getNotificationConfigurationsByUserId(Integer.MAX_VALUE);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(configService).getNotificationConfigurationsByUserId(Integer.MAX_VALUE);
    }

    @Test
    void testGetNotificationConfigurationByUserIdAndCategoryIdWithZeroValues() {
        // Arrange
        when(configService.getNotificationConfigurationByUserIdAndCategoryId(0, 0)).thenThrow(new IllegalArgumentException("Invalid IDs"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationConfigController.getNotificationConfigurationByUserIdAndCategoryId(0, 0);
        });
        verify(configService).getNotificationConfigurationByUserIdAndCategoryId(0, 0);
    }

    @Test
    void testGetNotificationConfigurationByUserIdAndCategoryIdWithNegativeValues() {
        // Arrange
        when(configService.getNotificationConfigurationByUserIdAndCategoryId(-1, -1)).thenThrow(new IllegalArgumentException("Invalid IDs"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationConfigController.getNotificationConfigurationByUserIdAndCategoryId(-1, -1);
        });
        verify(configService).getNotificationConfigurationByUserIdAndCategoryId(-1, -1);
    }

    @Test
    void testGetNotificationConfigurationByUserIdAndCategoryIdWithLargeValues() {
        // Arrange
        when(configService.getNotificationConfigurationByUserIdAndCategoryId(Integer.MAX_VALUE, Integer.MAX_VALUE)).thenReturn(configDTO1);

        // Act
        NotificationConfigDTO result = notificationConfigController.getNotificationConfigurationByUserIdAndCategoryId(Integer.MAX_VALUE, Integer.MAX_VALUE);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(configService).getNotificationConfigurationByUserIdAndCategoryId(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    @Test
    void testUpdateNotificationConfigurationWithZeroValues() {
        // Arrange
        configDTO1.setUserId(0);
        configDTO1.setCategoryId(0);
        when(configService.updateNotificationConfiguration(configDTO1)).thenThrow(new IllegalArgumentException("Invalid IDs"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationConfigController.updateNotificationConfiguration(configDTO1);
        });
        verify(configService).updateNotificationConfiguration(configDTO1);
    }

    @Test
    void testUpdateNotificationConfigurationWithNegativeValues() {
        // Arrange
        configDTO1.setUserId(-1);
        configDTO1.setCategoryId(-1);
        when(configService.updateNotificationConfiguration(configDTO1)).thenThrow(new IllegalArgumentException("Invalid IDs"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationConfigController.updateNotificationConfiguration(configDTO1);
        });
        verify(configService).updateNotificationConfiguration(configDTO1);
    }

    @Test
    void testUpdateNotificationConfigurationWithLargeValues() {
        // Arrange
        configDTO1.setUserId(Integer.MAX_VALUE);
        configDTO1.setCategoryId(Integer.MAX_VALUE);
        when(configService.updateNotificationConfiguration(configDTO1)).thenReturn(configDTO1);

        // Act
        NotificationConfigDTO result = notificationConfigController.updateNotificationConfiguration(configDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(Integer.MAX_VALUE, result.getUserId());
        assertEquals(Integer.MAX_VALUE, result.getCategoryId());
        verify(configService).updateNotificationConfiguration(configDTO1);
    }

    @Test
    void testDeleteNotificationConfigurationWithZeroValues() {
        // Arrange
        doThrow(new IllegalArgumentException("Invalid IDs")).when(configService).deleteNotificationConfiguration(0, 0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationConfigController.deleteNotificationConfiguration(0, 0);
        });
        verify(configService).deleteNotificationConfiguration(0, 0);
    }

    @Test
    void testDeleteNotificationConfigurationWithNegativeValues() {
        // Arrange
        doThrow(new IllegalArgumentException("Invalid IDs")).when(configService).deleteNotificationConfiguration(-1, -1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationConfigController.deleteNotificationConfiguration(-1, -1);
        });
        verify(configService).deleteNotificationConfiguration(-1, -1);
    }

    @Test
    void testDeleteNotificationConfigurationWithLargeValues() {
        // Arrange
        doNothing().when(configService).deleteNotificationConfiguration(Integer.MAX_VALUE, Integer.MAX_VALUE);

        // Act
        notificationConfigController.deleteNotificationConfiguration(Integer.MAX_VALUE, Integer.MAX_VALUE);

        // Assert
        verify(configService).deleteNotificationConfiguration(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
} 