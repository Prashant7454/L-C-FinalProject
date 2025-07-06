package com.server.NewsAggrigationServer.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class NewsTest {

    private News news;
    private LocalDateTime testDateTime;

    @BeforeEach
    void setUp() {
        news = new News();
        testDateTime = LocalDateTime.of(2023, 12, 25, 10, 30, 0);
    }

    @Test
    void testNewsCreation() {
        assertNotNull(news);
    }

    @Test
    void testIdGetterAndSetter() {
        Integer expectedId = 1;
        news.setId(expectedId);
        assertEquals(expectedId, news.getId());
    }

    @Test
    void testTitleGetterAndSetter() {
        String expectedTitle = "Test News Title";
        news.setTitle(expectedTitle);
        assertEquals(expectedTitle, news.getTitle());
    }

    @Test
    void testDescriptionGetterAndSetter() {
        String expectedDescription = "This is a test news description that can be quite long.";
        news.setDescription(expectedDescription);
        assertEquals(expectedDescription, news.getDescription());
    }

    @Test
    void testSourceGetterAndSetter() {
        String expectedSource = "Test News Source";
        news.setSource(expectedSource);
        assertEquals(expectedSource, news.getSource());
    }

    @Test
    void testUrlGetterAndSetter() {
        String expectedUrl = "https://example.com/news/test-article";
        news.setUrl(expectedUrl);
        assertEquals(expectedUrl, news.getUrl());
    }

    @Test
    void testPublishAtGetterAndSetter() {
        news.setPublishAt(testDateTime);
        assertEquals(testDateTime, news.getPublishAt());
    }

    @Test
    void testKeywordGetterAndSetter() {
        String expectedKeyword = "technology";
        news.setKeyword(expectedKeyword);
        assertEquals(expectedKeyword, news.getKeyword());
    }

    @Test
    void testLikeCountGetterAndSetter() {
        Integer expectedLikeCount = 42;
        news.setLikeCount(expectedLikeCount);
        assertEquals(expectedLikeCount, news.getLikeCount());
    }

    @Test
    void testDisLikeCountGetterAndSetter() {
        Integer expectedDisLikeCount = 5;
        news.setDisLikeCount(expectedDisLikeCount);
        assertEquals(expectedDisLikeCount, news.getDisLikeCount());
    }

    @Test
    void testReportCountGetterAndSetter() {
        Integer expectedReportCount = 2;
        news.setReportCount(expectedReportCount);
        assertEquals(expectedReportCount, news.getReportCount());
    }

    @Test
    void testIsHideGetterAndSetter() {
        Integer expectedIsHide = 1;
        news.setIsHide(expectedIsHide);
        assertEquals(expectedIsHide, news.getIsHide());
    }

    @Test
    void testDefaultValues() {
        News newNews = new News();
        assertEquals(0, newNews.getLikeCount());
        assertEquals(0, newNews.getDisLikeCount());
        assertEquals(0, newNews.getReportCount());
        assertEquals(0, newNews.getIsHide());
    }

    @Test
    void testNewsWithAllFields() {
        Integer id = 1;
        String title = "Breaking News";
        String description = "This is breaking news about technology";
        String source = "Tech News";
        String url = "https://technews.com/breaking";
        String keyword = "technology";
        Integer likeCount = 100;
        Integer disLikeCount = 10;
        Integer reportCount = 3;
        Integer isHide = 0;

        news.setId(id);
        news.setTitle(title);
        news.setDescription(description);
        news.setSource(source);
        news.setUrl(url);
        news.setPublishAt(testDateTime);
        news.setKeyword(keyword);
        news.setLikeCount(likeCount);
        news.setDisLikeCount(disLikeCount);
        news.setReportCount(reportCount);
        news.setIsHide(isHide);

        assertEquals(id, news.getId());
        assertEquals(title, news.getTitle());
        assertEquals(description, news.getDescription());
        assertEquals(source, news.getSource());
        assertEquals(url, news.getUrl());
        assertEquals(testDateTime, news.getPublishAt());
        assertEquals(keyword, news.getKeyword());
        assertEquals(likeCount, news.getLikeCount());
        assertEquals(disLikeCount, news.getDisLikeCount());
        assertEquals(reportCount, news.getReportCount());
        assertEquals(isHide, news.getIsHide());
    }

    @Test
    void testNewsWithNullValues() {
        news.setId(null);
        news.setTitle(null);
        news.setDescription(null);
        news.setSource(null);
        news.setUrl(null);
        news.setPublishAt(null);
        news.setKeyword(null);
        news.setLikeCount(null);
        news.setDisLikeCount(null);
        news.setReportCount(null);
        news.setIsHide(null);

        assertNull(news.getId());
        assertNull(news.getTitle());
        assertNull(news.getDescription());
        assertNull(news.getSource());
        assertNull(news.getUrl());
        assertNull(news.getPublishAt());
        assertNull(news.getKeyword());
        assertNull(news.getLikeCount());
        assertNull(news.getDisLikeCount());
        assertNull(news.getReportCount());
        assertNull(news.getIsHide());
    }

    @Test
    void testToString() {
        news.setId(1);
        news.setTitle("Test News");
        news.setLikeCount(10);
        news.setDisLikeCount(2);
        news.setReportCount(1);
        news.setIsHide(0);

        String expectedToString = "News{id=1, title='Test News', likeCount=10, disLikeCount=2, reportCount=1, isHide=0}";
        assertEquals(expectedToString, news.toString());
    }

    @Test
    void testToStringWithNullValues() {
        news.setId(null);
        news.setTitle(null);
        news.setLikeCount(null);
        news.setDisLikeCount(null);
        news.setReportCount(null);
        news.setIsHide(null);

        String expectedToString = "News{id=null, title='null', likeCount=null, disLikeCount=null, reportCount=null, isHide=null}";
        assertEquals(expectedToString, news.toString());
    }
} 