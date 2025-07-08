package com.server.NewsAggrigationServer.dto;

import java.time.LocalDateTime;

public class NotificationDTO {
    private Integer id;
    private Integer newsId;
    private Integer userId;
    private LocalDateTime timestamp;

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getNewsId() { return newsId; }
    public void setNewsId(Integer newsId) { this.newsId = newsId; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}

