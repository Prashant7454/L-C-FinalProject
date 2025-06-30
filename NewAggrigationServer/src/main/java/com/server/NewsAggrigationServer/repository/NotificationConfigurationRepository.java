package com.server.NewsAggrigationServer.repository;

import com.server.NewsAggrigationServer.model.NotificationConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationConfigurationRepository extends JpaRepository<NotificationConfiguration, Integer> {
    NotificationConfiguration getNotificationConfigurationByCategoryId(Integer categoryId);
}
