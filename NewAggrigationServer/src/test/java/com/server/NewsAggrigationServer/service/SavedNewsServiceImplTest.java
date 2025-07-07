package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.NewsDTO;
import com.server.NewsAggrigationServer.dto.SavedNewsDTO;
import com.server.NewsAggrigationServer.model.SavedNews;
import com.server.NewsAggrigationServer.repository.SavedNewsRepository;
import com.server.NewsAggrigationServer.service.impl.SavedNewsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SavedNewsServiceImplTest {

    @InjectMocks
    private SavedNewsServiceImpl savedNewsService;

    @Mock
    private SavedNewsRepository savedNewsRepository;

    @Mock
    private NewsService newsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveNews_Success() {
        SavedNewsDTO dto = new SavedNewsDTO();
        dto.setUserId(1);
        dto.setNewsId(2);

        when(savedNewsRepository.existsByUserIdAndNewsId(1, 2)).thenReturn(false);
        SavedNews saved = new SavedNews(2, 1);
        saved.setId(10);
        when(savedNewsRepository.save(any(SavedNews.class))).thenReturn(saved);

        SavedNewsDTO result = savedNewsService.saveNews(dto);
        assertEquals(10, result.getId());
        assertEquals(1, result.getUserId());
        assertEquals(2, result.getNewsId());
    }

    @Test
    void testSaveNews_AlreadySaved() {
        SavedNewsDTO dto = new SavedNewsDTO();
        dto.setUserId(1);
        dto.setNewsId(2);
        when(savedNewsRepository.existsByUserIdAndNewsId(1, 2)).thenReturn(true);
        assertThrows(RuntimeException.class, () -> savedNewsService.saveNews(dto));
    }

    @Test
    void testGetSavedNewsByUserId() {
        SavedNews saved1 = new SavedNews(2, 1);
        saved1.setId(10);
        SavedNews saved2 = new SavedNews(3, 1);
        saved2.setId(11);
        when(savedNewsRepository.findByUserId(1)).thenReturn(Arrays.asList(saved1, saved2));
        NewsDTO news1 = new NewsDTO();
        news1.setId(2);
        NewsDTO news2 = new NewsDTO();
        news2.setId(3);
        when(newsService.getNewsByIds(Arrays.asList(2, 3))).thenReturn(Arrays.asList(news1, news2));
        List<NewsDTO> result = savedNewsService.getSavedNewsByUserId(1);
        assertEquals(2, result.size());
        assertEquals(2, result.get(0).getId());
        assertEquals(3, result.get(1).getId());
    }

    @Test
    void testDeleteSavedNews_Success() {
        when(savedNewsRepository.existsByUserIdAndNewsId(1, 2)).thenReturn(true);
        doNothing().when(savedNewsRepository).deleteByUserIdAndNewsId(1, 2);
        assertDoesNotThrow(() -> savedNewsService.deleteSavedNews(1, 2));
        verify(savedNewsRepository, times(1)).deleteByUserIdAndNewsId(1, 2);
    }

    @Test
    void testDeleteSavedNews_NotFound() {
        when(savedNewsRepository.existsByUserIdAndNewsId(1, 2)).thenReturn(false);
        assertThrows(RuntimeException.class, () -> savedNewsService.deleteSavedNews(1, 2));
    }
}
