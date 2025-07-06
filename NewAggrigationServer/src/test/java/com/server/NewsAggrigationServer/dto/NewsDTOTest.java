package com.server.NewsAggrigationServer.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class NewsDTOTest {

    private NewsDTO newsDTO;

    @BeforeEach
    void setUp() {
        newsDTO = new NewsDTO();
    }

    @Test
    void testNewsDTOCreation() {
        assertNotNull(newsDTO);
    }

    @Test
    void testIdGetterAndSetter() {
        Integer expectedId = 1;
        newsDTO.setId(expectedId);
        assertEquals(expectedId, newsDTO.getId());
    }

    @Test
    void testTitleGetterAndSetter() {
        String expectedTitle = "Breaking News";
        newsDTO.setTitle(expectedTitle);
        assertEquals(expectedTitle, newsDTO.getTitle());
    }

    @Test
    void testDescriptionGetterAndSetter() {
        String expectedDescription = "This is a test news description";
        newsDTO.setDescription(expectedDescription);
        assertEquals(expectedDescription, newsDTO.getDescription());
    }

    @Test
    void testSourceGetterAndSetter() {
        String expectedSource = "CNN";
        newsDTO.setSource(expectedSource);
        assertEquals(expectedSource, newsDTO.getSource());
    }

    @Test
    void testUrlGetterAndSetter() {
        String expectedUrl = "https://example.com/news/1";
        newsDTO.setUrl(expectedUrl);
        assertEquals(expectedUrl, newsDTO.getUrl());
    }

    @Test
    void testPublishAtGetterAndSetter() {
        LocalDateTime expectedPublishAt = LocalDateTime.now();
        newsDTO.setPublishAt(expectedPublishAt);
        assertEquals(expectedPublishAt, newsDTO.getPublishAt());
    }

    @Test
    void testKeywordGetterAndSetter() {
        String expectedKeyword = "technology";
        newsDTO.setKeyword(expectedKeyword);
        assertEquals(expectedKeyword, newsDTO.getKeyword());
    }

    @Test
    void testLikeCountGetterAndSetter() {
        Integer expectedLikeCount = 100;
        newsDTO.setLikeCount(expectedLikeCount);
        assertEquals(expectedLikeCount, newsDTO.getLikeCount());
    }

    @Test
    void testDisLikeCountGetterAndSetter() {
        Integer expectedDisLikeCount = 10;
        newsDTO.setDisLikeCount(expectedDisLikeCount);
        assertEquals(expectedDisLikeCount, newsDTO.getDisLikeCount());
    }

    @Test
    void testReportCountGetterAndSetter() {
        Integer expectedReportCount = 5;
        newsDTO.setReportCount(expectedReportCount);
        assertEquals(expectedReportCount, newsDTO.getReportCount());
    }

    @Test
    void testIsHideGetterAndSetter() {
        Integer expectedIsHide = 0;
        newsDTO.setIsHide(expectedIsHide);
        assertEquals(expectedIsHide, newsDTO.getIsHide());
    }

    @Test
    void testNewsDTOWithAllFields() {
        Integer id = 1;
        String title = "Test News Title";
        String description = "Test news description";
        String source = "Test Source";
        String url = "https://test.com/news";
        LocalDateTime publishAt = LocalDateTime.of(2024, 1, 15, 12, 0, 0);
        String keyword = "test";
        Integer likeCount = 50;
        Integer disLikeCount = 5;
        Integer reportCount = 2;
        Integer isHide = 0;

        newsDTO.setId(id);
        newsDTO.setTitle(title);
        newsDTO.setDescription(description);
        newsDTO.setSource(source);
        newsDTO.setUrl(url);
        newsDTO.setPublishAt(publishAt);
        newsDTO.setKeyword(keyword);
        newsDTO.setLikeCount(likeCount);
        newsDTO.setDisLikeCount(disLikeCount);
        newsDTO.setReportCount(reportCount);
        newsDTO.setIsHide(isHide);

        assertEquals(id, newsDTO.getId());
        assertEquals(title, newsDTO.getTitle());
        assertEquals(description, newsDTO.getDescription());
        assertEquals(source, newsDTO.getSource());
        assertEquals(url, newsDTO.getUrl());
        assertEquals(publishAt, newsDTO.getPublishAt());
        assertEquals(keyword, newsDTO.getKeyword());
        assertEquals(likeCount, newsDTO.getLikeCount());
        assertEquals(disLikeCount, newsDTO.getDisLikeCount());
        assertEquals(reportCount, newsDTO.getReportCount());
        assertEquals(isHide, newsDTO.getIsHide());
    }

    @Test
    void testNewsDTOWithNullValues() {
        newsDTO.setId(null);
        newsDTO.setTitle(null);
        newsDTO.setDescription(null);
        newsDTO.setSource(null);
        newsDTO.setUrl(null);
        newsDTO.setPublishAt(null);
        newsDTO.setKeyword(null);
        newsDTO.setLikeCount(null);
        newsDTO.setDisLikeCount(null);
        newsDTO.setReportCount(null);
        newsDTO.setIsHide(null);

        assertNull(newsDTO.getId());
        assertNull(newsDTO.getTitle());
        assertNull(newsDTO.getDescription());
        assertNull(newsDTO.getSource());
        assertNull(newsDTO.getUrl());
        assertNull(newsDTO.getPublishAt());
        assertNull(newsDTO.getKeyword());
        assertNull(newsDTO.getLikeCount());
        assertNull(newsDTO.getDisLikeCount());
        assertNull(newsDTO.getReportCount());
        assertNull(newsDTO.getIsHide());
    }

    @Test
    void testNewsDTOWithEmptyStrings() {
        newsDTO.setTitle("");
        newsDTO.setDescription("");
        newsDTO.setSource("");
        newsDTO.setUrl("");
        newsDTO.setKeyword("");

        assertEquals("", newsDTO.getTitle());
        assertEquals("", newsDTO.getDescription());
        assertEquals("", newsDTO.getSource());
        assertEquals("", newsDTO.getUrl());
        assertEquals("", newsDTO.getKeyword());
    }

    @Test
    void testNewsDTOWithZeroValues() {
        newsDTO.setId(0);
        newsDTO.setLikeCount(0);
        newsDTO.setDisLikeCount(0);
        newsDTO.setReportCount(0);
        newsDTO.setIsHide(0);

        assertEquals(0, newsDTO.getId());
        assertEquals(0, newsDTO.getLikeCount());
        assertEquals(0, newsDTO.getDisLikeCount());
        assertEquals(0, newsDTO.getReportCount());
        assertEquals(0, newsDTO.getIsHide());
    }

    @Test
    void testNewsDTOWithNegativeValues() {
        newsDTO.setId(-1);
        newsDTO.setLikeCount(-10);
        newsDTO.setDisLikeCount(-5);
        newsDTO.setReportCount(-2);
        newsDTO.setIsHide(-1);

        assertEquals(-1, newsDTO.getId());
        assertEquals(-10, newsDTO.getLikeCount());
        assertEquals(-5, newsDTO.getDisLikeCount());
        assertEquals(-2, newsDTO.getReportCount());
        assertEquals(-1, newsDTO.getIsHide());
    }

    @Test
    void testNewsDTOWithLargeValues() {
        Integer largeId = Integer.MAX_VALUE;
        Integer largeLikeCount = Integer.MAX_VALUE - 1;
        Integer largeDisLikeCount = Integer.MAX_VALUE - 2;
        Integer largeReportCount = Integer.MAX_VALUE - 3;
        Integer largeIsHide = Integer.MAX_VALUE - 4;

        newsDTO.setId(largeId);
        newsDTO.setLikeCount(largeLikeCount);
        newsDTO.setDisLikeCount(largeDisLikeCount);
        newsDTO.setReportCount(largeReportCount);
        newsDTO.setIsHide(largeIsHide);

        assertEquals(largeId, newsDTO.getId());
        assertEquals(largeLikeCount, newsDTO.getLikeCount());
        assertEquals(largeDisLikeCount, newsDTO.getDisLikeCount());
        assertEquals(largeReportCount, newsDTO.getReportCount());
        assertEquals(largeIsHide, newsDTO.getIsHide());
    }

    @Test
    void testNewsDTOWithSpecialCharacters() {
        String titleWithSpecialChars = "News with @#$%^&*() symbols";
        String descriptionWithSpecialChars = "Description with émojis 🚀 and symbols ©®™";
        String sourceWithSpecialChars = "Source with !@# symbols";
        String urlWithSpecialChars = "https://example.com/news?param=value&other=123";
        String keywordWithSpecialChars = "tech@science#2023";

        newsDTO.setTitle(titleWithSpecialChars);
        newsDTO.setDescription(descriptionWithSpecialChars);
        newsDTO.setSource(sourceWithSpecialChars);
        newsDTO.setUrl(urlWithSpecialChars);
        newsDTO.setKeyword(keywordWithSpecialChars);

        assertEquals(titleWithSpecialChars, newsDTO.getTitle());
        assertEquals(descriptionWithSpecialChars, newsDTO.getDescription());
        assertEquals(sourceWithSpecialChars, newsDTO.getSource());
        assertEquals(urlWithSpecialChars, newsDTO.getUrl());
        assertEquals(keywordWithSpecialChars, newsDTO.getKeyword());
    }

    @Test
    void testNewsDTOWithLongStrings() {
        String longTitle = "This is a very long news title that contains many characters and should be properly handled by the getter and setter methods without any issues.";
        String longDescription = "This is a very long news description that contains many characters and should be properly handled by the getter and setter methods without any issues. It includes various types of content descriptions.";
        String longSource = "This is a very long news source name that contains many characters and should be properly handled";
        String longUrl = "https://example.com/very/long/url/path/that/contains/many/segments/and/parameters?param1=value1&param2=value2&param3=value3";
        String longKeyword = "very_long_keyword_name_that_contains_many_characters_and_should_be_properly_handled";

        newsDTO.setTitle(longTitle);
        newsDTO.setDescription(longDescription);
        newsDTO.setSource(longSource);
        newsDTO.setUrl(longUrl);
        newsDTO.setKeyword(longKeyword);

        assertEquals(longTitle, newsDTO.getTitle());
        assertEquals(longDescription, newsDTO.getDescription());
        assertEquals(longSource, newsDTO.getSource());
        assertEquals(longUrl, newsDTO.getUrl());
        assertEquals(longKeyword, newsDTO.getKeyword());
    }

    @Test
    void testNewsDTOWithHiddenNews() {
        newsDTO.setIsHide(1);
        assertEquals(1, newsDTO.getIsHide());
    }

    @Test
    void testNewsDTOWithVisibleNews() {
        newsDTO.setIsHide(0);
        assertEquals(0, newsDTO.getIsHide());
    }

    @Test
    void testNewsDTOToString() {
        newsDTO.setId(1);
        newsDTO.setTitle("Test News");
        newsDTO.setLikeCount(100);
        newsDTO.setDisLikeCount(10);
        newsDTO.setReportCount(5);
        newsDTO.setIsHide(0);

        String result = newsDTO.toString();
        assertNotNull(result);
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("title='Test News'"));
        assertTrue(result.contains("likeCount=100"));
        assertTrue(result.contains("disLikeCount=10"));
        assertTrue(result.contains("reportCount=5"));
        assertTrue(result.contains("isHide=0"));
    }
} 