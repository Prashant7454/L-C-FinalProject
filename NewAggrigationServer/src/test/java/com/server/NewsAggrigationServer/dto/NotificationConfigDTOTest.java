package com.server.NewsAggrigationServer.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NotificationConfigDTOTest {

    private NotificationConfigDTO notificationConfigDTO;

    @BeforeEach
    void setUp() {
        notificationConfigDTO = new NotificationConfigDTO();
    }

    @Test
    void testNotificationConfigDTOCreation() {
        assertNotNull(notificationConfigDTO);
    }

    @Test
    void testIdGetterAndSetter() {
        Integer expectedId = 1;
        notificationConfigDTO.setId(expectedId);
        assertEquals(expectedId, notificationConfigDTO.getId());
    }

    @Test
    void testUserIdGetterAndSetter() {
        Integer expectedUserId = 1;
        notificationConfigDTO.setUserId(expectedUserId);
        assertEquals(expectedUserId, notificationConfigDTO.getUserId());
    }

    @Test
    void testCategoryIdGetterAndSetter() {
        Integer expectedCategoryId = 1;
        notificationConfigDTO.setCategoryId(expectedCategoryId);
        assertEquals(expectedCategoryId, notificationConfigDTO.getCategoryId());
    }

    @Test
    void testIsEnabledGetterAndSetter() {
        Integer expectedIsEnabled = 1;
        notificationConfigDTO.setIsEnabled(expectedIsEnabled);
        assertEquals(expectedIsEnabled, notificationConfigDTO.getIsEnabled());
    }

    @Test
    void testNotificationConfigDTOWithAllFields() {
        Integer id = 1;
        Integer userId = 2;
        Integer categoryId = 3;
        Integer isEnabled = 1;

        notificationConfigDTO.setId(id);
        notificationConfigDTO.setUserId(userId);
        notificationConfigDTO.setCategoryId(categoryId);
        notificationConfigDTO.setIsEnabled(isEnabled);

        assertEquals(id, notificationConfigDTO.getId());
        assertEquals(userId, notificationConfigDTO.getUserId());
        assertEquals(categoryId, notificationConfigDTO.getCategoryId());
        assertEquals(isEnabled, notificationConfigDTO.getIsEnabled());
    }

    @Test
    void testNotificationConfigDTOWithNullValues() {
        notificationConfigDTO.setId(null);
        notificationConfigDTO.setUserId(null);
        notificationConfigDTO.setCategoryId(null);
        notificationConfigDTO.setIsEnabled(null);

        assertNull(notificationConfigDTO.getId());
        assertNull(notificationConfigDTO.getUserId());
        assertNull(notificationConfigDTO.getCategoryId());
        assertNull(notificationConfigDTO.getIsEnabled());
    }

    @Test
    void testNotificationConfigDTOWithZeroValues() {
        notificationConfigDTO.setId(0);
        notificationConfigDTO.setUserId(0);
        notificationConfigDTO.setCategoryId(0);
        notificationConfigDTO.setIsEnabled(0);

        assertEquals(0, notificationConfigDTO.getId());
        assertEquals(0, notificationConfigDTO.getUserId());
        assertEquals(0, notificationConfigDTO.getCategoryId());
        assertEquals(0, notificationConfigDTO.getIsEnabled());
    }

    @Test
    void testNotificationConfigDTOWithNegativeValues() {
        notificationConfigDTO.setId(-1);
        notificationConfigDTO.setUserId(-2);
        notificationConfigDTO.setCategoryId(-3);
        notificationConfigDTO.setIsEnabled(-1);

        assertEquals(-1, notificationConfigDTO.getId());
        assertEquals(-2, notificationConfigDTO.getUserId());
        assertEquals(-3, notificationConfigDTO.getCategoryId());
        assertEquals(-1, notificationConfigDTO.getIsEnabled());
    }

    @Test
    void testNotificationConfigDTOWithLargeValues() {
        Integer largeId = Integer.MAX_VALUE;
        Integer largeUserId = Integer.MAX_VALUE - 1;
        Integer largeCategoryId = Integer.MAX_VALUE - 2;
        Integer largeIsEnabled = Integer.MAX_VALUE - 3;

        notificationConfigDTO.setId(largeId);
        notificationConfigDTO.setUserId(largeUserId);
        notificationConfigDTO.setCategoryId(largeCategoryId);
        notificationConfigDTO.setIsEnabled(largeIsEnabled);

        assertEquals(largeId, notificationConfigDTO.getId());
        assertEquals(largeUserId, notificationConfigDTO.getUserId());
        assertEquals(largeCategoryId, notificationConfigDTO.getCategoryId());
        assertEquals(largeIsEnabled, notificationConfigDTO.getIsEnabled());
    }

    @Test
    void testNotificationConfigDTOWithEnabledValue() {
        notificationConfigDTO.setIsEnabled(1);
        assertEquals(1, notificationConfigDTO.getIsEnabled());
    }

    @Test
    void testNotificationConfigDTOWithDisabledValue() {
        notificationConfigDTO.setIsEnabled(0);
        assertEquals(0, notificationConfigDTO.getIsEnabled());
    }

    @Test
    void testNotificationConfigDTOWithDifferentCombinations() {
        // Test different combinations of values
        Integer[][] testValues = {
            {1, 1, 1, 1},   // Enabled
            {2, 1, 2, 0},   // Disabled
            {3, 2, 1, 1},   // Enabled
            {4, 2, 3, 0},   // Disabled
            {5, 3, 2, 1}    // Enabled
        };

        for (Integer[] values : testValues) {
            notificationConfigDTO.setId(values[0]);
            notificationConfigDTO.setUserId(values[1]);
            notificationConfigDTO.setCategoryId(values[2]);
            notificationConfigDTO.setIsEnabled(values[3]);

            assertEquals(values[0], notificationConfigDTO.getId());
            assertEquals(values[1], notificationConfigDTO.getUserId());
            assertEquals(values[2], notificationConfigDTO.getCategoryId());
            assertEquals(values[3], notificationConfigDTO.getIsEnabled());
        }
    }

    @Test
    void testNotificationConfigDTOWithSameValues() {
        Integer sameValue = 42;
        notificationConfigDTO.setId(sameValue);
        notificationConfigDTO.setUserId(sameValue);
        notificationConfigDTO.setCategoryId(sameValue);
        notificationConfigDTO.setIsEnabled(1);

        assertEquals(sameValue, notificationConfigDTO.getId());
        assertEquals(sameValue, notificationConfigDTO.getUserId());
        assertEquals(sameValue, notificationConfigDTO.getCategoryId());
        assertEquals(1, notificationConfigDTO.getIsEnabled());
    }

    @Test
    void testNotificationConfigDTOWithSequentialValues() {
        for (int i = 1; i <= 10; i++) {
            notificationConfigDTO.setId(i);
            notificationConfigDTO.setUserId(i * 10);
            notificationConfigDTO.setCategoryId(i * 100);
            notificationConfigDTO.setIsEnabled(i % 2); // Alternate between 0 and 1

            assertEquals(i, notificationConfigDTO.getId());
            assertEquals(i * 10, notificationConfigDTO.getUserId());
            assertEquals(i * 100, notificationConfigDTO.getCategoryId());
            assertEquals(i % 2, notificationConfigDTO.getIsEnabled());
        }
    }

    @Test
    void testNotificationConfigDTOWithBoundaryValues() {
        // Test minimum values
        notificationConfigDTO.setId(0);
        notificationConfigDTO.setUserId(0);
        notificationConfigDTO.setCategoryId(0);
        notificationConfigDTO.setIsEnabled(0);

        assertEquals(0, notificationConfigDTO.getId());
        assertEquals(0, notificationConfigDTO.getUserId());
        assertEquals(0, notificationConfigDTO.getCategoryId());
        assertEquals(0, notificationConfigDTO.getIsEnabled());

        // Test maximum values
        notificationConfigDTO.setId(Integer.MAX_VALUE);
        notificationConfigDTO.setUserId(Integer.MAX_VALUE);
        notificationConfigDTO.setCategoryId(Integer.MAX_VALUE);
        notificationConfigDTO.setIsEnabled(1);

        assertEquals(Integer.MAX_VALUE, notificationConfigDTO.getId());
        assertEquals(Integer.MAX_VALUE, notificationConfigDTO.getUserId());
        assertEquals(Integer.MAX_VALUE, notificationConfigDTO.getCategoryId());
        assertEquals(1, notificationConfigDTO.getIsEnabled());
    }

    @Test
    void testNotificationConfigDTOWithRealisticValues() {
        // Test with realistic values that might be used in a real application
        Integer[] realisticIds = {1, 5, 10, 25, 50, 100, 500, 1000};

        for (Integer id : realisticIds) {
            notificationConfigDTO.setId(id);
            notificationConfigDTO.setUserId(id * 2);
            notificationConfigDTO.setCategoryId(id * 3);
            notificationConfigDTO.setIsEnabled(id % 2); // Alternate between 0 and 1

            assertEquals(id, notificationConfigDTO.getId());
            assertEquals(id * 2, notificationConfigDTO.getUserId());
            assertEquals(id * 3, notificationConfigDTO.getCategoryId());
            assertEquals(id % 2, notificationConfigDTO.getIsEnabled());
        }
    }

    @Test
    void testNotificationConfigDTOWithCategoryMapping() {
        // Test mapping between users and categories
        Integer[][] userCategoryMappings = {
            {1, 1, 1, 1},   // User 1 -> Category 1 -> Enabled
            {2, 1, 2, 0},   // User 1 -> Category 2 -> Disabled
            {3, 2, 1, 1},   // User 2 -> Category 1 -> Enabled
            {4, 2, 3, 0},   // User 2 -> Category 3 -> Disabled
            {5, 3, 2, 1}    // User 3 -> Category 2 -> Enabled
        };

        for (Integer[] mapping : userCategoryMappings) {
            notificationConfigDTO.setId(mapping[0]);
            notificationConfigDTO.setUserId(mapping[1]);
            notificationConfigDTO.setCategoryId(mapping[2]);
            notificationConfigDTO.setIsEnabled(mapping[3]);

            assertEquals(mapping[0], notificationConfigDTO.getId());
            assertEquals(mapping[1], notificationConfigDTO.getUserId());
            assertEquals(mapping[2], notificationConfigDTO.getCategoryId());
            assertEquals(mapping[3], notificationConfigDTO.getIsEnabled());
        }
    }

    @Test
    void testNotificationConfigDTOWithInvalidEnabledValues() {
        // Test values other than 0 and 1
        Integer[] invalidValues = {-1, 2, 3, 10, 100, -100};

        for (Integer value : invalidValues) {
            notificationConfigDTO.setIsEnabled(value);
            assertEquals(value, notificationConfigDTO.getIsEnabled());
        }
    }
} 