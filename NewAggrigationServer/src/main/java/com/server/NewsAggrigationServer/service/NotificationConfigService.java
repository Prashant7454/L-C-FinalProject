package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.NotificationConfigDTO;

public interface NotificationConfigService {
    NotificationConfigDTO saveConfig(NotificationConfigDTO dto);
    NotificationConfigDTO getNotificationConfigurationByCategoryId(int categoryId);
}
