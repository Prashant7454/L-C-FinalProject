package com.server.NewsAggrigationServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.NewsAggrigationServer.dto.NewsDTO;
import com.server.NewsAggrigationServer.service.NewsService;
import com.server.NewsAggrigationServer.service.SavedNewsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class NewsControllerTest {

    @Mock
    private NewsService newsService;

    @Mock
    private SavedNewsService savedNewsService;

    @InjectMocks
    private NewsController newsController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(newsController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void createNews_Success() throws Exception {
        // Arrange
        NewsDTO inputDto = createNewsDTO(null, "Test News", "Test Description", "Test Source", "http://test.com", LocalDateTime.now(), "test", 0, 0, 0, 0);
        NewsDTO expectedDto = createNewsDTO(1, "Test News", "Test Description", "Test Source", "http://test.com", LocalDateTime.now(), "test", 0, 0, 0, 0);

        when(newsService.createNews(any(NewsDTO.class))).thenReturn(expectedDto);

        // Act & Assert
        mockMvc.perform(post("/api/news")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Test News"))
                .andExpect(jsonPath("$.description").value("Test Description"))
                .andExpect(jsonPath("$.source").value("Test Source"));

        verify(newsService, times(1)).createNews(any(NewsDTO.class));
    }

    @Test
    void updateNews_Success() throws Exception {
        // Arrange
        Integer newsId = 1;
        NewsDTO inputDto = createNewsDTO(1, "Updated News", "Updated Description", "Updated Source", "http://updated.com", LocalDateTime.now(), "updated", 5, 2, 1, 0);
        NewsDTO expectedDto = createNewsDTO(1, "Updated News", "Updated Description", "Updated Source", "http://updated.com", LocalDateTime.now(), "updated", 5, 2, 1, 0);

        when(newsService.updateNews(eq(newsId), any(NewsDTO.class))).thenReturn(expectedDto);

        // Act & Assert
        mockMvc.perform(put("/api/news/{id}", newsId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Updated News"))
                .andExpect(jsonPath("$.likeCount").value(5))
                .andExpect(jsonPath("$.disLikeCount").value(2))
                .andExpect(jsonPath("$.reportCount").value(1));

        verify(newsService, times(1)).updateNews(eq(newsId), any(NewsDTO.class));
    }

    @Test
    void getNewsById_Success() throws Exception {
        // Arrange
        Integer newsId = 1;
        NewsDTO expectedDto = createNewsDTO(1, "Test News", "Test Description", "Test Source", "http://test.com", LocalDateTime.now(), "test", 10, 2, 0, 0);

        when(newsService.getNewsById(newsId)).thenReturn(expectedDto);

        // Act & Assert
        mockMvc.perform(get("/api/news/{id}", newsId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Test News"))
                .andExpect(jsonPath("$.likeCount").value(10))
                .andExpect(jsonPath("$.disLikeCount").value(2));

        verify(newsService, times(1)).getNewsById(newsId);
    }

    @Test
    void getAllNews_Success() throws Exception {
        // Arrange
        List<NewsDTO> expectedNews = Arrays.asList(
                createNewsDTO(1, "News 1", "Description 1", "Source 1", "http://news1.com", LocalDateTime.now(), "news1", 5, 1, 0, 0),
                createNewsDTO(2, "News 2", "Description 2", "Source 2", "http://news2.com", LocalDateTime.now(), "news2", 3, 2, 1, 0)
        );

        when(newsService.getAllNews()).thenReturn(expectedNews);

        // Act & Assert
        mockMvc.perform(get("/api/news"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("News 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("News 2"));

        verify(newsService, times(1)).getAllNews();
    }

    @Test
    void searchNews_Success() throws Exception {
        // Arrange
        String searchString = "technology";
        List<NewsDTO> expectedNews = Arrays.asList(
                createNewsDTO(1, "Tech News", "Technology description", "Tech Source", "http://tech.com", LocalDateTime.now(), "tech", 8, 1, 0, 0)
        );

        when(newsService.getNews(searchString)).thenReturn(expectedNews);

        // Act & Assert
        mockMvc.perform(get("/api/news/search")
                .param("searchString", searchString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Tech News"));

        verify(newsService, times(1)).getNews(searchString);
    }

    @Test
    void addMultipleNews_Success() throws Exception {
        // Arrange
        List<NewsDTO> newsList = Arrays.asList(
                createNewsDTO(null, "News 1", "Description 1", "Source 1", "http://news1.com", LocalDateTime.now(), "news1", 0, 0, 0, 0),
                createNewsDTO(null, "News 2", "Description 2", "Source 2", "http://news2.com", LocalDateTime.now(), "news2", 0, 0, 0, 0)
        );

        doNothing().when(newsService).addMultipleNews(anyList());

        // Act & Assert
        mockMvc.perform(post("/api/news/add-multiple")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newsList)))
                .andExpect(status().isOk())
                .andExpect(content().string("News added successfully"));

        verify(newsService, times(1)).addMultipleNews(anyList());
    }

    @Test
    void getSavedNews_Success() throws Exception {
        // Arrange
        Integer userId = 1;
        List<NewsDTO> expectedNews = Arrays.asList(
                createNewsDTO(1, "Saved News 1", "Description 1", "Source 1", "http://saved1.com", LocalDateTime.now(), "saved1", 5, 1, 0, 0)
        );

        when(savedNewsService.getSavedNewsByUserId(userId)).thenReturn(expectedNews);

        // Act & Assert
        mockMvc.perform(get("/api/news/save/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Saved News 1"));

        verify(savedNewsService, times(1)).getSavedNewsByUserId(userId);
    }

    @Test
    void getTodayNewsByIds_Success() throws Exception {
        // Arrange
        List<Integer> newsIds = Arrays.asList(1, 2, 3);
        List<NewsDTO> expectedNews = Arrays.asList(
                createNewsDTO(1, "Today News 1", "Description 1", "Source 1", "http://today1.com", LocalDateTime.now(), "today1", 3, 1, 0, 0),
                createNewsDTO(2, "Today News 2", "Description 2", "Source 2", "http://today2.com", LocalDateTime.now(), "today2", 2, 0, 0, 0)
        );

        when(newsService.getTodayNewsByIds(newsIds)).thenReturn(expectedNews);

        // Act & Assert
        mockMvc.perform(post("/api/news/today")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newsIds)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        verify(newsService, times(1)).getTodayNewsByIds(newsIds);
    }

    @Test
    void getNewsByIdsAndDateRange_Success() throws Exception {
        // Arrange
        List<Integer> newsIds = Arrays.asList(1, 2);
        String startDate = "2024-01-01T00:00:00";
        String endDate = "2024-01-31T23:59:59";
        
        List<NewsDTO> expectedNews = Arrays.asList(
                createNewsDTO(1, "Date Range News 1", "Description 1", "Source 1", "http://date1.com", LocalDateTime.now(), "date1", 4, 1, 0, 0)
        );

        when(newsService.getNewsByIdsAndDateRange(eq(newsIds), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(expectedNews);

        // Act & Assert
        mockMvc.perform(post("/api/news/date-range")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"ids\":[1,2],\"start\":\"" + startDate + "\",\"end\":\"" + endDate + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1));

        verify(newsService, times(1)).getNewsByIdsAndDateRange(eq(newsIds), any(LocalDateTime.class), any(LocalDateTime.class));
    }

    @Test
    void getAllVisibleNews_Success() throws Exception {
        // Arrange
        List<NewsDTO> expectedNews = Arrays.asList(
                createNewsDTO(1, "Visible News 1", "Description 1", "Source 1", "http://visible1.com", LocalDateTime.now(), "visible1", 5, 1, 0, 0),
                createNewsDTO(2, "Visible News 2", "Description 2", "Source 2", "http://visible2.com", LocalDateTime.now(), "visible2", 3, 2, 0, 0)
        );

        when(newsService.getAllVisibleNews()).thenReturn(expectedNews);

        // Act & Assert
        mockMvc.perform(get("/api/news/visible"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        verify(newsService, times(1)).getAllVisibleNews();
    }

    @Test
    void getVisibleNews_Success() throws Exception {
        // Arrange
        String searchString = "visible";
        List<NewsDTO> expectedNews = Arrays.asList(
                createNewsDTO(1, "Visible Search News", "Description", "Source", "http://visible.com", LocalDateTime.now(), "visible", 2, 0, 0, 0)
        );

        when(newsService.getVisibleNews(searchString)).thenReturn(expectedNews);

        // Act & Assert
        mockMvc.perform(get("/api/news/visible/search")
                .param("searchString", searchString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Visible Search News"));

        verify(newsService, times(1)).getVisibleNews(searchString);
    }

    @Test
    void getVisibleTodayNewsByIds_Success() throws Exception {
        // Arrange
        List<Integer> newsIds = Arrays.asList(1, 2);
        List<NewsDTO> expectedNews = Arrays.asList(
                createNewsDTO(1, "Visible Today News", "Description", "Source", "http://visible.com", LocalDateTime.now(), "visible", 1, 0, 0, 0)
        );

        when(newsService.getVisibleTodayNewsByIds(newsIds)).thenReturn(expectedNews);

        // Act & Assert
        mockMvc.perform(post("/api/news/visible/today")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newsIds)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1));

        verify(newsService, times(1)).getVisibleTodayNewsByIds(newsIds);
    }

    @Test
    void getVisibleNewsByIdsAndDateRange_Success() throws Exception {
        // Arrange
        List<Integer> newsIds = Arrays.asList(1, 2);
        String startDate = "2024-01-01T00:00:00";
        String endDate = "2024-01-31T23:59:59";
        
        List<NewsDTO> expectedNews = Arrays.asList(
                createNewsDTO(1, "Visible Date Range News", "Description", "Source", "http://visible.com", LocalDateTime.now(), "visible", 2, 1, 0, 0)
        );

        when(newsService.getVisibleNewsByIdsAndDateRange(eq(newsIds), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(expectedNews);

        // Act & Assert
        mockMvc.perform(post("/api/news/visible/date-range")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"ids\":[1,2],\"start\":\"" + startDate + "\",\"end\":\"" + endDate + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1));

        verify(newsService, times(1)).getVisibleNewsByIdsAndDateRange(eq(newsIds), any(LocalDateTime.class), any(LocalDateTime.class));
    }

    @Test
    void getNewsInVisibleCategories_Success() throws Exception {
        // Arrange
        List<NewsDTO> expectedNews = Arrays.asList(
                createNewsDTO(1, "Visible Category News 1", "Description 1", "Source 1", "http://category1.com", LocalDateTime.now(), "category1", 3, 1, 0, 0),
                createNewsDTO(2, "Visible Category News 2", "Description 2", "Source 2", "http://category2.com", LocalDateTime.now(), "category2", 2, 0, 0, 0)
        );

        when(newsService.getNewsInVisibleCategories()).thenReturn(expectedNews);

        // Act & Assert
        mockMvc.perform(get("/api/news/visible-categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        verify(newsService, times(1)).getNewsInVisibleCategories();
    }

    @Test
    void getNewsByIdsInVisibleCategories_Success() throws Exception {
        // Arrange
        List<Integer> newsIds = Arrays.asList(1, 2, 3);
        List<NewsDTO> expectedNews = Arrays.asList(
                createNewsDTO(1, "Visible Category News 1", "Description 1", "Source 1", "http://category1.com", LocalDateTime.now(), "category1", 4, 1, 0, 0),
                createNewsDTO(2, "Visible Category News 2", "Description 2", "Source 2", "http://category2.com", LocalDateTime.now(), "category2", 3, 2, 0, 0)
        );

        when(newsService.getNewsByIdsInVisibleCategories(newsIds)).thenReturn(expectedNews);

        // Act & Assert
        mockMvc.perform(post("/api/news/visible-categories/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newsIds)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        verify(newsService, times(1)).getNewsByIdsInVisibleCategories(newsIds);
    }

    @Test
    void getReportedNews_Success() throws Exception {
        // Arrange
        List<NewsDTO> expectedNews = Arrays.asList(
                createNewsDTO(1, "Reported News 1", "Description 1", "Source 1", "http://reported1.com", LocalDateTime.now(), "reported1", 2, 5, 3, 0),
                createNewsDTO(2, "Reported News 2", "Description 2", "Source 2", "http://reported2.com", LocalDateTime.now(), "reported2", 1, 8, 5, 0)
        );

        when(newsService.getReportedNews()).thenReturn(expectedNews);

        // Act & Assert
        mockMvc.perform(get("/api/news/reported"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].reportCount").value(3))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].reportCount").value(5));

        verify(newsService, times(1)).getReportedNews();
    }

    @Test
    void hideNews_Success() throws Exception {
        // Arrange
        Integer newsId = 1;
        NewsDTO expectedDto = createNewsDTO(1, "Hidden News", "Description", "Source", "http://hidden.com", LocalDateTime.now(), "hidden", 2, 1, 0, 1);

        when(newsService.hideNews(newsId)).thenReturn(expectedDto);

        // Act & Assert
        mockMvc.perform(put("/api/news/{id}/hide", newsId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.isHide").value(1));

        verify(newsService, times(1)).hideNews(newsId);
    }

    @Test
    void unhideNews_Success() throws Exception {
        // Arrange
        Integer newsId = 1;
        NewsDTO expectedDto = createNewsDTO(1, "Unhidden News", "Description", "Source", "http://unhidden.com", LocalDateTime.now(), "unhidden", 3, 1, 0, 0);

        when(newsService.unhideNews(newsId)).thenReturn(expectedDto);

        // Act & Assert
        mockMvc.perform(put("/api/news/{id}/unhide", newsId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.isHide").value(0));

        verify(newsService, times(1)).unhideNews(newsId);
    }

    // Helper method to create NewsDTO objects
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