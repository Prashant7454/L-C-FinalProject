package com.server.NewsAggrigationServer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_news_report")
public class UserNewsReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Integer newsId;

    @Column(nullable = false)
    private Integer isReported = 0;

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getNewsId() { return newsId; }
    public void setNewsId(Integer newsId) { this.newsId = newsId; }

    public Integer getIsReported() { return isReported; }
    public void setIsReported(Integer isReported) { this.isReported = isReported; }
}
