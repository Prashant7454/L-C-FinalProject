package com.server.NewsAggrigationServer.repository;

import com.server.NewsAggrigationServer.model.NotificationConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationConfigurationRepository extends JpaRepository<NotificationConfiguration, Integer> {
    NotificationConfiguration getNotificationConfigurationByCategoryId(Integer categoryId);

    List<NotificationConfiguration> findByUserId(Integer userId);

    NotificationConfiguration findByUserIdAndCategoryId(Integer userId, Integer categoryId);

    void deleteByUserIdAndCategoryId(Integer userId, Integer categoryId);

    List<NotificationConfiguration> findByCategoryIdAndEnabledTrue(Integer categoryId);
}
