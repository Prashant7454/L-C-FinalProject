package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.dto.NewsDTO;
import com.server.NewsAggrigationServer.service.NewsPersonalizationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NewsPersonalizationControllerTest {

    @Mock
    private NewsPersonalizationService personalizationService;

    @InjectMocks
    private NewsPersonalizationController personalizationController;

    private NewsDTO newsDTO1;
    private NewsDTO newsDTO2;
    private List<NewsDTO> newsList;

    @BeforeEach
    void setUp() {
        newsDTO1 = new NewsDTO();
        newsDTO1.setId(1);
        newsDTO1.setTitle("Personalized News 1");
        newsDTO1.setContent("Personalized content 1");

        newsDTO2 = new NewsDTO();
        newsDTO2.setId(2);
        newsDTO2.setTitle("Personalized News 2");
        newsDTO2.setContent("Personalized content 2");

        newsList = Arrays.asList(newsDTO1, newsDTO2);
    }

    @Test
    void testGetPersonalizedNews() {
        // Arrange
        when(personalizationService.getPersonalizedNews(1, 10)).thenReturn(newsList);

        // Act
        ResponseEntity<List<NewsDTO>> result = personalizationController.getPersonalizedNews(1, 10);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
        assertEquals(1, result.getBody().get(0).getId());
        assertEquals(2, result.getBody().get(1).getId());
        verify(personalizationService).getPersonalizedNews(1, 10);
    }

    @Test
    void testGetPersonalizedNewsWithDefaultLimit() {
        // Arrange
        when(personalizationService.getPersonalizedNews(1, 10)).thenReturn(newsList);

        // Act
        ResponseEntity<List<NewsDTO>> result = personalizationController.getPersonalizedNews(1, 10);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
        verify(personalizationService).getPersonalizedNews(1, 10);
    }

    @Test
    void testGetPersonalizedNewsWithCustomLimit() {
        // Arrange
        when(personalizationService.getPersonalizedNews(1, 5)).thenReturn(Collections.singletonList(newsDTO1));

        // Act
        ResponseEntity<List<NewsDTO>> result = personalizationController.getPersonalizedNews(1, 5);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(1, result.getBody().size());
        verify(personalizationService).getPersonalizedNews(1, 5);
    }

    @Test
    void testGetPersonalizedNewsWithException() {
        // Arrange
        when(personalizationService.getPersonalizedNews(1, 10)).thenThrow(new RuntimeException("Service error"));

        // Act
        ResponseEntity<List<NewsDTO>> result = personalizationController.getPersonalizedNews(1, 10);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertNull(result.getBody());
        verify(personalizationService).getPersonalizedNews(1, 10);
    }

    @Test
    void testGetPersonalizedNewsPaginated() {
        // Arrange
        when(personalizationService.getPersonalizedNewsPaginated(1, 0, 10)).thenReturn(newsList);

        // Act
        ResponseEntity<List<NewsDTO>> result = personalizationController.getPersonalizedNewsPaginated(1, 0, 10);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
        verify(personalizationService).getPersonalizedNewsPaginated(1, 0, 10);
    }

    @Test
    void testGetPersonalizedNewsPaginatedWithDefaultValues() {
        // Arrange
        when(personalizationService.getPersonalizedNewsPaginated(1, 0, 10)).thenReturn(newsList);

        // Act
        ResponseEntity<List<NewsDTO>> result = personalizationController.getPersonalizedNewsPaginated(1, 0, 10);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
        verify(personalizationService).getPersonalizedNewsPaginated(1, 0, 10);
    }

    @Test
    void testGetPersonalizedNewsPaginatedWithException() {
        // Arrange
        when(personalizationService.getPersonalizedNewsPaginated(1, 0, 10)).thenThrow(new RuntimeException("Service error"));

        // Act
        ResponseEntity<List<NewsDTO>> result = personalizationController.getPersonalizedNewsPaginated(1, 0, 10);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertNull(result.getBody());
        verify(personalizationService).getPersonalizedNewsPaginated(1, 0, 10);
    }

    @Test
    void testGetUserInterestScore() {
        // Arrange
        double expectedScore = 0.85;
        when(personalizationService.calculateUserInterestScore(1, 1)).thenReturn(expectedScore);

        // Act
        ResponseEntity<Double> result = personalizationController.getUserInterestScore(1, 1);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(expectedScore, result.getBody(), 0.001);
        verify(personalizationService).calculateUserInterestScore(1, 1);
    }

    @Test
    void testGetUserInterestScoreWithException() {
        // Arrange
        when(personalizationService.calculateUserInterestScore(1, 1)).thenThrow(new RuntimeException("Service error"));

        // Act
        ResponseEntity<Double> result = personalizationController.getUserInterestScore(1, 1);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertNull(result.getBody());
        verify(personalizationService).calculateUserInterestScore(1, 1);
    }

    @Test
    void testGetUserTopInterestCategories() {
        // Arrange
        List<Integer> expectedCategories = Arrays.asList(1, 2, 3);
        when(personalizationService.getUserTopInterestCategories(1, 5)).thenReturn(expectedCategories);

        // Act
        ResponseEntity<List<Integer>> result = personalizationController.getUserTopInterestCategories(1, 5);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(3, result.getBody().size());
        assertEquals(1, result.getBody().get(0));
        assertEquals(2, result.getBody().get(1));
        assertEquals(3, result.getBody().get(2));
        verify(personalizationService).getUserTopInterestCategories(1, 5);
    }

    @Test
    void testGetUserTopInterestCategoriesWithDefaultLimit() {
        // Arrange
        List<Integer> expectedCategories = Arrays.asList(1, 2, 3, 4, 5);
        when(personalizationService.getUserTopInterestCategories(1, 5)).thenReturn(expectedCategories);

        // Act
        ResponseEntity<List<Integer>> result = personalizationController.getUserTopInterestCategories(1, 5);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(5, result.getBody().size());
        verify(personalizationService).getUserTopInterestCategories(1, 5);
    }

    @Test
    void testGetUserTopInterestCategoriesWithException() {
        // Arrange
        when(personalizationService.getUserTopInterestCategories(1, 5)).thenThrow(new RuntimeException("Service error"));

        // Act
        ResponseEntity<List<Integer>> result = personalizationController.getUserTopInterestCategories(1, 5);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertNull(result.getBody());
        verify(personalizationService).getUserTopInterestCategories(1, 5);
    }

    @Test
    void testRecordArticleRead() {
        // Arrange
        doNothing().when(personalizationService).recordArticleRead(1, 1);

        // Act
        ResponseEntity<String> result = personalizationController.recordArticleRead(1, 1);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Article read recorded successfully", result.getBody());
        verify(personalizationService).recordArticleRead(1, 1);
    }

    @Test
    void testRecordArticleReadWithException() {
        // Arrange
        doThrow(new RuntimeException("Service error")).when(personalizationService).recordArticleRead(1, 1);

        // Act
        ResponseEntity<String> result = personalizationController.recordArticleRead(1, 1);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Error recording article read: Service error", result.getBody());
        verify(personalizationService).recordArticleRead(1, 1);
    }

    @Test
    void testGetPersonalizedNewsWithZeroUserId() {
        // Arrange
        when(personalizationService.getPersonalizedNews(0, 10)).thenThrow(new IllegalArgumentException("Invalid user ID"));

        // Act
        ResponseEntity<List<NewsDTO>> result = personalizationController.getPersonalizedNews(0, 10);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertNull(result.getBody());
        verify(personalizationService).getPersonalizedNews(0, 10);
    }

    @Test
    void testGetPersonalizedNewsWithNegativeUserId() {
        // Arrange
        when(personalizationService.getPersonalizedNews(-1, 10)).thenThrow(new IllegalArgumentException("Invalid user ID"));

        // Act
        ResponseEntity<List<NewsDTO>> result = personalizationController.getPersonalizedNews(-1, 10);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertNull(result.getBody());
        verify(personalizationService).getPersonalizedNews(-1, 10);
    }

    @Test
    void testGetPersonalizedNewsWithZeroLimit() {
        // Arrange
        when(personalizationService.getPersonalizedNews(1, 0)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<NewsDTO>> result = personalizationController.getPersonalizedNews(1, 0);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().isEmpty());
        verify(personalizationService).getPersonalizedNews(1, 0);
    }

    @Test
    void testGetPersonalizedNewsWithLargeLimit() {
        // Arrange
        when(personalizationService.getPersonalizedNews(1, 1000)).thenReturn(newsList);

        // Act
        ResponseEntity<List<NewsDTO>> result = personalizationController.getPersonalizedNews(1, 1000);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
        verify(personalizationService).getPersonalizedNews(1, 1000);
    }

    @Test
    void testGetUserInterestScoreWithZeroValues() {
        // Arrange
        when(personalizationService.calculateUserInterestScore(0, 0)).thenThrow(new IllegalArgumentException("Invalid IDs"));

        // Act
        ResponseEntity<Double> result = personalizationController.getUserInterestScore(0, 0);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertNull(result.getBody());
        verify(personalizationService).calculateUserInterestScore(0, 0);
    }

    @Test
    void testRecordArticleReadWithZeroValues() {
        // Arrange
        doThrow(new IllegalArgumentException("Invalid IDs")).when(personalizationService).recordArticleRead(0, 0);

        // Act
        ResponseEntity<String> result = personalizationController.recordArticleRead(0, 0);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertTrue(result.getBody().contains("Error recording article read"));
        verify(personalizationService).recordArticleRead(0, 0);
    }
} 