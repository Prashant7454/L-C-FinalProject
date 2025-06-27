package com.server.NewAggrigationServer.repository;

import com.server.NewAggrigationServer.dto.NotificationConfigDTO;
import com.server.NewAggrigationServer.model.NotificationConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationConfigurationRepository extends JpaRepository<NotificationConfiguration, Integer> {
    NotificationConfiguration getNotificationConfigurationByCategoryId(Integer categoryId);
}
