package com.server.NewsAggrigationServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.NewsAggrigationServer.dto.KeywordDTO;
import com.server.NewsAggrigationServer.service.KeywordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class KeywordControllerTest {

    @Mock
    private KeywordService keywordService;

    @InjectMocks
    private KeywordController keywordController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private KeywordDTO testKeywordDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(keywordController).build();
        objectMapper = new ObjectMapper();

        testKeywordDTO = new KeywordDTO();
        testKeywordDTO.setId(1);
        testKeywordDTO.setName("Technology");
    }

    @Test
    void addKeyword_ShouldReturnCreatedKeyword() throws Exception {
        // Arrange
        KeywordDTO inputDTO = new KeywordDTO();
        inputDTO.setName("Science");

        KeywordDTO responseDTO = new KeywordDTO();
        responseDTO.setId(2);
        responseDTO.setName("Science");

        when(keywordService.addKeyword(any(KeywordDTO.class))).thenReturn(responseDTO);

        // Act & Assert
        mockMvc.perform(post("/api/keywords")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("Science"));

        verify(keywordService).addKeyword(any(KeywordDTO.class));
    }

    @Test
    void addKeyword_ShouldHandleNullName() throws Exception {
        // Arrange
        KeywordDTO inputDTO = new KeywordDTO();
        inputDTO.setName(null);

        KeywordDTO responseDTO = new KeywordDTO();
        responseDTO.setId(3);
        responseDTO.setName(null);

        when(keywordService.addKeyword(any(KeywordDTO.class))).thenReturn(responseDTO);

        // Act & Assert
        mockMvc.perform(post("/api/keywords")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.name").isEmpty());

        verify(keywordService).addKeyword(any(KeywordDTO.class));
    }

    @Test
    void addKeyword_ShouldHandleEmptyName() throws Exception {
        // Arrange
        KeywordDTO inputDTO = new KeywordDTO();
        inputDTO.setName("");

        KeywordDTO responseDTO = new KeywordDTO();
        responseDTO.setId(4);
        responseDTO.setName("");

        when(keywordService.addKeyword(any(KeywordDTO.class))).thenReturn(responseDTO);

        // Act & Assert
        mockMvc.perform(post("/api/keywords")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.name").value(""));

        verify(keywordService).addKeyword(any(KeywordDTO.class));
    }

    @Test
    void addKeyword_ShouldHandleSpecialCharacters() throws Exception {
        // Arrange
        KeywordDTO inputDTO = new KeywordDTO();
        inputDTO.setName("AI & Machine Learning");

        KeywordDTO responseDTO = new KeywordDTO();
        responseDTO.setId(5);
        responseDTO.setName("AI & Machine Learning");

        when(keywordService.addKeyword(any(KeywordDTO.class))).thenReturn(responseDTO);

        // Act & Assert
        mockMvc.perform(post("/api/keywords")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.name").value("AI & Machine Learning"));

        verify(keywordService).addKeyword(any(KeywordDTO.class));
    }

    @Test
    void addKeyword_ShouldHandleLongName() throws Exception {
        // Arrange
        String longName = "This is a very long keyword name that might be used for testing purposes";
        KeywordDTO inputDTO = new KeywordDTO();
        inputDTO.setName(longName);

        KeywordDTO responseDTO = new KeywordDTO();
        responseDTO.setId(6);
        responseDTO.setName(longName);

        when(keywordService.addKeyword(any(KeywordDTO.class))).thenReturn(responseDTO);

        // Act & Assert
        mockMvc.perform(post("/api/keywords")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(6))
                .andExpect(jsonPath("$.name").value(longName));

        verify(keywordService).addKeyword(any(KeywordDTO.class));
    }

    @Test
    void addKeyword_ShouldHandleInvalidJson() throws Exception {
        // Act & Assert
        mockMvc.perform(post("/api/keywords")
                .contentType(MediaType.APPLICATION_JSON)
                .content("invalid json"))
                .andExpect(status().isBadRequest());

        verify(keywordService, never()).addKeyword(any(KeywordDTO.class));
    }

    @Test
    void addKeyword_ShouldHandleMissingContentType() throws Exception {
        // Arrange
        KeywordDTO inputDTO = new KeywordDTO();
        inputDTO.setName("Test");

        // Act & Assert
        mockMvc.perform(post("/api/keywords")
                .content(objectMapper.writeValueAsString(inputDTO)))
                .andExpect(status().isUnsupportedMediaType());

        verify(keywordService, never()).addKeyword(any(KeywordDTO.class));
    }

    @Test
    void getAllKeywords_ShouldReturnAllKeywords() throws Exception {
        // Arrange
        KeywordDTO keyword1 = new KeywordDTO();
        keyword1.setId(1);
        keyword1.setName("Technology");

        KeywordDTO keyword2 = new KeywordDTO();
        keyword2.setId(2);
        keyword2.setName("Science");

        KeywordDTO keyword3 = new KeywordDTO();
        keyword3.setId(3);
        keyword3.setName("Sports");

        List<KeywordDTO> mockKeywords = Arrays.asList(keyword1, keyword2, keyword3);
        when(keywordService.getAllKeywords()).thenReturn(mockKeywords);

        // Act & Assert
        mockMvc.perform(get("/api/keywords"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Technology"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Science"))
                .andExpect(jsonPath("$[2].id").value(3))
                .andExpect(jsonPath("$[2].name").value("Sports"));

        verify(keywordService).getAllKeywords();
    }

    @Test
    void getAllKeywords_ShouldReturnEmptyList_WhenNoKeywordsExist() throws Exception {
        // Arrange
        when(keywordService.getAllKeywords()).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/keywords"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());

        verify(keywordService).getAllKeywords();
    }

    @Test
    void getAllKeywords_ShouldHandleKeywordsWithNullNames() throws Exception {
        // Arrange
        KeywordDTO keyword1 = new KeywordDTO();
        keyword1.setId(1);
        keyword1.setName(null);

        KeywordDTO keyword2 = new KeywordDTO();
        keyword2.setId(2);
        keyword2.setName("Valid Name");

        List<KeywordDTO> mockKeywords = Arrays.asList(keyword1, keyword2);
        when(keywordService.getAllKeywords()).thenReturn(mockKeywords);

        // Act & Assert
        mockMvc.perform(get("/api/keywords"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").isEmpty())
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Valid Name"));

        verify(keywordService).getAllKeywords();
    }

    @Test
    void getAllKeywords_ShouldHandleSingleKeyword() throws Exception {
        // Arrange
        List<KeywordDTO> mockKeywords = Arrays.asList(testKeywordDTO);
        when(keywordService.getAllKeywords()).thenReturn(mockKeywords);

        // Act & Assert
        mockMvc.perform(get("/api/keywords"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Technology"));

        verify(keywordService).getAllKeywords();
    }

    @Test
    void getAllKeywords_ShouldHandleUnsupportedHttpMethod() throws Exception {
        // Act & Assert
        mockMvc.perform(put("/api/keywords"))
                .andExpect(status().isMethodNotAllowed());

        verify(keywordService, never()).getAllKeywords();
    }

    @Test
    void addKeyword_ShouldHandleEmptyBody() throws Exception {
        // Act & Assert
        mockMvc.perform(post("/api/keywords")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().isBadRequest());

        verify(keywordService, never()).addKeyword(any(KeywordDTO.class));
    }

    @Test
    void addKeyword_ShouldHandleMalformedJson() throws Exception {
        // Act & Assert
        mockMvc.perform(post("/api/keywords")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"test\",}"))
                .andExpect(status().isBadRequest());

        verify(keywordService, never()).addKeyword(any(KeywordDTO.class));
    }
}