package com.server.NewAggrigationServer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "news_category")
public class NewsCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer newsId;
    private Integer categoryId;

    // Constructors
    public NewsCategory() {}

    public NewsCategory(Integer newsId, Integer categoryId) {
        this.newsId = newsId;
        this.categoryId = categoryId;
    }

    // Getters & Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getNewsId() { return newsId; }
    public void setNewsId(Integer newsId) { this.newsId = newsId; }

    public Integer getCategoryId() { return categoryId; }
    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }
}

