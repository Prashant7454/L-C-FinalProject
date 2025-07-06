package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.NewsDTO;
import com.server.NewsAggrigationServer.exception.NewsServiceException;
import com.server.NewsAggrigationServer.exception.ResourceNotFoundException;
import com.server.NewsAggrigationServer.model.News;
import com.server.NewsAggrigationServer.repository.NewsRepository;
import com.server.NewsAggrigationServer.service.impl.NewsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NewsServiceTest {

    @Mock
    private NewsRepository newsRepository;

    @InjectMocks
    private NewsServiceImpl newsService;

    private News testNews;
    private NewsDTO testNewsDTO;
    private LocalDateTime testDateTime;

    @BeforeEach
    void setUp() {
        testDateTime = LocalDateTime.of(2023, 12, 25, 10, 30, 0);
        
        testNews = new News();
        testNews.setId(1);
        testNews.setTitle("Test News");
        testNews.setDescription("Test Description");
        testNews.setSource("Test Source");
        testNews.setUrl("https://test.com");
        testNews.setPublishAt(testDateTime);
        testNews.setKeyword("test");
        testNews.setLikeCount(10);
        testNews.setDisLikeCount(2);
        testNews.setReportCount(1);
        testNews.setIsHide(0);

        testNewsDTO = new NewsDTO();
        testNewsDTO.setTitle("Test News");
        testNewsDTO.setDescription("Test Description");
        testNewsDTO.setSource("Test Source");
        testNewsDTO.setUrl("https://test.com");
        testNewsDTO.setPublishAt(testDateTime);
        testNewsDTO.setKeyword("test");
        testNewsDTO.setLikeCount(10);
        testNewsDTO.setDisLikeCount(2);
        testNewsDTO.setReportCount(1);
        testNewsDTO.setIsHide(0);
    }

    @Test
    void testCreateNews_Success() {
        // Arrange
        when(newsRepository.save(any(News.class))).thenReturn(testNews);

        // Act
        NewsDTO result = newsService.createNews(testNewsDTO);

        // Assert
        assertNotNull(result);
        assertEquals(testNews.getId(), result.getId());
        assertEquals(testNewsDTO.getTitle(), result.getTitle());
        assertEquals(testNewsDTO.getDescription(), result.getDescription());
        
        verify(newsRepository).save(any(News.class));
    }

    @Test
    void testCreateNews_Exception() {
        // Arrange
        when(newsRepository.save(any(News.class))).thenThrow(RuntimeException.class);

        // Act & Assert
        assertThrows(NewsServiceException.class, () -> {
            newsService.createNews(testNewsDTO);
        });

        verify(newsRepository).save(any(News.class));
    }

    @Test
    void testUpdateNews_Success() {
        // Arrange
        when(newsRepository.findById(1)).thenReturn(Optional.of(testNews));
        when(newsRepository.save(any(News.class))).thenReturn(testNews);

        // Act
        NewsDTO result = newsService.updateNews(1, testNewsDTO);

        // Assert
        assertNotNull(result);
        assertEquals(testNews.getId(), result.getId());
        
        verify(newsRepository).findById(1);
        verify(newsRepository).save(any(News.class));
    }

    @Test
    void testUpdateNews_NewsNotFound() {
        // Arrange
        when(newsRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            newsService.updateNews(1, testNewsDTO);
        });

        verify(newsRepository).findById(1);
        verify(newsRepository, never()).save(any(News.class));
    }

    @Test
    void testUpdateNews_Exception() {
        // Arrange
        when(newsRepository.findById(1)).thenReturn(Optional.of(testNews));
        when(newsRepository.save(any(News.class))).thenThrow(RuntimeException.class);

        // Act & Assert
        assertThrows(NewsServiceException.class, () -> {
            newsService.updateNews(1, testNewsDTO);
        });

        verify(newsRepository).findById(1);
        verify(newsRepository).save(any(News.class));
    }

    @Test
    void testGetNewsById_Success() {
        // Arrange
        when(newsRepository.findById(1)).thenReturn(Optional.of(testNews));

        // Act
        NewsDTO result = newsService.getNewsById(1);

        // Assert
        assertNotNull(result);
        assertEquals(testNews.getId(), result.getId());
        assertEquals(testNews.getTitle(), result.getTitle());
        
        verify(newsRepository).findById(1);
    }

    @Test
    void testGetNewsById_NotFound() {
        // Arrange
        when(newsRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            newsService.getNewsById(1);
        });

        verify(newsRepository).findById(1);
    }

    @Test
    void testGetAllNews_Success() {
        // Arrange
        List<News> newsList = Arrays.asList(testNews);
        when(newsRepository.findAll()).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsService.getAllNews();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testNews.getId(), result.get(0).getId());
        
        verify(newsRepository).findAll();
    }

    @Test
    void testGetAllNews_EmptyList() {
        // Arrange
        when(newsRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<NewsDTO> result = newsService.getAllNews();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        
        verify(newsRepository).findAll();
    }

    @Test
    void testGetNews_SearchSuccess() {
        // Arrange
        String searchString = "test";
        List<News> newsList = Arrays.asList(testNews);
        when(newsRepository.search(searchString)).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsService.getNews(searchString);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testNews.getId(), result.get(0).getId());
        
        verify(newsRepository).search(searchString);
    }

    @Test
    void testGetNewsByIds_Success() {
        // Arrange
        List<Integer> ids = Arrays.asList(1, 2);
        List<News> newsList = Arrays.asList(testNews);
        when(newsRepository.findByIdIn(ids)).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsService.getNewsByIds(ids);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testNews.getId(), result.get(0).getId());
        
        verify(newsRepository).findByIdIn(ids);
    }

    @Test
    void testAddMultipleNews_Success() {
        // Arrange
        List<NewsDTO> newsList = Arrays.asList(testNewsDTO);
        when(newsRepository.saveAll(any())).thenReturn(Arrays.asList(testNews));

        // Act
        newsService.addMultipleNews(newsList);

        // Assert
        verify(newsRepository).saveAll(any());
    }

    @Test
    void testGetTodayNewsByIds_Success() {
        // Arrange
        List<Integer> ids = Arrays.asList(1, 2);
        List<News> newsList = Arrays.asList(testNews);
        when(newsRepository.findByIdInAndPublishAtBetween(any(), any(), any())).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsService.getTodayNewsByIds(ids);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        
        verify(newsRepository).findByIdInAndPublishAtBetween(any(), any(), any());
    }

    @Test
    void testGetNewsByIdsAndDateRange_Success() {
        // Arrange
        List<Integer> ids = Arrays.asList(1, 2);
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        List<News> newsList = Arrays.asList(testNews);
        when(newsRepository.findByIdInAndPublishAtBetween(ids, startDate, endDate)).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsService.getNewsByIdsAndDateRange(ids, startDate, endDate);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        
        verify(newsRepository).findByIdInAndPublishAtBetween(ids, startDate, endDate);
    }

    @Test
    void testGetAllVisibleNews_Success() {
        // Arrange
        List<News> newsList = Arrays.asList(testNews);
        when(newsRepository.findByIsHide(0)).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsService.getAllVisibleNews();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testNews.getId(), result.get(0).getId());
        
        verify(newsRepository).findByIsHide(0);
    }

    @Test
    void testGetVisibleNews_SearchSuccess() {
        // Arrange
        String searchString = "test";
        List<News> newsList = Arrays.asList(testNews);
        when(newsRepository.searchVisible(searchString)).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsService.getVisibleNews(searchString);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        
        verify(newsRepository).searchVisible(searchString);
    }

    @Test
    void testGetVisibleNewsByIds_Success() {
        // Arrange
        List<Integer> ids = Arrays.asList(1, 2);
        List<News> newsList = Arrays.asList(testNews);
        when(newsRepository.findByIdInAndIsHide(ids, 0)).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsService.getVisibleNewsByIds(ids);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        
        verify(newsRepository).findByIdInAndIsHide(ids, 0);
    }

    @Test
    void testHideNews_Success() {
        // Arrange
        when(newsRepository.findById(1)).thenReturn(Optional.of(testNews));
        when(newsRepository.save(any(News.class))).thenReturn(testNews);

        // Act
        NewsDTO result = newsService.hideNews(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getIsHide());
        
        verify(newsRepository).findById(1);
        verify(newsRepository).save(any(News.class));
    }

    @Test
    void testUnhideNews_Success() {
        // Arrange
        testNews.setIsHide(1);
        when(newsRepository.findById(1)).thenReturn(Optional.of(testNews));
        when(newsRepository.save(any(News.class))).thenReturn(testNews);

        // Act
        NewsDTO result = newsService.unhideNews(1);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.getIsHide());
        
        verify(newsRepository).findById(1);
        verify(newsRepository).save(any(News.class));
    }
} 