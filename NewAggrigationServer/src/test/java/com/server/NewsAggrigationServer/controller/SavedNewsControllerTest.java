package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.dto.SavedNewsDTO;
import com.server.NewsAggrigationServer.service.SavedNewsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SavedNewsControllerTest {

    @Mock
    private SavedNewsService savedNewsService;

    @InjectMocks
    private SavedNewsController savedNewsController;

    private SavedNewsDTO savedNewsDTO;

    @BeforeEach
    void setUp() {
        savedNewsDTO = new SavedNewsDTO();
        savedNewsDTO.setId(1);
        savedNewsDTO.setUserId(1);
        savedNewsDTO.setNewsId(1);
        savedNewsDTO.setSavedAt(LocalDateTime.now());
    }

    @Test
    void testSaveNews() {
        // Arrange
        when(savedNewsService.saveNews(any(SavedNewsDTO.class))).thenReturn(savedNewsDTO);

        // Act
        SavedNewsDTO result = savedNewsController.saveNews(savedNewsDTO);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(1, result.getUserId());
        assertEquals(1, result.getNewsId());
        verify(savedNewsService).saveNews(savedNewsDTO);
    }

    @Test
    void testSaveNewsWithNullInput() {
        // Arrange
        when(savedNewsService.saveNews(null)).thenThrow(new IllegalArgumentException("Saved news cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            savedNewsController.saveNews(null);
        });
        verify(savedNewsService).saveNews(null);
    }

    @Test
    void testSaveNewsWithNullUserId() {
        // Arrange
        savedNewsDTO.setUserId(null);
        when(savedNewsService.saveNews(savedNewsDTO)).thenThrow(new IllegalArgumentException("User ID cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            savedNewsController.saveNews(savedNewsDTO);
        });
        verify(savedNewsService).saveNews(savedNewsDTO);
    }

    @Test
    void testSaveNewsWithNullNewsId() {
        // Arrange
        savedNewsDTO.setNewsId(null);
        when(savedNewsService.saveNews(savedNewsDTO)).thenThrow(new IllegalArgumentException("News ID cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            savedNewsController.saveNews(savedNewsDTO);
        });
        verify(savedNewsService).saveNews(savedNewsDTO);
    }

    @Test
    void testSaveNewsWithServiceException() {
        // Arrange
        when(savedNewsService.saveNews(any(SavedNewsDTO.class))).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            savedNewsController.saveNews(savedNewsDTO);
        });
        verify(savedNewsService).saveNews(savedNewsDTO);
    }

    @Test
    void testDeleteSavedNews() {
        // Arrange
        doNothing().when(savedNewsService).deleteSavedNews(1, 1);

        // Act
        String result = savedNewsController.deleteSavedNews(1, 1);

        // Assert
        assertNotNull(result);
        assertEquals("Saved news deleted successfully", result);
        verify(savedNewsService).deleteSavedNews(1, 1);
    }

    @Test
    void testDeleteSavedNewsWithZeroUserId() {
        // Arrange
        doThrow(new IllegalArgumentException("Invalid user ID")).when(savedNewsService).deleteSavedNews(0, 1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            savedNewsController.deleteSavedNews(0, 1);
        });
        verify(savedNewsService).deleteSavedNews(0, 1);
    }

    @Test
    void testDeleteSavedNewsWithZeroNewsId() {
        // Arrange
        doThrow(new IllegalArgumentException("Invalid news ID")).when(savedNewsService).deleteSavedNews(1, 0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            savedNewsController.deleteSavedNews(1, 0);
        });
        verify(savedNewsService).deleteSavedNews(1, 0);
    }

    @Test
    void testDeleteSavedNewsWithNegativeUserId() {
        // Arrange
        doThrow(new IllegalArgumentException("Invalid user ID")).when(savedNewsService).deleteSavedNews(-1, 1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            savedNewsController.deleteSavedNews(-1, 1);
        });
        verify(savedNewsService).deleteSavedNews(-1, 1);
    }

    @Test
    void testDeleteSavedNewsWithNegativeNewsId() {
        // Arrange
        doThrow(new IllegalArgumentException("Invalid news ID")).when(savedNewsService).deleteSavedNews(1, -1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            savedNewsController.deleteSavedNews(1, -1);
        });
        verify(savedNewsService).deleteSavedNews(1, -1);
    }

    @Test
    void testDeleteSavedNewsWithServiceException() {
        // Arrange
        doThrow(new RuntimeException("Service error")).when(savedNewsService).deleteSavedNews(1, 1);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            savedNewsController.deleteSavedNews(1, 1);
        });
        verify(savedNewsService).deleteSavedNews(1, 1);
    }

    @Test
    void testSaveNewsWithZeroValues() {
        // Arrange
        savedNewsDTO.setUserId(0);
        savedNewsDTO.setNewsId(0);
        when(savedNewsService.saveNews(savedNewsDTO)).thenThrow(new IllegalArgumentException("Invalid IDs"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            savedNewsController.saveNews(savedNewsDTO);
        });
        verify(savedNewsService).saveNews(savedNewsDTO);
    }

    @Test
    void testSaveNewsWithNegativeValues() {
        // Arrange
        savedNewsDTO.setUserId(-1);
        savedNewsDTO.setNewsId(-1);
        when(savedNewsService.saveNews(savedNewsDTO)).thenThrow(new IllegalArgumentException("Invalid IDs"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            savedNewsController.saveNews(savedNewsDTO);
        });
        verify(savedNewsService).saveNews(savedNewsDTO);
    }

    @Test
    void testSaveNewsWithLargeValues() {
        // Arrange
        savedNewsDTO.setUserId(Integer.MAX_VALUE);
        savedNewsDTO.setNewsId(Integer.MAX_VALUE);
        when(savedNewsService.saveNews(savedNewsDTO)).thenReturn(savedNewsDTO);

        // Act
        SavedNewsDTO result = savedNewsController.saveNews(savedNewsDTO);

        // Assert
        assertNotNull(result);
        assertEquals(Integer.MAX_VALUE, result.getUserId());
        assertEquals(Integer.MAX_VALUE, result.getNewsId());
        verify(savedNewsService).saveNews(savedNewsDTO);
    }

    @Test
    void testDeleteSavedNewsWithLargeValues() {
        // Arrange
        doNothing().when(savedNewsService).deleteSavedNews(Integer.MAX_VALUE, Integer.MAX_VALUE);

        // Act
        String result = savedNewsController.deleteSavedNews(Integer.MAX_VALUE, Integer.MAX_VALUE);

        // Assert
        assertNotNull(result);
        assertEquals("Saved news deleted successfully", result);
        verify(savedNewsService).deleteSavedNews(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    @Test
    void testSaveNewsWithZeroId() {
        // Arrange
        savedNewsDTO.setId(0);
        when(savedNewsService.saveNews(savedNewsDTO)).thenReturn(savedNewsDTO);

        // Act
        SavedNewsDTO result = savedNewsController.saveNews(savedNewsDTO);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.getId());
        verify(savedNewsService).saveNews(savedNewsDTO);
    }

    @Test
    void testSaveNewsWithNegativeId() {
        // Arrange
        savedNewsDTO.setId(-1);
        when(savedNewsService.saveNews(savedNewsDTO)).thenReturn(savedNewsDTO);

        // Act
        SavedNewsDTO result = savedNewsController.saveNews(savedNewsDTO);

        // Assert
        assertNotNull(result);
        assertEquals(-1, result.getId());
        verify(savedNewsService).saveNews(savedNewsDTO);
    }

    @Test
    void testSaveNewsWithLargeId() {
        // Arrange
        savedNewsDTO.setId(Integer.MAX_VALUE);
        when(savedNewsService.saveNews(savedNewsDTO)).thenReturn(savedNewsDTO);

        // Act
        SavedNewsDTO result = savedNewsController.saveNews(savedNewsDTO);

        // Assert
        assertNotNull(result);
        assertEquals(Integer.MAX_VALUE, result.getId());
        verify(savedNewsService).saveNews(savedNewsDTO);
    }

    @Test
    void testSaveNewsWithNullSavedAt() {
        // Arrange
        savedNewsDTO.setSavedAt(null);
        when(savedNewsService.saveNews(savedNewsDTO)).thenReturn(savedNewsDTO);

        // Act
        SavedNewsDTO result = savedNewsController.saveNews(savedNewsDTO);

        // Assert
        assertNotNull(result);
        assertNull(result.getSavedAt());
        verify(savedNewsService).saveNews(savedNewsDTO);
    }

    @Test
    void testSaveNewsWithFutureSavedAt() {
        // Arrange
        savedNewsDTO.setSavedAt(LocalDateTime.now().plusDays(1));
        when(savedNewsService.saveNews(savedNewsDTO)).thenReturn(savedNewsDTO);

        // Act
        SavedNewsDTO result = savedNewsController.saveNews(savedNewsDTO);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getSavedAt());
        assertTrue(result.getSavedAt().isAfter(LocalDateTime.now()));
        verify(savedNewsService).saveNews(savedNewsDTO);
    }

    @Test
    void testSaveNewsWithPastSavedAt() {
        // Arrange
        savedNewsDTO.setSavedAt(LocalDateTime.now().minusDays(1));
        when(savedNewsService.saveNews(savedNewsDTO)).thenReturn(savedNewsDTO);

        // Act
        SavedNewsDTO result = savedNewsController.saveNews(savedNewsDTO);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getSavedAt());
        assertTrue(result.getSavedAt().isBefore(LocalDateTime.now()));
        verify(savedNewsService).saveNews(savedNewsDTO);
    }

    @Test
    void testDeleteSavedNewsWithNullUserId() {
        // Arrange
        doThrow(new IllegalArgumentException("User ID cannot be null")).when(savedNewsService).deleteSavedNews(null, 1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            savedNewsController.deleteSavedNews(null, 1);
        });
        verify(savedNewsService).deleteSavedNews(null, 1);
    }

    @Test
    void testDeleteSavedNewsWithNullNewsId() {
        // Arrange
        doThrow(new IllegalArgumentException("News ID cannot be null")).when(savedNewsService).deleteSavedNews(1, null);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            savedNewsController.deleteSavedNews(1, null);
        });
        verify(savedNewsService).deleteSavedNews(1, null);
    }

    @Test
    void testSaveNewsWithDuplicateEntry() {
        // Arrange
        when(savedNewsService.saveNews(savedNewsDTO)).thenThrow(new IllegalArgumentException("News already saved by this user"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            savedNewsController.saveNews(savedNewsDTO);
        });
        verify(savedNewsService).saveNews(savedNewsDTO);
    }

    @Test
    void testDeleteSavedNewsWithNonExistentEntry() {
        // Arrange
        doThrow(new IllegalArgumentException("Saved news not found")).when(savedNewsService).deleteSavedNews(1, 1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            savedNewsController.deleteSavedNews(1, 1);
        });
        verify(savedNewsService).deleteSavedNews(1, 1);
    }
} 