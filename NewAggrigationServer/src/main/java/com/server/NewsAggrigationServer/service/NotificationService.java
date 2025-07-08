package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.NewsDTO;
import com.server.NewsAggrigationServer.dto.NotificationDTO;

import java.util.List;

public interface NotificationService {
    NotificationDTO createNotification(NotificationDTO dto);

    List<NewsDTO> getNotificationsByUserId(Integer userId);

    void clearNotifications(Integer userId);

    void deleteNotification(Integer newsId, Integer userId);
}

