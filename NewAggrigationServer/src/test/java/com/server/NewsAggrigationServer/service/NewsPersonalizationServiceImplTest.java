package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.NewsDTO;
import com.server.NewsAggrigationServer.model.NotificationConfiguration;
import com.server.NewsAggrigationServer.model.UserReadingHistory;
import com.server.NewsAggrigationServer.repository.NotificationConfigurationRepository;
import com.server.NewsAggrigationServer.repository.UserReadingHistoryRepository;
import com.server.NewsAggrigationServer.service.impl.NewsPersonalizationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NewsPersonalizationServiceImplTest {

    @Mock
    private NewsService newsService;
    @Mock
    private NotificationConfigurationRepository notificationConfigRepository;
    @Mock
    private UserReadingHistoryRepository readingHistoryRepository;
    @Mock
    private NewsLikeDislikeUserService likeDislikeService;
    @Mock
    private SavedNewsService savedNewsService;
    @Mock
    private NewsCategoryService newsCategoryService;
    @Mock
    private CategoryService categoryService;
    @Mock
    private CategoryKeywordService categoryKeywordService;

    @InjectMocks
    private NewsPersonalizationServiceImpl service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void recordArticleRead_SavesIfNotExists() {
        when(readingHistoryRepository.existsByUserIdAndNewsId(1, 2)).thenReturn(false);
        service.recordArticleRead(1, 2);
        verify(readingHistoryRepository, times(1)).save(any(UserReadingHistory.class));
    }

    @Test
    void recordArticleRead_DoesNotSaveIfExists() {
        when(readingHistoryRepository.existsByUserIdAndNewsId(1, 2)).thenReturn(true);
        service.recordArticleRead(1, 2);
        verify(readingHistoryRepository, never()).save(any(UserReadingHistory.class));
    }

    @Test
    void getUserTopInterestCategories_ReturnsList() {
        NotificationConfiguration config = new NotificationConfiguration();
        config.setCategoryId(1); config.setEnabled(true);
        when(notificationConfigRepository.findByUserId(1)).thenReturn(Collections.singletonList(config));
        when(readingHistoryRepository.findByUserIdOrderByReadAtDesc(1)).thenReturn(Collections.emptyList());
        when(likeDislikeService.getLikedNewsByUserId(1)).thenReturn(Collections.emptyList());
        when(savedNewsService.getSavedNewsByUserId(1)).thenReturn(Collections.emptyList());
        List<Integer> result = service.getUserTopInterestCategories(1, 5);
        assertNotNull(result);
    }
}
