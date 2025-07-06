package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.model.NewsCategory;
import com.server.NewsAggrigationServer.repository.NewsCategoryRepository;
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
class NewsCategoryServiceTest {

    @Mock
    private NewsCategoryRepository newsCategoryRepository;

    @InjectMocks
    private NewsCategoryService newsCategoryService;

    private NewsCategory newsCategory1;
    private NewsCategory newsCategory2;

    @BeforeEach
    void setUp() {
        newsCategory1 = new NewsCategory();
        newsCategory1.setId(1);
        newsCategory1.setNewsId(1);
        newsCategory1.setCategoryId(1);

        newsCategory2 = new NewsCategory();
        newsCategory2.setId(2);
        newsCategory2.setNewsId(1);
        newsCategory2.setCategoryId(2);
    }

    @Test
    void testGetCategoriesByNewsId() {
        // Arrange
        List<NewsCategory> expectedNewsCategories = Arrays.asList(newsCategory1, newsCategory2);
        when(newsCategoryRepository.findByNewsId(1)).thenReturn(expectedNewsCategories);

        // Act
        List<NewsCategory> result = newsCategoryService.getCategoriesByNewsId(1);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getNewsId());
        assertEquals(1, result.get(0).getCategoryId());
        assertEquals(1, result.get(1).getNewsId());
        assertEquals(2, result.get(1).getCategoryId());
        verify(newsCategoryRepository).findByNewsId(1);
    }

    @Test
    void testGetCategoriesByNewsIdEmptyList() {
        // Arrange
        when(newsCategoryRepository.findByNewsId(1)).thenReturn(Collections.emptyList());

        // Act
        List<NewsCategory> result = newsCategoryService.getCategoriesByNewsId(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(newsCategoryRepository).findByNewsId(1);
    }

    @Test
    void testGetCategoriesByNewsIdWithZero() {
        // Arrange
        when(newsCategoryRepository.findByNewsId(0)).thenReturn(Collections.emptyList());

        // Act
        List<NewsCategory> result = newsCategoryService.getCategoriesByNewsId(0);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(newsCategoryRepository).findByNewsId(0);
    }

    @Test
    void testGetCategoriesByNewsIdWithNegativeValue() {
        // Arrange
        when(newsCategoryRepository.findByNewsId(-1)).thenReturn(Collections.emptyList());

        // Act
        List<NewsCategory> result = newsCategoryService.getCategoriesByNewsId(-1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(newsCategoryRepository).findByNewsId(-1);
    }

    @Test
    void testGetCategoriesByNewsIdWithLargeValue() {
        // Arrange
        when(newsCategoryRepository.findByNewsId(Integer.MAX_VALUE)).thenReturn(Collections.emptyList());

        // Act
        List<NewsCategory> result = newsCategoryService.getCategoriesByNewsId(Integer.MAX_VALUE);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(newsCategoryRepository).findByNewsId(Integer.MAX_VALUE);
    }

    @Test
    void testGetCategoriesByNewsIdWithRepositoryException() {
        // Arrange
        when(newsCategoryRepository.findByNewsId(1)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            newsCategoryService.getCategoriesByNewsId(1);
        });
        verify(newsCategoryRepository).findByNewsId(1);
    }

    @Test
    void testGetCategoriesByNewsIdWithRealisticValues() {
        // Test with realistic news IDs that might be used in a real application
        Integer[] realisticNewsIds = {1, 5, 10, 25, 50, 100, 500, 1000};
        for (Integer newsId : realisticNewsIds) {
            when(newsCategoryRepository.findByNewsId(newsId)).thenReturn(Arrays.asList(newsCategory1));
            List<NewsCategory> result = newsCategoryService.getCategoriesByNewsId(newsId);
            assertNotNull(result);
            assertEquals(1, result.size());
            verify(newsCategoryRepository).findByNewsId(newsId);
        }
    }

    @Test
    void testGetCategoriesByNewsIdWithBoundaryValues() {
        // Test minimum value
        when(newsCategoryRepository.findByNewsId(0)).thenReturn(Collections.emptyList());
        List<NewsCategory> minResult = newsCategoryService.getCategoriesByNewsId(0);
        assertTrue(minResult.isEmpty());
        // Test maximum value
        when(newsCategoryRepository.findByNewsId(Integer.MAX_VALUE)).thenReturn(Collections.emptyList());
        List<NewsCategory> maxResult = newsCategoryService.getCategoriesByNewsId(Integer.MAX_VALUE);
        assertTrue(maxResult.isEmpty());
        verify(newsCategoryRepository).findByNewsId(0);
        verify(newsCategoryRepository).findByNewsId(Integer.MAX_VALUE);
    }
} 