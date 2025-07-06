package com.server.NewsAggrigationServer.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NotificationConfigurationTest {
    private NotificationConfiguration notificationConfiguration;

    @BeforeEach
    void setUp() {
        notificationConfiguration = new NotificationConfiguration();
    }

    @Test
    void testNotificationConfigurationCreation() {
        assertNotNull(notificationConfiguration);
    }

    @Test
    void testIdGetterAndSetter() {
        Integer expectedId = 1;
        notificationConfiguration.setId(expectedId);
        assertEquals(expectedId, notificationConfiguration.getId());
    }

    @Test
    void testUserIdGetterAndSetter() {
        Integer expectedUserId = 1;
        notificationConfiguration.setUserId(expectedUserId);
        assertEquals(expectedUserId, notificationConfiguration.getUserId());
    }

    @Test
    void testCategoryIdGetterAndSetter() {
        Integer expectedCategoryId = 1;
        notificationConfiguration.setCategoryId(expectedCategoryId);
        assertEquals(expectedCategoryId, notificationConfiguration.getCategoryId());
    }

    @Test
    void testIsEnabledGetterAndSetter() {
        Integer expectedIsEnabled = 1;
        notificationConfiguration.setIsEnabled(expectedIsEnabled);
        assertEquals(expectedIsEnabled, notificationConfiguration.getIsEnabled());
    }

    @Test
    void testNotificationConfigurationWithAllFields() {
        Integer id = 1;
        Integer userId = 2;
        Integer categoryId = 3;
        Integer isEnabled = 1;

        notificationConfiguration.setId(id);
        notificationConfiguration.setUserId(userId);
        notificationConfiguration.setCategoryId(categoryId);
        notificationConfiguration.setIsEnabled(isEnabled);

        assertEquals(id, notificationConfiguration.getId());
        assertEquals(userId, notificationConfiguration.getUserId());
        assertEquals(categoryId, notificationConfiguration.getCategoryId());
        assertEquals(isEnabled, notificationConfiguration.getIsEnabled());
    }

    @Test
    void testNotificationConfigurationWithNullValues() {
        notificationConfiguration.setId(null);
        notificationConfiguration.setUserId(null);
        notificationConfiguration.setCategoryId(null);
        notificationConfiguration.setIsEnabled(null);

        assertNull(notificationConfiguration.getId());
        assertNull(notificationConfiguration.getUserId());
        assertNull(notificationConfiguration.getCategoryId());
        assertNull(notificationConfiguration.getIsEnabled());
    }

    @Test
    void testNotificationConfigurationWithZeroValues() {
        notificationConfiguration.setId(0);
        notificationConfiguration.setUserId(0);
        notificationConfiguration.setCategoryId(0);
        notificationConfiguration.setIsEnabled(0);

        assertEquals(0, notificationConfiguration.getId());
        assertEquals(0, notificationConfiguration.getUserId());
        assertEquals(0, notificationConfiguration.getCategoryId());
        assertEquals(0, notificationConfiguration.getIsEnabled());
    }

    @Test
    void testNotificationConfigurationWithEnabledValue() {
        notificationConfiguration.setIsEnabled(1);
        assertEquals(1, notificationConfiguration.getIsEnabled());
    }

    @Test
    void testNotificationConfigurationWithDisabledValue() {
        notificationConfiguration.setIsEnabled(0);
        assertEquals(0, notificationConfiguration.getIsEnabled());
    }

    @Test
    void testNotificationConfigurationWithNegativeValues() {
        notificationConfiguration.setId(-1);
        notificationConfiguration.setUserId(-2);
        notificationConfiguration.setCategoryId(-3);
        notificationConfiguration.setIsEnabled(-1);

        assertEquals(-1, notificationConfiguration.getId());
        assertEquals(-2, notificationConfiguration.getUserId());
        assertEquals(-3, notificationConfiguration.getCategoryId());
        assertEquals(-1, notificationConfiguration.getIsEnabled());
    }

    @Test
    void testNotificationConfigurationWithLargeValues() {
        Integer largeId = Integer.MAX_VALUE;
        Integer largeUserId = Integer.MAX_VALUE - 1;
        Integer largeCategoryId = Integer.MAX_VALUE - 2;
        Integer largeIsEnabled = Integer.MAX_VALUE - 3;

        notificationConfiguration.setId(largeId);
        notificationConfiguration.setUserId(largeUserId);
        notificationConfiguration.setCategoryId(largeCategoryId);
        notificationConfiguration.setIsEnabled(largeIsEnabled);

        assertEquals(largeId, notificationConfiguration.getId());
        assertEquals(largeUserId, notificationConfiguration.getUserId());
        assertEquals(largeCategoryId, notificationConfiguration.getCategoryId());
        assertEquals(largeIsEnabled, notificationConfiguration.getIsEnabled());
    }
} 