package com.server.NewsAggrigationServer.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NewsLikeDislikeUserDTOTest {

    private NewsLikeDislikeUserDTO newsLikeDislikeUserDTO;

    @BeforeEach
    void setUp() {
        newsLikeDislikeUserDTO = new NewsLikeDislikeUserDTO();
    }

    @Test
    void testNewsLikeDislikeUserDTOCreation() {
        assertNotNull(newsLikeDislikeUserDTO);
    }

    @Test
    void testIdGetterAndSetter() {
        Integer expectedId = 1;
        newsLikeDislikeUserDTO.setId(expectedId);
        assertEquals(expectedId, newsLikeDislikeUserDTO.getId());
    }

    @Test
    void testNewsIdGetterAndSetter() {
        Integer expectedNewsId = 1;
        newsLikeDislikeUserDTO.setNewsId(expectedNewsId);
        assertEquals(expectedNewsId, newsLikeDislikeUserDTO.getNewsId());
    }

    @Test
    void testUserIdGetterAndSetter() {
        Integer expectedUserId = 1;
        newsLikeDislikeUserDTO.setUserId(expectedUserId);
        assertEquals(expectedUserId, newsLikeDislikeUserDTO.getUserId());
    }

    @Test
    void testIsLikeGetterAndSetter() {
        Integer expectedIsLike = 1;
        newsLikeDislikeUserDTO.setIsLike(expectedIsLike);
        assertEquals(expectedIsLike, newsLikeDislikeUserDTO.getIsLike());
    }

    @Test
    void testNewsLikeDislikeUserDTOWithAllFields() {
        Integer id = 1;
        Integer newsId = 2;
        Integer userId = 3;
        Integer isLike = 1;

        newsLikeDislikeUserDTO.setId(id);
        newsLikeDislikeUserDTO.setNewsId(newsId);
        newsLikeDislikeUserDTO.setUserId(userId);
        newsLikeDislikeUserDTO.setIsLike(isLike);

        assertEquals(id, newsLikeDislikeUserDTO.getId());
        assertEquals(newsId, newsLikeDislikeUserDTO.getNewsId());
        assertEquals(userId, newsLikeDislikeUserDTO.getUserId());
        assertEquals(isLike, newsLikeDislikeUserDTO.getIsLike());
    }

    @Test
    void testNewsLikeDislikeUserDTOWithNullValues() {
        newsLikeDislikeUserDTO.setId(null);
        newsLikeDislikeUserDTO.setNewsId(null);
        newsLikeDislikeUserDTO.setUserId(null);
        newsLikeDislikeUserDTO.setIsLike(null);

        assertNull(newsLikeDislikeUserDTO.getId());
        assertNull(newsLikeDislikeUserDTO.getNewsId());
        assertNull(newsLikeDislikeUserDTO.getUserId());
        assertNull(newsLikeDislikeUserDTO.getIsLike());
    }

    @Test
    void testNewsLikeDislikeUserDTOWithZeroValues() {
        newsLikeDislikeUserDTO.setId(0);
        newsLikeDislikeUserDTO.setNewsId(0);
        newsLikeDislikeUserDTO.setUserId(0);
        newsLikeDislikeUserDTO.setIsLike(0);

        assertEquals(0, newsLikeDislikeUserDTO.getId());
        assertEquals(0, newsLikeDislikeUserDTO.getNewsId());
        assertEquals(0, newsLikeDislikeUserDTO.getUserId());
        assertEquals(0, newsLikeDislikeUserDTO.getIsLike());
    }

    @Test
    void testNewsLikeDislikeUserDTOWithNegativeValues() {
        newsLikeDislikeUserDTO.setId(-1);
        newsLikeDislikeUserDTO.setNewsId(-2);
        newsLikeDislikeUserDTO.setUserId(-3);
        newsLikeDislikeUserDTO.setIsLike(-1);

        assertEquals(-1, newsLikeDislikeUserDTO.getId());
        assertEquals(-2, newsLikeDislikeUserDTO.getNewsId());
        assertEquals(-3, newsLikeDislikeUserDTO.getUserId());
        assertEquals(-1, newsLikeDislikeUserDTO.getIsLike());
    }

    @Test
    void testNewsLikeDislikeUserDTOWithLargeValues() {
        Integer largeId = Integer.MAX_VALUE;
        Integer largeNewsId = Integer.MAX_VALUE - 1;
        Integer largeUserId = Integer.MAX_VALUE - 2;
        Integer largeIsLike = Integer.MAX_VALUE - 3;

        newsLikeDislikeUserDTO.setId(largeId);
        newsLikeDislikeUserDTO.setNewsId(largeNewsId);
        newsLikeDislikeUserDTO.setUserId(largeUserId);
        newsLikeDislikeUserDTO.setIsLike(largeIsLike);

        assertEquals(largeId, newsLikeDislikeUserDTO.getId());
        assertEquals(largeNewsId, newsLikeDislikeUserDTO.getNewsId());
        assertEquals(largeUserId, newsLikeDislikeUserDTO.getUserId());
        assertEquals(largeIsLike, newsLikeDislikeUserDTO.getIsLike());
    }

    @Test
    void testNewsLikeDislikeUserDTOWithLikeValue() {
        newsLikeDislikeUserDTO.setIsLike(1);
        assertEquals(1, newsLikeDislikeUserDTO.getIsLike());
    }

    @Test
    void testNewsLikeDislikeUserDTOWithDislikeValue() {
        newsLikeDislikeUserDTO.setIsLike(0);
        assertEquals(0, newsLikeDislikeUserDTO.getIsLike());
    }

    @Test
    void testNewsLikeDislikeUserDTOWithDifferentCombinations() {
        // Test different combinations of values
        Integer[][] testValues = {
            {1, 1, 1, 1},   // Like
            {2, 1, 1, 0},   // Dislike
            {3, 2, 1, 1},   // Like
            {4, 2, 2, 0},   // Dislike
            {5, 3, 1, 1}    // Like
        };

        for (Integer[] values : testValues) {
            newsLikeDislikeUserDTO.setId(values[0]);
            newsLikeDislikeUserDTO.setNewsId(values[1]);
            newsLikeDislikeUserDTO.setUserId(values[2]);
            newsLikeDislikeUserDTO.setIsLike(values[3]);

            assertEquals(values[0], newsLikeDislikeUserDTO.getId());
            assertEquals(values[1], newsLikeDislikeUserDTO.getNewsId());
            assertEquals(values[2], newsLikeDislikeUserDTO.getUserId());
            assertEquals(values[3], newsLikeDislikeUserDTO.getIsLike());
        }
    }

    @Test
    void testNewsLikeDislikeUserDTOWithSameValues() {
        Integer sameValue = 42;
        newsLikeDislikeUserDTO.setId(sameValue);
        newsLikeDislikeUserDTO.setNewsId(sameValue);
        newsLikeDislikeUserDTO.setUserId(sameValue);
        newsLikeDislikeUserDTO.setIsLike(1);

        assertEquals(sameValue, newsLikeDislikeUserDTO.getId());
        assertEquals(sameValue, newsLikeDislikeUserDTO.getNewsId());
        assertEquals(sameValue, newsLikeDislikeUserDTO.getUserId());
        assertEquals(1, newsLikeDislikeUserDTO.getIsLike());
    }

    @Test
    void testNewsLikeDislikeUserDTOWithSequentialValues() {
        for (int i = 1; i <= 10; i++) {
            newsLikeDislikeUserDTO.setId(i);
            newsLikeDislikeUserDTO.setNewsId(i * 10);
            newsLikeDislikeUserDTO.setUserId(i * 100);
            newsLikeDislikeUserDTO.setIsLike(i % 2); // Alternate between 0 and 1

            assertEquals(i, newsLikeDislikeUserDTO.getId());
            assertEquals(i * 10, newsLikeDislikeUserDTO.getNewsId());
            assertEquals(i * 100, newsLikeDislikeUserDTO.getUserId());
            assertEquals(i % 2, newsLikeDislikeUserDTO.getIsLike());
        }
    }

    @Test
    void testNewsLikeDislikeUserDTOWithBoundaryValues() {
        // Test minimum values
        newsLikeDislikeUserDTO.setId(0);
        newsLikeDislikeUserDTO.setNewsId(0);
        newsLikeDislikeUserDTO.setUserId(0);
        newsLikeDislikeUserDTO.setIsLike(0);

        assertEquals(0, newsLikeDislikeUserDTO.getId());
        assertEquals(0, newsLikeDislikeUserDTO.getNewsId());
        assertEquals(0, newsLikeDislikeUserDTO.getUserId());
        assertEquals(0, newsLikeDislikeUserDTO.getIsLike());

        // Test maximum values
        newsLikeDislikeUserDTO.setId(Integer.MAX_VALUE);
        newsLikeDislikeUserDTO.setNewsId(Integer.MAX_VALUE);
        newsLikeDislikeUserDTO.setUserId(Integer.MAX_VALUE);
        newsLikeDislikeUserDTO.setIsLike(1);

        assertEquals(Integer.MAX_VALUE, newsLikeDislikeUserDTO.getId());
        assertEquals(Integer.MAX_VALUE, newsLikeDislikeUserDTO.getNewsId());
        assertEquals(Integer.MAX_VALUE, newsLikeDislikeUserDTO.getUserId());
        assertEquals(1, newsLikeDislikeUserDTO.getIsLike());
    }

    @Test
    void testNewsLikeDislikeUserDTOWithRealisticValues() {
        // Test with realistic values that might be used in a real application
        Integer[] realisticIds = {1, 5, 10, 25, 50, 100, 500, 1000};

        for (Integer id : realisticIds) {
            newsLikeDislikeUserDTO.setId(id);
            newsLikeDislikeUserDTO.setNewsId(id * 2);
            newsLikeDislikeUserDTO.setUserId(id * 3);
            newsLikeDislikeUserDTO.setIsLike(id % 2); // Alternate between 0 and 1

            assertEquals(id, newsLikeDislikeUserDTO.getId());
            assertEquals(id * 2, newsLikeDislikeUserDTO.getNewsId());
            assertEquals(id * 3, newsLikeDislikeUserDTO.getUserId());
            assertEquals(id % 2, newsLikeDislikeUserDTO.getIsLike());
        }
    }

    @Test
    void testNewsLikeDislikeUserDTOWithLikeDislikePatterns() {
        // Test different like/dislike patterns
        Integer[][] patterns = {
            {1, 1, 1, 1},   // User 1 likes News 1
            {2, 1, 2, 0},   // User 2 dislikes News 1
            {3, 1, 3, 1},   // User 3 likes News 1
            {4, 2, 1, 0},   // User 1 dislikes News 2
            {5, 2, 2, 1},   // User 2 likes News 2
            {6, 2, 3, 0}    // User 3 dislikes News 2
        };

        for (Integer[] pattern : patterns) {
            newsLikeDislikeUserDTO.setId(pattern[0]);
            newsLikeDislikeUserDTO.setNewsId(pattern[1]);
            newsLikeDislikeUserDTO.setUserId(pattern[2]);
            newsLikeDislikeUserDTO.setIsLike(pattern[3]);

            assertEquals(pattern[0], newsLikeDislikeUserDTO.getId());
            assertEquals(pattern[1], newsLikeDislikeUserDTO.getNewsId());
            assertEquals(pattern[2], newsLikeDislikeUserDTO.getUserId());
            assertEquals(pattern[3], newsLikeDislikeUserDTO.getIsLike());
        }
    }

    @Test
    void testNewsLikeDislikeUserDTOWithInvalidLikeValues() {
        // Test values other than 0 and 1
        Integer[] invalidValues = {-1, 2, 3, 10, 100, -100};

        for (Integer value : invalidValues) {
            newsLikeDislikeUserDTO.setIsLike(value);
            assertEquals(value, newsLikeDislikeUserDTO.getIsLike());
        }
    }
} 