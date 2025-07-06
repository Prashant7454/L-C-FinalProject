package com.server.NewsAggrigationServer.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserNewsReportTest {
    private UserNewsReport report;

    @BeforeEach
    void setUp() {
        report = new UserNewsReport();
    }

    @Test
    void testUserNewsReportCreation() {
        assertNotNull(report);
    }

    @Test
    void testIdGetterAndSetter() {
        Integer expectedId = 1;
        report.setId(expectedId);
        assertEquals(expectedId, report.getId());
    }

    @Test
    void testNewsIdGetterAndSetter() {
        Integer expectedNewsId = 1;
        report.setNewsId(expectedNewsId);
        assertEquals(expectedNewsId, report.getNewsId());
    }

    @Test
    void testUserIdGetterAndSetter() {
        Integer expectedUserId = 1;
        report.setUserId(expectedUserId);
        assertEquals(expectedUserId, report.getUserId());
    }

    @Test
    void testReportReasonGetterAndSetter() {
        String expectedReason = "Inappropriate content";
        report.setReportReason(expectedReason);
        assertEquals(expectedReason, report.getReportReason());
    }

    @Test
    void testReportDateGetterAndSetter() {
        String expectedDate = "2024-01-01";
        report.setReportDate(expectedDate);
        assertEquals(expectedDate, report.getReportDate());
    }

    @Test
    void testUserNewsReportWithAllFields() {
        Integer id = 1;
        Integer newsId = 2;
        Integer userId = 3;
        String reason = "Fake news";
        String date = "2024-01-15";

        report.setId(id);
        report.setNewsId(newsId);
        report.setUserId(userId);
        report.setReportReason(reason);
        report.setReportDate(date);

        assertEquals(id, report.getId());
        assertEquals(newsId, report.getNewsId());
        assertEquals(userId, report.getUserId());
        assertEquals(reason, report.getReportReason());
        assertEquals(date, report.getReportDate());
    }

    @Test
    void testUserNewsReportWithNullValues() {
        report.setId(null);
        report.setNewsId(null);
        report.setUserId(null);
        report.setReportReason(null);
        report.setReportDate(null);

        assertNull(report.getId());
        assertNull(report.getNewsId());
        assertNull(report.getUserId());
        assertNull(report.getReportReason());
        assertNull(report.getReportDate());
    }

    @Test
    void testUserNewsReportWithEmptyStrings() {
        report.setReportReason("");
        report.setReportDate("");

        assertEquals("", report.getReportReason());
        assertEquals("", report.getReportDate());
    }

    @Test
    void testUserNewsReportWithZeroValues() {
        report.setId(0);
        report.setNewsId(0);
        report.setUserId(0);

        assertEquals(0, report.getId());
        assertEquals(0, report.getNewsId());
        assertEquals(0, report.getUserId());
    }

    @Test
    void testUserNewsReportWithNegativeValues() {
        report.setId(-1);
        report.setNewsId(-2);
        report.setUserId(-3);

        assertEquals(-1, report.getId());
        assertEquals(-2, report.getNewsId());
        assertEquals(-3, report.getUserId());
    }

    @Test
    void testUserNewsReportWithLargeValues() {
        Integer largeId = Integer.MAX_VALUE;
        Integer largeNewsId = Integer.MAX_VALUE - 1;
        Integer largeUserId = Integer.MAX_VALUE - 2;

        report.setId(largeId);
        report.setNewsId(largeNewsId);
        report.setUserId(largeUserId);

        assertEquals(largeId, report.getId());
        assertEquals(largeNewsId, report.getNewsId());
        assertEquals(largeUserId, report.getUserId());
    }

    @Test
    void testUserNewsReportWithSpecialCharacters() {
        String reasonWithSpecialChars = "Content with @#$%^&*() symbols";
        String dateWithSpecialFormat = "2024-12-31T23:59:59";

        report.setReportReason(reasonWithSpecialChars);
        report.setReportDate(dateWithSpecialFormat);

        assertEquals(reasonWithSpecialChars, report.getReportReason());
        assertEquals(dateWithSpecialFormat, report.getReportDate());
    }

    @Test
    void testUserNewsReportWithLongStrings() {
        String longReason = "This is a very long report reason that contains many characters and should be properly handled by the getter and setter methods without any issues. It includes various types of content descriptions.";
        String longDate = "2024-12-31T23:59:59.999Z";

        report.setReportReason(longReason);
        report.setReportDate(longDate);

        assertEquals(longReason, report.getReportReason());
        assertEquals(longDate, report.getReportDate());
    }
} 