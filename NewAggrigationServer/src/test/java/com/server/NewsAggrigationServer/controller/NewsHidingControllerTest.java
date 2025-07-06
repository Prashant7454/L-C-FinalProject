package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.dto.NewsDTO;
import com.server.NewsAggrigationServer.service.NewsHidingService;
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
class NewsHidingControllerTest {

    @Mock
    private NewsHidingService newsHidingService;

    @InjectMocks
    private NewsHidingController newsHidingController;

    private NewsDTO newsDTO1;
    private NewsDTO newsDTO2;
    private List<NewsDTO> newsList;

    @BeforeEach
    void setUp() {
        newsDTO1 = new NewsDTO();
        newsDTO1.setId(1);
        newsDTO1.setTitle("Hidden News 1");
        newsDTO1.setContent("Hidden content 1");

        newsDTO2 = new NewsDTO();
        newsDTO2.setId(2);
        newsDTO2.setTitle("Hidden News 2");
        newsDTO2.setContent("Hidden content 2");

        newsList = Arrays.asList(newsDTO1, newsDTO2);
    }

    @Test
    void testHideNewsByKeywords() {
        // Arrange
        List<String> keywords = Arrays.asList("test", "hidden");
        when(newsHidingService.hideNewsByKeywords(keywords)).thenReturn(newsList);

        // Act
        ResponseEntity<List<NewsDTO>> result = newsHidingController.hideNewsByKeywords(keywords);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
        assertEquals(1, result.getBody().get(0).getId());
        assertEquals(2, result.getBody().get(1).getId());
        verify(newsHidingService).hideNewsByKeywords(keywords);
    }

    @Test
    void testHideNewsByKeywordsWithEmptyList() {
        // Arrange
        List<String> keywords = Collections.emptyList();
        when(newsHidingService.hideNewsByKeywords(keywords)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<NewsDTO>> result = newsHidingController.hideNewsByKeywords(keywords);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().isEmpty());
        verify(newsHidingService).hideNewsByKeywords(keywords);
    }

    @Test
    void testHideNewsByKeywordsWithNullInput() {
        // Arrange
        when(newsHidingService.hideNewsByKeywords(null)).thenThrow(new IllegalArgumentException("Keywords cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsHidingController.hideNewsByKeywords(null);
        });
        verify(newsHidingService).hideNewsByKeywords(null);
    }

