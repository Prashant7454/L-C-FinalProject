package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.dto.ExternalNewsSourceDTO;
import com.server.NewsAggrigationServer.service.ExternalNewsSourceService;
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
class ExternalNewsSourceControllerTest {

    @Mock
    private ExternalNewsSourceService externalNewsSourceService;

    @InjectMocks
    private ExternalNewsSourceController externalNewsSourceController;

    private ExternalNewsSourceDTO sourceDTO1;
    private ExternalNewsSourceDTO sourceDTO2;
    private List<ExternalNewsSourceDTO> sourceList;

    @BeforeEach
    void setUp() {
        sourceDTO1 = new ExternalNewsSourceDTO();
        sourceDTO1.setId(1);
        sourceDTO1.setName("CNN");
        sourceDTO1.setUrl("https://cnn.com");
        sourceDTO1.setIsActive(1);

        sourceDTO2 = new ExternalNewsSourceDTO();
        sourceDTO2.setId(2);
        sourceDTO2.setName("BBC");
        sourceDTO2.setUrl("https://bbc.com");
        sourceDTO2.setIsActive(0);

        sourceList = Arrays.asList(sourceDTO1, sourceDTO2);
    }

    @Test
    void testGetExternalSourceById() {
        // Arrange
        when(externalNewsSourceService.getExternalSourceById(1)).thenReturn(sourceDTO1);

        // Act
        ExternalNewsSourceDTO result = externalNewsSourceController.getExternalSourceById(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("CNN", result.getName());
        assertEquals("https://cnn.com", result.getUrl());
        assertEquals(1, result.getIsActive());
        verify(externalNewsSourceService).getExternalSourceById(1);
    }

    @Test
    void testGetExternalSourceByIdWithZeroId() {
        // Arrange
        when(externalNewsSourceService.getExternalSourceById(0)).thenThrow(new IllegalArgumentException("Invalid source ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            externalNewsSourceController.getExternalSourceById(0);
        });
        verify(externalNewsSourceService).getExternalSourceById(0);
    }

    @Test
    void testGetExternalSourceByIdWithNegativeId() {
        // Arrange
        when(externalNewsSourceService.getExternalSourceById(-1)).thenThrow(new IllegalArgumentException("Invalid source ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            externalNewsSourceController.getExternalSourceById(-1);
        });
        verify(externalNewsSourceService).getExternalSourceById(-1);
    }

    @Test
    void testGetExternalSourceByIdWithServiceException() {
        // Arrange
        when(externalNewsSourceService.getExternalSourceById(1)).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            externalNewsSourceController.getExternalSourceById(1);
        });
        verify(externalNewsSourceService).getExternalSourceById(1);
    }

    @Test
    void testGetAll() {
        // Arrange
        when(externalNewsSourceService.getAll()).thenReturn(sourceList);

        // Act
        List<ExternalNewsSourceDTO> result = externalNewsSourceController.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
        assertEquals("CNN", result.get(0).getName());
        assertEquals("BBC", result.get(1).getName());
        verify(externalNewsSourceService).getAll();
    }

    @Test
    void testGetAllWithEmptyList() {
        // Arrange
        when(externalNewsSourceService.getAll()).thenReturn(Collections.emptyList());

        // Act
        List<ExternalNewsSourceDTO> result = externalNewsSourceController.getAll();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(externalNewsSourceService).getAll();
    }

    @Test
    void testGetAllWithServiceException() {
        // Arrange
        when(externalNewsSourceService.getAll()).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            externalNewsSourceController.getAll();
        });
        verify(externalNewsSourceService).getAll();
    }

    @Test
    void testUpdateExternalSource() {
        // Arrange
        when(externalNewsSourceService.updateExternalNewsSource(1, sourceDTO1)).thenReturn(sourceDTO1);

        // Act
        ExternalNewsSourceDTO result = externalNewsSourceController.getExternalSourceById(sourceDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("CNN", result.getName());
        assertEquals("https://cnn.com", result.getUrl());
        assertEquals(1, result.getIsActive());
        verify(externalNewsSourceService).updateExternalNewsSource(1, sourceDTO1);
    }

    @Test
    void testUpdateExternalSourceWithNullInput() {
        // Arrange
        when(externalNewsSourceService.updateExternalNewsSource(1, null)).thenThrow(new IllegalArgumentException("Source cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            externalNewsSourceController.getExternalSourceById(null);
        });
        verify(externalNewsSourceService).updateExternalNewsSource(1, null);
    }

    @Test
    void testUpdateExternalSourceWithNullId() {
        // Arrange
        sourceDTO1.setId(null);
        when(externalNewsSourceService.updateExternalNewsSource(null, sourceDTO1)).thenThrow(new IllegalArgumentException("Source ID cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            externalNewsSourceController.getExternalSourceById(sourceDTO1);
        });
        verify(externalNewsSourceService).updateExternalNewsSource(null, sourceDTO1);
    }

    @Test
    void testUpdateExternalSourceWithServiceException() {
        // Arrange
        when(externalNewsSourceService.updateExternalNewsSource(1, sourceDTO1)).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            externalNewsSourceController.getExternalSourceById(sourceDTO1);
        });
        verify(externalNewsSourceService).updateExternalNewsSource(1, sourceDTO1);
    }

    @Test
    void testGetExternalSourceByIdWithLargeId() {
        // Arrange
        when(externalNewsSourceService.getExternalSourceById(Integer.MAX_VALUE)).thenReturn(sourceDTO1);

        // Act
        ExternalNewsSourceDTO result = externalNewsSourceController.getExternalSourceById(Integer.MAX_VALUE);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(externalNewsSourceService).getExternalSourceById(Integer.MAX_VALUE);
    }

    @Test
    void testUpdateExternalSourceWithZeroId() {
        // Arrange
        sourceDTO1.setId(0);
        when(externalNewsSourceService.updateExternalNewsSource(0, sourceDTO1)).thenThrow(new IllegalArgumentException("Invalid source ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            externalNewsSourceController.getExternalSourceById(sourceDTO1);
        });
        verify(externalNewsSourceService).updateExternalNewsSource(0, sourceDTO1);
    }

    @Test
    void testUpdateExternalSourceWithNegativeId() {
        // Arrange
        sourceDTO1.setId(-1);
        when(externalNewsSourceService.updateExternalNewsSource(-1, sourceDTO1)).thenThrow(new IllegalArgumentException("Invalid source ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            externalNewsSourceController.getExternalSourceById(sourceDTO1);
        });
        verify(externalNewsSourceService).updateExternalNewsSource(-1, sourceDTO1);
    }

    @Test
    void testUpdateExternalSourceWithLargeId() {
        // Arrange
        sourceDTO1.setId(Integer.MAX_VALUE);
        when(externalNewsSourceService.updateExternalNewsSource(Integer.MAX_VALUE, sourceDTO1)).thenReturn(sourceDTO1);

        // Act
        ExternalNewsSourceDTO result = externalNewsSourceController.getExternalSourceById(sourceDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(Integer.MAX_VALUE, result.getId());
        verify(externalNewsSourceService).updateExternalNewsSource(Integer.MAX_VALUE, sourceDTO1);
    }

    @Test
    void testGetExternalSourceByIdWithNullName() {
        // Arrange
        sourceDTO1.setName(null);
        when(externalNewsSourceService.getExternalSourceById(1)).thenReturn(sourceDTO1);

        // Act
        ExternalNewsSourceDTO result = externalNewsSourceController.getExternalSourceById(1);

        // Assert
        assertNotNull(result);
        assertNull(result.getName());
        verify(externalNewsSourceService).getExternalSourceById(1);
    }

    @Test
    void testGetExternalSourceByIdWithEmptyName() {
        // Arrange
        sourceDTO1.setName("");
        when(externalNewsSourceService.getExternalSourceById(1)).thenReturn(sourceDTO1);

        // Act
        ExternalNewsSourceDTO result = externalNewsSourceController.getExternalSourceById(1);

        // Assert
        assertNotNull(result);
        assertEquals("", result.getName());
        verify(externalNewsSourceService).getExternalSourceById(1);
    }

    @Test
    void testGetExternalSourceByIdWithNullUrl() {
        // Arrange
        sourceDTO1.setUrl(null);
        when(externalNewsSourceService.getExternalSourceById(1)).thenReturn(sourceDTO1);

        // Act
        ExternalNewsSourceDTO result = externalNewsSourceController.getExternalSourceById(1);

        // Assert
        assertNotNull(result);
        assertNull(result.getUrl());
        verify(externalNewsSourceService).getExternalSourceById(1);
    }

    @Test
    void testGetExternalSourceByIdWithEmptyUrl() {
        // Arrange
        sourceDTO1.setUrl("");
        when(externalNewsSourceService.getExternalSourceById(1)).thenReturn(sourceDTO1);

        // Act
        ExternalNewsSourceDTO result = externalNewsSourceController.getExternalSourceById(1);

        // Assert
        assertNotNull(result);
        assertEquals("", result.getUrl());
        verify(externalNewsSourceService).getExternalSourceById(1);
    }

    @Test
    void testGetExternalSourceByIdWithNullIsActive() {
        // Arrange
        sourceDTO1.setIsActive(null);
        when(externalNewsSourceService.getExternalSourceById(1)).thenReturn(sourceDTO1);

        // Act
        ExternalNewsSourceDTO result = externalNewsSourceController.getExternalSourceById(1);

        // Assert
        assertNotNull(result);
        assertNull(result.getIsActive());
        verify(externalNewsSourceService).getExternalSourceById(1);
    }

    @Test
    void testGetExternalSourceByIdWithZeroIsActive() {
        // Arrange
        sourceDTO1.setIsActive(0);
        when(externalNewsSourceService.getExternalSourceById(1)).thenReturn(sourceDTO1);

        // Act
        ExternalNewsSourceDTO result = externalNewsSourceController.getExternalSourceById(1);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.getIsActive());
        verify(externalNewsSourceService).getExternalSourceById(1);
    }

    @Test
    void testGetExternalSourceByIdWithNegativeIsActive() {
        // Arrange
        sourceDTO1.setIsActive(-1);
        when(externalNewsSourceService.getExternalSourceById(1)).thenReturn(sourceDTO1);

        // Act
        ExternalNewsSourceDTO result = externalNewsSourceController.getExternalSourceById(1);

        // Assert
        assertNotNull(result);
        assertEquals(-1, result.getIsActive());
        verify(externalNewsSourceService).getExternalSourceById(1);
    }

    @Test
    void testGetExternalSourceByIdWithLargeIsActive() {
        // Arrange
        sourceDTO1.setIsActive(Integer.MAX_VALUE);
        when(externalNewsSourceService.getExternalSourceById(1)).thenReturn(sourceDTO1);

        // Act
        ExternalNewsSourceDTO result = externalNewsSourceController.getExternalSourceById(1);

        // Assert
        assertNotNull(result);
        assertEquals(Integer.MAX_VALUE, result.getIsActive());
        verify(externalNewsSourceService).getExternalSourceById(1);
    }

    @Test
    void testGetAllWithLargeList() {
        // Arrange
        List<ExternalNewsSourceDTO> largeList = Arrays.asList(
            sourceDTO1, sourceDTO2, sourceDTO1, sourceDTO2, sourceDTO1,
            sourceDTO2, sourceDTO1, sourceDTO2, sourceDTO1, sourceDTO2
        );
        when(externalNewsSourceService.getAll()).thenReturn(largeList);

        // Act
        List<ExternalNewsSourceDTO> result = externalNewsSourceController.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(10, result.size());
        verify(externalNewsSourceService).getAll();
    }

    @Test
    void testGetExternalSourceByIdWithSpecialCharacters() {
        // Arrange
        sourceDTO1.setName("CNN@#$%^&*()");
        sourceDTO1.setUrl("https://cnn@#$%^&*().com");
        when(externalNewsSourceService.getExternalSourceById(1)).thenReturn(sourceDTO1);

        // Act
        ExternalNewsSourceDTO result = externalNewsSourceController.getExternalSourceById(1);

        // Assert
        assertNotNull(result);
        assertEquals("CNN@#$%^&*()", result.getName());
        assertEquals("https://cnn@#$%^&*().com", result.getUrl());
        verify(externalNewsSourceService).getExternalSourceById(1);
    }

    @Test
    void testGetExternalSourceByIdWithUnicode() {
        // Arrange
        sourceDTO1.setName("CNN\u00E9\u00F1\u00FC");
        sourceDTO1.setUrl("https://cnn\u00E9\u00F1\u00FC.com");
        when(externalNewsSourceService.getExternalSourceById(1)).thenReturn(sourceDTO1);

        // Act
        ExternalNewsSourceDTO result = externalNewsSourceController.getExternalSourceById(1);

        // Assert
        assertNotNull(result);
        assertEquals("CNN\u00E9\u00F1\u00FC", result.getName());
        assertEquals("https://cnn\u00E9\u00F1\u00FC.com", result.getUrl());
        verify(externalNewsSourceService).getExternalSourceById(1);
    }

    @Test
    void testGetExternalSourceByIdWithLongValues() {
        // Arrange
        String longString = "a".repeat(1000);
        sourceDTO1.setName(longString);
        sourceDTO1.setUrl("https://" + longString + ".com");
        when(externalNewsSourceService.getExternalSourceById(1)).thenReturn(sourceDTO1);

        // Act
        ExternalNewsSourceDTO result = externalNewsSourceController.getExternalSourceById(1);

        // Assert
        assertNotNull(result);
        assertEquals(longString, result.getName());
        assertEquals("https://" + longString + ".com", result.getUrl());
        verify(externalNewsSourceService).getExternalSourceById(1);
    }
} 