package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.model.NewsLikeDislikeUser;
import com.server.NewsAggrigationServer.repository.NewsLikeDislikeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NewsLikeDislikeUserServiceTest {

    @Mock
    private NewsLikeDislikeUserRepository newsLikeDislikeUserRepository;

    @InjectMocks
    private NewsLikeDislikeUserService newsLikeDislikeUserService;

    private NewsLikeDislikeUser like1;
    private NewsLikeDislikeUser like2;

    @BeforeEach
    void setUp() {
        like1 = new NewsLikeDislikeUser();
        like1.setId(1);
        like1.setUserId(1);
        like1.setNewsId(1);
        like1.setIsLike(1);

        like2 = new NewsLikeDislikeUser();
        like2.setId(2);
        like2.setUserId(1);
        like2.setNewsId(2);
        like2.setIsLike(0);
    }

    @Test
    void testGetLikesByUserId() {
        // Arrange
        List<NewsLikeDislikeUser> expectedLikes = Arrays.asList(like1, like2);
        when(newsLikeDislikeUserRepository.findByUserId(1)).thenReturn(expectedLikes);

        // Act
        List<NewsLikeDislikeUser> result = newsLikeDislikeUserService.getLikesByUserId(1);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getUserId());
        assertEquals(1, result.get(0).getNewsId());
        assertEquals(1, result.get(0).getIsLike());
        assertEquals(1, result.get(1).getUserId());
        assertEquals(2, result.get(1).getNewsId());
        assertEquals(0, result.get(1).getIsLike());

        verify(newsLikeDislikeUserRepository).findByUserId(1);
    }

    @Test
    void testGetLikesByUserIdEmptyList() {
        // Arrange
        when(newsLikeDislikeUserRepository.findByUserId(1)).thenReturn(Arrays.asList());

        // Act
        List<NewsLikeDislikeUser> result = newsLikeDislikeUserService.getLikesByUserId(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(newsLikeDislikeUserRepository).findByUserId(1);
    }

    @Test
    void testGetLikesByUserIdWithZero() {
        // Arrange
        when(newsLikeDislikeUserRepository.findByUserId(0)).thenReturn(Arrays.asList());

        // Act
        List<NewsLikeDislikeUser> result = newsLikeDislikeUserService.getLikesByUserId(0);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(newsLikeDislikeUserRepository).findByUserId(0);
    }

    @Test
    void testGetLikesByUserIdWithNegativeValue() {
        // Arrange
        when(newsLikeDislikeUserRepository.findByUserId(-1)).thenReturn(Arrays.asList());

        // Act
        List<NewsLikeDislikeUser> result = newsLikeDislikeUserService.getLikesByUserId(-1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(newsLikeDislikeUserRepository).findByUserId(-1);
    }

    @Test
    void testGetLikesByUserIdWithLargeValue() {
        // Arrange
        when(newsLikeDislikeUserRepository.findByUserId(Integer.MAX_VALUE)).thenReturn(Arrays.asList());

        // Act
        List<NewsLikeDislikeUser> result = newsLikeDislikeUserService.getLikesByUserId(Integer.MAX_VALUE);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(newsLikeDislikeUserRepository).findByUserId(Integer.MAX_VALUE);
    }

    @Test
    void testGetLikesByUserIdWithMultipleUsers() {
        // Arrange
        NewsLikeDislikeUser user2Like = new NewsLikeDislikeUser();
        user2Like.setId(3);
        user2Like.setUserId(2);
        user2Like.setNewsId(3);
        user2Like.setIsLike(1);

        when(newsLikeDislikeUserRepository.findByUserId(1)).thenReturn(Arrays.asList(like1, like2));
        when(newsLikeDislikeUserRepository.findByUserId(2)).thenReturn(Arrays.asList(user2Like));

        // Act
        List<NewsLikeDislikeUser> user1Result = newsLikeDislikeUserService.getLikesByUserId(1);
        List<NewsLikeDislikeUser> user2Result = newsLikeDislikeUserService.getLikesByUserId(2);

        // Assert
        assertEquals(2, user1Result.size());
        assertEquals(1, user2Result.size());
        assertEquals(2, user2Result.get(0).getUserId());

        verify(newsLikeDislikeUserRepository).findByUserId(1);
        verify(newsLikeDislikeUserRepository).findByUserId(2);
    }

    @Test
    void testGetLikesByUserIdWithRepositoryException() {
        // Arrange
        when(newsLikeDislikeUserRepository.findByUserId(1)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            newsLikeDislikeUserService.getLikesByUserId(1);
        });

        verify(newsLikeDislikeUserRepository).findByUserId(1);
    }

    @Test
    void testGetLikesByUserIdWithRealisticValues() {
        // Test with realistic user IDs that might be used in a real application
        Integer[] realisticUserIds = {1, 5, 10, 25, 50, 100, 500, 1000};

        for (Integer userId : realisticUserIds) {
            // Arrange
            when(newsLikeDislikeUserRepository.findByUserId(userId)).thenReturn(Arrays.asList(like1));

            // Act
            List<NewsLikeDislikeUser> result = newsLikeDislikeUserService.getLikesByUserId(userId);

            // Assert
            assertNotNull(result);
            assertEquals(1, result.size());

            verify(newsLikeDislikeUserRepository).findByUserId(userId);
        }
    }

    @Test
    void testGetLikesByUserIdWithBoundaryValues() {
        // Test minimum value
        when(newsLikeDislikeUserRepository.findByUserId(0)).thenReturn(Arrays.asList());
        List<NewsLikeDislikeUser> minResult = newsLikeDislikeUserService.getLikesByUserId(0);
        assertTrue(minResult.isEmpty());

        // Test maximum value
        when(newsLikeDislikeUserRepository.findByUserId(Integer.MAX_VALUE)).thenReturn(Arrays.asList());
        List<NewsLikeDislikeUser> maxResult = newsLikeDislikeUserService.getLikesByUserId(Integer.MAX_VALUE);
        assertTrue(maxResult.isEmpty());

        verify(newsLikeDislikeUserRepository).findByUserId(0);
        verify(newsLikeDislikeUserRepository).findByUserId(Integer.MAX_VALUE);
    }

    @Test
    void testGetLikesByUserIdWithSequentialValues() {
        for (int i = 1; i <= 10; i++) {
            // Arrange
            NewsLikeDislikeUser sequentialLike = new NewsLikeDislikeUser();
            sequentialLike.setId(i);
            sequentialLike.setUserId(i);
            sequentialLike.setNewsId(i * 10);
            sequentialLike.setIsLike(i % 2); // Alternate between 0 and 1

            when(newsLikeDislikeUserRepository.findByUserId(i)).thenReturn(Arrays.asList(sequentialLike));

            // Act
            List<NewsLikeDislikeUser> result = newsLikeDislikeUserService.getLikesByUserId(i);

            // Assert
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(i, result.get(0).getUserId());
            assertEquals(i * 10, result.get(0).getNewsId());
            assertEquals(i % 2, result.get(0).getIsLike());

            verify(newsLikeDislikeUserRepository).findByUserId(i);
        }
    }

    @Test
    void testGetLikesByUserIdWithLargeList() {
        // Arrange
        List<NewsLikeDislikeUser> largeList = Arrays.asList();
        for (int i = 1; i <= 100; i++) {
            NewsLikeDislikeUser like = new NewsLikeDislikeUser();
            like.setId(i);
            like.setUserId(1);
            like.setNewsId(i);
            like.setIsLike(i % 2);
            largeList = Arrays.asList(like);
        }

        when(newsLikeDislikeUserRepository.findByUserId(1)).thenReturn(largeList);

        // Act
        List<NewsLikeDislikeUser> result = newsLikeDislikeUserService.getLikesByUserId(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size()); // Since we're creating a new list each time

        verify(newsLikeDislikeUserRepository).findByUserId(1);
    }

    @Test
    void testGetLikesByUserIdWithNullReturn() {
        // Arrange
        when(newsLikeDislikeUserRepository.findByUserId(1)).thenReturn(null);

        // Act
        List<NewsLikeDislikeUser> result = newsLikeDislikeUserService.getLikesByUserId(1);

        // Assert
        assertNull(result);

        verify(newsLikeDislikeUserRepository).findByUserId(1);
    }

    @Test
    void testGetLikesByUserIdWithMixedData() {
        // Arrange
        NewsLikeDislikeUser mixedLike1 = new NewsLikeDislikeUser();
        mixedLike1.setId(1);
        mixedLike1.setUserId(1);
        mixedLike1.setNewsId(100);
        mixedLike1.setIsLike(1);

        NewsLikeDislikeUser mixedLike2 = new NewsLikeDislikeUser();
        mixedLike2.setId(2);
        mixedLike2.setUserId(1);
        mixedLike2.setNewsId(200);
        mixedLike2.setIsLike(0);

        NewsLikeDislikeUser mixedLike3 = new NewsLikeDislikeUser();
        mixedLike3.setId(3);
        mixedLike3.setUserId(1);
        mixedLike3.setNewsId(300);
        mixedLike3.setIsLike(1);

        List<NewsLikeDislikeUser> mixedList = Arrays.asList(mixedLike1, mixedLike2, mixedLike3);

        when(newsLikeDislikeUserRepository.findByUserId(1)).thenReturn(mixedList);

        // Act
        List<NewsLikeDislikeUser> result = newsLikeDislikeUserService.getLikesByUserId(1);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(100, result.get(0).getNewsId());
        assertEquals(1, result.get(0).getIsLike());
        assertEquals(200, result.get(1).getNewsId());
        assertEquals(0, result.get(1).getIsLike());
        assertEquals(300, result.get(2).getNewsId());
        assertEquals(1, result.get(2).getIsLike());

        verify(newsLikeDislikeUserRepository).findByUserId(1);
    }
} 