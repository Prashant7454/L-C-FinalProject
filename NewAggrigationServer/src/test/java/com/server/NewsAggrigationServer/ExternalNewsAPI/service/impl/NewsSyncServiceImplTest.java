package com.server.NewsAggrigationServer.ExternalNewsAPI.service.impl;

import com.server.NewsAggrigationServer.ExternalNewsAPI.service.NewsAPIClient;
import com.server.NewsAggrigationServer.ExternalNewsAPI.service.TheNewsAPIClient;
import com.server.NewsAggrigationServer.model.News;
import com.server.NewsAggrigationServer.repository.NewsRepository;
import com.server.NewsAggrigationServer.service.CategoryAssignmentService;
import com.server.NewsAggrigationServer.service.NewsNotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

class NewsSyncServiceImplTest {
    @Mock
    private NewsAPIClient newsAPIClient;
    @Mock
    private TheNewsAPIClient theNewsAPIClient;
    @Mock
    private NewsRepository newsRepository;
    @Mock
    private CategoryAssignmentService categoryAssignmentService;
    @Mock
    private NewsNotificationService newsNotificationService;
    @InjectMocks
    private NewsSyncServiceImpl newsSyncServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void syncAllFeeds_fetchesAndSavesNews() {
        News news = new News();
        news.setUrl("url");
        when(newsAPIClient.fetchNews()).thenReturn(List.of(news));
        when(theNewsAPIClient.fetchNews()).thenReturn(List.of(news));
        when(newsRepository.existsByUrl(anyString())).thenReturn(false);
        when(newsRepository.saveAll(anyList())).thenReturn(List.of(news));
        newsSyncServiceImpl.syncAllFeeds();
        verify(newsRepository, atLeastOnce()).saveAll(anyList());
        verify(categoryAssignmentService, atLeastOnce()).assignCategoriesToNewsList(anyList());
        verify(newsNotificationService, atLeastOnce()).sendNotificationsForNewsList(anyList());
    }

    @Test
    void syncAllFeeds_skipsDuplicates() {
        News news = new News();
        news.setUrl("url");
        when(newsAPIClient.fetchNews()).thenReturn(List.of(news));
        when(theNewsAPIClient.fetchNews()).thenReturn(List.of(news));
        when(newsRepository.existsByUrl(anyString())).thenReturn(true);
        newsSyncServiceImpl.syncAllFeeds();
        verify(newsRepository, never()).saveAll(anyList());
    }

    @Test
    void syncAllFeeds_handlesException() {
        when(newsAPIClient.fetchNews()).thenThrow(new RuntimeException("fail"));
        newsSyncServiceImpl.syncAllFeeds();
        verify(newsRepository, never()).saveAll(anyList());
    }
}
