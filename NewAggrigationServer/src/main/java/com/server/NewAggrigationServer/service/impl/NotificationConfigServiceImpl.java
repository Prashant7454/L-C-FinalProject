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
    public List<NotificationConfigDTO> getConfigsByUserId(Integer userId) {
        return configRepository.findByUserId(userId).stream()
                .map(c -> {
                    NotificationConfigDTO dto = new NotificationConfigDTO();
                    dto.setId(c.getId());
                    dto.setUserId(c.getUserId());
                    dto.setCategoryId(c.getCategoryId());
                    dto.setEnabled(c.getEnabled());
                    return dto;
                }).collect(Collectors.toList());
    }
}
