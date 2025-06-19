package com.server.NewAggrigationServer.service.impl;

import com.server.NewAggrigationServer.dto.NewsDTO;
import com.server.NewAggrigationServer.dto.NotificationDTO;
import com.server.NewAggrigationServer.model.Notification;
import com.server.NewAggrigationServer.model.SavedNews;
import com.server.NewAggrigationServer.repository.NotificationRepository;
import com.server.NewAggrigationServer.service.NewsService;
import com.server.NewAggrigationServer.service.NotificationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NewsService newsService;

    @Override
    public NotificationDTO createNotification(NotificationDTO dto) {
        Notification notification = new Notification(
                dto.getNewsId(),
                dto.getUserId(),
                dto.getTimestamp() != null ? dto.getTimestamp() : LocalDateTime.now()
        );

        notification = notificationRepository.save(notification);

        dto.setId(notification.getId());
        dto.setTimestamp(notification.getTimestamp());

        return dto;
    }

    @Override
    public List<NewsDTO> getNotificationsByUserId(Integer userId) {
        List<Integer> notificationNewsId = new ArrayList<>();
        List<Notification> notificationList = notificationRepository.findByUserId(userId);
        for(Notification notification: notificationList){
            notificationNewsId.add(notification.getNewsId());
        }
        return newsService.getNewsByIds(notificationNewsId);
    }

    @Override
    @Transactional
    public void clearNotifications(Integer userId) {
        notificationRepository.deleteByUserId(userId);
    }

    @Override
    @Transactional
    public void deleteNotification(Integer newsId, Integer userId) {
        notificationRepository.deleteByNewsIdAndUserId(newsId, userId);
    }
}

