package com.server.NewsAggrigationServer.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NewsCategoryDTOTest {

    private NewsCategoryDTO newsCategoryDTO;

    @BeforeEach
    void setUp() {
        newsCategoryDTO = new NewsCategoryDTO();
    }

    @Test
    void testNewsCategoryDTOCreation() {
        assertNotNull(newsCategoryDTO);
    }

    @Test
    void testIdGetterAndSetter() {
        Integer expectedId = 1;
        newsCategoryDTO.setId(expectedId);
        assertEquals(expectedId, newsCategoryDTO.getId());
    }

    @Test
    void testNewsIdGetterAndSetter() {
        Integer expectedNewsId = 1;
        newsCategoryDTO.setNewsId(expectedNewsId);
        assertEquals(expectedNewsId, newsCategoryDTO.getNewsId());
    }

    @Test
    void testCategoryIdGetterAndSetter() {
        Integer expectedCategoryId = 1;
        newsCategoryDTO.setCategoryId(expectedCategoryId);
        assertEquals(expectedCategoryId, newsCategoryDTO.getCategoryId());
    }

    @Test
    void testNewsCategoryDTOWithAllFields() {
        Integer id = 1;
        Integer newsId = 2;
        Integer categoryId = 3;

        newsCategoryDTO.setId(id);
        newsCategoryDTO.setNewsId(newsId);
        newsCategoryDTO.setCategoryId(categoryId);

        assertEquals(id, newsCategoryDTO.getId());
        assertEquals(newsId, newsCategoryDTO.getNewsId());
        assertEquals(categoryId, newsCategoryDTO.getCategoryId());
    }

    @Test
    void testNewsCategoryDTOWithNullValues() {
        newsCategoryDTO.setId(null);
        newsCategoryDTO.setNewsId(null);
        newsCategoryDTO.setCategoryId(null);

        assertNull(newsCategoryDTO.getId());
        assertNull(newsCategoryDTO.getNewsId());
        assertNull(newsCategoryDTO.getCategoryId());
    }

    @Test
    void testNewsCategoryDTOWithZeroValues() {
        newsCategoryDTO.setId(0);
        newsCategoryDTO.setNewsId(0);
        newsCategoryDTO.setCategoryId(0);

        assertEquals(0, newsCategoryDTO.getId());
        assertEquals(0, newsCategoryDTO.getNewsId());
        assertEquals(0, newsCategoryDTO.getCategoryId());
    }

    @Test
    void testNewsCategoryDTOWithNegativeValues() {
        newsCategoryDTO.setId(-1);
        newsCategoryDTO.setNewsId(-2);
        newsCategoryDTO.setCategoryId(-3);

        assertEquals(-1, newsCategoryDTO.getId());
        assertEquals(-2, newsCategoryDTO.getNewsId());
        assertEquals(-3, newsCategoryDTO.getCategoryId());
    }

    @Test
    void testNewsCategoryDTOWithLargeValues() {
        Integer largeId = Integer.MAX_VALUE;
        Integer largeNewsId = Integer.MAX_VALUE - 1;
        Integer largeCategoryId = Integer.MAX_VALUE - 2;

        newsCategoryDTO.setId(largeId);
        newsCategoryDTO.setNewsId(largeNewsId);
        newsCategoryDTO.setCategoryId(largeCategoryId);

        assertEquals(largeId, newsCategoryDTO.getId());
        assertEquals(largeNewsId, newsCategoryDTO.getNewsId());
        assertEquals(largeCategoryId, newsCategoryDTO.getCategoryId());
    }

    @Test
    void testNewsCategoryDTOWithDifferentCombinations() {
        // Test different combinations of values
        Integer[][] testValues = {
            {1, 1, 1},
            {100, 200, 300},
            {999, 888, 777},
            {Integer.MAX_VALUE, Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 2}
        };

        for (Integer[] values : testValues) {
            newsCategoryDTO.setId(values[0]);
            newsCategoryDTO.setNewsId(values[1]);
            newsCategoryDTO.setCategoryId(values[2]);

            assertEquals(values[0], newsCategoryDTO.getId());
            assertEquals(values[1], newsCategoryDTO.getNewsId());
            assertEquals(values[2], newsCategoryDTO.getCategoryId());
        }
    }

    @Test
    void testNewsCategoryDTOWithSameValues() {
        Integer sameValue = 42;
        newsCategoryDTO.setId(sameValue);
        newsCategoryDTO.setNewsId(sameValue);
        newsCategoryDTO.setCategoryId(sameValue);

        assertEquals(sameValue, newsCategoryDTO.getId());
        assertEquals(sameValue, newsCategoryDTO.getNewsId());
        assertEquals(sameValue, newsCategoryDTO.getCategoryId());
    }

    @Test
    void testNewsCategoryDTOWithSequentialValues() {
        for (int i = 1; i <= 10; i++) {
            newsCategoryDTO.setId(i);
            newsCategoryDTO.setNewsId(i * 10);
            newsCategoryDTO.setCategoryId(i * 100);

            assertEquals(i, newsCategoryDTO.getId());
            assertEquals(i * 10, newsCategoryDTO.getNewsId());
            assertEquals(i * 100, newsCategoryDTO.getCategoryId());
        }
    }

    @Test
    void testNewsCategoryDTOWithBoundaryValues() {
        // Test minimum values
        newsCategoryDTO.setId(0);
        newsCategoryDTO.setNewsId(0);
        newsCategoryDTO.setCategoryId(0);

        assertEquals(0, newsCategoryDTO.getId());
        assertEquals(0, newsCategoryDTO.getNewsId());
        assertEquals(0, newsCategoryDTO.getCategoryId());

        // Test maximum values
        newsCategoryDTO.setId(Integer.MAX_VALUE);
        newsCategoryDTO.setNewsId(Integer.MAX_VALUE);
        newsCategoryDTO.setCategoryId(Integer.MAX_VALUE);

        assertEquals(Integer.MAX_VALUE, newsCategoryDTO.getId());
        assertEquals(Integer.MAX_VALUE, newsCategoryDTO.getNewsId());
        assertEquals(Integer.MAX_VALUE, newsCategoryDTO.getCategoryId());
    }

    @Test
    void testNewsCategoryDTOWithRealisticValues() {
        // Test with realistic values that might be used in a real application
        Integer[] realisticIds = {1, 5, 10, 25, 50, 100, 500, 1000};

        for (Integer id : realisticIds) {
            newsCategoryDTO.setId(id);
            newsCategoryDTO.setNewsId(id * 2);
            newsCategoryDTO.setCategoryId(id * 3);

            assertEquals(id, newsCategoryDTO.getId());
            assertEquals(id * 2, newsCategoryDTO.getNewsId());
            assertEquals(id * 3, newsCategoryDTO.getCategoryId());
        }
    }

    @Test
    void testNewsCategoryDTOWithCategoryMapping() {
        // Test mapping between news and categories
        Integer[][] newsCategoryMappings = {
            {1, 1, 1},   // News 1 -> Category 1
            {2, 1, 2},   // News 2 -> Category 2
            {3, 2, 1},   // News 3 -> Category 1
            {4, 2, 3},   // News 4 -> Category 3
            {5, 3, 2}    // News 5 -> Category 2
        };

        for (Integer[] mapping : newsCategoryMappings) {
            newsCategoryDTO.setId(mapping[0]);
            newsCategoryDTO.setNewsId(mapping[1]);
            newsCategoryDTO.setCategoryId(mapping[2]);

            assertEquals(mapping[0], newsCategoryDTO.getId());
            assertEquals(mapping[1], newsCategoryDTO.getNewsId());
            assertEquals(mapping[2], newsCategoryDTO.getCategoryId());
        }
    }
} 