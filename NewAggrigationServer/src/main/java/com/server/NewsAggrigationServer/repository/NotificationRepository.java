package com.server.NewsAggrigationServer.repository;

import com.server.NewsAggrigationServer.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByUserId(Integer userId);
    void deleteByUserId(Integer userId);
    void deleteByNewsIdAndUserId(Integer newsId, Integer userId);
}

