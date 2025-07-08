package com.server.NewsAggrigationServer.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_reading_history")
public class UserReadingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
    private Integer newsId;
    private LocalDateTime readAt;

    // Constructors
    public UserReadingHistory() {}

    public UserReadingHistory(Integer userId, Integer newsId) {
        this.userId = userId;
        this.newsId = newsId;
        this.readAt = LocalDateTime.now();
    }

    public UserReadingHistory(Integer userId, Integer newsId, LocalDateTime readAt) {
        this.userId = userId;
        this.newsId = newsId;
        this.readAt = readAt;
    }

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