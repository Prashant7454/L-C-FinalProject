package com.server.NewsAggrigationServer.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NewsLikeDislikeUserTest {
    private NewsLikeDislikeUser likeDislikeUser;

    @BeforeEach
    void setUp() {
        likeDislikeUser = new NewsLikeDislikeUser();
    }

    @Test
    void testNewsLikeDislikeUserCreation() {
        assertNotNull(likeDislikeUser);
    }

    @Test
    void testIdGetterAndSetter() {
        Integer expectedId = 1;
        likeDislikeUser.setId(expectedId);
        assertEquals(expectedId, likeDislikeUser.getId());
    }

    @Test
    void testNewsIdGetterAndSetter() {
        Integer expectedNewsId = 1;
        likeDislikeUser.setNewsId(expectedNewsId);
        assertEquals(expectedNewsId, likeDislikeUser.getNewsId());
    }

    @Test
    void testUserIdGetterAndSetter() {
        Integer expectedUserId = 1;
        likeDislikeUser.setUserId(expectedUserId);
        assertEquals(expectedUserId, likeDislikeUser.getUserId());
    }

    @Test
    void testIsLikeGetterAndSetter() {
        Integer expectedIsLike = 1;
        likeDislikeUser.setIsLike(expectedIsLike);
        assertEquals(expectedIsLike, likeDislikeUser.getIsLike());
    }

    @Test
    void testNewsLikeDislikeUserWithAllFields() {
        Integer id = 1;
        Integer newsId = 2;
        Integer userId = 3;
        Integer isLike = 1;

        likeDislikeUser.setId(id);
        likeDislikeUser.setNewsId(newsId);
        likeDislikeUser.setUserId(userId);
        likeDislikeUser.setIsLike(isLike);

        assertEquals(id, likeDislikeUser.getId());
        assertEquals(newsId, likeDislikeUser.getNewsId());
        assertEquals(userId, likeDislikeUser.getUserId());
        assertEquals(isLike, likeDislikeUser.getIsLike());
    }

    @Test
    void testNewsLikeDislikeUserWithNullValues() {
        likeDislikeUser.setId(null);
        likeDislikeUser.setNewsId(null);
        likeDislikeUser.setUserId(null);
        likeDislikeUser.setIsLike(null);

        assertNull(likeDislikeUser.getId());
        assertNull(likeDislikeUser.getNewsId());
        assertNull(likeDislikeUser.getUserId());
        assertNull(likeDislikeUser.getIsLike());
    }

    @Test
    void testNewsLikeDislikeUserWithZeroValues() {
        likeDislikeUser.setId(0);
        likeDislikeUser.setNewsId(0);
        likeDislikeUser.setUserId(0);
        likeDislikeUser.setIsLike(0);

        assertEquals(0, likeDislikeUser.getId());
        assertEquals(0, likeDislikeUser.getNewsId());
        assertEquals(0, likeDislikeUser.getUserId());
        assertEquals(0, likeDislikeUser.getIsLike());
    }

    @Test
    void testNewsLikeDislikeUserWithLikeValue() {
        likeDislikeUser.setIsLike(1);
        assertEquals(1, likeDislikeUser.getIsLike());
    }

    @Test
    void testNewsLikeDislikeUserWithDislikeValue() {
        likeDislikeUser.setIsLike(0);
        assertEquals(0, likeDislikeUser.getIsLike());
    }

    @Test
    void testNewsLikeDislikeUserWithNegativeValues() {
        likeDislikeUser.setId(-1);
        likeDislikeUser.setNewsId(-2);
        likeDislikeUser.setUserId(-3);
        likeDislikeUser.setIsLike(-1);

        assertEquals(-1, likeDislikeUser.getId());
        assertEquals(-2, likeDislikeUser.getNewsId());
        assertEquals(-3, likeDislikeUser.getUserId());
        assertEquals(-1, likeDislikeUser.getIsLike());
    }

    @Test
    void testNewsLikeDislikeUserWithLargeValues() {
        Integer largeId = Integer.MAX_VALUE;
        Integer largeNewsId = Integer.MAX_VALUE - 1;
        Integer largeUserId = Integer.MAX_VALUE - 2;
        Integer largeIsLike = Integer.MAX_VALUE - 3;

        likeDislikeUser.setId(largeId);
        likeDislikeUser.setNewsId(largeNewsId);
        likeDislikeUser.setUserId(largeUserId);
        likeDislikeUser.setIsLike(largeIsLike);

        assertEquals(largeId, likeDislikeUser.getId());
        assertEquals(largeNewsId, likeDislikeUser.getNewsId());
        assertEquals(largeUserId, likeDislikeUser.getUserId());
        assertEquals(largeIsLike, likeDislikeUser.getIsLike());
    }
} 