package com.server.NewAggrigationServer.service;

import com.server.NewAggrigationServer.dto.NotificationConfigDTO;

import java.util.List;

public interface NotificationConfigService {
    NotificationConfigDTO saveConfig(NotificationConfigDTO dto);
    List<NotificationConfigDTO> getConfigsByUserId(Integer userId);
}
