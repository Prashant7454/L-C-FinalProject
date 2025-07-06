package com.server.NewsAggrigationServer.service.impl;

import com.server.NewsAggrigationServer.dto.NotificationConfigDTO;
import com.server.NewsAggrigationServer.model.NotificationConfiguration;
import com.server.NewsAggrigationServer.repository.NotificationConfigurationRepository;
import com.server.NewsAggrigationServer.service.NotificationConfigService;
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

    @Override
    public List<NotificationConfigDTO> getNotificationConfigurationsByUserId(Integer userId) {
        List<NotificationConfiguration> configs = configRepository.findByUserId(userId);
        return configs.stream().map(config -> {
            NotificationConfigDTO dto = new NotificationConfigDTO();
            dto.setId(config.getId());
            dto.setCategoryId(config.getCategoryId());
            dto.setEnabled(config.getEnabled());
            dto.setUserId(config.getUserId());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public NotificationConfigDTO getNotificationConfigurationByUserIdAndCategoryId(Integer userId, Integer categoryId) {
        NotificationConfiguration config = configRepository.findByUserIdAndCategoryId(userId, categoryId);
        if (config == null) {
            return null;
        }
        NotificationConfigDTO dto = new NotificationConfigDTO();
        dto.setId(config.getId());
        dto.setCategoryId(config.getCategoryId());
        dto.setEnabled(config.getEnabled());
        dto.setUserId(config.getUserId());
        return dto;
    }

    @Override
    public NotificationConfigDTO updateNotificationConfiguration(NotificationConfigDTO dto) {
        NotificationConfiguration existingConfig = configRepository.findByUserIdAndCategoryId(dto.getUserId(), dto.getCategoryId());
        
        if (existingConfig != null) {
            System.out.println("Called If");
            existingConfig.setEnabled(dto.getEnabled());
            existingConfig = configRepository.save(existingConfig);
            dto.setId(existingConfig.getId());
        } else {
            System.out.println("Called else");
            // Create new configuration if it doesn't exist
            NotificationConfiguration newConfig = new NotificationConfiguration();
            newConfig.setUserId(dto.getUserId());
            newConfig.setCategoryId(dto.getCategoryId());
            newConfig.setEnabled(dto.getEnabled());
            newConfig = configRepository.save(newConfig);
            dto.setId(newConfig.getId());
        }
        
        return dto;
    }

    @Override
    public void deleteNotificationConfiguration(Integer userId, Integer categoryId) {
        configRepository.deleteByUserIdAndCategoryId(userId, categoryId);
    }
}
