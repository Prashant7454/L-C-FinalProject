package com.server.NewAggrigationServer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "saved_news")
public class SavedNews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer newsId;
    private Integer userId;

    // Constructors
    public SavedNews() {}
    public SavedNews(Integer newsId, Integer userId) {
        this.newsId = newsId;
        this.userId = userId;
    }

    // Getters & Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getNewsId() { return newsId; }
    public void setNewsId(Integer newsId) { this.newsId = newsId; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
}