    @Test
    void testHideNewsByKeywordsWithServiceException() {
        // Arrange
        List<String> keywords = Arrays.asList("test");
        when(newsHidingService.hideNewsByKeywords(keywords)).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            newsHidingController.hideNewsByKeywords(keywords);
        });
        verify(newsHidingService).hideNewsByKeywords(keywords);
    }

    @Test
    void testPreviewNewsByKeywords() {
        // Arrange
        List<String> keywords = Arrays.asList("test", "preview");
        when(newsHidingService.previewNewsByKeywords(keywords)).thenReturn(newsList);

        // Act
        ResponseEntity<List<NewsDTO>> result = newsHidingController.previewNewsByKeywords(keywords);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
        assertEquals(1, result.getBody().get(0).getId());
        assertEquals(2, result.getBody().get(1).getId());
        verify(newsHidingService).previewNewsByKeywords(keywords);
    }

    @Test
    void testPreviewNewsByKeywordsWithEmptyList() {
        // Arrange
        List<String> keywords = Collections.emptyList();
        when(newsHidingService.previewNewsByKeywords(keywords)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<NewsDTO>> result = newsHidingController.previewNewsByKeywords(keywords);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().isEmpty());
        verify(newsHidingService).previewNewsByKeywords(keywords);
    }

    @Test
    void testPreviewNewsByKeywordsWithNullInput() {
        // Arrange
        when(newsHidingService.previewNewsByKeywords(null)).thenThrow(new IllegalArgumentException("Keywords cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsHidingController.previewNewsByKeywords(null);
        });
        verify(newsHidingService).previewNewsByKeywords(null);
    }

    @Test
    void testPreviewNewsByKeywordsWithServiceException() {
        // Arrange
        List<String> keywords = Arrays.asList("test");
        when(newsHidingService.previewNewsByKeywords(keywords)).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            newsHidingController.previewNewsByKeywords(keywords);
        });
        verify(newsHidingService).previewNewsByKeywords(keywords);
    }

    @Test
    void testGetHiddenNews() {
        // Arrange
        when(newsHidingService.getHiddenNews()).thenReturn(newsList);

        // Act
        ResponseEntity<List<NewsDTO>> result = newsHidingController.getHiddenNews();

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
        assertEquals(1, result.getBody().get(0).getId());
        assertEquals(2, result.getBody().get(1).getId());
        verify(newsHidingService).getHiddenNews();
    }

    @Test
    void testGetHiddenNewsWithEmptyList() {
        // Arrange
        when(newsHidingService.getHiddenNews()).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<NewsDTO>> result = newsHidingController.getHiddenNews();

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().isEmpty());
        verify(newsHidingService).getHiddenNews();
    }

    @Test
    void testGetHiddenNewsWithServiceException() {
        // Arrange
        when(newsHidingService.getHiddenNews()).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            newsHidingController.getHiddenNews();
        });
        verify(newsHidingService).getHiddenNews();
    }

    @Test
    void testUnhideNews() {
        // Arrange
        when(newsHidingService.unhideNews(1)).thenReturn(newsDTO1);

        // Act
        ResponseEntity<NewsDTO> result = newsHidingController.unhideNews(1);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(1, result.getBody().getId());
        assertEquals("Hidden News 1", result.getBody().getTitle());
        verify(newsHidingService).unhideNews(1);
    }

    @Test
    void testUnhideNewsWithZeroId() {
        // Arrange
        when(newsHidingService.unhideNews(0)).thenThrow(new IllegalArgumentException("Invalid news ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsHidingController.unhideNews(0);
        });
        verify(newsHidingService).unhideNews(0);
    }

    @Test
    void testUnhideNewsWithNegativeId() {
        // Arrange
        when(newsHidingService.unhideNews(-1)).thenThrow(new IllegalArgumentException("Invalid news ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsHidingController.unhideNews(-1);
        });
        verify(newsHidingService).unhideNews(-1);
    }

    @Test
    void testUnhideNewsWithServiceException() {
        // Arrange
        when(newsHidingService.unhideNews(1)).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            newsHidingController.unhideNews(1);
        });
        verify(newsHidingService).unhideNews(1);
    }

    @Test
    void testHideNewsByKeywordsWithSpecialCharacters() {
        // Arrange
        List<String> keywords = Arrays.asList("test@#$%", "hidden^&*()");
        when(newsHidingService.hideNewsByKeywords(keywords)).thenReturn(newsList);

        // Act
        ResponseEntity<List<NewsDTO>> result = newsHidingController.hideNewsByKeywords(keywords);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
        verify(newsHidingService).hideNewsByKeywords(keywords);
    }

    @Test
    void testHideNewsByKeywordsWithUnicode() {
        // Arrange
        List<String> keywords = Arrays.asList("test\u00E9\u00F1\u00FC", "hidden\u00E7\u00F8\u00E5");
        when(newsHidingService.hideNewsByKeywords(keywords)).thenReturn(newsList);

        // Act
        ResponseEntity<List<NewsDTO>> result = newsHidingController.hideNewsByKeywords(keywords);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
        verify(newsHidingService).hideNewsByKeywords(keywords);
    }

    @Test
    void testHideNewsByKeywordsWithLargeList() {
        // Arrange
        List<String> keywords = Arrays.asList("keyword1", "keyword2", "keyword3", "keyword4", "keyword5");
        when(newsHidingService.hideNewsByKeywords(keywords)).thenReturn(newsList);

        // Act
        ResponseEntity<List<NewsDTO>> result = newsHidingController.hideNewsByKeywords(keywords);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
        verify(newsHidingService).hideNewsByKeywords(keywords);
    }

    @Test
    void testPreviewNewsByKeywordsWithSpecialCharacters() {
        // Arrange
        List<String> keywords = Arrays.asList("preview@#$%", "test^&*()");
        when(newsHidingService.previewNewsByKeywords(keywords)).thenReturn(newsList);

        // Act
        ResponseEntity<List<NewsDTO>> result = newsHidingController.previewNewsByKeywords(keywords);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
        verify(newsHidingService).previewNewsByKeywords(keywords);
    }

    @Test
    void testPreviewNewsByKeywordsWithUnicode() {
        // Arrange
        List<String> keywords = Arrays.asList("preview\u00E9\u00F1\u00FC", "test\u00E7\u00F8\u00E5");
        when(newsHidingService.previewNewsByKeywords(keywords)).thenReturn(newsList);

        // Act
        ResponseEntity<List<NewsDTO>> result = newsHidingController.previewNewsByKeywords(keywords);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
        verify(newsHidingService).previewNewsByKeywords(keywords);
    }

    @Test
    void testUnhideNewsWithLargeId() {
        // Arrange
        when(newsHidingService.unhideNews(Integer.MAX_VALUE)).thenReturn(newsDTO1);

        // Act
        ResponseEntity<NewsDTO> result = newsHidingController.unhideNews(Integer.MAX_VALUE);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(1, result.getBody().getId());
        verify(newsHidingService).unhideNews(Integer.MAX_VALUE);
    }
} 