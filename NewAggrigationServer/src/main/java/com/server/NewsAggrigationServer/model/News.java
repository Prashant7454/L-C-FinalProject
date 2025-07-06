package com.server.NewsAggrigationServer.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @Column(length = 2000)
    private String description;

    private String source;

    @Column(length = 255, unique = true)
    private String url;

    private LocalDateTime publishAt;

    private String keyword;

    @Column(nullable = false)
    private Integer likeCount = 0;

    @Column(nullable = false)
    private Integer disLikeCount = 0;

    @Column(nullable = false)
    private Integer reportCount = 0;

    @Column(nullable = false)
    private Integer isHide = 0;

    // Getters & Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public LocalDateTime getPublishAt() { return publishAt; }
    public void setPublishAt(LocalDateTime publishAt) { this.publishAt = publishAt; }

    public String getKeyword() { return keyword; }
    public void setKeyword(String keyword) { this.keyword = keyword; }

    public Integer getLikeCount() { return likeCount; }
    public void setLikeCount(Integer likeCount) { this.likeCount = likeCount; }

    public Integer getDisLikeCount() { return disLikeCount; }
    public void setDisLikeCount(Integer disLikeCount) { this.disLikeCount = disLikeCount; }

    public Integer getReportCount() { return reportCount; }
    public void setReportCount(Integer reportCount) { this.reportCount = reportCount; }

    public Integer getIsHide() { return isHide; }
    public void setIsHide(Integer isHide) { this.isHide = isHide; }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", likeCount=" + likeCount +
                ", disLikeCount=" + disLikeCount +
                ", reportCount=" + reportCount +
                ", isHide=" + isHide +
                '}';
    }
}