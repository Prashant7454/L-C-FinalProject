package com.server.NewsAggrigationServer.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SavedNewsDTOTest {

    private SavedNewsDTO savedNewsDTO;

    @BeforeEach
    void setUp() {
        savedNewsDTO = new SavedNewsDTO();
    }

    @Test
    void testSavedNewsDTOCreation() {
        assertNotNull(savedNewsDTO);
    }

    @Test
    void testIdGetterAndSetter() {
        Integer expectedId = 1;
        savedNewsDTO.setId(expectedId);
        assertEquals(expectedId, savedNewsDTO.getId());
    }

    @Test
    void testNewsIdGetterAndSetter() {
        Integer expectedNewsId = 1;
        savedNewsDTO.setNewsId(expectedNewsId);
        assertEquals(expectedNewsId, savedNewsDTO.getNewsId());
    }

    @Test
    void testUserIdGetterAndSetter() {
        Integer expectedUserId = 1;
        savedNewsDTO.setUserId(expectedUserId);
        assertEquals(expectedUserId, savedNewsDTO.getUserId());
    }

    @Test
    void testSavedNewsDTOWithAllFields() {
        Integer id = 1;
        Integer newsId = 2;
        Integer userId = 3;

        savedNewsDTO.setId(id);
        savedNewsDTO.setNewsId(newsId);
        savedNewsDTO.setUserId(userId);

        assertEquals(id, savedNewsDTO.getId());
        assertEquals(newsId, savedNewsDTO.getNewsId());
        assertEquals(userId, savedNewsDTO.getUserId());
    }

    @Test
    void testSavedNewsDTOWithNullValues() {
        savedNewsDTO.setId(null);
        savedNewsDTO.setNewsId(null);
        savedNewsDTO.setUserId(null);

        assertNull(savedNewsDTO.getId());
        assertNull(savedNewsDTO.getNewsId());
        assertNull(savedNewsDTO.getUserId());
    }

    @Test
    void testSavedNewsDTOWithZeroValues() {
        savedNewsDTO.setId(0);
        savedNewsDTO.setNewsId(0);
        savedNewsDTO.setUserId(0);

        assertEquals(0, savedNewsDTO.getId());
        assertEquals(0, savedNewsDTO.getNewsId());
        assertEquals(0, savedNewsDTO.getUserId());
    }

    @Test
    void testSavedNewsDTOWithNegativeValues() {
        savedNewsDTO.setId(-1);
        savedNewsDTO.setNewsId(-2);
        savedNewsDTO.setUserId(-3);

        assertEquals(-1, savedNewsDTO.getId());
        assertEquals(-2, savedNewsDTO.getNewsId());
        assertEquals(-3, savedNewsDTO.getUserId());
    }

    @Test
    void testSavedNewsDTOWithLargeValues() {
        Integer largeId = Integer.MAX_VALUE;
        Integer largeNewsId = Integer.MAX_VALUE - 1;
        Integer largeUserId = Integer.MAX_VALUE - 2;

        savedNewsDTO.setId(largeId);
        savedNewsDTO.setNewsId(largeNewsId);
        savedNewsDTO.setUserId(largeUserId);

        assertEquals(largeId, savedNewsDTO.getId());
        assertEquals(largeNewsId, savedNewsDTO.getNewsId());
        assertEquals(largeUserId, savedNewsDTO.getUserId());
    }

    @Test
    void testSavedNewsDTOWithDifferentCombinations() {
        // Test different combinations of values
        Integer[][] testValues = {
            {1, 1, 1},
            {100, 200, 300},
            {999, 888, 777},
            {Integer.MAX_VALUE, Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 2}
        };

        for (Integer[] values : testValues) {
            savedNewsDTO.setId(values[0]);
            savedNewsDTO.setNewsId(values[1]);
            savedNewsDTO.setUserId(values[2]);

            assertEquals(values[0], savedNewsDTO.getId());
            assertEquals(values[1], savedNewsDTO.getNewsId());
            assertEquals(values[2], savedNewsDTO.getUserId());
        }
    }

    @Test
    void testSavedNewsDTOWithSameValues() {
        Integer sameValue = 42;
        savedNewsDTO.setId(sameValue);
        savedNewsDTO.setNewsId(sameValue);
        savedNewsDTO.setUserId(sameValue);

        assertEquals(sameValue, savedNewsDTO.getId());
        assertEquals(sameValue, savedNewsDTO.getNewsId());
        assertEquals(sameValue, savedNewsDTO.getUserId());
    }

    @Test
    void testSavedNewsDTOWithSequentialValues() {
        for (int i = 1; i <= 10; i++) {
            savedNewsDTO.setId(i);
            savedNewsDTO.setNewsId(i * 10);
            savedNewsDTO.setUserId(i * 100);

            assertEquals(i, savedNewsDTO.getId());
            assertEquals(i * 10, savedNewsDTO.getNewsId());
            assertEquals(i * 100, savedNewsDTO.getUserId());
        }
    }

    @Test
    void testSavedNewsDTOWithBoundaryValues() {
        // Test minimum values
        savedNewsDTO.setId(0);
        savedNewsDTO.setNewsId(0);
        savedNewsDTO.setUserId(0);

        assertEquals(0, savedNewsDTO.getId());
        assertEquals(0, savedNewsDTO.getNewsId());
        assertEquals(0, savedNewsDTO.getUserId());

        // Test maximum values
        savedNewsDTO.setId(Integer.MAX_VALUE);
        savedNewsDTO.setNewsId(Integer.MAX_VALUE);
        savedNewsDTO.setUserId(Integer.MAX_VALUE);

        assertEquals(Integer.MAX_VALUE, savedNewsDTO.getId());
        assertEquals(Integer.MAX_VALUE, savedNewsDTO.getNewsId());
        assertEquals(Integer.MAX_VALUE, savedNewsDTO.getUserId());
    }

    @Test
    void testSavedNewsDTOWithRealisticValues() {
        // Test with realistic values that might be used in a real application
        Integer[] realisticIds = {1, 5, 10, 25, 50, 100, 500, 1000};

        for (Integer id : realisticIds) {
            savedNewsDTO.setId(id);
            savedNewsDTO.setNewsId(id * 2);
            savedNewsDTO.setUserId(id * 3);

            assertEquals(id, savedNewsDTO.getId());
            assertEquals(id * 2, savedNewsDTO.getNewsId());
            assertEquals(id * 3, savedNewsDTO.getUserId());
        }
    }
} 