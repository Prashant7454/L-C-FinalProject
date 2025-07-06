package com.server.NewsAggrigationServer.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserReadingHistoryDTOTest {

    private UserReadingHistoryDTO userReadingHistoryDTO;

    @BeforeEach
    void setUp() {
        userReadingHistoryDTO = new UserReadingHistoryDTO();
    }

    @Test
    void testUserReadingHistoryDTOCreation() {
        assertNotNull(userReadingHistoryDTO);
    }

    @Test
    void testIdGetterAndSetter() {
        Integer expectedId = 1;
        userReadingHistoryDTO.setId(expectedId);
        assertEquals(expectedId, userReadingHistoryDTO.getId());
    }

    @Test
    void testUserIdGetterAndSetter() {
        Integer expectedUserId = 1;
        userReadingHistoryDTO.setUserId(expectedUserId);
        assertEquals(expectedUserId, userReadingHistoryDTO.getUserId());
    }

    @Test
    void testNewsIdGetterAndSetter() {
        Integer expectedNewsId = 1;
        userReadingHistoryDTO.setNewsId(expectedNewsId);
        assertEquals(expectedNewsId, userReadingHistoryDTO.getNewsId());
    }

    @Test
    void testReadDateGetterAndSetter() {
        String expectedReadDate = "2024-01-01";
        userReadingHistoryDTO.setReadDate(expectedReadDate);
        assertEquals(expectedReadDate, userReadingHistoryDTO.getReadDate());
    }

    @Test
    void testUserReadingHistoryDTOWithAllFields() {
        Integer id = 1;
        Integer userId = 2;
        Integer newsId = 3;
        String readDate = "2024-01-15";

        userReadingHistoryDTO.setId(id);
        userReadingHistoryDTO.setUserId(userId);
        userReadingHistoryDTO.setNewsId(newsId);
        userReadingHistoryDTO.setReadDate(readDate);

        assertEquals(id, userReadingHistoryDTO.getId());
        assertEquals(userId, userReadingHistoryDTO.getUserId());
        assertEquals(newsId, userReadingHistoryDTO.getNewsId());
        assertEquals(readDate, userReadingHistoryDTO.getReadDate());
    }

    @Test
    void testUserReadingHistoryDTOWithNullValues() {
        userReadingHistoryDTO.setId(null);
        userReadingHistoryDTO.setUserId(null);
        userReadingHistoryDTO.setNewsId(null);
        userReadingHistoryDTO.setReadDate(null);

        assertNull(userReadingHistoryDTO.getId());
        assertNull(userReadingHistoryDTO.getUserId());
        assertNull(userReadingHistoryDTO.getNewsId());
        assertNull(userReadingHistoryDTO.getReadDate());
    }

    @Test
    void testUserReadingHistoryDTOWithEmptyString() {
        userReadingHistoryDTO.setReadDate("");
        assertEquals("", userReadingHistoryDTO.getReadDate());
    }

    @Test
    void testUserReadingHistoryDTOWithZeroValues() {
        userReadingHistoryDTO.setId(0);
        userReadingHistoryDTO.setUserId(0);
        userReadingHistoryDTO.setNewsId(0);

        assertEquals(0, userReadingHistoryDTO.getId());
        assertEquals(0, userReadingHistoryDTO.getUserId());
        assertEquals(0, userReadingHistoryDTO.getNewsId());
    }

    @Test
    void testUserReadingHistoryDTOWithNegativeValues() {
        userReadingHistoryDTO.setId(-1);
        userReadingHistoryDTO.setUserId(-2);
        userReadingHistoryDTO.setNewsId(-3);

        assertEquals(-1, userReadingHistoryDTO.getId());
        assertEquals(-2, userReadingHistoryDTO.getUserId());
        assertEquals(-3, userReadingHistoryDTO.getNewsId());
    }

    @Test
    void testUserReadingHistoryDTOWithLargeValues() {
        Integer largeId = Integer.MAX_VALUE;
        Integer largeUserId = Integer.MAX_VALUE - 1;
        Integer largeNewsId = Integer.MAX_VALUE - 2;

        userReadingHistoryDTO.setId(largeId);
        userReadingHistoryDTO.setUserId(largeUserId);
        userReadingHistoryDTO.setNewsId(largeNewsId);

        assertEquals(largeId, userReadingHistoryDTO.getId());
        assertEquals(largeUserId, userReadingHistoryDTO.getUserId());
        assertEquals(largeNewsId, userReadingHistoryDTO.getNewsId());
    }

    @Test
    void testUserReadingHistoryDTOWithDifferentDateFormats() {
        String[] dateFormats = {
            "2024-01-01",
            "2024-12-31",
            "2024-02-29",
            "2023-06-15",
            "2025-03-08"
        };

        for (String date : dateFormats) {
            userReadingHistoryDTO.setReadDate(date);
            assertEquals(date, userReadingHistoryDTO.getReadDate());
        }
    }

    @Test
    void testUserReadingHistoryDTOWithSpecialCharacters() {
        String dateWithSpecialChars = "2024-01-01T12:00:00Z";
        userReadingHistoryDTO.setReadDate(dateWithSpecialChars);
        assertEquals(dateWithSpecialChars, userReadingHistoryDTO.getReadDate());
    }

    @Test
    void testUserReadingHistoryDTOWithUnicodeCharacters() {
        String dateWithUnicode = "2024-01-01_émojis_🚀";
        userReadingHistoryDTO.setReadDate(dateWithUnicode);
        assertEquals(dateWithUnicode, userReadingHistoryDTO.getReadDate());
    }

    @Test
    void testUserReadingHistoryDTOWithLongString() {
        String longDate = "2024-12-31T23:59:59.999Z_with_very_long_timestamp_format";
        userReadingHistoryDTO.setReadDate(longDate);
        assertEquals(longDate, userReadingHistoryDTO.getReadDate());
    }

    @Test
    void testUserReadingHistoryDTOWithWhitespace() {
        String dateWithWhitespace = "  2024-01-01  ";
        userReadingHistoryDTO.setReadDate(dateWithWhitespace);
        assertEquals(dateWithWhitespace, userReadingHistoryDTO.getReadDate());
    }

    @Test
    void testUserReadingHistoryDTOWithNewlines() {
        String dateWithNewlines = "2024-01-01\n12:00:00";
        userReadingHistoryDTO.setReadDate(dateWithNewlines);
        assertEquals(dateWithNewlines, userReadingHistoryDTO.getReadDate());
    }

    @Test
    void testUserReadingHistoryDTOWithNumbers() {
        String dateWithNumbers = "2024-01-01_123";
        userReadingHistoryDTO.setReadDate(dateWithNumbers);
        assertEquals(dateWithNumbers, userReadingHistoryDTO.getReadDate());
    }

    @Test
    void testUserReadingHistoryDTOWithMixedCase() {
        String dateWithMixedCase = "2024-01-01_AM";
        userReadingHistoryDTO.setReadDate(dateWithMixedCase);
        assertEquals(dateWithMixedCase, userReadingHistoryDTO.getReadDate());
    }

    @Test
    void testUserReadingHistoryDTOWithDifferentCombinations() {
        // Test different combinations of values
        Object[][] testValues = {
            {1, 1, 1, "2024-01-01"},
            {100, 200, 300, "2024-12-31"},
            {999, 888, 777, "2024-06-15"},
            {Integer.MAX_VALUE, Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 2, "2024-02-29"}
        };

        for (Object[] values : testValues) {
            userReadingHistoryDTO.setId((Integer) values[0]);
            userReadingHistoryDTO.setUserId((Integer) values[1]);
            userReadingHistoryDTO.setNewsId((Integer) values[2]);
            userReadingHistoryDTO.setReadDate((String) values[3]);

            assertEquals(values[0], userReadingHistoryDTO.getId());
            assertEquals(values[1], userReadingHistoryDTO.getUserId());
            assertEquals(values[2], userReadingHistoryDTO.getNewsId());
            assertEquals(values[3], userReadingHistoryDTO.getReadDate());
        }
    }

    @Test
    void testUserReadingHistoryDTOWithSameValues() {
        Integer sameValue = 42;
        String sameDate = "2024-01-01";
        
        userReadingHistoryDTO.setId(sameValue);
        userReadingHistoryDTO.setUserId(sameValue);
        userReadingHistoryDTO.setNewsId(sameValue);
        userReadingHistoryDTO.setReadDate(sameDate);

        assertEquals(sameValue, userReadingHistoryDTO.getId());
        assertEquals(sameValue, userReadingHistoryDTO.getUserId());
        assertEquals(sameValue, userReadingHistoryDTO.getNewsId());
        assertEquals(sameDate, userReadingHistoryDTO.getReadDate());
    }

    @Test
    void testUserReadingHistoryDTOWithSequentialValues() {
        for (int i = 1; i <= 10; i++) {
            userReadingHistoryDTO.setId(i);
            userReadingHistoryDTO.setUserId(i * 10);
            userReadingHistoryDTO.setNewsId(i * 100);
            userReadingHistoryDTO.setReadDate("2024-01-" + String.format("%02d", i));

            assertEquals(i, userReadingHistoryDTO.getId());
            assertEquals(i * 10, userReadingHistoryDTO.getUserId());
            assertEquals(i * 100, userReadingHistoryDTO.getNewsId());
            assertEquals("2024-01-" + String.format("%02d", i), userReadingHistoryDTO.getReadDate());
        }
    }

    @Test
    void testUserReadingHistoryDTOWithBoundaryValues() {
        // Test minimum values
        userReadingHistoryDTO.setId(0);
        userReadingHistoryDTO.setUserId(0);
        userReadingHistoryDTO.setNewsId(0);

        assertEquals(0, userReadingHistoryDTO.getId());
        assertEquals(0, userReadingHistoryDTO.getUserId());
        assertEquals(0, userReadingHistoryDTO.getNewsId());

        // Test maximum values
        userReadingHistoryDTO.setId(Integer.MAX_VALUE);
        userReadingHistoryDTO.setUserId(Integer.MAX_VALUE);
        userReadingHistoryDTO.setNewsId(Integer.MAX_VALUE);

        assertEquals(Integer.MAX_VALUE, userReadingHistoryDTO.getId());
        assertEquals(Integer.MAX_VALUE, userReadingHistoryDTO.getUserId());
        assertEquals(Integer.MAX_VALUE, userReadingHistoryDTO.getNewsId());
    }

    @Test
    void testUserReadingHistoryDTOWithRealisticValues() {
        // Test with realistic values that might be used in a real application
        Integer[] realisticIds = {1, 5, 10, 25, 50, 100, 500, 1000};

        for (Integer id : realisticIds) {
            userReadingHistoryDTO.setId(id);
            userReadingHistoryDTO.setUserId(id * 2);
            userReadingHistoryDTO.setNewsId(id * 3);
            userReadingHistoryDTO.setReadDate("2024-01-" + String.format("%02d", (id % 28) + 1));

            assertEquals(id, userReadingHistoryDTO.getId());
            assertEquals(id * 2, userReadingHistoryDTO.getUserId());
            assertEquals(id * 3, userReadingHistoryDTO.getNewsId());
            assertEquals("2024-01-" + String.format("%02d", (id % 28) + 1), userReadingHistoryDTO.getReadDate());
        }
    }
} 