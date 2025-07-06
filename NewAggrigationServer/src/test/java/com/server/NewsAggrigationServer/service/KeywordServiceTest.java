package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.model.Keyword;
import com.server.NewsAggrigationServer.repository.KeywordRepository;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KeywordServiceTest {

    @Mock
    private KeywordRepository keywordRepository;

    @InjectMocks
    private KeywordService keywordService;

    private Keyword keyword1;
    private Keyword keyword2;

    @BeforeEach
    void setUp() {
        keyword1 = new Keyword();
        keyword1.setId(1);
        keyword1.setName("Technology");

        keyword2 = new Keyword();
        keyword2.setId(2);
        keyword2.setName("Science");
    }

    @Test
    void testGetAllKeywords() {
        // Arrange
        List<Keyword> expectedKeywords = Arrays.asList(keyword1, keyword2);
        when(keywordRepository.findAll()).thenReturn(expectedKeywords);

        // Act
        List<Keyword> result = keywordService.getAllKeywords();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Technology", result.get(0).getName());
        assertEquals("Science", result.get(1).getName());

        verify(keywordRepository).findAll();
    }

    @Test
    void testGetAllKeywordsEmptyList() {
        // Arrange
        when(keywordRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<Keyword> result = keywordService.getAllKeywords();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(keywordRepository).findAll();
    }

    @Test
    void testGetKeywordById() {
        // Arrange
        when(keywordRepository.findById(1)).thenReturn(Optional.of(keyword1));

        // Act
        Optional<Keyword> result = keywordService.getKeywordById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Technology", result.get().getName());
        assertEquals(1, result.get().getId());

        verify(keywordRepository).findById(1);
    }

    @Test
    void testGetKeywordByIdNotFound() {
        // Arrange
        when(keywordRepository.findById(999)).thenReturn(Optional.empty());

        // Act
        Optional<Keyword> result = keywordService.getKeywordById(999);

        // Assert
        assertFalse(result.isPresent());

        verify(keywordRepository).findById(999);
    }

    @Test
    void testGetKeywordByIdWithZero() {
        // Arrange
        when(keywordRepository.findById(0)).thenReturn(Optional.empty());

        // Act
        Optional<Keyword> result = keywordService.getKeywordById(0);

        // Assert
        assertFalse(result.isPresent());

        verify(keywordRepository).findById(0);
    }

    @Test
    void testGetKeywordByIdWithNegativeValue() {
        // Arrange
        when(keywordRepository.findById(-1)).thenReturn(Optional.empty());

        // Act
        Optional<Keyword> result = keywordService.getKeywordById(-1);

        // Assert
        assertFalse(result.isPresent());

        verify(keywordRepository).findById(-1);
    }

    @Test
    void testGetKeywordByIdWithLargeValue() {
        // Arrange
        when(keywordRepository.findById(Integer.MAX_VALUE)).thenReturn(Optional.empty());

        // Act
        Optional<Keyword> result = keywordService.getKeywordById(Integer.MAX_VALUE);

        // Assert
        assertFalse(result.isPresent());

        verify(keywordRepository).findById(Integer.MAX_VALUE);
    }

    @Test
    void testGetKeywordByName() {
        // Arrange
        when(keywordRepository.findByName("Technology")).thenReturn(Optional.of(keyword1));

        // Act
        Optional<Keyword> result = keywordService.getKeywordByName("Technology");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Technology", result.get().getName());
        assertEquals(1, result.get().getId());

        verify(keywordRepository).findByName("Technology");
    }

    @Test
    void testGetKeywordByNameNotFound() {
        // Arrange
        when(keywordRepository.findByName("Nonexistent")).thenReturn(Optional.empty());

        // Act
        Optional<Keyword> result = keywordService.getKeywordByName("Nonexistent");

        // Assert
        assertFalse(result.isPresent());

        verify(keywordRepository).findByName("Nonexistent");
    }

    @Test
    void testGetKeywordByNameWithNull() {
        // Arrange
        when(keywordRepository.findByName(null)).thenReturn(Optional.empty());

        // Act
        Optional<Keyword> result = keywordService.getKeywordByName(null);

        // Assert
        assertFalse(result.isPresent());

        verify(keywordRepository).findByName(null);
    }

    @Test
    void testGetKeywordByNameWithEmptyString() {
        // Arrange
        when(keywordRepository.findByName("")).thenReturn(Optional.empty());

        // Act
        Optional<Keyword> result = keywordService.getKeywordByName("");

        // Assert
        assertFalse(result.isPresent());

        verify(keywordRepository).findByName("");
    }

    @Test
    void testGetKeywordByNameWithWhitespace() {
        // Arrange
        when(keywordRepository.findByName("   ")).thenReturn(Optional.empty());

        // Act
        Optional<Keyword> result = keywordService.getKeywordByName("   ");

        // Assert
        assertFalse(result.isPresent());

        verify(keywordRepository).findByName("   ");
    }

    @Test
    void testGetKeywordByNameWithSpecialCharacters() {
        // Arrange
        String specialName = "Tech@2023#AI";
        Keyword specialKeyword = new Keyword();
        specialKeyword.setId(3);
        specialKeyword.setName(specialName);

        when(keywordRepository.findByName(specialName)).thenReturn(Optional.of(specialKeyword));

        // Act
        Optional<Keyword> result = keywordService.getKeywordByName(specialName);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(specialName, result.get().getName());
        assertEquals(3, result.get().getId());

        verify(keywordRepository).findByName(specialName);
    }

    @Test
    void testGetKeywordByNameWithUnicodeCharacters() {
        // Arrange
        String unicodeName = "Technology émojis 🚀";
        Keyword unicodeKeyword = new Keyword();
        unicodeKeyword.setId(4);
        unicodeKeyword.setName(unicodeName);

        when(keywordRepository.findByName(unicodeName)).thenReturn(Optional.of(unicodeKeyword));

        // Act
        Optional<Keyword> result = keywordService.getKeywordByName(unicodeName);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(unicodeName, result.get().getName());
        assertEquals(4, result.get().getId());

        verify(keywordRepository).findByName(unicodeName);
    }

    @Test
    void testGetKeywordByNameWithLongString() {
        // Arrange
        String longName = "Very Long Keyword Name That Contains Many Characters And Should Be Properly Handled";
        Keyword longKeyword = new Keyword();
        longKeyword.setId(5);
        longKeyword.setName(longName);

        when(keywordRepository.findByName(longName)).thenReturn(Optional.of(longKeyword));

        // Act
        Optional<Keyword> result = keywordService.getKeywordByName(longName);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(longName, result.get().getName());
        assertEquals(5, result.get().getId());

        verify(keywordRepository).findByName(longName);
    }

    @Test
    void testGetKeywordByNameWithMixedCase() {
        // Arrange
        String mixedCaseName = "Technology";
        Keyword mixedCaseKeyword = new Keyword();
        mixedCaseKeyword.setId(6);
        mixedCaseKeyword.setName(mixedCaseName);

        when(keywordRepository.findByName(mixedCaseName)).thenReturn(Optional.of(mixedCaseKeyword));

        // Act
        Optional<Keyword> result = keywordService.getKeywordByName(mixedCaseName);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(mixedCaseName, result.get().getName());
        assertEquals(6, result.get().getId());

        verify(keywordRepository).findByName(mixedCaseName);
    }

    @Test
    void testGetKeywordByNameWithNumbers() {
        // Arrange
        String nameWithNumbers = "Tech123";
        Keyword numberKeyword = new Keyword();
        numberKeyword.setId(7);
        numberKeyword.setName(nameWithNumbers);

        when(keywordRepository.findByName(nameWithNumbers)).thenReturn(Optional.of(numberKeyword));

        // Act
        Optional<Keyword> result = keywordService.getKeywordByName(nameWithNumbers);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(nameWithNumbers, result.get().getName());
        assertEquals(7, result.get().getId());

        verify(keywordRepository).findByName(nameWithNumbers);
    }

    @Test
    void testGetKeywordByNameWithRepositoryException() {
        // Arrange
        when(keywordRepository.findByName("Technology")).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            keywordService.getKeywordByName("Technology");
        });

        verify(keywordRepository).findByName("Technology");
    }

    @Test
    void testGetAllKeywordsWithRepositoryException() {
        // Arrange
        when(keywordRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            keywordService.getAllKeywords();
        });

        verify(keywordRepository).findAll();
    }

    @Test
    void testGetKeywordByIdWithRepositoryException() {
        // Arrange
        when(keywordRepository.findById(1)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            keywordService.getKeywordById(1);
        });

        verify(keywordRepository).findById(1);
    }
} 