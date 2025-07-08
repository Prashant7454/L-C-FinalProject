package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.NotificationConfigDTO;

import java.util.List;

public interface NotificationConfigService {
    NotificationConfigDTO saveConfig(NotificationConfigDTO dto);

    NotificationConfigDTO getNotificationConfigurationByCategoryId(int categoryId);

    List<NotificationConfigDTO> getNotificationConfigurationsByUserId(Integer userId);

    NotificationConfigDTO getNotificationConfigurationByUserIdAndCategoryId(Integer userId, Integer categoryId);

    NotificationConfigDTO updateNotificationConfiguration(NotificationConfigDTO dto);

    void deleteNotificationConfiguration(Integer userId, Integer categoryId);
}
