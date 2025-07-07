package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.NewsDTO;
import com.server.NewsAggrigationServer.dto.NewsLikeDislikeUserDTO;
import com.server.NewsAggrigationServer.model.NewsLikeDislikeUser;
import com.server.NewsAggrigationServer.repository.NewsLikeDislikeUserRepository;
import com.server.NewsAggrigationServer.service.impl.NewsLikeDislikeUserServiceImpl;
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
public class NewsLikeDislikeUserServiceImplTest {

    @Mock
    private NewsLikeDislikeUserRepository newsLikeDislikeUserRepository;
    @Mock
    private NewsService newsService;

    @InjectMocks
    private NewsLikeDislikeUserServiceImpl newsLikeDislikeUserService;

    private NewsLikeDislikeUserDTO dto;
    private NewsLikeDislikeUser model;

    @BeforeEach
    void setUp() {
        dto = new NewsLikeDislikeUserDTO();
        dto.setId(1);
        dto.setNewsId(2);
        dto.setUserId(3);
        dto.setLiked(1);
        dto.setDisliked(0);

        model = new NewsLikeDislikeUser();
        model.setId(1);
        model.setNewsId(2);
        model.setUserId(3);
        model.setLiked(1);
        model.setDisliked(0);
    }

    @Test
    void saveOrUpdate_Success() {
        when(newsLikeDislikeUserRepository.save(any(NewsLikeDislikeUser.class))).thenReturn(model);
        NewsLikeDislikeUser result = newsLikeDislikeUserService.saveOrUpdate(dto);
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(2, result.getNewsId());
        assertEquals(3, result.getUserId());
        assertEquals(1, result.getLiked());
        assertEquals(0, result.getDisliked());
        verify(newsLikeDislikeUserRepository, times(1)).save(any(NewsLikeDislikeUser.class));
    }

    @Test
    void getByNewsIdAndUserId_Success() {
        when(newsLikeDislikeUserRepository.findByNewsIdAndUserId(2, 3)).thenReturn(model);
        NewsLikeDislikeUser result = newsLikeDislikeUserService.getByNewsIdAndUserId(2, 3);
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(newsLikeDislikeUserRepository, times(1)).findByNewsIdAndUserId(2, 3);
    }

    @Test
    void getLikedNewsByUserId_Success() {
        NewsLikeDislikeUser liked = new NewsLikeDislikeUser();
        liked.setNewsId(10);
        List<NewsLikeDislikeUser> likedList = Arrays.asList(liked);
        List<Integer> newsIds = Arrays.asList(10);
        List<NewsDTO> newsDTOs = Arrays.asList(new NewsDTO());
        when(newsLikeDislikeUserRepository.findByUserIdAndLiked(3, 1)).thenReturn(likedList);
        when(newsService.getNewsByIds(newsIds)).thenReturn(newsDTOs);
        List<NewsDTO> result = newsLikeDislikeUserService.getLikedNewsByUserId(3);
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(newsLikeDislikeUserRepository, times(1)).findByUserIdAndLiked(3, 1);
        verify(newsService, times(1)).getNewsByIds(newsIds);
    }

    @Test
    void getLikedNewsByUserId_Empty() {
        when(newsLikeDislikeUserRepository.findByUserIdAndLiked(3, 1)).thenReturn(Collections.emptyList());
        List<NewsDTO> result = newsLikeDislikeUserService.getLikedNewsByUserId(3);
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(newsLikeDislikeUserRepository, times(1)).findByUserIdAndLiked(3, 1);
        verify(newsService, never()).getNewsByIds(anyList());
    }

    @Test
    void getDislikedNewsByUserId_Success() {
        NewsLikeDislikeUser disliked = new NewsLikeDislikeUser();
        disliked.setNewsId(20);
        List<NewsLikeDislikeUser> dislikedList = Arrays.asList(disliked);
        List<Integer> newsIds = Arrays.asList(20);
        List<NewsDTO> newsDTOs = Arrays.asList(new NewsDTO());
        when(newsLikeDislikeUserRepository.findByUserIdAndDisliked(3, 1)).thenReturn(dislikedList);
        when(newsService.getNewsByIds(newsIds)).thenReturn(newsDTOs);
        List<NewsDTO> result = newsLikeDislikeUserService.getDislikedNewsByUserId(3);
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(newsLikeDislikeUserRepository, times(1)).findByUserIdAndDisliked(3, 1);
        verify(newsService, times(1)).getNewsByIds(newsIds);
    }

    @Test
    void getDislikedNewsByUserId_Empty() {
        when(newsLikeDislikeUserRepository.findByUserIdAndDisliked(3, 1)).thenReturn(Collections.emptyList());
        List<NewsDTO> result = newsLikeDislikeUserService.getDislikedNewsByUserId(3);
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(newsLikeDislikeUserRepository, times(1)).findByUserIdAndDisliked(3, 1);
        verify(newsService, never()).getNewsByIds(anyList());
    }
} 