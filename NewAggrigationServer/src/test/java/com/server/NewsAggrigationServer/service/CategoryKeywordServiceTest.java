package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.model.CategoryKeyword;
import com.server.NewsAggrigationServer.repository.CategoryKeywordRepository;
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
class CategoryKeywordServiceTest {

    @Mock
    private CategoryKeywordRepository categoryKeywordRepository;

    @InjectMocks
    private CategoryKeywordService categoryKeywordService;

    private CategoryKeyword categoryKeyword1;
    private CategoryKeyword categoryKeyword2;

    @BeforeEach
    void setUp() {
        categoryKeyword1 = new CategoryKeyword();
        categoryKeyword1.setId(1);
        categoryKeyword1.setCategoryId(1);
        categoryKeyword1.setKeywordId(1);

        categoryKeyword2 = new CategoryKeyword();
        categoryKeyword2.setId(2);
        categoryKeyword2.setCategoryId(1);
        categoryKeyword2.setKeywordId(2);
    }

    @Test
    void testGetKeywordsByCategoryId() {
        // Arrange
        List<CategoryKeyword> expectedCategoryKeywords = Arrays.asList(categoryKeyword1, categoryKeyword2);
        when(categoryKeywordRepository.findByCategoryId(1)).thenReturn(expectedCategoryKeywords);

        // Act
        List<CategoryKeyword> result = categoryKeywordService.getKeywordsByCategoryId(1);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getCategoryId());
        assertEquals(1, result.get(0).getKeywordId());
        assertEquals(1, result.get(1).getCategoryId());
        assertEquals(2, result.get(1).getKeywordId());
        verify(categoryKeywordRepository).findByCategoryId(1);
    }

    @Test
    void testGetKeywordsByCategoryIdEmptyList() {
        // Arrange
        when(categoryKeywordRepository.findByCategoryId(1)).thenReturn(Collections.emptyList());

        // Act
        List<CategoryKeyword> result = categoryKeywordService.getKeywordsByCategoryId(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(categoryKeywordRepository).findByCategoryId(1);
    }

    @Test
    void testGetKeywordsByCategoryIdWithZero() {
        // Arrange
        when(categoryKeywordRepository.findByCategoryId(0)).thenReturn(Collections.emptyList());

        // Act
        List<CategoryKeyword> result = categoryKeywordService.getKeywordsByCategoryId(0);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(categoryKeywordRepository).findByCategoryId(0);
    }

    @Test
    void testGetKeywordsByCategoryIdWithNegativeValue() {
        // Arrange
        when(categoryKeywordRepository.findByCategoryId(-1)).thenReturn(Collections.emptyList());

        // Act
        List<CategoryKeyword> result = categoryKeywordService.getKeywordsByCategoryId(-1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(categoryKeywordRepository).findByCategoryId(-1);
    }

    @Test
    void testGetKeywordsByCategoryIdWithLargeValue() {
        // Arrange
        when(categoryKeywordRepository.findByCategoryId(Integer.MAX_VALUE)).thenReturn(Collections.emptyList());

        // Act
        List<CategoryKeyword> result = categoryKeywordService.getKeywordsByCategoryId(Integer.MAX_VALUE);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(categoryKeywordRepository).findByCategoryId(Integer.MAX_VALUE);
    }

    @Test
    void testGetKeywordsByCategoryIdWithRepositoryException() {
        // Arrange
        when(categoryKeywordRepository.findByCategoryId(1)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            categoryKeywordService.getKeywordsByCategoryId(1);
        });
        verify(categoryKeywordRepository).findByCategoryId(1);
    }

    @Test
    void testGetKeywordsByCategoryIdWithRealisticValues() {
        // Test with realistic category IDs that might be used in a real application
        Integer[] realisticCategoryIds = {1, 5, 10, 25, 50, 100, 500, 1000};
        for (Integer categoryId : realisticCategoryIds) {
            when(categoryKeywordRepository.findByCategoryId(categoryId)).thenReturn(Arrays.asList(categoryKeyword1));
            List<CategoryKeyword> result = categoryKeywordService.getKeywordsByCategoryId(categoryId);
            assertNotNull(result);
            assertEquals(1, result.size());
            verify(categoryKeywordRepository).findByCategoryId(categoryId);
        }
    }

    @Test
    void testGetKeywordsByCategoryIdWithBoundaryValues() {
        // Test minimum value
        when(categoryKeywordRepository.findByCategoryId(0)).thenReturn(Collections.emptyList());
        List<CategoryKeyword> minResult = categoryKeywordService.getKeywordsByCategoryId(0);
        assertTrue(minResult.isEmpty());
        // Test maximum value
        when(categoryKeywordRepository.findByCategoryId(Integer.MAX_VALUE)).thenReturn(Collections.emptyList());
        List<CategoryKeyword> maxResult = categoryKeywordService.getKeywordsByCategoryId(Integer.MAX_VALUE);
        assertTrue(maxResult.isEmpty());
        verify(categoryKeywordRepository).findByCategoryId(0);
        verify(categoryKeywordRepository).findByCategoryId(Integer.MAX_VALUE);
    }
} 