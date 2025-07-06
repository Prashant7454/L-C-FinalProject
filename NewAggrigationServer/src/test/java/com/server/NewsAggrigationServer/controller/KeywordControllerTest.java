package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.dto.KeywordDTO;
import com.server.NewsAggrigationServer.service.KeywordService;
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
class KeywordControllerTest {

    @Mock
    private KeywordService keywordService;

    @InjectMocks
    private KeywordController keywordController;

    private KeywordDTO keywordDTO1;
    private KeywordDTO keywordDTO2;
    private List<KeywordDTO> keywordList;

    @BeforeEach
    void setUp() {
        keywordDTO1 = new KeywordDTO();
        keywordDTO1.setId(1);
        keywordDTO1.setName("technology");
        keywordDTO1.setDescription("Technology related keywords");

        keywordDTO2 = new KeywordDTO();
        keywordDTO2.setId(2);
        keywordDTO2.setName("sports");
        keywordDTO2.setDescription("Sports related keywords");

        keywordList = Arrays.asList(keywordDTO1, keywordDTO2);
    }

    @Test
    void testAddKeyword() {
        // Arrange
        when(keywordService.addKeyword(any(KeywordDTO.class))).thenReturn(keywordDTO1);

        // Act
        KeywordDTO result = keywordController.addKeyword(keywordDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("technology", result.getName());
        assertEquals("Technology related keywords", result.getDescription());
        verify(keywordService).addKeyword(keywordDTO1);
    }

    @Test
    void testAddKeywordWithNullInput() {
        // Arrange
        when(keywordService.addKeyword(null)).thenThrow(new IllegalArgumentException("Keyword cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            keywordController.addKeyword(null);
        });
        verify(keywordService).addKeyword(null);
    }

    @Test
    void testAddKeywordWithEmptyName() {
        // Arrange
        keywordDTO1.setName("");
        when(keywordService.addKeyword(keywordDTO1)).thenThrow(new IllegalArgumentException("Keyword name cannot be empty"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            keywordController.addKeyword(keywordDTO1);
        });
        verify(keywordService).addKeyword(keywordDTO1);
    }

    @Test
    void testAddKeywordWithNullName() {
        // Arrange
        keywordDTO1.setName(null);
        when(keywordService.addKeyword(keywordDTO1)).thenThrow(new IllegalArgumentException("Keyword name cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            keywordController.addKeyword(keywordDTO1);
        });
        verify(keywordService).addKeyword(keywordDTO1);
    }

    @Test
    void testAddKeywordWithServiceException() {
        // Arrange
        when(keywordService.addKeyword(any(KeywordDTO.class))).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            keywordController.addKeyword(keywordDTO1);
        });
        verify(keywordService).addKeyword(keywordDTO1);
    }

    @Test
    void testGetAllKeywords() {
        // Arrange
        when(keywordService.getAllKeywords()).thenReturn(keywordList);

        // Act
        List<KeywordDTO> result = keywordController.getAllKeywords();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
        assertEquals("technology", result.get(0).getName());
        assertEquals("sports", result.get(1).getName());
        verify(keywordService).getAllKeywords();
    }

    @Test
    void testGetAllKeywordsWithEmptyList() {
        // Arrange
        when(keywordService.getAllKeywords()).thenReturn(Collections.emptyList());

        // Act
        List<KeywordDTO> result = keywordController.getAllKeywords();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(keywordService).getAllKeywords();
    }

    @Test
    void testGetAllKeywordsWithServiceException() {
        // Arrange
        when(keywordService.getAllKeywords()).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            keywordController.getAllKeywords();
        });
        verify(keywordService).getAllKeywords();
    }

    @Test
    void testAddKeywordWithSpecialCharacters() {
        // Arrange
        keywordDTO1.setName("test@#$%^&*()");
        keywordDTO1.setDescription("Test with special characters");
        when(keywordService.addKeyword(keywordDTO1)).thenReturn(keywordDTO1);

        // Act
        KeywordDTO result = keywordController.addKeyword(keywordDTO1);

        // Assert
        assertNotNull(result);
        assertEquals("test@#$%^&*()", result.getName());
        assertEquals("Test with special characters", result.getDescription());
        verify(keywordService).addKeyword(keywordDTO1);
    }

    @Test
    void testAddKeywordWithUnicode() {
        // Arrange
        keywordDTO1.setName("test\u00E9\u00F1\u00FC");
        keywordDTO1.setDescription("Test with unicode characters");
        when(keywordService.addKeyword(keywordDTO1)).thenReturn(keywordDTO1);

        // Act
        KeywordDTO result = keywordController.addKeyword(keywordDTO1);

        // Assert
        assertNotNull(result);
        assertEquals("test\u00E9\u00F1\u00FC", result.getName());
        assertEquals("Test with unicode characters", result.getDescription());
        verify(keywordService).addKeyword(keywordDTO1);
    }

    @Test
    void testAddKeywordWithLongName() {
        // Arrange
        String longName = "a".repeat(1000);
        keywordDTO1.setName(longName);
        keywordDTO1.setDescription("Test with long name");
        when(keywordService.addKeyword(keywordDTO1)).thenReturn(keywordDTO1);

        // Act
        KeywordDTO result = keywordController.addKeyword(keywordDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(longName, result.getName());
        assertEquals("Test with long name", result.getDescription());
        verify(keywordService).addKeyword(keywordDTO1);
    }

    @Test
    void testAddKeywordWithLongDescription() {
        // Arrange
        String longDescription = "a".repeat(10000);
        keywordDTO1.setName("test");
        keywordDTO1.setDescription(longDescription);
        when(keywordService.addKeyword(keywordDTO1)).thenReturn(keywordDTO1);

        // Act
        KeywordDTO result = keywordController.addKeyword(keywordDTO1);

        // Assert
        assertNotNull(result);
        assertEquals("test", result.getName());
        assertEquals(longDescription, result.getDescription());
        verify(keywordService).addKeyword(keywordDTO1);
    }

    @Test
    void testAddKeywordWithZeroId() {
        // Arrange
        keywordDTO1.setId(0);
        when(keywordService.addKeyword(keywordDTO1)).thenReturn(keywordDTO1);

        // Act
        KeywordDTO result = keywordController.addKeyword(keywordDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.getId());
        verify(keywordService).addKeyword(keywordDTO1);
    }

    @Test
    void testAddKeywordWithNegativeId() {
        // Arrange
        keywordDTO1.setId(-1);
        when(keywordService.addKeyword(keywordDTO1)).thenReturn(keywordDTO1);

        // Act
        KeywordDTO result = keywordController.addKeyword(keywordDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(-1, result.getId());
        verify(keywordService).addKeyword(keywordDTO1);
    }

    @Test
    void testAddKeywordWithLargeId() {
        // Arrange
        keywordDTO1.setId(Integer.MAX_VALUE);
        when(keywordService.addKeyword(keywordDTO1)).thenReturn(keywordDTO1);

        // Act
        KeywordDTO result = keywordController.addKeyword(keywordDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(Integer.MAX_VALUE, result.getId());
        verify(keywordService).addKeyword(keywordDTO1);
    }

    @Test
    void testGetAllKeywordsWithLargeList() {
        // Arrange
        List<KeywordDTO> largeList = Arrays.asList(
            keywordDTO1, keywordDTO2, keywordDTO1, keywordDTO2, keywordDTO1,
            keywordDTO2, keywordDTO1, keywordDTO2, keywordDTO1, keywordDTO2
        );
        when(keywordService.getAllKeywords()).thenReturn(largeList);

        // Act
        List<KeywordDTO> result = keywordController.getAllKeywords();

        // Assert
        assertNotNull(result);
        assertEquals(10, result.size());
        verify(keywordService).getAllKeywords();
    }

    @Test
    void testAddKeywordWithWhitespaceOnly() {
        // Arrange
        keywordDTO1.setName("   ");
        keywordDTO1.setDescription("Test with whitespace only");
        when(keywordService.addKeyword(keywordDTO1)).thenThrow(new IllegalArgumentException("Keyword name cannot be whitespace only"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            keywordController.addKeyword(keywordDTO1);
        });
        verify(keywordService).addKeyword(keywordDTO1);
    }

    @Test
    void testAddKeywordWithLeadingTrailingWhitespace() {
        // Arrange
        keywordDTO1.setName("  technology  ");
        keywordDTO1.setDescription("Test with leading/trailing whitespace");
        when(keywordService.addKeyword(keywordDTO1)).thenReturn(keywordDTO1);

        // Act
        KeywordDTO result = keywordController.addKeyword(keywordDTO1);

        // Assert
        assertNotNull(result);
        assertEquals("  technology  ", result.getName());
        assertEquals("Test with leading/trailing whitespace", result.getDescription());
        verify(keywordService).addKeyword(keywordDTO1);
    }

    @Test
    void testAddKeywordWithNullDescription() {
        // Arrange
        keywordDTO1.setDescription(null);
        when(keywordService.addKeyword(keywordDTO1)).thenReturn(keywordDTO1);

        // Act
        KeywordDTO result = keywordController.addKeyword(keywordDTO1);

        // Assert
        assertNotNull(result);
        assertNull(result.getDescription());
        verify(keywordService).addKeyword(keywordDTO1);
    }

    @Test
    void testAddKeywordWithEmptyDescription() {
        // Arrange
        keywordDTO1.setDescription("");
        when(keywordService.addKeyword(keywordDTO1)).thenReturn(keywordDTO1);

        // Act
        KeywordDTO result = keywordController.addKeyword(keywordDTO1);

        // Assert
        assertNotNull(result);
        assertEquals("", result.getDescription());
        verify(keywordService).addKeyword(keywordDTO1);
    }
} 