package com.server.NewsAggrigationServer.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserNewsReportDTOTest {

    private UserNewsReportDTO userNewsReportDTO;

    @BeforeEach
    void setUp() {
        userNewsReportDTO = new UserNewsReportDTO();
    }

    @Test
    void testUserNewsReportDTOCreation() {
        assertNotNull(userNewsReportDTO);
    }

    @Test
    void testIdGetterAndSetter() {
        Integer expectedId = 1;
        userNewsReportDTO.setId(expectedId);
        assertEquals(expectedId, userNewsReportDTO.getId());
    }

    @Test
    void testNewsIdGetterAndSetter() {
        Integer expectedNewsId = 1;
        userNewsReportDTO.setNewsId(expectedNewsId);
        assertEquals(expectedNewsId, userNewsReportDTO.getNewsId());
    }

    @Test
    void testUserIdGetterAndSetter() {
        Integer expectedUserId = 1;
        userNewsReportDTO.setUserId(expectedUserId);
        assertEquals(expectedUserId, userNewsReportDTO.getUserId());
    }

    @Test
    void testReportReasonGetterAndSetter() {
        String expectedReportReason = "Inappropriate content";
        userNewsReportDTO.setReportReason(expectedReportReason);
        assertEquals(expectedReportReason, userNewsReportDTO.getReportReason());
    }

    @Test
    void testReportDateGetterAndSetter() {
        String expectedReportDate = "2024-01-01";
        userNewsReportDTO.setReportDate(expectedReportDate);
        assertEquals(expectedReportDate, userNewsReportDTO.getReportDate());
    }

    @Test
    void testUserNewsReportDTOWithAllFields() {
        Integer id = 1;
        Integer newsId = 2;
        Integer userId = 3;
        String reportReason = "Fake news";
        String reportDate = "2024-01-15";

        userNewsReportDTO.setId(id);
        userNewsReportDTO.setNewsId(newsId);
        userNewsReportDTO.setUserId(userId);
        userNewsReportDTO.setReportReason(reportReason);
        userNewsReportDTO.setReportDate(reportDate);

        assertEquals(id, userNewsReportDTO.getId());
        assertEquals(newsId, userNewsReportDTO.getNewsId());
        assertEquals(userId, userNewsReportDTO.getUserId());
        assertEquals(reportReason, userNewsReportDTO.getReportReason());
        assertEquals(reportDate, userNewsReportDTO.getReportDate());
    }

    @Test
    void testUserNewsReportDTOWithNullValues() {
        userNewsReportDTO.setId(null);
        userNewsReportDTO.setNewsId(null);
        userNewsReportDTO.setUserId(null);
        userNewsReportDTO.setReportReason(null);
        userNewsReportDTO.setReportDate(null);

        assertNull(userNewsReportDTO.getId());
        assertNull(userNewsReportDTO.getNewsId());
        assertNull(userNewsReportDTO.getUserId());
        assertNull(userNewsReportDTO.getReportReason());
        assertNull(userNewsReportDTO.getReportDate());
    }

    @Test
    void testUserNewsReportDTOWithEmptyStrings() {
        userNewsReportDTO.setReportReason("");
        userNewsReportDTO.setReportDate("");

        assertEquals("", userNewsReportDTO.getReportReason());
        assertEquals("", userNewsReportDTO.getReportDate());
    }

    @Test
    void testUserNewsReportDTOWithZeroValues() {
        userNewsReportDTO.setId(0);
        userNewsReportDTO.setNewsId(0);
        userNewsReportDTO.setUserId(0);

        assertEquals(0, userNewsReportDTO.getId());
        assertEquals(0, userNewsReportDTO.getNewsId());
        assertEquals(0, userNewsReportDTO.getUserId());
    }

    @Test
    void testUserNewsReportDTOWithNegativeValues() {
        userNewsReportDTO.setId(-1);
        userNewsReportDTO.setNewsId(-2);
        userNewsReportDTO.setUserId(-3);

        assertEquals(-1, userNewsReportDTO.getId());
        assertEquals(-2, userNewsReportDTO.getNewsId());
        assertEquals(-3, userNewsReportDTO.getUserId());
    }

    @Test
    void testUserNewsReportDTOWithLargeValues() {
        Integer largeId = Integer.MAX_VALUE;
        Integer largeNewsId = Integer.MAX_VALUE - 1;
        Integer largeUserId = Integer.MAX_VALUE - 2;

        userNewsReportDTO.setId(largeId);
        userNewsReportDTO.setNewsId(largeNewsId);
        userNewsReportDTO.setUserId(largeUserId);

        assertEquals(largeId, userNewsReportDTO.getId());
        assertEquals(largeNewsId, userNewsReportDTO.getNewsId());
        assertEquals(largeUserId, userNewsReportDTO.getUserId());
    }

    @Test
    void testUserNewsReportDTOWithSpecialCharacters() {
        String reasonWithSpecialChars = "Content with @#$%^&*() symbols";
        String dateWithSpecialChars = "2024-12-31T23:59:59";

        userNewsReportDTO.setReportReason(reasonWithSpecialChars);
        userNewsReportDTO.setReportDate(dateWithSpecialChars);

        assertEquals(reasonWithSpecialChars, userNewsReportDTO.getReportReason());
        assertEquals(dateWithSpecialChars, userNewsReportDTO.getReportDate());
    }

    @Test
    void testUserNewsReportDTOWithUnicodeCharacters() {
        String reasonWithUnicode = "Content with émojis 🚀 and symbols ©®™";
        String dateWithUnicode = "2024-01-01_émojis_🚀";

        userNewsReportDTO.setReportReason(reasonWithUnicode);
        userNewsReportDTO.setReportDate(dateWithUnicode);

        assertEquals(reasonWithUnicode, userNewsReportDTO.getReportReason());
        assertEquals(dateWithUnicode, userNewsReportDTO.getReportDate());
    }

    @Test
    void testUserNewsReportDTOWithLongStrings() {
        String longReason = "This is a very long report reason that contains many characters and should be properly handled by the getter and setter methods without any issues. It includes various types of content descriptions.";
        String longDate = "2024-12-31T23:59:59.999Z_with_very_long_timestamp_format";

        userNewsReportDTO.setReportReason(longReason);
        userNewsReportDTO.setReportDate(longDate);

        assertEquals(longReason, userNewsReportDTO.getReportReason());
        assertEquals(longDate, userNewsReportDTO.getReportDate());
    }

    @Test
    void testUserNewsReportDTOWithWhitespace() {
        String reasonWithWhitespace = "  Inappropriate content  ";
        String dateWithWhitespace = "  2024-01-01  ";

        userNewsReportDTO.setReportReason(reasonWithWhitespace);
        userNewsReportDTO.setReportDate(dateWithWhitespace);

        assertEquals(reasonWithWhitespace, userNewsReportDTO.getReportReason());
        assertEquals(dateWithWhitespace, userNewsReportDTO.getReportDate());
    }

    @Test
    void testUserNewsReportDTOWithNewlines() {
        String reasonWithNewlines = "Inappropriate\ncontent";
        String dateWithNewlines = "2024-01-01\n12:00:00";

        userNewsReportDTO.setReportReason(reasonWithNewlines);
        userNewsReportDTO.setReportDate(dateWithNewlines);

        assertEquals(reasonWithNewlines, userNewsReportDTO.getReportReason());
        assertEquals(dateWithNewlines, userNewsReportDTO.getReportDate());
    }

    @Test
    void testUserNewsReportDTOWithNumbers() {
        String reasonWithNumbers = "Fake news 123";
        String dateWithNumbers = "2024-01-01_123";

        userNewsReportDTO.setReportReason(reasonWithNumbers);
        userNewsReportDTO.setReportDate(dateWithNumbers);

        assertEquals(reasonWithNumbers, userNewsReportDTO.getReportReason());
        assertEquals(dateWithNumbers, userNewsReportDTO.getReportDate());
    }

    @Test
    void testUserNewsReportDTOWithMixedCase() {
        String reasonWithMixedCase = "Inappropriate Content";
        String dateWithMixedCase = "2024-01-01_AM";

        userNewsReportDTO.setReportReason(reasonWithMixedCase);
        userNewsReportDTO.setReportDate(dateWithMixedCase);

        assertEquals(reasonWithMixedCase, userNewsReportDTO.getReportReason());
        assertEquals(dateWithMixedCase, userNewsReportDTO.getReportDate());
    }

    @Test
    void testUserNewsReportDTOWithDifferentReportReasons() {
        String[] reportReasons = {
            "Fake news",
            "Inappropriate content",
            "Spam",
            "Misleading information",
            "Violence",
            "Hate speech",
            "Copyright violation"
        };

        for (String reason : reportReasons) {
            userNewsReportDTO.setReportReason(reason);
            assertEquals(reason, userNewsReportDTO.getReportReason());
        }
    }

    @Test
    void testUserNewsReportDTOWithDifferentDateFormats() {
        String[] dateFormats = {
            "2024-01-01",
            "2024-12-31",
            "2024-02-29",
            "2023-06-15",
            "2025-03-08",
            "2024-01-01T12:00:00Z",
            "2024-12-31T23:59:59.999Z"
        };

        for (String date : dateFormats) {
            userNewsReportDTO.setReportDate(date);
            assertEquals(date, userNewsReportDTO.getReportDate());
        }
    }

    @Test
    void testUserNewsReportDTOWithRealisticValues() {
        // Test with realistic values that might be used in a real application
        Integer[] realisticIds = {1, 5, 10, 25, 50, 100, 500, 1000};

        for (Integer id : realisticIds) {
            userNewsReportDTO.setId(id);
            userNewsReportDTO.setNewsId(id * 2);
            userNewsReportDTO.setUserId(id * 3);

            assertEquals(id, userNewsReportDTO.getId());
            assertEquals(id * 2, userNewsReportDTO.getNewsId());
            assertEquals(id * 3, userNewsReportDTO.getUserId());
        }
    }

    @Test
    void testUserNewsReportDTOWithBoundaryValues() {
        // Test minimum values
        userNewsReportDTO.setId(0);
        userNewsReportDTO.setNewsId(0);
        userNewsReportDTO.setUserId(0);

        assertEquals(0, userNewsReportDTO.getId());
        assertEquals(0, userNewsReportDTO.getNewsId());
        assertEquals(0, userNewsReportDTO.getUserId());

        // Test maximum values
        userNewsReportDTO.setId(Integer.MAX_VALUE);
        userNewsReportDTO.setNewsId(Integer.MAX_VALUE);
        userNewsReportDTO.setUserId(Integer.MAX_VALUE);

        assertEquals(Integer.MAX_VALUE, userNewsReportDTO.getId());
        assertEquals(Integer.MAX_VALUE, userNewsReportDTO.getNewsId());
        assertEquals(Integer.MAX_VALUE, userNewsReportDTO.getUserId());
    }
} 