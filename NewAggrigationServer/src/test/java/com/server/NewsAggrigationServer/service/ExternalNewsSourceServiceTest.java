package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.model.ExternalNewsSource;
import com.server.NewsAggrigationServer.repository.ExternalNewsSourceRepository;
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
class ExternalNewsSourceServiceTest {

    @Mock
    private ExternalNewsSourceRepository externalNewsSourceRepository;

    @InjectMocks
    private ExternalNewsSourceService externalNewsSourceService;

    private ExternalNewsSource source1;
    private ExternalNewsSource source2;

    @BeforeEach
    void setUp() {
        source1 = new ExternalNewsSource();
        source1.setId(1);
        source1.setName("CNN");
        source1.setUrl("https://cnn.com");
        source1.setIsActive(1);

        source2 = new ExternalNewsSource();
        source2.setId(2);
        source2.setName("BBC");
        source2.setUrl("https://bbc.com");
        source2.setIsActive(0);
    }

    @Test
    void testGetAllActiveSources() {
        // Arrange
        List<ExternalNewsSource> expectedSources = Arrays.asList(source1);
        when(externalNewsSourceRepository.findByIsActive(1)).thenReturn(expectedSources);

        // Act
        List<ExternalNewsSource> result = externalNewsSourceService.getAllActiveSources();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("CNN", result.get(0).getName());
        assertEquals(1, result.get(0).getIsActive());
        verify(externalNewsSourceRepository).findByIsActive(1);
    }

    @Test
    void testGetAllActiveSourcesEmptyList() {
        // Arrange
        when(externalNewsSourceRepository.findByIsActive(1)).thenReturn(Collections.emptyList());

        // Act
        List<ExternalNewsSource> result = externalNewsSourceService.getAllActiveSources();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(externalNewsSourceRepository).findByIsActive(1);
    }

    @Test
    void testGetAllActiveSourcesWithRepositoryException() {
        // Arrange
        when(externalNewsSourceRepository.findByIsActive(1)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            externalNewsSourceService.getAllActiveSources();
        });
        verify(externalNewsSourceRepository).findByIsActive(1);
    }

    @Test
    void testGetAllActiveSourcesWithRealisticData() {
        // Arrange
        List<ExternalNewsSource> expectedSources = Arrays.asList(source1, source2);
        when(externalNewsSourceRepository.findByIsActive(1)).thenReturn(expectedSources);

        // Act
        List<ExternalNewsSource> result = externalNewsSourceService.getAllActiveSources();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("CNN", result.get(0).getName());
        assertEquals("BBC", result.get(1).getName());
        verify(externalNewsSourceRepository).findByIsActive(1);
    }

    @Test
    void testGetAllActiveSourcesWithBoundaryValues() {
        // Test with empty list
        when(externalNewsSourceRepository.findByIsActive(1)).thenReturn(Collections.emptyList());
        List<ExternalNewsSource> emptyResult = externalNewsSourceService.getAllActiveSources();
        assertTrue(emptyResult.isEmpty());
        verify(externalNewsSourceRepository).findByIsActive(1);
    }
} 