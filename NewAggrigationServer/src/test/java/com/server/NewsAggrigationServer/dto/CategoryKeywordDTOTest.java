package com.server.NewsAggrigationServer.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoryKeywordDTOTest {

    private CategoryKeywordDTO categoryKeywordDTO;

    @BeforeEach
    void setUp() {
        categoryKeywordDTO = new CategoryKeywordDTO();
    }

    @Test
    void testCategoryKeywordDTOCreation() {
        assertNotNull(categoryKeywordDTO);
    }

    @Test
    void testIdGetterAndSetter() {
        Integer expectedId = 1;
        categoryKeywordDTO.setId(expectedId);
        assertEquals(expectedId, categoryKeywordDTO.getId());
    }

    @Test
    void testCategoryIdGetterAndSetter() {
        Integer expectedCategoryId = 1;
        categoryKeywordDTO.setCategoryId(expectedCategoryId);
        assertEquals(expectedCategoryId, categoryKeywordDTO.getCategoryId());
    }

    @Test
    void testKeywordIdGetterAndSetter() {
        Integer expectedKeywordId = 1;
        categoryKeywordDTO.setKeywordId(expectedKeywordId);
        assertEquals(expectedKeywordId, categoryKeywordDTO.getKeywordId());
    }

    @Test
    void testCategoryKeywordDTOWithAllFields() {
        Integer id = 1;
        Integer categoryId = 2;
        Integer keywordId = 3;

        categoryKeywordDTO.setId(id);
        categoryKeywordDTO.setCategoryId(categoryId);
        categoryKeywordDTO.setKeywordId(keywordId);

        assertEquals(id, categoryKeywordDTO.getId());
        assertEquals(categoryId, categoryKeywordDTO.getCategoryId());
        assertEquals(keywordId, categoryKeywordDTO.getKeywordId());
    }

    @Test
    void testCategoryKeywordDTOWithNullValues() {
        categoryKeywordDTO.setId(null);
        categoryKeywordDTO.setCategoryId(null);
        categoryKeywordDTO.setKeywordId(null);

        assertNull(categoryKeywordDTO.getId());
        assertNull(categoryKeywordDTO.getCategoryId());
        assertNull(categoryKeywordDTO.getKeywordId());
    }

    @Test
    void testCategoryKeywordDTOWithZeroValues() {
        categoryKeywordDTO.setId(0);
        categoryKeywordDTO.setCategoryId(0);
        categoryKeywordDTO.setKeywordId(0);

        assertEquals(0, categoryKeywordDTO.getId());
        assertEquals(0, categoryKeywordDTO.getCategoryId());
        assertEquals(0, categoryKeywordDTO.getKeywordId());
    }

    @Test
    void testCategoryKeywordDTOWithNegativeValues() {
        categoryKeywordDTO.setId(-1);
        categoryKeywordDTO.setCategoryId(-2);
        categoryKeywordDTO.setKeywordId(-3);

        assertEquals(-1, categoryKeywordDTO.getId());
        assertEquals(-2, categoryKeywordDTO.getCategoryId());
        assertEquals(-3, categoryKeywordDTO.getKeywordId());
    }

    @Test
    void testCategoryKeywordDTOWithLargeValues() {
        Integer largeId = Integer.MAX_VALUE;
        Integer largeCategoryId = Integer.MAX_VALUE - 1;
        Integer largeKeywordId = Integer.MAX_VALUE - 2;

        categoryKeywordDTO.setId(largeId);
        categoryKeywordDTO.setCategoryId(largeCategoryId);
        categoryKeywordDTO.setKeywordId(largeKeywordId);

        assertEquals(largeId, categoryKeywordDTO.getId());
        assertEquals(largeCategoryId, categoryKeywordDTO.getCategoryId());
        assertEquals(largeKeywordId, categoryKeywordDTO.getKeywordId());
    }

    @Test
    void testCategoryKeywordDTOWithDifferentCombinations() {
        // Test different combinations of values
        Integer[][] testValues = {
            {1, 1, 1},
            {100, 200, 300},
            {999, 888, 777},
            {Integer.MAX_VALUE, Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 2}
        };

        for (Integer[] values : testValues) {
            categoryKeywordDTO.setId(values[0]);
            categoryKeywordDTO.setCategoryId(values[1]);
            categoryKeywordDTO.setKeywordId(values[2]);

            assertEquals(values[0], categoryKeywordDTO.getId());
            assertEquals(values[1], categoryKeywordDTO.getCategoryId());
            assertEquals(values[2], categoryKeywordDTO.getKeywordId());
        }
    }

    @Test
    void testCategoryKeywordDTOWithSameValues() {
        Integer sameValue = 42;
        categoryKeywordDTO.setId(sameValue);
        categoryKeywordDTO.setCategoryId(sameValue);
        categoryKeywordDTO.setKeywordId(sameValue);

        assertEquals(sameValue, categoryKeywordDTO.getId());
        assertEquals(sameValue, categoryKeywordDTO.getCategoryId());
        assertEquals(sameValue, categoryKeywordDTO.getKeywordId());
    }

    @Test
    void testCategoryKeywordDTOWithSequentialValues() {
        for (int i = 1; i <= 10; i++) {
            categoryKeywordDTO.setId(i);
            categoryKeywordDTO.setCategoryId(i * 10);
            categoryKeywordDTO.setKeywordId(i * 100);

            assertEquals(i, categoryKeywordDTO.getId());
            assertEquals(i * 10, categoryKeywordDTO.getCategoryId());
            assertEquals(i * 100, categoryKeywordDTO.getKeywordId());
        }
    }

    @Test
    void testCategoryKeywordDTOWithBoundaryValues() {
        // Test minimum values
        categoryKeywordDTO.setId(0);
        categoryKeywordDTO.setCategoryId(0);
        categoryKeywordDTO.setKeywordId(0);

        assertEquals(0, categoryKeywordDTO.getId());
        assertEquals(0, categoryKeywordDTO.getCategoryId());
        assertEquals(0, categoryKeywordDTO.getKeywordId());

        // Test maximum values
        categoryKeywordDTO.setId(Integer.MAX_VALUE);
        categoryKeywordDTO.setCategoryId(Integer.MAX_VALUE);
        categoryKeywordDTO.setKeywordId(Integer.MAX_VALUE);

        assertEquals(Integer.MAX_VALUE, categoryKeywordDTO.getId());
        assertEquals(Integer.MAX_VALUE, categoryKeywordDTO.getCategoryId());
        assertEquals(Integer.MAX_VALUE, categoryKeywordDTO.getKeywordId());
    }

    @Test
    void testCategoryKeywordDTOWithRealisticValues() {
        // Test with realistic values that might be used in a real application
        Integer[] realisticIds = {1, 5, 10, 25, 50, 100, 500, 1000};

        for (Integer id : realisticIds) {
            categoryKeywordDTO.setId(id);
            categoryKeywordDTO.setCategoryId(id * 2);
            categoryKeywordDTO.setKeywordId(id * 3);

            assertEquals(id, categoryKeywordDTO.getId());
            assertEquals(id * 2, categoryKeywordDTO.getCategoryId());
            assertEquals(id * 3, categoryKeywordDTO.getKeywordId());
        }
    }

    @Test
    void testCategoryKeywordDTOWithCategoryKeywordMapping() {
        // Test mapping between categories and keywords
        Integer[][] categoryKeywordMappings = {
            {1, 1, 1},   // Category 1 -> Keyword 1
            {2, 1, 2},   // Category 1 -> Keyword 2
            {3, 2, 1},   // Category 2 -> Keyword 1
            {4, 2, 3},   // Category 2 -> Keyword 3
            {5, 3, 2}    // Category 3 -> Keyword 2
        };

        for (Integer[] mapping : categoryKeywordMappings) {
            categoryKeywordDTO.setId(mapping[0]);
            categoryKeywordDTO.setCategoryId(mapping[1]);
            categoryKeywordDTO.setKeywordId(mapping[2]);

            assertEquals(mapping[0], categoryKeywordDTO.getId());
            assertEquals(mapping[1], categoryKeywordDTO.getCategoryId());
            assertEquals(mapping[2], categoryKeywordDTO.getKeywordId());
        }
    }

    @Test
    void testCategoryKeywordDTOWithMultipleKeywordsPerCategory() {
        // Test multiple keywords for the same category
        Integer categoryId = 1;
        Integer[] keywordIds = {1, 2, 3, 4, 5};

        for (int i = 0; i < keywordIds.length; i++) {
            categoryKeywordDTO.setId(i + 1);
            categoryKeywordDTO.setCategoryId(categoryId);
            categoryKeywordDTO.setKeywordId(keywordIds[i]);

            assertEquals(i + 1, categoryKeywordDTO.getId());
            assertEquals(categoryId, categoryKeywordDTO.getCategoryId());
            assertEquals(keywordIds[i], categoryKeywordDTO.getKeywordId());
        }
    }

    @Test
    void testCategoryKeywordDTOWithMultipleCategoriesPerKeyword() {
        // Test multiple categories for the same keyword
        Integer keywordId = 1;
        Integer[] categoryIds = {1, 2, 3, 4, 5};

        for (int i = 0; i < categoryIds.length; i++) {
            categoryKeywordDTO.setId(i + 1);
            categoryKeywordDTO.setCategoryId(categoryIds[i]);
            categoryKeywordDTO.setKeywordId(keywordId);

            assertEquals(i + 1, categoryKeywordDTO.getId());
            assertEquals(categoryIds[i], categoryKeywordDTO.getCategoryId());
            assertEquals(keywordId, categoryKeywordDTO.getKeywordId());
        }
    }

    @Test
    void testCategoryKeywordDTOWithCommonMappings() {
        // Test common category-keyword mappings that might be used in a news application
        Integer[][] commonMappings = {
            {1, 1, 1},   // Technology -> "AI"
            {2, 1, 2},   // Technology -> "Machine Learning"
            {3, 1, 3},   // Technology -> "Programming"
            {4, 2, 4},   // Science -> "Research"
            {5, 2, 5},   // Science -> "Discovery"
            {6, 3, 6},   // Business -> "Finance"
            {7, 3, 7},   // Business -> "Investment"
            {8, 4, 8},   // Politics -> "Election"
            {9, 4, 9},   // Politics -> "Government"
            {10, 5, 10}  // Sports -> "Football"
        };

        for (Integer[] mapping : commonMappings) {
            categoryKeywordDTO.setId(mapping[0]);
            categoryKeywordDTO.setCategoryId(mapping[1]);
            categoryKeywordDTO.setKeywordId(mapping[2]);

            assertEquals(mapping[0], categoryKeywordDTO.getId());
            assertEquals(mapping[1], categoryKeywordDTO.getCategoryId());
            assertEquals(mapping[2], categoryKeywordDTO.getKeywordId());
        }
    }
} 