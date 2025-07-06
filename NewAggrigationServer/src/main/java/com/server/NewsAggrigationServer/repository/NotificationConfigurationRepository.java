package com.server.NewsAggrigationServer.repository;

import com.server.NewsAggrigationServer.model.NotificationConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationConfigurationRepository extends JpaRepository<NotificationConfiguration, Integer> {
    NotificationConfiguration getNotificationConfigurationByCategoryId(Integer categoryId);
    
    // New methods for user-specific configurations
    List<NotificationConfiguration> findByUserId(Integer userId);
    NotificationConfiguration findByUserIdAndCategoryId(Integer userId, Integer categoryId);
    void deleteByUserIdAndCategoryId(Integer userId, Integer categoryId);
    
    // Method to find enabled notifications by category
    List<NotificationConfiguration> findByCategoryIdAndEnabledTrue(Integer categoryId);
}
