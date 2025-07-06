package com.server.NewsAggrigationServer.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KeywordDTOTest {

    private KeywordDTO keywordDTO;

    @BeforeEach
    void setUp() {
        keywordDTO = new KeywordDTO();
    }

    @Test
    void testKeywordDTOCreation() {
        assertNotNull(keywordDTO);
    }

    @Test
    void testIdGetterAndSetter() {
        Integer expectedId = 1;
        keywordDTO.setId(expectedId);
        assertEquals(expectedId, keywordDTO.getId());
    }

    @Test
    void testNameGetterAndSetter() {
        String expectedName = "technology";
        keywordDTO.setName(expectedName);
        assertEquals(expectedName, keywordDTO.getName());
    }

    @Test
    void testKeywordDTOWithAllFields() {
        Integer id = 1;
        String name = "science";

        keywordDTO.setId(id);
        keywordDTO.setName(name);

        assertEquals(id, keywordDTO.getId());
        assertEquals(name, keywordDTO.getName());
    }

    @Test
    void testKeywordDTOWithNullValues() {
        keywordDTO.setId(null);
        keywordDTO.setName(null);

        assertNull(keywordDTO.getId());
        assertNull(keywordDTO.getName());
    }

    @Test
    void testKeywordDTOWithEmptyString() {
        keywordDTO.setName("");
        assertEquals("", keywordDTO.getName());
    }

    @Test
    void testKeywordDTOWithZeroValue() {
        keywordDTO.setId(0);
        assertEquals(0, keywordDTO.getId());
    }

    @Test
    void testKeywordDTOWithNegativeValue() {
        keywordDTO.setId(-1);
        assertEquals(-1, keywordDTO.getId());
    }

    @Test
    void testKeywordDTOWithLargeValue() {
        Integer largeId = Integer.MAX_VALUE;
        keywordDTO.setId(largeId);
        assertEquals(largeId, keywordDTO.getId());
    }

    @Test
    void testKeywordDTOWithSpecialCharacters() {
        String nameWithSpecialChars = "tech@science#2023";
        keywordDTO.setName(nameWithSpecialChars);
        assertEquals(nameWithSpecialChars, keywordDTO.getName());
    }

    @Test
    void testKeywordDTOWithUnicodeCharacters() {
        String nameWithUnicode = "tech_émojis_🚀";
        keywordDTO.setName(nameWithUnicode);
        assertEquals(nameWithUnicode, keywordDTO.getName());
    }

    @Test
    void testKeywordDTOWithLongString() {
        String longName = "very_long_keyword_name_that_contains_many_characters_and_should_be_properly_handled";
        keywordDTO.setName(longName);
        assertEquals(longName, keywordDTO.getName());
    }

    @Test
    void testKeywordDTOWithWhitespace() {
        String nameWithWhitespace = "  technology  ";
        keywordDTO.setName(nameWithWhitespace);
        assertEquals(nameWithWhitespace, keywordDTO.getName());
    }

    @Test
    void testKeywordDTOWithNewlines() {
        String nameWithNewlines = "tech\nology";
        keywordDTO.setName(nameWithNewlines);
        assertEquals(nameWithNewlines, keywordDTO.getName());
    }

    @Test
    void testKeywordDTOWithNumbers() {
        String nameWithNumbers = "tech123";
        keywordDTO.setName(nameWithNumbers);
        assertEquals(nameWithNumbers, keywordDTO.getName());
    }

    @Test
    void testKeywordDTOWithMixedCase() {
        String nameWithMixedCase = "TechNology";
        keywordDTO.setName(nameWithMixedCase);
        assertEquals(nameWithMixedCase, keywordDTO.getName());
    }

    @Test
    void testKeywordDTOWithUnderscores() {
        String nameWithUnderscores = "tech_news_2023";
        keywordDTO.setName(nameWithUnderscores);
        assertEquals(nameWithUnderscores, keywordDTO.getName());
    }

    @Test
    void testKeywordDTOWithHyphens() {
        String nameWithHyphens = "tech-news-2023";
        keywordDTO.setName(nameWithHyphens);
        assertEquals(nameWithHyphens, keywordDTO.getName());
    }

    @Test
    void testKeywordDTOWithMultipleWords() {
        String nameWithMultipleWords = "artificial intelligence";
        keywordDTO.setName(nameWithMultipleWords);
        assertEquals(nameWithMultipleWords, keywordDTO.getName());
    }

    @Test
    void testKeywordDTOWithSingleCharacter() {
        String singleChar = "a";
        keywordDTO.setName(singleChar);
        assertEquals(singleChar, keywordDTO.getName());
    }

    @Test
    void testKeywordDTOWithCommonKeywords() {
        String[] commonKeywords = {
            "technology", "science", "business", "politics", "sports",
            "entertainment", "health", "education", "environment", "finance"
        };

        for (String keyword : commonKeywords) {
            keywordDTO.setName(keyword);
            assertEquals(keyword, keywordDTO.getName());
        }
    }
} 