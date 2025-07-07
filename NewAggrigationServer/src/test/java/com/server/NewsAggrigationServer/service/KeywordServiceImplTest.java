package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.KeywordDTO;
import com.server.NewsAggrigationServer.model.Keyword;
import com.server.NewsAggrigationServer.repository.KeywordRepository;
import com.server.NewsAggrigationServer.service.impl.KeywordServiceImpl;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KeywordServiceImplTest {

    @Mock
    private KeywordRepository keywordRepository;

    @InjectMocks
    private KeywordServiceImpl keywordService;

    private Keyword testKeyword;
    private KeywordDTO testKeywordDTO;

    @BeforeEach
    void setUp() {
        testKeyword = new Keyword();
        testKeyword.setId(1);
        testKeyword.setName("Technology");

        testKeywordDTO = new KeywordDTO();
        testKeywordDTO.setId(1);
        testKeywordDTO.setName("Technology");
    }

    @Test
    void addKeyword_ShouldSaveAndReturnKeyword() {
        // Arrange
        KeywordDTO inputDTO = new KeywordDTO();
        inputDTO.setName("Science");
        
        Keyword savedKeyword = new Keyword();
        savedKeyword.setId(2);
        savedKeyword.setName("Science");
        
        when(keywordRepository.save(any(Keyword.class))).thenReturn(savedKeyword);

        // Act
        KeywordDTO result = keywordService.addKeyword(inputDTO);

        // Assert
        assertNotNull(result);
        assertEquals(savedKeyword.getId(), result.getId());
        assertEquals(savedKeyword.getName(), result.getName());
        
        verify(keywordRepository).save(any(Keyword.class));
    }

    @Test
    void addKeyword_ShouldHandleNullName() {
        // Arrange
        KeywordDTO inputDTO = new KeywordDTO();
        inputDTO.setName(null);
        
        Keyword savedKeyword = new Keyword();
        savedKeyword.setId(3);
        savedKeyword.setName(null);
        
        when(keywordRepository.save(any(Keyword.class))).thenReturn(savedKeyword);

        // Act
        KeywordDTO result = keywordService.addKeyword(inputDTO);

        // Assert
        assertNotNull(result);
        assertEquals(savedKeyword.getId(), result.getId());
        assertNull(result.getName());
        
        verify(keywordRepository).save(any(Keyword.class));
    }

    @Test
    void addKeyword_ShouldHandleEmptyName() {
        // Arrange
        KeywordDTO inputDTO = new KeywordDTO();
        inputDTO.setName("");
        
        Keyword savedKeyword = new Keyword();
        savedKeyword.setId(4);
        savedKeyword.setName("");
        
        when(keywordRepository.save(any(Keyword.class))).thenReturn(savedKeyword);

        // Act
        KeywordDTO result = keywordService.addKeyword(inputDTO);

        // Assert
        assertNotNull(result);
        assertEquals(savedKeyword.getId(), result.getId());
        assertEquals("", result.getName());
        
        verify(keywordRepository).save(any(Keyword.class));
    }

    @Test
    void addKeyword_ShouldHandleSpecialCharacters() {
        // Arrange
        KeywordDTO inputDTO = new KeywordDTO();
        inputDTO.setName("AI & Machine Learning");
        
        Keyword savedKeyword = new Keyword();
        savedKeyword.setId(5);
        savedKeyword.setName("AI & Machine Learning");
        
        when(keywordRepository.save(any(Keyword.class))).thenReturn(savedKeyword);

        // Act
        KeywordDTO result = keywordService.addKeyword(inputDTO);

        // Assert
        assertNotNull(result);
        assertEquals(savedKeyword.getId(), result.getId());
        assertEquals("AI & Machine Learning", result.getName());
        
        verify(keywordRepository).save(any(Keyword.class));
    }

    @Test
    void getAllKeywords_ShouldReturnAllKeywords() {
        // Arrange
        Keyword keyword1 = new Keyword();
        keyword1.setId(1);
        keyword1.setName("Technology");

        Keyword keyword2 = new Keyword();
        keyword2.setId(2);
        keyword2.setName("Science");

        Keyword keyword3 = new Keyword();
        keyword3.setId(3);
        keyword3.setName("Sports");

        List<Keyword> mockKeywords = Arrays.asList(keyword1, keyword2, keyword3);
        when(keywordRepository.findAll()).thenReturn(mockKeywords);

        // Act
        List<KeywordDTO> result = keywordService.getAllKeywords();

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        
        assertEquals(keyword1.getId(), result.get(0).getId());
        assertEquals(keyword1.getName(), result.get(0).getName());
        
        assertEquals(keyword2.getId(), result.get(1).getId());
        assertEquals(keyword2.getName(), result.get(1).getName());
        
        assertEquals(keyword3.getId(), result.get(2).getId());
        assertEquals(keyword3.getName(), result.get(2).getName());
        
        verify(keywordRepository).findAll();
    }

    @Test
    void getAllKeywords_ShouldReturnEmptyList_WhenNoKeywordsExist() {
        // Arrange
        when(keywordRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<KeywordDTO> result = keywordService.getAllKeywords();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(keywordRepository).findAll();
    }

    @Test
    void getAllKeywords_ShouldHandleKeywordsWithNullNames() {
        // Arrange
        Keyword keyword1 = new Keyword();
        keyword1.setId(1);
        keyword1.setName(null);

        Keyword keyword2 = new Keyword();
        keyword2.setId(2);
        keyword2.setName("Valid Name");

        List<Keyword> mockKeywords = Arrays.asList(keyword1, keyword2);
        when(keywordRepository.findAll()).thenReturn(mockKeywords);

        // Act
        List<KeywordDTO> result = keywordService.getAllKeywords();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        
        assertEquals(keyword1.getId(), result.get(0).getId());
        assertNull(result.get(0).getName());
        
        assertEquals(keyword2.getId(), result.get(1).getId());
        assertEquals(keyword2.getName(), result.get(1).getName());
        
        verify(keywordRepository).findAll();
    }

    @Test
    void getAllKeywords_ShouldHandleSingleKeyword() {
        // Arrange
        List<Keyword> mockKeywords = Arrays.asList(testKeyword);
        when(keywordRepository.findAll()).thenReturn(mockKeywords);

        // Act
        List<KeywordDTO> result = keywordService.getAllKeywords();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testKeyword.getId(), result.get(0).getId());
        assertEquals(testKeyword.getName(), result.get(0).getName());
        
        verify(keywordRepository).findAll();
    }

    @Test
    void addKeyword_ShouldPreserveInputDTO_WhenSaving() {
        // Arrange
        KeywordDTO inputDTO = new KeywordDTO();
        inputDTO.setName("Test Keyword");
        
        Keyword savedKeyword = new Keyword();
        savedKeyword.setId(10);
        savedKeyword.setName("Test Keyword");
        
        when(keywordRepository.save(any(Keyword.class))).thenReturn(savedKeyword);

        // Act
        KeywordDTO result = keywordService.addKeyword(inputDTO);

        // Assert
        assertNotNull(result);
        assertEquals(savedKeyword.getId(), result.getId());
        assertEquals(savedKeyword.getName(), result.getName());
        
        // Verify that the repository was called with a Keyword object that has the correct name
        verify(keywordRepository).save(argThat(keyword -> 
            keyword.getName().equals("Test Keyword")
        ));
    }
} 