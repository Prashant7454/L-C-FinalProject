package com.server.NewsAggrigationServer.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NewsCategoryTest {

    private NewsCategory newsCategory;

    @BeforeEach
    void setUp() {
        newsCategory = new NewsCategory();
    }

    @Test
    void testNewsCategoryCreation() {
        assertNotNull(newsCategory);
    }

    @Test
    void testNewsCategoryConstructorWithParameters() {
        Integer newsId = 1;
        Integer categoryId = 2;
        NewsCategory newsCategoryWithParams = new NewsCategory(newsId, categoryId);
        
        assertNotNull(newsCategoryWithParams);
        assertEquals(newsId, newsCategoryWithParams.getNewsId());
        assertEquals(categoryId, newsCategoryWithParams.getCategoryId());
        assertNull(newsCategoryWithParams.getId()); // Not set yet
    }

    @Test
    void testIdGetterAndSetter() {
        Integer expectedId = 1;
        newsCategory.setId(expectedId);
        assertEquals(expectedId, newsCategory.getId());
    }

    @Test
    void testNewsIdGetterAndSetter() {
        Integer expectedNewsId = 1;
        newsCategory.setNewsId(expectedNewsId);
        assertEquals(expectedNewsId, newsCategory.getNewsId());
    }

    @Test
    void testCategoryIdGetterAndSetter() {
        Integer expectedCategoryId = 2;
        newsCategory.setCategoryId(expectedCategoryId);
        assertEquals(expectedCategoryId, newsCategory.getCategoryId());
    }

    @Test
    void testNewsCategoryWithAllFields() {
        Integer id = 1;
        Integer newsId = 10;
        Integer categoryId = 20;

        newsCategory.setId(id);
        newsCategory.setNewsId(newsId);
        newsCategory.setCategoryId(categoryId);

        assertEquals(id, newsCategory.getId());
        assertEquals(newsId, newsCategory.getNewsId());
        assertEquals(categoryId, newsCategory.getCategoryId());
    }

    @Test
    void testNewsCategoryWithNullValues() {
        newsCategory.setId(null);
        newsCategory.setNewsId(null);
        newsCategory.setCategoryId(null);

        assertNull(newsCategory.getId());
        assertNull(newsCategory.getNewsId());
        assertNull(newsCategory.getCategoryId());
    }

    @Test
    void testNewsCategoryConstructorWithNullParameters() {
        NewsCategory newsCategoryWithNulls = new NewsCategory(null, null);
        
        assertNull(newsCategoryWithNulls.getNewsId());
        assertNull(newsCategoryWithNulls.getCategoryId());
        assertNull(newsCategoryWithNulls.getId());
    }

    @Test
    void testNewsCategoryConstructorWithMixedNullParameters() {
        NewsCategory newsCategoryWithMixedNulls = new NewsCategory(1, null);
        
        assertEquals(1, newsCategoryWithMixedNulls.getNewsId());
        assertNull(newsCategoryWithMixedNulls.getCategoryId());
        assertNull(newsCategoryWithMixedNulls.getId());
    }

    @Test
    void testNewsCategoryWithZeroValues() {
        newsCategory.setId(0);
        newsCategory.setNewsId(0);
        newsCategory.setCategoryId(0);

        assertEquals(0, newsCategory.getId());
        assertEquals(0, newsCategory.getNewsId());
        assertEquals(0, newsCategory.getCategoryId());
    }

    @Test
    void testNewsCategoryWithNegativeValues() {
        newsCategory.setId(-1);
        newsCategory.setNewsId(-10);
        newsCategory.setCategoryId(-20);

        assertEquals(-1, newsCategory.getId());
        assertEquals(-10, newsCategory.getNewsId());
        assertEquals(-20, newsCategory.getCategoryId());
    }

    @Test
    void testNewsCategoryWithLargeValues() {
        newsCategory.setId(Integer.MAX_VALUE);
        newsCategory.setNewsId(Integer.MAX_VALUE);
        newsCategory.setCategoryId(Integer.MAX_VALUE);

        assertEquals(Integer.MAX_VALUE, newsCategory.getId());
        assertEquals(Integer.MAX_VALUE, newsCategory.getNewsId());
        assertEquals(Integer.MAX_VALUE, newsCategory.getCategoryId());
    }

    @Test
    void testNewsCategoryWithMinIntegerValues() {
        newsCategory.setId(Integer.MIN_VALUE);
        newsCategory.setNewsId(Integer.MIN_VALUE);
        newsCategory.setCategoryId(Integer.MIN_VALUE);

        assertEquals(Integer.MIN_VALUE, newsCategory.getId());
        assertEquals(Integer.MIN_VALUE, newsCategory.getNewsId());
        assertEquals(Integer.MIN_VALUE, newsCategory.getCategoryId());
    }

    @Test
    void testNewsCategoryEquality() {
        NewsCategory newsCategory1 = new NewsCategory(1, 2);
        newsCategory1.setId(10);

        NewsCategory newsCategory2 = new NewsCategory(1, 2);
        newsCategory2.setId(10);

        assertEquals(newsCategory1.getId(), newsCategory2.getId());
        assertEquals(newsCategory1.getNewsId(), newsCategory2.getNewsId());
        assertEquals(newsCategory1.getCategoryId(), newsCategory2.getCategoryId());
    }

    @Test
    void testNewsCategoryWithSameNewsAndCategoryIds() {
        Integer sameId = 5;
        newsCategory.setNewsId(sameId);
        newsCategory.setCategoryId(sameId);

        assertEquals(sameId, newsCategory.getNewsId());
        assertEquals(sameId, newsCategory.getCategoryId());
    }

    @Test
    void testNewsCategoryWithDifferentNewsAndCategoryIds() {
        Integer newsId = 100;
        Integer categoryId = 200;
        newsCategory.setNewsId(newsId);
        newsCategory.setCategoryId(categoryId);

        assertEquals(newsId, newsCategory.getNewsId());
        assertEquals(categoryId, newsCategory.getCategoryId());
        assertNotEquals(newsCategory.getNewsId(), newsCategory.getCategoryId());
    }

    @Test
    void testNewsCategoryConstructorWithZeroParameters() {
        NewsCategory newsCategoryWithZeros = new NewsCategory(0, 0);
        
        assertEquals(0, newsCategoryWithZeros.getNewsId());
        assertEquals(0, newsCategoryWithZeros.getCategoryId());
        assertNull(newsCategoryWithZeros.getId());
    }

    @Test
    void testNewsCategoryConstructorWithNegativeParameters() {
        NewsCategory newsCategoryWithNegatives = new NewsCategory(-1, -2);
        
        assertEquals(-1, newsCategoryWithNegatives.getNewsId());
        assertEquals(-2, newsCategoryWithNegatives.getCategoryId());
        assertNull(newsCategoryWithNegatives.getId());
    }

    @Test
    void testNewsCategoryConstructorWithLargeParameters() {
        NewsCategory newsCategoryWithLarges = new NewsCategory(Integer.MAX_VALUE, Integer.MAX_VALUE);
        
        assertEquals(Integer.MAX_VALUE, newsCategoryWithLarges.getNewsId());
        assertEquals(Integer.MAX_VALUE, newsCategoryWithLarges.getCategoryId());
        assertNull(newsCategoryWithLarges.getId());
    }

    @Test
    void testNewsCategoryDefaultValues() {
        NewsCategory newNewsCategory = new NewsCategory();
        assertNull(newNewsCategory.getId());
        assertNull(newNewsCategory.getNewsId());
        assertNull(newNewsCategory.getCategoryId());
    }

    @Test
    void testNewsCategoryWithSequentialIds() {
        for (int i = 1; i <= 10; i++) {
            newsCategory.setId(i);
            newsCategory.setNewsId(i * 10);
            newsCategory.setCategoryId(i * 100);

            assertEquals(i, newsCategory.getId());
            assertEquals(i * 10, newsCategory.getNewsId());
            assertEquals(i * 100, newsCategory.getCategoryId());
        }
    }

    @Test
    void testNewsCategoryWithRandomValues() {
        Integer randomId = 42;
        Integer randomNewsId = 123;
        Integer randomCategoryId = 456;

        newsCategory.setId(randomId);
        newsCategory.setNewsId(randomNewsId);
        newsCategory.setCategoryId(randomCategoryId);

        assertEquals(randomId, newsCategory.getId());
        assertEquals(randomNewsId, newsCategory.getNewsId());
        assertEquals(randomCategoryId, newsCategory.getCategoryId());
    }
} 