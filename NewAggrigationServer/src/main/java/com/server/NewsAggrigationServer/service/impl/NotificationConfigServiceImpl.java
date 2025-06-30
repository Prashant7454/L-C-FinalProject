package com.server.NewsAggrigationServer.service.impl;

import com.server.NewsAggrigationServer.dto.NotificationConfigDTO;
import com.server.NewsAggrigationServer.model.NotificationConfiguration;
import com.server.NewsAggrigationServer.repository.NotificationConfigurationRepository;
import com.server.NewsAggrigationServer.service.NotificationConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationConfigServiceImpl implements NotificationConfigService {

    @Autowired
    private NotificationConfigurationRepository configRepository;

    @Override
    public NotificationConfigDTO saveConfig(NotificationConfigDTO dto) {
        NotificationConfiguration config = new NotificationConfiguration();
        config.setEnabled(dto.getEnabled());
        config.setId(dto.getId());
        config.setCategoryId(dto.getCategoryId());
        config.setUserId((dto.getUserId()));
        config = configRepository.save(config);
        dto.setId(config.getId());
        return dto;
    }

    @Override
    public NotificationConfigDTO getNotificationConfigurationByCategoryId(int categoryId) {
        NotificationConfiguration notificationConfiguration = configRepository.getNotificationConfigurationByCategoryId(categoryId);
        NotificationConfigDTO notificationConfigDTO = new NotificationConfigDTO();
        notificationConfigDTO.setCategoryId(notificationConfiguration.getCategoryId());
        notificationConfigDTO.setEnabled(notificationConfiguration.getEnabled());
        notificationConfigDTO.setUserId(notificationConfiguration.getUserId());
        notificationConfigDTO.setId(notificationConfiguration.getId());
        return notificationConfigDTO;
    }


}
