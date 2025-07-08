package com.server.NewsAggrigationServer.ExternalNewsAPI.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.NewsAggrigationServer.dto.ExternalNewsSourceDTO;
import com.server.NewsAggrigationServer.model.News;
import com.server.NewsAggrigationServer.service.ExternalNewsSourceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class NewsAPIClientImplTest {
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ExternalNewsSourceService externalNewsSourceService;
    @InjectMocks
    private NewsAPIClientImpl newsAPIClientImpl;

    private ExternalNewsSourceDTO sourceDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sourceDTO = new ExternalNewsSourceDTO();
        sourceDTO.setBaseUrl("http://test.com");
        sourceDTO.setApiKey("key");
        sourceDTO.setStatus(1);
        sourceDTO.setLastAccessed(LocalDateTime.now());
    }

    @Test
    void fetchNews_returnsNewsList_whenArticlesPresent() throws Exception {
        when(externalNewsSourceService.getNewsSourceBySourceName(anyString())).thenReturn(List.of(sourceDTO));
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"articles\":[{\"title\":\"t\",\"description\":\"d\",\"url\":\"u\",\"publishedAt\":\"2024-01-01T00:00:00Z\",\"name\":\"src\"}]}";
        JsonNode node = mapper.readTree(json);
        when(restTemplate.exchange(any(), eq(HttpMethod.GET), any(HttpEntity.class), eq(JsonNode.class)))
                .thenReturn(ResponseEntity.ok(node));
        List<News> result = newsAPIClientImpl.fetchNews();
        assertEquals(1, result.size());
        assertEquals("t", result.get(0).getTitle());
        verify(externalNewsSourceService, atLeastOnce()).save(any());
    }

    @Test
    void fetchNews_returnsEmptyList_whenNoArticles() throws Exception {
        when(externalNewsSourceService.getNewsSourceBySourceName(anyString())).thenReturn(List.of(sourceDTO));
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"articles\":null}";
        JsonNode node = mapper.readTree(json);
        when(restTemplate.exchange(any(), eq(HttpMethod.GET), any(HttpEntity.class), eq(JsonNode.class)))
                .thenReturn(ResponseEntity.ok(node));
        List<News> result = newsAPIClientImpl.fetchNews();
        assertTrue(result.isEmpty());
        verify(externalNewsSourceService, atLeastOnce()).save(any());
    }

    @Test
    void fetchNews_handlesException() {
        when(externalNewsSourceService.getNewsSourceBySourceName(anyString())).thenReturn(List.of(sourceDTO));
        when(restTemplate.exchange(any(), eq(HttpMethod.GET), any(HttpEntity.class), eq(JsonNode.class)))
                .thenThrow(new RuntimeException("fail"));
        assertThrows(RuntimeException.class, () -> newsAPIClientImpl.fetchNews());
    }
}
