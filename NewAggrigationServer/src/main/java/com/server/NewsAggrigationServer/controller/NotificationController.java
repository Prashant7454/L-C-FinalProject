package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.dto.NewsDTO;
import com.server.NewsAggrigationServer.dto.NotificationDTO;
import com.server.NewsAggrigationServer.service.NotificationService;
import com.server.NewsAggrigationServer.service.NewsNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NewsNotificationService newsNotificationService;

    @PostMapping
    public NotificationDTO createNotification(@RequestBody NotificationDTO dto) {
        return notificationService.createNotification(dto);
    }

    @GetMapping("/user/{userId}")
    public List<NewsDTO> getNotificationsByUser(@PathVariable Integer userId) {
        return notificationService.getNotificationsByUserId(userId);
    }

    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<String> clearAll(@PathVariable Integer userId) {
        notificationService.clearNotifications(userId);
        return ResponseEntity.ok("All notifications cleared for userId: " + userId);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteNotification(
            @RequestParam Integer newsId,
            @RequestParam Integer userId) {

        notificationService.deleteNotification(newsId, userId);
        return ResponseEntity.ok("Notification deleted for newsId " + newsId + " and userId " + userId);
    }

    // Test endpoint to manually trigger notifications for a specific news article
    @PostMapping("/test/{newsId}")
    public ResponseEntity<String> testNotificationForNews(@PathVariable Integer newsId) {
        try {
            // This would need to be implemented to get the actual news and its categories
            // For now, just return a success message
            return ResponseEntity.ok("Notification test endpoint called for newsId: " + newsId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error testing notification: " + e.getMessage());
        }
    }
}

