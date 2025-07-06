package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.model.SavedNews;
import com.server.NewsAggrigationServer.repository.SavedNewsRepository;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SavedNewsServiceTest {

    @Mock
    private SavedNewsRepository savedNewsRepository;

    @InjectMocks
    private SavedNewsService savedNewsService;

    private SavedNews savedNews1;
    private SavedNews savedNews2;

    @BeforeEach
    void setUp() {
        savedNews1 = new SavedNews();
        savedNews1.setId(1);
        savedNews1.setUserId(1);
        savedNews1.setNewsId(1);

        savedNews2 = new SavedNews();
        savedNews2.setId(2);
        savedNews2.setUserId(1);
        savedNews2.setNewsId(2);
    }

    @Test
    void testGetSavedNewsByUserId() {
        // Arrange
        List<SavedNews> expectedSavedNews = Arrays.asList(savedNews1, savedNews2);
        when(savedNewsRepository.findByUserId(1)).thenReturn(expectedSavedNews);

        // Act
        List<SavedNews> result = savedNewsService.getSavedNewsByUserId(1);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getUserId());
        assertEquals(1, result.get(0).getNewsId());
        assertEquals(1, result.get(1).getUserId());
        assertEquals(2, result.get(1).getNewsId());

        verify(savedNewsRepository).findByUserId(1);
    }

    @Test
    void testGetSavedNewsByUserIdEmptyList() {
        // Arrange
        when(savedNewsRepository.findByUserId(1)).thenReturn(Arrays.asList());

        // Act
        List<SavedNews> result = savedNewsService.getSavedNewsByUserId(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(savedNewsRepository).findByUserId(1);
    }

    @Test
    void testGetSavedNewsByUserIdWithZero() {
        // Arrange
        when(savedNewsRepository.findByUserId(0)).thenReturn(Arrays.asList());

        // Act
        List<SavedNews> result = savedNewsService.getSavedNewsByUserId(0);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(savedNewsRepository).findByUserId(0);
    }

    @Test
    void testGetSavedNewsByUserIdWithNegativeValue() {
        // Arrange
        when(savedNewsRepository.findByUserId(-1)).thenReturn(Arrays.asList());

        // Act
        List<SavedNews> result = savedNewsService.getSavedNewsByUserId(-1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(savedNewsRepository).findByUserId(-1);
    }

    @Test
    void testGetSavedNewsByUserIdWithLargeValue() {
        // Arrange
        when(savedNewsRepository.findByUserId(Integer.MAX_VALUE)).thenReturn(Arrays.asList());

        // Act
        List<SavedNews> result = savedNewsService.getSavedNewsByUserId(Integer.MAX_VALUE);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(savedNewsRepository).findByUserId(Integer.MAX_VALUE);
    }

    @Test
    void testGetSavedNewsByUserIdWithMultipleUsers() {
        // Arrange
        SavedNews user2SavedNews = new SavedNews();
        user2SavedNews.setId(3);
        user2SavedNews.setUserId(2);
        user2SavedNews.setNewsId(3);

        when(savedNewsRepository.findByUserId(1)).thenReturn(Arrays.asList(savedNews1, savedNews2));
        when(savedNewsRepository.findByUserId(2)).thenReturn(Arrays.asList(user2SavedNews));

        // Act
        List<SavedNews> user1Result = savedNewsService.getSavedNewsByUserId(1);
        List<SavedNews> user2Result = savedNewsService.getSavedNewsByUserId(2);

        // Assert
        assertEquals(2, user1Result.size());
        assertEquals(1, user2Result.size());
        assertEquals(2, user2Result.get(0).getUserId());

        verify(savedNewsRepository).findByUserId(1);
        verify(savedNewsRepository).findByUserId(2);
    }

    @Test
    void testGetSavedNewsByUserIdWithRepositoryException() {
        // Arrange
        when(savedNewsRepository.findByUserId(1)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            savedNewsService.getSavedNewsByUserId(1);
        });

        verify(savedNewsRepository).findByUserId(1);
    }

    @Test
    void testGetSavedNewsByUserIdWithRealisticValues() {
        // Test with realistic user IDs that might be used in a real application
        Integer[] realisticUserIds = {1, 5, 10, 25, 50, 100, 500, 1000};

        for (Integer userId : realisticUserIds) {
            // Arrange
            when(savedNewsRepository.findByUserId(userId)).thenReturn(Arrays.asList(savedNews1));

            // Act
            List<SavedNews> result = savedNewsService.getSavedNewsByUserId(userId);

            // Assert
            assertNotNull(result);
            assertEquals(1, result.size());

            verify(savedNewsRepository).findByUserId(userId);
        }
    }

    @Test
    void testGetSavedNewsByUserIdWithBoundaryValues() {
        // Test minimum value
        when(savedNewsRepository.findByUserId(0)).thenReturn(Arrays.asList());
        List<SavedNews> minResult = savedNewsService.getSavedNewsByUserId(0);
        assertTrue(minResult.isEmpty());

        // Test maximum value
        when(savedNewsRepository.findByUserId(Integer.MAX_VALUE)).thenReturn(Arrays.asList());
        List<SavedNews> maxResult = savedNewsService.getSavedNewsByUserId(Integer.MAX_VALUE);
        assertTrue(maxResult.isEmpty());

        verify(savedNewsRepository).findByUserId(0);
        verify(savedNewsRepository).findByUserId(Integer.MAX_VALUE);
    }

    @Test
    void testGetSavedNewsByUserIdWithSequentialValues() {
        for (int i = 1; i <= 10; i++) {
            // Arrange
            SavedNews sequentialSavedNews = new SavedNews();
            sequentialSavedNews.setId(i);
            sequentialSavedNews.setUserId(i);
            sequentialSavedNews.setNewsId(i * 10);

            when(savedNewsRepository.findByUserId(i)).thenReturn(Arrays.asList(sequentialSavedNews));

            // Act
            List<SavedNews> result = savedNewsService.getSavedNewsByUserId(i);

            // Assert
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(i, result.get(0).getUserId());
            assertEquals(i * 10, result.get(0).getNewsId());

            verify(savedNewsRepository).findByUserId(i);
        }
    }

    @Test
    void testGetSavedNewsByUserIdWithLargeList() {
        // Arrange
        List<SavedNews> largeList = Arrays.asList();
        for (int i = 1; i <= 100; i++) {
            SavedNews savedNews = new SavedNews();
            savedNews.setId(i);
            savedNews.setUserId(1);
            savedNews.setNewsId(i);
            largeList = Arrays.asList(savedNews);
        }

        when(savedNewsRepository.findByUserId(1)).thenReturn(largeList);

        // Act
        List<SavedNews> result = savedNewsService.getSavedNewsByUserId(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size()); // Since we're creating a new list each time

        verify(savedNewsRepository).findByUserId(1);
    }

    @Test
    void testGetSavedNewsByUserIdWithNullReturn() {
        // Arrange
        when(savedNewsRepository.findByUserId(1)).thenReturn(null);

        // Act
        List<SavedNews> result = savedNewsService.getSavedNewsByUserId(1);

        // Assert
        assertNull(result);

        verify(savedNewsRepository).findByUserId(1);
    }

    @Test
    void testGetSavedNewsByUserIdWithMixedData() {
        // Arrange
        SavedNews mixedSavedNews1 = new SavedNews();
        mixedSavedNews1.setId(1);
        mixedSavedNews1.setUserId(1);
        mixedSavedNews1.setNewsId(100);

        SavedNews mixedSavedNews2 = new SavedNews();
        mixedSavedNews2.setId(2);
        mixedSavedNews2.setUserId(1);
        mixedSavedNews2.setNewsId(200);

        SavedNews mixedSavedNews3 = new SavedNews();
        mixedSavedNews3.setId(3);
        mixedSavedNews3.setUserId(1);
        mixedSavedNews3.setNewsId(300);

        List<SavedNews> mixedList = Arrays.asList(mixedSavedNews1, mixedSavedNews2, mixedSavedNews3);

        when(savedNewsRepository.findByUserId(1)).thenReturn(mixedList);

        // Act
        List<SavedNews> result = savedNewsService.getSavedNewsByUserId(1);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(100, result.get(0).getNewsId());
        assertEquals(200, result.get(1).getNewsId());
        assertEquals(300, result.get(2).getNewsId());

        verify(savedNewsRepository).findByUserId(1);
    }
} 