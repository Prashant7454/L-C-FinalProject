package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.dto.NewsDTO;
import com.server.NewsAggrigationServer.service.NewsService;
import com.server.NewsAggrigationServer.service.SavedNewsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NewsControllerTest {

    @Mock
    private NewsService newsService;

    @Mock
    private SavedNewsService savedNewsService;

    @InjectMocks
    private NewsController newsController;

    private NewsDTO newsDTO1;
    private NewsDTO newsDTO2;
    private List<NewsDTO> newsList;

    @BeforeEach
    void setUp() {
        newsDTO1 = new NewsDTO();
        newsDTO1.setId(1);
        newsDTO1.setTitle("Test News 1");
        newsDTO1.setContent("Test content 1");
        newsDTO1.setAuthor("Test Author 1");
        newsDTO1.setPublishedAt(LocalDateTime.now());

        newsDTO2 = new NewsDTO();
        newsDTO2.setId(2);
        newsDTO2.setTitle("Test News 2");
        newsDTO2.setContent("Test content 2");
        newsDTO2.setAuthor("Test Author 2");
        newsDTO2.setPublishedAt(LocalDateTime.now());

        newsList = Arrays.asList(newsDTO1, newsDTO2);
    }

    @Test
    void testCreateNews() {
        // Arrange
        when(newsService.createNews(any(NewsDTO.class))).thenReturn(newsDTO1);

        // Act
        NewsDTO result = newsController.createNews(newsDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Test News 1", result.getTitle());
        verify(newsService).createNews(newsDTO1);
    }

    @Test
    void testCreateNewsWithNullInput() {
        // Arrange
        when(newsService.createNews(null)).thenThrow(new IllegalArgumentException("News cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsController.createNews(null);
        });
        verify(newsService).createNews(null);
    }

    @Test
    void testUpdateNews() {
        // Arrange
        when(newsService.updateNews(1, newsDTO1)).thenReturn(newsDTO1);

        // Act
        NewsDTO result = newsController.updateNews(1, newsDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Test News 1", result.getTitle());
        verify(newsService).updateNews(1, newsDTO1);
    }

    @Test
    void testUpdateNewsWithZeroId() {
        // Arrange
        when(newsService.updateNews(0, newsDTO1)).thenThrow(new IllegalArgumentException("Invalid news ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsController.updateNews(0, newsDTO1);
        });
        verify(newsService).updateNews(0, newsDTO1);
    }

    @Test
    void testGetNewsById() {
        // Arrange
        when(newsService.getNewsById(1)).thenReturn(newsDTO1);

        // Act
        NewsDTO result = newsController.getNewsById(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Test News 1", result.getTitle());
        verify(newsService).getNewsById(1);
    }

    @Test
    void testGetNewsByIdWithNegativeId() {
        // Arrange
        when(newsService.getNewsById(-1)).thenThrow(new IllegalArgumentException("Invalid news ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsController.getNewsById(-1);
        });
        verify(newsService).getNewsById(-1);
    }

    @Test
    void testGetNewsByIdList() {
        // Arrange
        List<Integer> ids = Arrays.asList(1, 2);
        when(newsService.getNewsByIds(ids)).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsController.getNewsByIdList(ids);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
        verify(newsService).getNewsByIds(ids);
    }

    @Test
    void testGetNewsByIdListWithEmptyList() {
        // Arrange
        List<Integer> ids = Collections.emptyList();
        when(newsService.getNewsByIds(ids)).thenReturn(Collections.emptyList());

        // Act
        List<NewsDTO> result = newsController.getNewsByIdList(ids);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(newsService).getNewsByIds(ids);
    }

    @Test
    void testGetAllNews() {
        // Arrange
        when(newsService.getAllNews()).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsController.getAllNews();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(newsService).getAllNews();
    }

    @Test
    void testSearchNews() {
        // Arrange
        String searchString = "test";
        when(newsService.getNews(searchString)).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsController.searchNews(searchString);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(newsService).getNews(searchString);
    }

    @Test
    void testSearchNewsWithEmptyString() {
        // Arrange
        String searchString = "";
        when(newsService.getNews(searchString)).thenReturn(Collections.emptyList());

        // Act
        List<NewsDTO> result = newsController.searchNews(searchString);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(newsService).getNews(searchString);
    }

    @Test
    void testAddMultipleNews() {
        // Arrange
        doNothing().when(newsService).addMultipleNews(newsList);

        // Act
        ResponseEntity<String> result = newsController.addMultipleNews(newsList);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("News added successfully", result.getBody());
        verify(newsService).addMultipleNews(newsList);
    }

    @Test
    void testGetSavedNews() {
        // Arrange
        when(savedNewsService.getSavedNewsByUserId(1)).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsController.getSavedNews(1);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(savedNewsService).getSavedNewsByUserId(1);
    }

    @Test
    void testGetTodayNewsByIds() {
        // Arrange
        List<Integer> ids = Arrays.asList(1, 2);
        when(newsService.getTodayNewsByIds(ids)).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsController.getTodayNewsByIds(ids);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(newsService).getTodayNewsByIds(ids);
    }

    @Test
    void testGetAllVisibleNews() {
        // Arrange
        when(newsService.getAllVisibleNews()).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsController.getAllVisibleNews();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(newsService).getAllVisibleNews();
    }

    @Test
    void testGetVisibleNews() {
        // Arrange
        String searchString = "test";
        when(newsService.getVisibleNews(searchString)).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsController.getVisibleNews(searchString);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(newsService).getVisibleNews(searchString);
    }

    @Test
    void testGetVisibleNewsByIdList() {
        // Arrange
        List<Integer> ids = Arrays.asList(1, 2);
        when(newsService.getVisibleNewsByIds(ids)).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsController.getVisibleNewsByIdList(ids);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(newsService).getVisibleNewsByIds(ids);
    }

    @Test
    void testGetVisibleTodayNewsByIds() {
        // Arrange
        List<Integer> ids = Arrays.asList(1, 2);
        when(newsService.getVisibleTodayNewsByIds(ids)).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsController.getVisibleTodayNewsByIds(ids);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(newsService).getVisibleTodayNewsByIds(ids);
    }

    @Test
    void testGetNewsInVisibleCategories() {
        // Arrange
        when(newsService.getNewsInVisibleCategories()).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsController.getNewsInVisibleCategories();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(newsService).getNewsInVisibleCategories();
    }

    @Test
    void testGetNewsByIdsInVisibleCategories() {
        // Arrange
        List<Integer> newsIds = Arrays.asList(1, 2);
        when(newsService.getNewsByIdsInVisibleCategories(newsIds)).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsController.getNewsByIdsInVisibleCategories(newsIds);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(newsService).getNewsByIdsInVisibleCategories(newsIds);
    }

    @Test
    void testGetReportedNews() {
        // Arrange
        when(newsService.getReportedNews()).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsController.getReportedNews();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(newsService).getReportedNews();
    }

    @Test
    void testHideNews() {
        // Arrange
        when(newsService.hideNews(1)).thenReturn(newsDTO1);

        // Act
        NewsDTO result = newsController.hideNews(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(newsService).hideNews(1);
    }

    @Test
    void testUnhideNews() {
        // Arrange
        when(newsService.unhideNews(1)).thenReturn(newsDTO1);

        // Act
        NewsDTO result = newsController.unhideNews(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(newsService).unhideNews(1);
    }

    @Test
    void testGetNewsByIdListWithNullInput() {
        // Arrange
        when(newsService.getNewsByIds(null)).thenThrow(new IllegalArgumentException("News IDs cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsController.getNewsByIdList(null);
        });
        verify(newsService).getNewsByIds(null);
    }

    @Test
    void testSearchNewsWithSpecialCharacters() {
        // Arrange
        String searchString = "test@#$%^&*()";
        when(newsService.getNews(searchString)).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsController.searchNews(searchString);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(newsService).getNews(searchString);
    }

    @Test
    void testSearchNewsWithUnicode() {
        // Arrange
        String searchString = "test\u00E9\u00F1\u00FC";
        when(newsService.getNews(searchString)).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsController.searchNews(searchString);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(newsService).getNews(searchString);
    }
} 