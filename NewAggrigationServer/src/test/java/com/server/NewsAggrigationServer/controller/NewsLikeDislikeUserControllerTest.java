package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.dto.NewsLikeDislikeUserDTO;
import com.server.NewsAggrigationServer.model.NewsLikeDislikeUser;
import com.server.NewsAggrigationServer.service.NewsLikeDislikeUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NewsLikeDislikeUserControllerTest {

    @Mock
    private NewsLikeDislikeUserService newsLikeDislikeUserService;

    @InjectMocks
    private NewsLikeDislikeUserController newsLikeDislikeUserController;

    private NewsLikeDislikeUserDTO dto;
    private NewsLikeDislikeUser model;

    @BeforeEach
    void setUp() {
        dto = new NewsLikeDislikeUserDTO();
        dto.setId(1);
        dto.setNewsId(1);
        dto.setUserId(1);
        dto.setIsLike(1);
        dto.setCreatedAt(LocalDateTime.now());

        model = new NewsLikeDislikeUser();
        model.setId(1);
        model.setNewsId(1);
        model.setUserId(1);
        model.setIsLike(1);
        model.setCreatedAt(LocalDateTime.now());
    }

    @Test
    void testLikeOrDislike() {
        // Arrange
        when(newsLikeDislikeUserService.saveOrUpdate(any(NewsLikeDislikeUserDTO.class))).thenReturn(model);

        // Act
        NewsLikeDislikeUser result = newsLikeDislikeUserController.likeOrDislike(dto);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(1, result.getNewsId());
        assertEquals(1, result.getUserId());
        assertEquals(1, result.getIsLike());
        verify(newsLikeDislikeUserService).saveOrUpdate(dto);
    }

    @Test
    void testLikeOrDislikeWithNullInput() {
        // Arrange
        when(newsLikeDislikeUserService.saveOrUpdate(null)).thenThrow(new IllegalArgumentException("DTO cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsLikeDislikeUserController.likeOrDislike(null);
        });
        verify(newsLikeDislikeUserService).saveOrUpdate(null);
    }

    @Test
    void testLikeOrDislikeWithNullNewsId() {
        // Arrange
        dto.setNewsId(null);
        when(newsLikeDislikeUserService.saveOrUpdate(dto)).thenThrow(new IllegalArgumentException("News ID cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsLikeDislikeUserController.likeOrDislike(dto);
        });
        verify(newsLikeDislikeUserService).saveOrUpdate(dto);
    }

    @Test
    void testLikeOrDislikeWithNullUserId() {
        // Arrange
        dto.setUserId(null);
        when(newsLikeDislikeUserService.saveOrUpdate(dto)).thenThrow(new IllegalArgumentException("User ID cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsLikeDislikeUserController.likeOrDislike(dto);
        });
        verify(newsLikeDislikeUserService).saveOrUpdate(dto);
    }

    @Test
    void testLikeOrDislikeWithServiceException() {
        // Arrange
        when(newsLikeDislikeUserService.saveOrUpdate(any(NewsLikeDislikeUserDTO.class))).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            newsLikeDislikeUserController.likeOrDislike(dto);
        });
        verify(newsLikeDislikeUserService).saveOrUpdate(dto);
    }

    @Test
    void testGetReaction() {
        // Arrange
        when(newsLikeDislikeUserService.getByNewsIdAndUserId(1, 1)).thenReturn(model);

        // Act
        NewsLikeDislikeUser result = newsLikeDislikeUserController.getReaction(1, 1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(1, result.getNewsId());
        assertEquals(1, result.getUserId());
        assertEquals(1, result.getIsLike());
        verify(newsLikeDislikeUserService).getByNewsIdAndUserId(1, 1);
    }

    @Test
    void testGetReactionWithZeroNewsId() {
        // Arrange
        when(newsLikeDislikeUserService.getByNewsIdAndUserId(0, 1)).thenThrow(new IllegalArgumentException("Invalid news ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsLikeDislikeUserController.getReaction(0, 1);
        });
        verify(newsLikeDislikeUserService).getByNewsIdAndUserId(0, 1);
    }

    @Test
    void testGetReactionWithZeroUserId() {
        // Arrange
        when(newsLikeDislikeUserService.getByNewsIdAndUserId(1, 0)).thenThrow(new IllegalArgumentException("Invalid user ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsLikeDislikeUserController.getReaction(1, 0);
        });
        verify(newsLikeDislikeUserService).getByNewsIdAndUserId(1, 0);
    }

    @Test
    void testGetReactionWithNegativeNewsId() {
        // Arrange
        when(newsLikeDislikeUserService.getByNewsIdAndUserId(-1, 1)).thenThrow(new IllegalArgumentException("Invalid news ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsLikeDislikeUserController.getReaction(-1, 1);
        });
        verify(newsLikeDislikeUserService).getByNewsIdAndUserId(-1, 1);
    }

    @Test
    void testGetReactionWithNegativeUserId() {
        // Arrange
        when(newsLikeDislikeUserService.getByNewsIdAndUserId(1, -1)).thenThrow(new IllegalArgumentException("Invalid user ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsLikeDislikeUserController.getReaction(1, -1);
        });
        verify(newsLikeDislikeUserService).getByNewsIdAndUserId(1, -1);
    }

    @Test
    void testGetReactionWithServiceException() {
        // Arrange
        when(newsLikeDislikeUserService.getByNewsIdAndUserId(1, 1)).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            newsLikeDislikeUserController.getReaction(1, 1);
        });
        verify(newsLikeDislikeUserService).getByNewsIdAndUserId(1, 1);
    }

    @Test
    void testLikeOrDislikeWithZeroValues() {
        // Arrange
        dto.setNewsId(0);
        dto.setUserId(0);
        when(newsLikeDislikeUserService.saveOrUpdate(dto)).thenThrow(new IllegalArgumentException("Invalid IDs"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsLikeDislikeUserController.likeOrDislike(dto);
        });
        verify(newsLikeDislikeUserService).saveOrUpdate(dto);
    }

    @Test
    void testLikeOrDislikeWithNegativeValues() {
        // Arrange
        dto.setNewsId(-1);
        dto.setUserId(-1);
        when(newsLikeDislikeUserService.saveOrUpdate(dto)).thenThrow(new IllegalArgumentException("Invalid IDs"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsLikeDislikeUserController.likeOrDislike(dto);
        });
        verify(newsLikeDislikeUserService).saveOrUpdate(dto);
    }

    @Test
    void testLikeOrDislikeWithLargeValues() {
        // Arrange
        dto.setNewsId(Integer.MAX_VALUE);
        dto.setUserId(Integer.MAX_VALUE);
        when(newsLikeDislikeUserService.saveOrUpdate(dto)).thenReturn(model);

        // Act
        NewsLikeDislikeUser result = newsLikeDislikeUserController.likeOrDislike(dto);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(newsLikeDislikeUserService).saveOrUpdate(dto);
    }

    @Test
    void testGetReactionWithLargeValues() {
        // Arrange
        when(newsLikeDislikeUserService.getByNewsIdAndUserId(Integer.MAX_VALUE, Integer.MAX_VALUE)).thenReturn(model);

        // Act
        NewsLikeDislikeUser result = newsLikeDislikeUserController.getReaction(Integer.MAX_VALUE, Integer.MAX_VALUE);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(newsLikeDislikeUserService).getByNewsIdAndUserId(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    @Test
    void testLikeOrDislikeWithZeroId() {
        // Arrange
        dto.setId(0);
        when(newsLikeDislikeUserService.saveOrUpdate(dto)).thenReturn(model);

        // Act
        NewsLikeDislikeUser result = newsLikeDislikeUserController.likeOrDislike(dto);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(newsLikeDislikeUserService).saveOrUpdate(dto);
    }

    @Test
    void testLikeOrDislikeWithNegativeId() {
        // Arrange
        dto.setId(-1);
        when(newsLikeDislikeUserService.saveOrUpdate(dto)).thenReturn(model);

        // Act
        NewsLikeDislikeUser result = newsLikeDislikeUserController.likeOrDislike(dto);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(newsLikeDislikeUserService).saveOrUpdate(dto);
    }

    @Test
    void testLikeOrDislikeWithLargeId() {
        // Arrange
        dto.setId(Integer.MAX_VALUE);
        when(newsLikeDislikeUserService.saveOrUpdate(dto)).thenReturn(model);

        // Act
        NewsLikeDislikeUser result = newsLikeDislikeUserController.likeOrDislike(dto);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(newsLikeDislikeUserService).saveOrUpdate(dto);
    }

    @Test
    void testLikeOrDislikeWithZeroIsLike() {
        // Arrange
        dto.setIsLike(0);
        when(newsLikeDislikeUserService.saveOrUpdate(dto)).thenReturn(model);

        // Act
        NewsLikeDislikeUser result = newsLikeDislikeUserController.likeOrDislike(dto);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getIsLike());
        verify(newsLikeDislikeUserService).saveOrUpdate(dto);
    }

    @Test
    void testLikeOrDislikeWithNegativeIsLike() {
        // Arrange
        dto.setIsLike(-1);
        when(newsLikeDislikeUserService.saveOrUpdate(dto)).thenReturn(model);

        // Act
        NewsLikeDislikeUser result = newsLikeDislikeUserController.likeOrDislike(dto);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getIsLike());
        verify(newsLikeDislikeUserService).saveOrUpdate(dto);
    }

    @Test
    void testLikeOrDislikeWithLargeIsLike() {
        // Arrange
        dto.setIsLike(Integer.MAX_VALUE);
        when(newsLikeDislikeUserService.saveOrUpdate(dto)).thenReturn(model);

        // Act
        NewsLikeDislikeUser result = newsLikeDislikeUserController.likeOrDislike(dto);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getIsLike());
        verify(newsLikeDislikeUserService).saveOrUpdate(dto);
    }

    @Test
    void testLikeOrDislikeWithNullIsLike() {
        // Arrange
        dto.setIsLike(null);
        when(newsLikeDislikeUserService.saveOrUpdate(dto)).thenThrow(new IllegalArgumentException("IsLike cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsLikeDislikeUserController.likeOrDislike(dto);
        });
        verify(newsLikeDislikeUserService).saveOrUpdate(dto);
    }

    @Test
    void testGetReactionWithNullReturn() {
        // Arrange
        when(newsLikeDislikeUserService.getByNewsIdAndUserId(1, 1)).thenReturn(null);

        // Act
        NewsLikeDislikeUser result = newsLikeDislikeUserController.getReaction(1, 1);

        // Assert
        assertNull(result);
        verify(newsLikeDislikeUserService).getByNewsIdAndUserId(1, 1);
    }
} 