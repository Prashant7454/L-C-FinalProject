package com.server.NewsAggrigationServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.NewsAggrigationServer.dto.ExternalNewsSourceDTO;
import com.server.NewsAggrigationServer.exception.ResourceNotFoundException;
import com.server.NewsAggrigationServer.service.ExternalNewsSourceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ExternalNewsSourceControllerTest {

    @Mock
    private ExternalNewsSourceService externalNewsSourceService;

    @InjectMocks
    private ExternalNewsSourceController externalNewsSourceController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private ExternalNewsSourceDTO testExternalNewsSourceDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(externalNewsSourceController).build();
        objectMapper = new ObjectMapper();

        testExternalNewsSourceDTO = new ExternalNewsSourceDTO();
        testExternalNewsSourceDTO.setId(1);
        testExternalNewsSourceDTO.setSourceName("Test News API");
        testExternalNewsSourceDTO.setBaseUrl("https://api.testnews.com");
        testExternalNewsSourceDTO.setApiKey("test-api-key-123");
        testExternalNewsSourceDTO.setStatus(true);
        testExternalNewsSourceDTO.setLastAccessed(LocalDateTime.now());
    }

    @Test
    void getExternalSourceById_ShouldReturnSource_WhenFound() throws Exception {
        // Arrange
        Integer id = 1;
        when(externalNewsSourceService.getExternalSourceById(id)).thenReturn(testExternalNewsSourceDTO);

        // Act & Assert
        mockMvc.perform(get("/api/external/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(testExternalNewsSourceDTO.getId()))
                .andExpect(jsonPath("$.sourceName").value(testExternalNewsSourceDTO.getSourceName()))
                .andExpect(jsonPath("$.baseUrl").value(testExternalNewsSourceDTO.getBaseUrl()))
                .andExpect(jsonPath("$.apiKey").value(testExternalNewsSourceDTO.getApiKey()))
                .andExpect(jsonPath("$.status").value(testExternalNewsSourceDTO.getStatus()));

        verify(externalNewsSourceService).getExternalSourceById(id);
    }

    @Test
    void getExternalSourceById_ShouldReturn404_WhenSourceNotFound() throws Exception {
        // Arrange
        Integer id = 999;
        when(externalNewsSourceService.getExternalSourceById(id))
                .thenThrow(new ResourceNotFoundException("News not found with ID: " + id));

        // Act & Assert
        mockMvc.perform(get("/api/external/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(externalNewsSourceService).getExternalSourceById(id);
    }

    @Test
    void getExternalSourceById_ShouldHandleInvalidId() throws Exception {
        // Arrange
        String invalidId = "invalid";
        when(externalNewsSourceService.getExternalSourceById(null))
                .thenThrow(new ResourceNotFoundException("News not found with ID: null"));

        // Act & Assert
        mockMvc.perform(get("/api/external/{id}", invalidId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // Note: This test might need adjustment based on how Spring handles path variable conversion
    }

    @Test
    void getAll_ShouldReturnAllSources() throws Exception {
        // Arrange
        ExternalNewsSourceDTO source2 = new ExternalNewsSourceDTO();
        source2.setId(2);
        source2.setSourceName("Another News API");
        source2.setBaseUrl("https://api.another.com");
        source2.setApiKey("another-api-key");
        source2.setStatus(false);
        source2.setLastAccessed(LocalDateTime.now().minusDays(1));

        List<ExternalNewsSourceDTO> mockSources = Arrays.asList(testExternalNewsSourceDTO, source2);
        when(externalNewsSourceService.getAll()).thenReturn(mockSources);

        // Act & Assert
        mockMvc.perform(get("/api/external/source")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(testExternalNewsSourceDTO.getId()))
                .andExpect(jsonPath("$[0].sourceName").value(testExternalNewsSourceDTO.getSourceName()))
                .andExpect(jsonPath("$[1].id").value(source2.getId()))
                .andExpect(jsonPath("$[1].sourceName").value(source2.getSourceName()));

        verify(externalNewsSourceService).getAll();
    }

    @Test
    void getAll_ShouldReturnEmptyArray_WhenNoSourcesExist() throws Exception {
        // Arrange
        when(externalNewsSourceService.getAll()).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/external/source")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());

        verify(externalNewsSourceService).getAll();
    }

    @Test
    void updateExternalNewsSource_ShouldUpdateSource_WhenValidRequest() throws Exception {
        // Arrange
        ExternalNewsSourceDTO updateDTO = new ExternalNewsSourceDTO();
        updateDTO.setId(1);
        updateDTO.setApiKey("updated-api-key");

        when(externalNewsSourceService.updateExternalNewsSource(eq(updateDTO.getId()), any(ExternalNewsSourceDTO.class)))
                .thenReturn(testExternalNewsSourceDTO);

        // Act & Assert
        mockMvc.perform(put("/api/external/updatesource")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(testExternalNewsSourceDTO.getId()))
                .andExpect(jsonPath("$.sourceName").value(testExternalNewsSourceDTO.getSourceName()))
                .andExpect(jsonPath("$.apiKey").value(testExternalNewsSourceDTO.getApiKey()));

        verify(externalNewsSourceService).updateExternalNewsSource(eq(updateDTO.getId()), any(ExternalNewsSourceDTO.class));
    }

    @Test
    void updateExternalNewsSource_ShouldReturn404_WhenSourceNotFound() throws Exception {
        // Arrange
        ExternalNewsSourceDTO updateDTO = new ExternalNewsSourceDTO();
        updateDTO.setId(999);
        updateDTO.setApiKey("updated-api-key");

        when(externalNewsSourceService.updateExternalNewsSource(eq(updateDTO.getId()), any(ExternalNewsSourceDTO.class)))
                .thenThrow(new ResourceNotFoundException("News not found with ID: " + updateDTO.getId()));

        // Act & Assert
        mockMvc.perform(put("/api/external/updatesource")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isNotFound());

        verify(externalNewsSourceService).updateExternalNewsSource(eq(updateDTO.getId()), any(ExternalNewsSourceDTO.class));
    }

    @Test
    void updateExternalNewsSource_ShouldHandleInvalidJson() throws Exception {
        // Arrange
        String invalidJson = "{ invalid json }";

        // Act & Assert
        mockMvc.perform(put("/api/external/updatesource")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidJson))
                .andExpect(status().isBadRequest());

        verify(externalNewsSourceService, never()).updateExternalNewsSource(any(), any());
    }

    @Test
    void updateExternalNewsSource_ShouldHandleNullRequestBody() throws Exception {
        // Act & Assert
        mockMvc.perform(put("/api/external/updatesource")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(externalNewsSourceService, never()).updateExternalNewsSource(any(), any());
    }

    @Test
    void updateExternalNewsSource_ShouldHandleEmptyRequestBody() throws Exception {
        // Act & Assert
        mockMvc.perform(put("/api/external/updatesource")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().isBadRequest());

        verify(externalNewsSourceService, never()).updateExternalNewsSource(any(), any());
    }

    @Test
    void updateExternalNewsSource_ShouldHandlePartialUpdate() throws Exception {
        // Arrange
        ExternalNewsSourceDTO updateDTO = new ExternalNewsSourceDTO();
        updateDTO.setId(1);
        // Only setting id, leaving other fields null

        when(externalNewsSourceService.updateExternalNewsSource(eq(updateDTO.getId()), any(ExternalNewsSourceDTO.class)))
                .thenReturn(testExternalNewsSourceDTO);

        // Act & Assert
        mockMvc.perform(put("/api/external/updatesource")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(testExternalNewsSourceDTO.getId()));

        verify(externalNewsSourceService).updateExternalNewsSource(eq(updateDTO.getId()), any(ExternalNewsSourceDTO.class));
    }

    @Test
    void controller_ShouldHandleServiceException() throws Exception {
        // Arrange
        Integer id = 1;
        when(externalNewsSourceService.getExternalSourceById(id))
                .thenThrow(new RuntimeException("Database connection failed"));

        // Act & Assert
        mockMvc.perform(get("/api/external/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

        verify(externalNewsSourceService).getExternalSourceById(id);
    }

    @Test
    void controller_ShouldHandleUnsupportedMediaType() throws Exception {
        // Arrange
        ExternalNewsSourceDTO updateDTO = new ExternalNewsSourceDTO();
        updateDTO.setId(1);
        updateDTO.setApiKey("updated-api-key");

        // Act & Assert
        mockMvc.perform(put("/api/external/updatesource")
                .contentType(MediaType.TEXT_PLAIN)
                .content("plain text content"))
                .andExpect(status().isUnsupportedMediaType());

        verify(externalNewsSourceService, never()).updateExternalNewsSource(any(), any());
    }
} 