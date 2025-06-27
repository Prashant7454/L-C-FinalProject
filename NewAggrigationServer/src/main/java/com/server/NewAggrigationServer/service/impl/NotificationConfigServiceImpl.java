package com.server.NewAggrigationServer.service.impl;

import com.server.NewAggrigationServer.dto.NotificationConfigDTO;
import com.server.NewAggrigationServer.model.NotificationConfiguration;
import com.server.NewAggrigationServer.repository.NotificationConfigurationRepository;
import com.server.NewAggrigationServer.service.NotificationConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        notificationConfigDTO.setId(notificationConfiguration.getId());
        return notificationConfigDTO;
    }


}
