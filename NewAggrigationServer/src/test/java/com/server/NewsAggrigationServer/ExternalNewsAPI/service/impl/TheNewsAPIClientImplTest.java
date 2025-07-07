package com.server.NewsAggrigationServer.ExternalNewsAPI.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.NewsAggrigationServer.dto.ExternalNewsSourceDTO;
import com.server.NewsAggrigationServer.model.Category;
import com.server.NewsAggrigationServer.model.News;
import com.server.NewsAggrigationServer.model.NewsCategory;
import com.server.NewsAggrigationServer.repository.CategoryRepository;
import com.server.NewsAggrigationServer.repository.NewsCategoryRepository;
import com.server.NewsAggrigationServer.repository.NewsRepository;
import com.server.NewsAggrigationServer.service.ExternalNewsSourceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class TheNewsAPIClientImplTest {
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ExternalNewsSourceService externalNewsSourceService;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private NewsRepository newsRepository;
    @Mock
    private NewsCategoryRepository newsCategoryRepository;
    @InjectMocks
    private TheNewsAPIClientImpl theNewsAPIClientImpl;

    private ExternalNewsSourceDTO sourceDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sourceDTO = new ExternalNewsSourceDTO();
        sourceDTO.setBaseUrl("http://test.com");
        sourceDTO.setApiKey("key");
        sourceDTO.setStatus(true);
        sourceDTO.setLastAccessed(LocalDateTime.now());
    }

    @Test
    void fetchNews_returnsNewsList_whenDataPresent() throws Exception {
        when(externalNewsSourceService.getNewsSourceBySourceName(anyString())).thenReturn(List.of(sourceDTO));
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"data\":[{\"title\":\"t\",\"description\":\"d\",\"url\":\"u\",\"published_at\":\"2024-01-01T00:00:00Z\",\"source\":\"src\",\"categories\":[\"cat1\"]}]}";
        JsonNode node = mapper.readTree(json);
        when(restTemplate.exchange(any(), eq(HttpMethod.GET), any(HttpEntity.class), eq(JsonNode.class)))
                .thenReturn(ResponseEntity.ok(node));
        when(newsRepository.save(any(News.class))).thenAnswer(i -> i.getArgument(0));
        when(categoryRepository.findByName(anyString())).thenReturn(null);
        when(categoryRepository.save(any(Category.class))).thenAnswer(i -> {
            Category c = i.getArgument(0);
            c.setId(1);
            return c;
        });
        when(newsCategoryRepository.save(any(NewsCategory.class))).thenAnswer(i -> i.getArgument(0));
        List<News> result = theNewsAPIClientImpl.fetchNews();
        assertEquals(1, result.size());
        assertEquals("t", result.get(0).getTitle());
        verify(externalNewsSourceService, atLeastOnce()).save(any());
    }

    @Test
    void fetchNews_returnsEmptyList_whenNoData() throws Exception {
        when(externalNewsSourceService.getNewsSourceBySourceName(anyString())).thenReturn(List.of(sourceDTO));
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"data\":null}";
        JsonNode node = mapper.readTree(json);
        when(restTemplate.exchange(any(), eq(HttpMethod.GET), any(HttpEntity.class), eq(JsonNode.class)))
                .thenReturn(ResponseEntity.ok(node));
        List<News> result = theNewsAPIClientImpl.fetchNews();
        assertTrue(result.isEmpty());
        verify(externalNewsSourceService, atLeastOnce()).save(any());
    }

    @Test
    void fetchNews_handlesException() {
        when(externalNewsSourceService.getNewsSourceBySourceName(anyString())).thenReturn(List.of(sourceDTO));
        when(restTemplate.exchange(any(), eq(HttpMethod.GET), any(HttpEntity.class), eq(JsonNode.class)))
                .thenThrow(new RuntimeException("fail"));
        assertThrows(RuntimeException.class, () -> theNewsAPIClientImpl.fetchNews());
    }
} 