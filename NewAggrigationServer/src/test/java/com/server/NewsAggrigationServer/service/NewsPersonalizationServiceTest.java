package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.model.News;
import com.server.NewsAggrigationServer.repository.NewsRepository;
import com.server.NewsAggrigationServer.repository.UserRepository;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NewsPersonalizationServiceTest {

    @Mock
    private NewsRepository newsRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private NewsPersonalizationService newsPersonalizationService;

    private News news1;
    private News news2;

    @BeforeEach
    void setUp() {
        news1 = new News();
        news1.setId(1);
        news1.setTitle("AI Breakthrough");
        news1.setCategoryId(1);

        news2 = new News();
        news2.setId(2);
        news2.setTitle("New Programming Language");
        news2.setCategoryId(2);
    }

    @Test
    void testGetPersonalizedNewsForUser() {
        // Arrange
        List<News> expectedNews = Arrays.asList(news1, news2);
        when(newsRepository.findPersonalizedNewsByUserId(1)).thenReturn(expectedNews);

        // Act
        List<News> result = newsPersonalizationService.getPersonalizedNewsForUser(1);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("AI Breakthrough", result.get(0).getTitle());
        assertEquals("New Programming Language", result.get(1).getTitle());
        verify(newsRepository).findPersonalizedNewsByUserId(1);
    }

    @Test
    void testGetPersonalizedNewsForUserEmptyList() {
        // Arrange
        when(newsRepository.findPersonalizedNewsByUserId(1)).thenReturn(Collections.emptyList());

        // Act
        List<News> result = newsPersonalizationService.getPersonalizedNewsForUser(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(newsRepository).findPersonalizedNewsByUserId(1);
    }

    @Test
    void testGetPersonalizedNewsForUserWithZero() {
        // Arrange
        when(newsRepository.findPersonalizedNewsByUserId(0)).thenReturn(Collections.emptyList());

        // Act
        List<News> result = newsPersonalizationService.getPersonalizedNewsForUser(0);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(newsRepository).findPersonalizedNewsByUserId(0);
    }

    @Test
    void testGetPersonalizedNewsForUserWithNegativeValue() {
        // Arrange
        when(newsRepository.findPersonalizedNewsByUserId(-1)).thenReturn(Collections.emptyList());

        // Act
        List<News> result = newsPersonalizationService.getPersonalizedNewsForUser(-1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(newsRepository).findPersonalizedNewsByUserId(-1);
    }

    @Test
    void testGetPersonalizedNewsForUserWithLargeValue() {
        // Arrange
        when(newsRepository.findPersonalizedNewsByUserId(Integer.MAX_VALUE)).thenReturn(Collections.emptyList());

        // Act
        List<News> result = newsPersonalizationService.getPersonalizedNewsForUser(Integer.MAX_VALUE);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(newsRepository).findPersonalizedNewsByUserId(Integer.MAX_VALUE);
    }

    @Test
    void testGetPersonalizedNewsForUserWithRepositoryException() {
        // Arrange
        when(newsRepository.findPersonalizedNewsByUserId(1)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            newsPersonalizationService.getPersonalizedNewsForUser(1);
        });
        verify(newsRepository).findPersonalizedNewsByUserId(1);
    }

    @Test
    void testGetPersonalizedNewsForUserWithRealisticValues() {
        // Test with realistic user IDs that might be used in a real application
        Integer[] realisticUserIds = {1, 5, 10, 25, 50, 100, 500, 1000};
        for (Integer userId : realisticUserIds) {
            when(newsRepository.findPersonalizedNewsByUserId(userId)).thenReturn(Arrays.asList(news1));
            List<News> result = newsPersonalizationService.getPersonalizedNewsForUser(userId);
            assertNotNull(result);
            assertEquals(1, result.size());
            verify(newsRepository).findPersonalizedNewsByUserId(userId);
        }
    }

    @Test
    void testGetPersonalizedNewsForUserWithBoundaryValues() {
        // Test minimum value
        when(newsRepository.findPersonalizedNewsByUserId(0)).thenReturn(Collections.emptyList());
        List<News> minResult = newsPersonalizationService.getPersonalizedNewsForUser(0);
        assertTrue(minResult.isEmpty());
        // Test maximum value
        when(newsRepository.findPersonalizedNewsByUserId(Integer.MAX_VALUE)).thenReturn(Collections.emptyList());
        List<News> maxResult = newsPersonalizationService.getPersonalizedNewsForUser(Integer.MAX_VALUE);
        assertTrue(maxResult.isEmpty());
        verify(newsRepository).findPersonalizedNewsByUserId(0);
        verify(newsRepository).findPersonalizedNewsByUserId(Integer.MAX_VALUE);
    }
} 