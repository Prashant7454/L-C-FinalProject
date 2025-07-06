package com.server.NewsAggrigationServer.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KeywordTest {

    private Keyword keyword;

    @BeforeEach
    void setUp() {
        keyword = new Keyword();
    }

    @Test
    void testKeywordCreation() {
        assertNotNull(keyword);
    }

    @Test
    void testIdGetterAndSetter() {
        Integer expectedId = 1;
        keyword.setId(expectedId);
        assertEquals(expectedId, keyword.getId());
    }

    @Test
    void testNameGetterAndSetter() {
        String expectedName = "technology";
        keyword.setName(expectedName);
        assertEquals(expectedName, keyword.getName());
    }

    @Test
    void testKeywordWithAllFields() {
        Integer id = 1;
        String name = "technology";

        keyword.setId(id);
        keyword.setName(name);

        assertEquals(id, keyword.getId());
        assertEquals(name, keyword.getName());
    }

    @Test
    void testKeywordWithNullValues() {
        keyword.setId(null);
        keyword.setName(null);

        assertNull(keyword.getId());
        assertNull(keyword.getName());
    }

    @Test
    void testKeywordWithEmptyName() {
        keyword.setName("");
        assertEquals("", keyword.getName());
    }

    @Test
    void testKeywordWithSpecialCharacters() {
        String nameWithSpecial = "tech@science#2023";
        keyword.setName(nameWithSpecial);
        assertEquals(nameWithSpecial, keyword.getName());
    }

    @Test
    void testKeywordWithUnicodeCharacters() {
        String nameWithUnicode = "tech_émojis_🚀";
        keyword.setName(nameWithUnicode);
        assertEquals(nameWithUnicode, keyword.getName());
    }

    @Test
    void testKeywordWithLongName() {
        String longName = "very_long_keyword_name_that_contains_many_characters_and_should_be_properly_handled";
        keyword.setName(longName);
        assertEquals(longName, keyword.getName());
    }

    @Test
    void testKeywordWithWhitespace() {
        keyword.setName("  technology  ");
        assertEquals("  technology  ", keyword.getName());
    }

    @Test
    void testKeywordWithNumbers() {
        keyword.setName("technology2023");
        assertEquals("technology2023", keyword.getName());
    }

    @Test
    void testKeywordWithUnderscores() {
        keyword.setName("tech_science_2023");
        assertEquals("tech_science_2023", keyword.getName());
    }

    @Test
    void testKeywordWithHyphens() {
        keyword.setName("tech-science-2023");
        assertEquals("tech-science-2023", keyword.getName());
    }

    @Test
    void testKeywordWithMixedCase() {
        keyword.setName("TechScience2023");
        assertEquals("TechScience2023", keyword.getName());
    }

    @Test
    void testKeywordWithSingleCharacter() {
        keyword.setName("a");
        assertEquals("a", keyword.getName());
    }

    @Test
    void testKeywordWithSpaces() {
        keyword.setName("tech science");
        assertEquals("tech science", keyword.getName());
    }

    @Test
    void testKeywordWithNewlines() {
        keyword.setName("tech\nscience");
        assertEquals("tech\nscience", keyword.getName());
    }

    @Test
    void testKeywordWithTabs() {
        keyword.setName("tech\tscience");
        assertEquals("tech\tscience", keyword.getName());
    }

    @Test
    void testKeywordEquality() {
        Keyword keyword1 = new Keyword();
        keyword1.setId(1);
        keyword1.setName("technology");

        Keyword keyword2 = new Keyword();
        keyword2.setId(1);
        keyword2.setName("technology");

        assertEquals(keyword1.getId(), keyword2.getId());
        assertEquals(keyword1.getName(), keyword2.getName());
    }

    @Test
    void testKeywordWithNegativeId() {
        keyword.setId(-1);
        assertEquals(-1, keyword.getId());
    }

    @Test
    void testKeywordWithZeroId() {
        keyword.setId(0);
        assertEquals(0, keyword.getId());
    }

    @Test
    void testKeywordWithLargeId() {
        keyword.setId(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, keyword.getId());
    }

    @Test
    void testKeywordWithMinIntegerId() {
        keyword.setId(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, keyword.getId());
    }

    @Test
    void testKeywordWithEmojiName() {
        keyword.setName("🚀");
        assertEquals("🚀", keyword.getName());
    }

    @Test
    void testKeywordWithSymbols() {
        keyword.setName("!@#$%^&*()");
        assertEquals("!@#$%^&*()", keyword.getName());
    }

    @Test
    void testKeywordWithUnicodeSymbols() {
        keyword.setName("©®™");
        assertEquals("©®™", keyword.getName());
    }

    @Test
    void testKeywordWithVeryLongName() {
        StringBuilder longName = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            longName.append("a");
        }
        keyword.setName(longName.toString());
        assertEquals(longName.toString(), keyword.getName());
    }

    @Test
    void testKeywordWithNullId() {
        keyword.setId(null);
        assertNull(keyword.getId());
    }

    @Test
    void testKeywordWithNullName() {
        keyword.setName(null);
        assertNull(keyword.getName());
    }
} 