package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.model.News;
import com.server.NewsAggrigationServer.repository.NewsRepository;
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
class NewsHidingServiceTest {

    @Mock
    private NewsRepository newsRepository;

    @InjectMocks
    private NewsHidingService newsHidingService;

    private News news1;
    private News news2;

    @BeforeEach
    void setUp() {
        news1 = new News();
        news1.setId(1);
        news1.setTitle("Hidden News 1");
        news1.setIsHidden(1);

        news2 = new News();
        news2.setId(2);
        news2.setTitle("Visible News 2");
        news2.setIsHidden(0);
    }

    @Test
    void testGetHiddenNews() {
        // Arrange
        List<News> expectedNews = Arrays.asList(news1);
        when(newsRepository.findByIsHidden(1)).thenReturn(expectedNews);

        // Act
        List<News> result = newsHidingService.getHiddenNews();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Hidden News 1", result.get(0).getTitle());
        verify(newsRepository).findByIsHidden(1);
    }

    @Test
    void testGetHiddenNewsEmptyList() {
        // Arrange
        when(newsRepository.findByIsHidden(1)).thenReturn(Collections.emptyList());

        // Act
        List<News> result = newsHidingService.getHiddenNews();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(newsRepository).findByIsHidden(1);
    }

    @Test
    void testGetHiddenNewsWithRepositoryException() {
        // Arrange
        when(newsRepository.findByIsHidden(1)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            newsHidingService.getHiddenNews();
        });
        verify(newsRepository).findByIsHidden(1);
    }

    @Test
    void testHideNewsById() {
        // Arrange
        when(newsRepository.hideNewsById(1)).thenReturn(1);

        // Act
        boolean result = newsHidingService.hideNewsById(1);

        // Assert
        assertTrue(result);
        verify(newsRepository).hideNewsById(1);
    }

    @Test
    void testHideNewsByIdFailure() {
        // Arrange
        when(newsRepository.hideNewsById(1)).thenReturn(0);

        // Act
        boolean result = newsHidingService.hideNewsById(1);

        // Assert
        assertFalse(result);
        verify(newsRepository).hideNewsById(1);
    }

    @Test
    void testHideNewsByIdWithRepositoryException() {
        // Arrange
        when(newsRepository.hideNewsById(1)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            newsHidingService.hideNewsById(1);
        });
        verify(newsRepository).hideNewsById(1);
    }
} 