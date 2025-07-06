package com.server.NewsAggrigationServer.dto;

import java.time.LocalDateTime;

public class UserReadingHistoryDTO {
    private Integer id;
    private Integer userId;
    private Integer newsId;
    private LocalDateTime readAt;

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getNewsId() { return newsId; }
    public void setNewsId(Integer newsId) { this.newsId = newsId; }

    public LocalDateTime getReadAt() { return readAt; }
    public void setReadAt(LocalDateTime readAt) { this.readAt = readAt; }
} 