package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.dto.NotificationConfigDTO;
import com.server.NewsAggrigationServer.service.NotificationConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification-config")
public class NotificationConfigController {

    @Autowired
    private NotificationConfigService configService;

    @PostMapping
    public NotificationConfigDTO saveConfig(@RequestBody NotificationConfigDTO dto) {
        return configService.saveConfig(dto);
    }
}
