package com.server.NewsAggrigationServer.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoryKeywordTest {
    private CategoryKeyword categoryKeyword;

    @BeforeEach
    void setUp() {
        categoryKeyword = new CategoryKeyword();
    }

    @Test
    void testCategoryKeywordCreation() {
        assertNotNull(categoryKeyword);
    }

    @Test
    void testIdGetterAndSetter() {
        Integer expectedId = 1;
        categoryKeyword.setId(expectedId);
        assertEquals(expectedId, categoryKeyword.getId());
    }

    @Test
    void testCategoryIdGetterAndSetter() {
        Integer expectedCategoryId = 1;
        categoryKeyword.setCategoryId(expectedCategoryId);
        assertEquals(expectedCategoryId, categoryKeyword.getCategoryId());
    }

    @Test
    void testKeywordIdGetterAndSetter() {
        Integer expectedKeywordId = 1;
        categoryKeyword.setKeywordId(expectedKeywordId);
        assertEquals(expectedKeywordId, categoryKeyword.getKeywordId());
    }

    @Test
    void testCategoryKeywordWithAllFields() {
        Integer id = 1;
        Integer categoryId = 2;
        Integer keywordId = 3;

        categoryKeyword.setId(id);
        categoryKeyword.setCategoryId(categoryId);
        categoryKeyword.setKeywordId(keywordId);

        assertEquals(id, categoryKeyword.getId());
        assertEquals(categoryId, categoryKeyword.getCategoryId());
        assertEquals(keywordId, categoryKeyword.getKeywordId());
    }

    @Test
    void testCategoryKeywordWithNullValues() {
        categoryKeyword.setId(null);
        categoryKeyword.setCategoryId(null);
        categoryKeyword.setKeywordId(null);

        assertNull(categoryKeyword.getId());
        assertNull(categoryKeyword.getCategoryId());
        assertNull(categoryKeyword.getKeywordId());
    }

    @Test
    void testCategoryKeywordWithZeroValues() {
        categoryKeyword.setId(0);
        categoryKeyword.setCategoryId(0);
        categoryKeyword.setKeywordId(0);

        assertEquals(0, categoryKeyword.getId());
        assertEquals(0, categoryKeyword.getCategoryId());
        assertEquals(0, categoryKeyword.getKeywordId());
    }

    @Test
    void testCategoryKeywordWithNegativeValues() {
        categoryKeyword.setId(-1);
        categoryKeyword.setCategoryId(-2);
        categoryKeyword.setKeywordId(-3);

        assertEquals(-1, categoryKeyword.getId());
        assertEquals(-2, categoryKeyword.getCategoryId());
        assertEquals(-3, categoryKeyword.getKeywordId());
    }

    @Test
    void testCategoryKeywordWithLargeValues() {
        Integer largeId = Integer.MAX_VALUE;
        Integer largeCategoryId = Integer.MAX_VALUE - 1;
        Integer largeKeywordId = Integer.MAX_VALUE - 2;

        categoryKeyword.setId(largeId);
        categoryKeyword.setCategoryId(largeCategoryId);
        categoryKeyword.setKeywordId(largeKeywordId);

        assertEquals(largeId, categoryKeyword.getId());
        assertEquals(largeCategoryId, categoryKeyword.getCategoryId());
        assertEquals(largeKeywordId, categoryKeyword.getKeywordId());
    }
} 