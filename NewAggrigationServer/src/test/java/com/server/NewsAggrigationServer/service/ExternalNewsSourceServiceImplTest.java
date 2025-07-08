package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.ExternalNewsSourceDTO;
import com.server.NewsAggrigationServer.exception.ResourceNotFoundException;
import com.server.NewsAggrigationServer.model.ExternalNewsSource;
import com.server.NewsAggrigationServer.repository.ExternalNewsSourceRepository;
import com.server.NewsAggrigationServer.service.impl.ExternalNewsSourceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExternalNewsSourceServiceImplTest {

    @Mock
    private ExternalNewsSourceRepository externalNewsSourceRepository;

    @InjectMocks
    private ExternalNewsSourceServiceImpl externalNewsSourceService;

    private ExternalNewsSource testExternalNewsSource;
    private ExternalNewsSourceDTO testExternalNewsSourceDTO;

    @BeforeEach
    void setUp() {
        testExternalNewsSource = new ExternalNewsSource();
        testExternalNewsSource.setId(1);
        testExternalNewsSource.setSourceName("Test News API");
        testExternalNewsSource.setBaseUrl("https://api.testnews.com");
        testExternalNewsSource.setApiKey("test-api-key-123");
        testExternalNewsSource.setStatus(1);
        testExternalNewsSource.setLastAccessed(LocalDateTime.now());

        testExternalNewsSourceDTO = new ExternalNewsSourceDTO();
        testExternalNewsSourceDTO.setId(1);
        testExternalNewsSourceDTO.setSourceName("Test News API");
        testExternalNewsSourceDTO.setBaseUrl("https://api.testnews.com");
        testExternalNewsSourceDTO.setApiKey("test-api-key-123");
        testExternalNewsSourceDTO.setStatus(1);
        testExternalNewsSourceDTO.setLastAccessed(LocalDateTime.now());
    }

    @Test
    void getNewsSourceBySourceName_ShouldReturnMatchingSources() {
        // Arrange
        String sourceName = "Test News API";
        List<ExternalNewsSource> mockSources = Arrays.asList(testExternalNewsSource);
        when(externalNewsSourceRepository.findBySourceName(sourceName)).thenReturn(mockSources);

        // Act
        List<ExternalNewsSourceDTO> result = externalNewsSourceService.getNewsSourceBySourceName(sourceName);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testExternalNewsSource.getId(), result.get(0).getId());
        assertEquals(testExternalNewsSource.getSourceName(), result.get(0).getSourceName());
        assertEquals(testExternalNewsSource.getApiKey(), result.get(0).getApiKey());
        assertEquals(testExternalNewsSource.getBaseUrl(), result.get(0).getBaseUrl());
        assertEquals(testExternalNewsSource.getStatus(), result.get(0).getStatus());
        assertEquals(testExternalNewsSource.getLastAccessed(), result.get(0).getLastAccessed());
        
        verify(externalNewsSourceRepository).findBySourceName(sourceName);
    }

    @Test
    void getNewsSourceBySourceName_ShouldReturnEmptyList_WhenNoSourcesFound() {
        // Arrange
        String sourceName = "Non-existent Source";
        when(externalNewsSourceRepository.findBySourceName(sourceName)).thenReturn(Arrays.asList());

        // Act
        List<ExternalNewsSourceDTO> result = externalNewsSourceService.getNewsSourceBySourceName(sourceName);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(externalNewsSourceRepository).findBySourceName(sourceName);
    }

    @Test
    void getNewsSourceBySourceName_ShouldReturnMultipleSources() {
        // Arrange
        String sourceName = "Test News API";
        ExternalNewsSource source2 = new ExternalNewsSource();
        source2.setId(2);
        source2.setSourceName("Test News API");
        source2.setBaseUrl("https://api2.testnews.com");
        source2.setApiKey("test-api-key-456");
        source2.setStatus(0);
        source2.setLastAccessed(LocalDateTime.now().minusHours(1));

        List<ExternalNewsSource> mockSources = Arrays.asList(testExternalNewsSource, source2);
        when(externalNewsSourceRepository.findBySourceName(sourceName)).thenReturn(mockSources);

        // Act
        List<ExternalNewsSourceDTO> result = externalNewsSourceService.getNewsSourceBySourceName(sourceName);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(testExternalNewsSource.getId(), result.get(0).getId());
        assertEquals(source2.getId(), result.get(1).getId());
        verify(externalNewsSourceRepository).findBySourceName(sourceName);
    }

    @Test
    void save_ShouldSaveExternalNewsSource() {
        // Arrange
        when(externalNewsSourceRepository.save(any(ExternalNewsSource.class))).thenReturn(testExternalNewsSource);

        // Act
        externalNewsSourceService.save(testExternalNewsSourceDTO);

        // Assert
        verify(externalNewsSourceRepository).save(any(ExternalNewsSource.class));
    }

    @Test
    void save_ShouldHandleNullValues() {
        // Arrange
        ExternalNewsSourceDTO dtoWithNulls = new ExternalNewsSourceDTO();
        dtoWithNulls.setId(1);
        dtoWithNulls.setSourceName(null);
        dtoWithNulls.setApiKey(null);
        dtoWithNulls.setBaseUrl(null);
        dtoWithNulls.setStatus(null);
        dtoWithNulls.setLastAccessed(null);

        when(externalNewsSourceRepository.save(any(ExternalNewsSource.class))).thenReturn(testExternalNewsSource);

        // Act
        externalNewsSourceService.save(dtoWithNulls);

        // Assert
        verify(externalNewsSourceRepository).save(any(ExternalNewsSource.class));
    }

    @Test
    void getAll_ShouldReturnAllSources() {
        // Arrange
        ExternalNewsSource source2 = new ExternalNewsSource();
        source2.setId(2);
        source2.setSourceName("Another News API");
        source2.setBaseUrl("https://api.another.com");
        source2.setApiKey("another-api-key");
        source2.setStatus(0);
        source2.setLastAccessed(LocalDateTime.now().minusDays(1));

        List<ExternalNewsSource> mockSources = Arrays.asList(testExternalNewsSource, source2);
        when(externalNewsSourceRepository.findAll()).thenReturn(mockSources);

        // Act
        List<ExternalNewsSourceDTO> result = externalNewsSourceService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(testExternalNewsSource.getId(), result.get(0).getId());
        assertEquals(source2.getId(), result.get(1).getId());
        verify(externalNewsSourceRepository).findAll();
    }

    @Test
    void getAll_ShouldReturnEmptyList_WhenNoSourcesExist() {
        // Arrange
        when(externalNewsSourceRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<ExternalNewsSourceDTO> result = externalNewsSourceService.getAll();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(externalNewsSourceRepository).findAll();
    }

    @Test
    void updateExternalNewsSource_ShouldUpdateExistingSource() {
        // Arrange
        Integer id = 1;
        ExternalNewsSourceDTO updateDTO = new ExternalNewsSourceDTO();
        updateDTO.setId(id);
        updateDTO.setApiKey("updated-api-key");

        when(externalNewsSourceRepository.findById(id)).thenReturn(Optional.of(testExternalNewsSource));
        when(externalNewsSourceRepository.save(any(ExternalNewsSource.class))).thenReturn(testExternalNewsSource);

        // Act
        ExternalNewsSourceDTO result = externalNewsSourceService.updateExternalNewsSource(id, updateDTO);

        // Assert
        assertNotNull(result);
        assertEquals(testExternalNewsSource.getId(), result.getId());
        assertEquals(testExternalNewsSource.getSourceName(), result.getSourceName());
        assertEquals(testExternalNewsSource.getApiKey(), result.getApiKey());
        assertEquals(testExternalNewsSource.getBaseUrl(), result.getBaseUrl());
        assertEquals(testExternalNewsSource.getStatus(), result.getStatus());
        assertEquals(testExternalNewsSource.getLastAccessed(), result.getLastAccessed());
        
        verify(externalNewsSourceRepository).findById(id);
        verify(externalNewsSourceRepository).save(any(ExternalNewsSource.class));
    }

    @Test
    void updateExternalNewsSource_ShouldThrowResourceNotFoundException_WhenSourceNotFound() {
        // Arrange
        Integer id = 999;
        ExternalNewsSourceDTO updateDTO = new ExternalNewsSourceDTO();
        updateDTO.setId(id);
        updateDTO.setApiKey("updated-api-key");

        when(externalNewsSourceRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            externalNewsSourceService.updateExternalNewsSource(id, updateDTO);
        });

        assertEquals("News not found with ID: " + id, exception.getMessage());
        verify(externalNewsSourceRepository).findById(id);
        verify(externalNewsSourceRepository, never()).save(any(ExternalNewsSource.class));
    }

    @Test
    void getExternalSourceById_ShouldReturnSource_WhenFound() {
        // Arrange
        Integer id = 1;
        when(externalNewsSourceRepository.findById(id)).thenReturn(Optional.of(testExternalNewsSource));

        // Act
        ExternalNewsSourceDTO result = externalNewsSourceService.getExternalSourceById(id);

        // Assert
        assertNotNull(result);
        assertEquals(testExternalNewsSource.getId(), result.getId());
        assertEquals(testExternalNewsSource.getSourceName(), result.getSourceName());
        assertEquals(testExternalNewsSource.getApiKey(), result.getApiKey());
        assertEquals(testExternalNewsSource.getBaseUrl(), result.getBaseUrl());
        assertEquals(testExternalNewsSource.getStatus(), result.getStatus());
        assertEquals(testExternalNewsSource.getLastAccessed(), result.getLastAccessed());
        
        verify(externalNewsSourceRepository).findById(id);
    }

    @Test
    void getExternalSourceById_ShouldThrowResourceNotFoundException_WhenSourceNotFound() {
        // Arrange
        Integer id = 999;
        when(externalNewsSourceRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            externalNewsSourceService.getExternalSourceById(id);
        });

        assertEquals("News not found with ID: " + id, exception.getMessage());
        verify(externalNewsSourceRepository).findById(id);
    }

    @Test
    void getExternalSourceById_ShouldHandleNullId() {
        // Arrange
        when(externalNewsSourceRepository.findById(null)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            externalNewsSourceService.getExternalSourceById(null);
        });

        assertEquals("News not found with ID: null", exception.getMessage());
        verify(externalNewsSourceRepository).findById(null);
    }
}