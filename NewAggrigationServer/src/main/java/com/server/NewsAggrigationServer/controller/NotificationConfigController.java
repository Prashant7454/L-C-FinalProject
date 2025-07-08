package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.dto.NotificationConfigDTO;
import com.server.NewsAggrigationServer.service.NotificationConfigService;
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

    // New endpoints for user-specific notification configuration
    @GetMapping("/user/{userId}")
    public List<NotificationConfigDTO> getNotificationConfigurationsByUserId(@PathVariable Integer userId) {
        return configService.getNotificationConfigurationsByUserId(userId);
    }

    @GetMapping("/user/{userId}/category/{categoryId}")
    public NotificationConfigDTO getNotificationConfigurationByUserIdAndCategoryId(
            @PathVariable Integer userId, 
            @PathVariable Integer categoryId) {
        return configService.getNotificationConfigurationByUserIdAndCategoryId(userId, categoryId);
    }

    @PutMapping
    public NotificationConfigDTO updateNotificationConfiguration(@RequestBody NotificationConfigDTO dto) {
        System.out.println("Called");
        return configService.updateNotificationConfiguration(dto);
    }

    @DeleteMapping("/user/{userId}/category/{categoryId}")
    public void deleteNotificationConfiguration(
            @PathVariable Integer userId, 
            @PathVariable Integer categoryId) {
        configService.deleteNotificationConfiguration(userId, categoryId);
    }
}
