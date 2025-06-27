package com.server.NewAggrigationServer.controller;

import com.server.NewAggrigationServer.dto.NotificationConfigDTO;
import com.server.NewAggrigationServer.service.NotificationConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
