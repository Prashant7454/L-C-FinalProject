package com.server.NewAggrigationServer.service;

import com.server.NewAggrigationServer.dto.NewsDTO;
import com.server.NewAggrigationServer.dto.NotificationDTO;

import java.util.List;

public interface NotificationService {
    NotificationDTO createNotification(NotificationDTO dto);
    List<NewsDTO> getNotificationsByUserId(Integer userId);
    void clearNotifications(Integer userId);
    void deleteNotification(Integer newsId, Integer userId);
}

