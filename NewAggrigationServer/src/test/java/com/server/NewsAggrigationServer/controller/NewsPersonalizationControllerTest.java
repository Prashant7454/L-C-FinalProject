package com.server.NewsAggrigationServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.NewsAggrigationServer.dto.NewsDTO;
import com.server.NewsAggrigationServer.service.NewsPersonalizationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class NewsPersonalizationControllerTest {

    @Mock
    private NewsPersonalizationService personalizationService;

    @InjectMocks
    private NewsPersonalizationController controller;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getPersonalizedNews_Success() throws Exception {
        List<NewsDTO> newsList = Arrays.asList(new NewsDTO(), new NewsDTO());
        when(personalizationService.getPersonalizedNews(eq(1), eq(10))).thenReturn(newsList);
        mockMvc.perform(get("/api/personalization/user/{userId}", 1)
                .param("limit", "10"))
                .andExpect(status().isOk());
        verify(personalizationService, times(1)).getPersonalizedNews(1, 10);
    }

    @Test
    void getPersonalizedNewsPaginated_Success() throws Exception {
        List<NewsDTO> newsList = Arrays.asList(new NewsDTO(), new NewsDTO());
        when(personalizationService.getPersonalizedNewsPaginated(eq(1), eq(0), eq(10))).thenReturn(newsList);
        mockMvc.perform(get("/api/personalization/user/{userId}/page", 1)
                .param("page", "0").param("size", "10"))
                .andExpect(status().isOk());
        verify(personalizationService, times(1)).getPersonalizedNewsPaginated(1, 0, 10);
    }

    @Test
    void getUserInterestScore_Success() throws Exception {
        when(personalizationService.calculateUserInterestScore(eq(1), eq(2))).thenReturn(0.75);
        mockMvc.perform(get("/api/personalization/user/{userId}/news/{newsId}/score", 1, 2))
                .andExpect(status().isOk())
                .andExpect(content().string("0.75"));
        verify(personalizationService, times(1)).calculateUserInterestScore(1, 2);
    }

    @Test
    void getUserTopInterestCategories_Success() throws Exception {
        List<Integer> categories = Arrays.asList(1, 2, 3);
        when(personalizationService.getUserTopInterestCategories(eq(1), eq(5))).thenReturn(categories);
        mockMvc.perform(get("/api/personalization/user/{userId}/top-categories", 1)
                .param("limit", "5"))
                .andExpect(status().isOk());
        verify(personalizationService, times(1)).getUserTopInterestCategories(1, 5);
    }

    @Test
    void recordArticleRead_Success() throws Exception {
        doNothing().when(personalizationService).recordArticleRead(eq(1), eq(2));
        mockMvc.perform(post("/api/personalization/user/{userId}/news/{newsId}/read", 1, 2))
                .andExpect(status().isOk())
                .andExpect(content().string("Article read recorded successfully"));
        verify(personalizationService, times(1)).recordArticleRead(1, 2);
    }
}
