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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NewsServiceImplTest {

    @Mock
    private NewsRepository newsRepository;

    @InjectMocks
    private NewsServiceImpl newsService;

    private News testNews;
    private NewsDTO testNewsDTO;

    @BeforeEach
    void setUp() {
        testNews = new News();
        testNews.setId(1);
        testNews.setTitle("Test News");
        testNews.setDescription("Test Description");
        testNews.setSource("Test Source");
        testNews.setUrl("http://test.com");
        testNews.setPublishAt(LocalDateTime.now());
        testNews.setKeyword("test");
        testNews.setLikeCount(5);
        testNews.setDisLikeCount(2);
        testNews.setReportCount(1);
        testNews.setIsHide(0);

        testNewsDTO = new NewsDTO();
        testNewsDTO.setId(1);
        testNewsDTO.setTitle("Test News");
        testNewsDTO.setDescription("Test Description");
        testNewsDTO.setSource("Test Source");
        testNewsDTO.setUrl("http://test.com");
        testNewsDTO.setPublishAt(LocalDateTime.now());
        testNewsDTO.setKeyword("test");
        testNewsDTO.setLikeCount(5);
        testNewsDTO.setDisLikeCount(2);
        testNewsDTO.setReportCount(1);
        testNewsDTO.setIsHide(0);
    }

    @Test
    void createNews_Success() {
        // Arrange
        NewsDTO inputDto = createNewsDTO(null, "New News", "New Description", "New Source", "http://new.com", LocalDateTime.now(), "new", 0, 0, 0, 0);
        News savedNews = createNews(1, "New News", "New Description", "New Source", "http://new.com", LocalDateTime.now(), "new", 0, 0, 0, 0);

        when(newsRepository.save(any(News.class))).thenReturn(savedNews);

        // Act
        NewsDTO result = newsService.createNews(inputDto);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("New News", result.getTitle());
        assertEquals("New Description", result.getDescription());
        assertEquals("New Source", result.getSource());

        verify(newsRepository, times(1)).save(any(News.class));
    }

    @Test
    void createNews_ThrowsException() {
        // Arrange
        NewsDTO inputDto = createNewsDTO(null, "New News", "New Description", "New Source", "http://new.com", LocalDateTime.now(), "new", 0, 0, 0, 0);

        when(newsRepository.save(any(News.class))).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        NewsServiceException exception = assertThrows(NewsServiceException.class, () -> {
            newsService.createNews(inputDto);
        });

        assertNotNull(exception);
        verify(newsRepository, times(1)).save(any(News.class));
    }

    @Test
    void updateNews_Success() {
        // Arrange
        Integer newsId = 1;
        NewsDTO inputDto = createNewsDTO(1, "Updated News", "Updated Description", "Updated Source", "http://updated.com", LocalDateTime.now(), "updated", 10, 5, 2, 0);
        News updatedNews = createNews(1, "Updated News", "Updated Description", "Updated Source", "http://updated.com", LocalDateTime.now(), "updated", 10, 5, 2, 0);

        when(newsRepository.findById(newsId)).thenReturn(Optional.of(testNews));
        when(newsRepository.save(any(News.class))).thenReturn(updatedNews);

        // Act
        NewsDTO result = newsService.updateNews(newsId, inputDto);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Updated News", result.getTitle());
        assertEquals(10, result.getLikeCount());
        assertEquals(5, result.getDisLikeCount());
        assertEquals(2, result.getReportCount());

        verify(newsRepository, times(1)).findById(newsId);
        verify(newsRepository, times(1)).save(any(News.class));
    }

    @Test
    void updateNews_NotFound_ThrowsException() {
        // Arrange
        Integer newsId = 999;
        NewsDTO inputDto = createNewsDTO(999, "Updated News", "Updated Description", "Updated Source", "http://updated.com", LocalDateTime.now(), "updated", 10, 5, 2, 0);

        when(newsRepository.findById(newsId)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            newsService.updateNews(newsId, inputDto);
        });

        assertNotNull(exception);
        verify(newsRepository, times(1)).findById(newsId);
        verify(newsRepository, never()).save(any(News.class));
    }

    @Test
    void updateNews_ThrowsException() {
        // Arrange
        Integer newsId = 1;
        NewsDTO inputDto = createNewsDTO(1, "Updated News", "Updated Description", "Updated Source", "http://updated.com", LocalDateTime.now(), "updated", 10, 5, 2, 0);

        when(newsRepository.findById(newsId)).thenReturn(Optional.of(testNews));
        when(newsRepository.save(any(News.class))).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        NewsServiceException exception = assertThrows(NewsServiceException.class, () -> {
            newsService.updateNews(newsId, inputDto);
        });

        assertNotNull(exception);
        verify(newsRepository, times(1)).findById(newsId);
        verify(newsRepository, times(1)).save(any(News.class));
    }

    @Test
    void getNewsById_Success() {
        // Arrange
        Integer newsId = 1;
        when(newsRepository.findById(newsId)).thenReturn(Optional.of(testNews));

        // Act
        NewsDTO result = newsService.getNewsById(newsId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Test News", result.getTitle());
        assertEquals("Test Description", result.getDescription());
        assertEquals(5, result.getLikeCount());
        assertEquals(2, result.getDisLikeCount());

        verify(newsRepository, times(1)).findById(newsId);
    }

    @Test
    void getNewsById_NotFound_ThrowsException() {
        // Arrange
        Integer newsId = 999;
        when(newsRepository.findById(newsId)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            newsService.getNewsById(newsId);
        });

        assertNotNull(exception);
        verify(newsRepository, times(1)).findById(newsId);
    }

    @Test
    void getAllNews_Success() {
        // Arrange
        News news1 = createNews(1, "News 1", "Description 1", "Source 1", "http://news1.com", LocalDateTime.now(), "news1", 5, 1, 0, 0);
        News news2 = createNews(2, "News 2", "Description 2", "Source 2", "http://news2.com", LocalDateTime.now(), "news2", 3, 2, 1, 0);
        List<News> newsList = Arrays.asList(news1, news2);

        when(newsRepository.findAll()).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsService.getAllNews();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("News 1", result.get(0).getTitle());
        assertEquals(2, result.get(1).getId());
        assertEquals("News 2", result.get(1).getTitle());

        verify(newsRepository, times(1)).findAll();
    }

    @Test
    void getAllNews_EmptyList() {
        // Arrange
        when(newsRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<NewsDTO> result = newsService.getAllNews();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(newsRepository, times(1)).findAll();
    }

    @Test
    void getNews_Success() {
        // Arrange
        String searchString = "technology";
        News news1 = createNews(1, "Tech News", "Technology description", "Tech Source", "http://tech.com", LocalDateTime.now(), "tech", 8, 1, 0, 0);
        List<News> newsList = Arrays.asList(news1);

        when(newsRepository.search(searchString)).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsService.getNews(searchString);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("Tech News", result.get(0).getTitle());

        verify(newsRepository, times(1)).search(searchString);
    }

    @Test
    void getNewsByIds_Success() {
        // Arrange
        List<Integer> newsIds = Arrays.asList(1, 2);
        News news1 = createNews(1, "News 1", "Description 1", "Source 1", "http://news1.com", LocalDateTime.now(), "news1", 5, 1, 0, 0);
        News news2 = createNews(2, "News 2", "Description 2", "Source 2", "http://news2.com", LocalDateTime.now(), "news2", 3, 2, 1, 0);
        List<News> newsList = Arrays.asList(news1, news2);

        when(newsRepository.findByIdIn(newsIds)).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsService.getNewsByIds(newsIds);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());

        verify(newsRepository, times(1)).findByIdIn(newsIds);
    }

    @Test
    void addMultipleNews_Success() {
        // Arrange
        List<NewsDTO> newsList = Arrays.asList(
                createNewsDTO(null, "News 1", "Description 1", "Source 1", "http://news1.com", LocalDateTime.now(), "news1", 0, 0, 0, 0),
                createNewsDTO(null, "News 2", "Description 2", "Source 2", "http://news2.com", LocalDateTime.now(), "news2", 0, 0, 0, 0)
        );

        when(newsRepository.saveAll(anyList())).thenReturn(Arrays.asList());

        // Act
        newsService.addMultipleNews(newsList);

        // Assert
        verify(newsRepository, times(1)).saveAll(anyList());
    }

    @Test
    void getTodayNewsByIds_Success() {
        // Arrange
        List<Integer> newsIds = Arrays.asList(1, 2);
        News news1 = createNews(1, "Today News 1", "Description 1", "Source 1", "http://today1.com", LocalDateTime.now(), "today1", 3, 1, 0, 0);
        List<News> newsList = Arrays.asList(news1);

        when(newsRepository.findByIdInAndPublishAtBetween(eq(newsIds), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsService.getTodayNewsByIds(newsIds);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("Today News 1", result.get(0).getTitle());

        verify(newsRepository, times(1)).findByIdInAndPublishAtBetween(eq(newsIds), any(LocalDateTime.class), any(LocalDateTime.class));
    }

    @Test
    void getNewsByIdsAndDateRange_Success() {
        // Arrange
        List<Integer> newsIds = Arrays.asList(1, 2);
        LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 1, 31, 23, 59);
        News news1 = createNews(1, "Date Range News", "Description", "Source", "http://daterange.com", LocalDateTime.now(), "daterange", 4, 1, 0, 0);
        List<News> newsList = Arrays.asList(news1);

        when(newsRepository.findByIdInAndPublishAtBetween(newsIds, startDate, endDate)).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsService.getNewsByIdsAndDateRange(newsIds, startDate, endDate);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("Date Range News", result.get(0).getTitle());

        verify(newsRepository, times(1)).findByIdInAndPublishAtBetween(newsIds, startDate, endDate);
    }

    @Test
    void getAllVisibleNews_Success() {
        // Arrange
        News news1 = createNews(1, "Visible News 1", "Description 1", "Source 1", "http://visible1.com", LocalDateTime.now(), "visible1", 5, 1, 0, 0);
        News news2 = createNews(2, "Visible News 2", "Description 2", "Source 2", "http://visible2.com", LocalDateTime.now(), "visible2", 3, 2, 0, 0);
        List<News> newsList = Arrays.asList(news1, news2);

        when(newsRepository.findByIsHide(0)).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsService.getAllVisibleNews();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());

        verify(newsRepository, times(1)).findByIsHide(0);
    }

    @Test
    void getVisibleNews_Success() {
        // Arrange
        String searchString = "visible";
        News news1 = createNews(1, "Visible Search News", "Description", "Source", "http://visible.com", LocalDateTime.now(), "visible", 2, 0, 0, 0);
        List<News> newsList = Arrays.asList(news1);

        when(newsRepository.searchVisible(searchString)).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsService.getVisibleNews(searchString);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("Visible Search News", result.get(0).getTitle());

        verify(newsRepository, times(1)).searchVisible(searchString);
    }

    @Test
    void getVisibleNewsByIds_Success() {
        // Arrange
        List<Integer> newsIds = Arrays.asList(1, 2);
        News news1 = createNews(1, "Visible News 1", "Description 1", "Source 1", "http://visible1.com", LocalDateTime.now(), "visible1", 3, 1, 0, 0);
        List<News> newsList = Arrays.asList(news1);

        when(newsRepository.findByIdInAndIsHide(newsIds, 0)).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsService.getVisibleNewsByIds(newsIds);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("Visible News 1", result.get(0).getTitle());

        verify(newsRepository, times(1)).findByIdInAndIsHide(newsIds, 0);
    }

    @Test
    void getVisibleTodayNewsByIds_Success() {
        // Arrange
        List<Integer> newsIds = Arrays.asList(1, 2);
        News news1 = createNews(1, "Visible Today News", "Description", "Source", "http://visible.com", LocalDateTime.now(), "visible", 1, 0, 0, 0);
        List<News> newsList = Arrays.asList(news1);

        when(newsRepository.findByIdInAndPublishAtBetweenAndIsHide(eq(newsIds), any(LocalDateTime.class), any(LocalDateTime.class), eq(0)))
                .thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsService.getVisibleTodayNewsByIds(newsIds);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("Visible Today News", result.get(0).getTitle());

        verify(newsRepository, times(1)).findByIdInAndPublishAtBetweenAndIsHide(eq(newsIds), any(LocalDateTime.class), any(LocalDateTime.class), eq(0));
    }

    @Test
    void getVisibleNewsByIdsAndDateRange_Success() {
        // Arrange
        List<Integer> newsIds = Arrays.asList(1, 2);
        LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 1, 31, 23, 59);
        News news1 = createNews(1, "Visible Date Range News", "Description", "Source", "http://visible.com", LocalDateTime.now(), "visible", 2, 1, 0, 0);
        List<News> newsList = Arrays.asList(news1);

        when(newsRepository.findByIdInAndPublishAtBetweenAndIsHide(newsIds, startDate, endDate, 0)).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsService.getVisibleNewsByIdsAndDateRange(newsIds, startDate, endDate);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("Visible Date Range News", result.get(0).getTitle());

        verify(newsRepository, times(1)).findByIdInAndPublishAtBetweenAndIsHide(newsIds, startDate, endDate, 0);
    }

    @Test
    void getNewsInVisibleCategories_Success() {
        // Arrange
        News news1 = createNews(1, "Visible Category News 1", "Description 1", "Source 1", "http://category1.com", LocalDateTime.now(), "category1", 3, 1, 0, 0);
        News news2 = createNews(2, "Visible Category News 2", "Description 2", "Source 2", "http://category2.com", LocalDateTime.now(), "category2", 2, 0, 0, 0);
        List<News> newsList = Arrays.asList(news1, news2);

        when(newsRepository.findNewsInVisibleCategories()).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsService.getNewsInVisibleCategories();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());

        verify(newsRepository, times(1)).findNewsInVisibleCategories();
    }

    @Test
    void getNewsByIdsInVisibleCategories_Success() {
        // Arrange
        List<Integer> newsIds = Arrays.asList(1, 2, 3);
        News news1 = createNews(1, "Visible Category News 1", "Description 1", "Source 1", "http://category1.com", LocalDateTime.now(), "category1", 4, 1, 0, 0);
        News news2 = createNews(2, "Visible Category News 2", "Description 2", "Source 2", "http://category2.com", LocalDateTime.now(), "category2", 3, 2, 0, 0);
        List<News> newsList = Arrays.asList(news1, news2);

        when(newsRepository.findNewsByIdsInVisibleCategories(newsIds)).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsService.getNewsByIdsInVisibleCategories(newsIds);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());

        verify(newsRepository, times(1)).findNewsByIdsInVisibleCategories(newsIds);
    }

    @Test
    void getReportedNews_Success() {
        // Arrange
        News news1 = createNews(1, "Reported News 1", "Description 1", "Source 1", "http://reported1.com", LocalDateTime.now(), "reported1", 2, 5, 3, 0);
        News news2 = createNews(2, "Reported News 2", "Description 2", "Source 2", "http://reported2.com", LocalDateTime.now(), "reported2", 1, 8, 5, 0);
        List<News> newsList = Arrays.asList(news1, news2);

        when(newsRepository.findByReportCountGreaterThan(0)).thenReturn(newsList);

        // Act
        List<NewsDTO> result = newsService.getReportedNews();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(3, result.get(0).getReportCount());
        assertEquals(2, result.get(1).getId());
        assertEquals(5, result.get(1).getReportCount());

        verify(newsRepository, times(1)).findByReportCountGreaterThan(0);
    }

    @Test
    void hideNews_Success() {
        // Arrange
        Integer newsId = 1;
        News hiddenNews = createNews(1, "Hidden News", "Description", "Source", "http://hidden.com", LocalDateTime.now(), "hidden", 2, 1, 0, 1);

        when(newsRepository.findById(newsId)).thenReturn(Optional.of(testNews));
        when(newsRepository.save(any(News.class))).thenReturn(hiddenNews);

        // Act
        NewsDTO result = newsService.hideNews(newsId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(1, result.getIsHide());

        verify(newsRepository, times(1)).findById(newsId);
        verify(newsRepository, times(1)).save(any(News.class));
    }

    @Test
    void hideNews_NotFound_ThrowsException() {
        // Arrange
        Integer newsId = 999;
        when(newsRepository.findById(newsId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            newsService.hideNews(newsId);
        });

        assertNotNull(exception);
        assertEquals("News not found with id: " + newsId, exception.getMessage());
        verify(newsRepository, times(1)).findById(newsId);
        verify(newsRepository, never()).save(any(News.class));
    }

    @Test
    void unhideNews_Success() {
        // Arrange
        Integer newsId = 1;
        News unhiddenNews = createNews(1, "Unhidden News", "Description", "Source", "http://unhidden.com", LocalDateTime.now(), "unhidden", 3, 1, 0, 0);

        when(newsRepository.findById(newsId)).thenReturn(Optional.of(testNews));
        when(newsRepository.save(any(News.class))).thenReturn(unhiddenNews);

        // Act
        NewsDTO result = newsService.unhideNews(newsId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(0, result.getIsHide());

        verify(newsRepository, times(1)).findById(newsId);
        verify(newsRepository, times(1)).save(any(News.class));
    }

    @Test
    void unhideNews_NotFound_ThrowsException() {
        // Arrange
        Integer newsId = 999;
        when(newsRepository.findById(newsId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            newsService.unhideNews(newsId);
        });

        assertNotNull(exception);
        assertEquals("News not found with id: " + newsId, exception.getMessage());
        verify(newsRepository, times(1)).findById(newsId);
        verify(newsRepository, never()).save(any(News.class));
    }

    // Helper methods
    private News createNews(Integer id, String title, String description, String source, String url, 
                           LocalDateTime publishAt, String keyword, Integer likeCount, Integer disLikeCount, 
                           Integer reportCount, Integer isHide) {
        News news = new News();
        news.setId(id);
        news.setTitle(title);
        news.setDescription(description);
        news.setSource(source);
        news.setUrl(url);
        news.setPublishAt(publishAt);
        news.setKeyword(keyword);
        news.setLikeCount(likeCount);
        news.setDisLikeCount(disLikeCount);
        news.setReportCount(reportCount);
        news.setIsHide(isHide);
        return news;
    }

    private NewsDTO createNewsDTO(Integer id, String title, String description, String source, String url, 
                                 LocalDateTime publishAt, String keyword, Integer likeCount, Integer disLikeCount, 
                                 Integer reportCount, Integer isHide) {
        NewsDTO dto = new NewsDTO();
        dto.setId(id);
        dto.setTitle(title);
        dto.setDescription(description);
        dto.setSource(source);
        dto.setUrl(url);
        dto.setPublishAt(publishAt);
        dto.setKeyword(keyword);
        dto.setLikeCount(likeCount);
        dto.setDisLikeCount(disLikeCount);
        dto.setReportCount(reportCount);
        dto.setIsHide(isHide);
        return dto;
    }
}