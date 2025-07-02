package com.server.NewsAggrigationServer.dto;

public class NewsLikeDislikeUserDTO {
    private Integer id;
    private Integer newsId;
    private Integer userId;
    private Integer liked;
    private Integer disliked;

    // Getters and Setters


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNewsId() { return newsId; }
    public void setNewsId(Integer newsId) { this.newsId = newsId; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getLiked() { return liked; }
    public void setLiked(Integer liked) { this.liked = liked; }

    public Integer getDisliked() { return disliked; }
    public void setDisliked(Integer disliked) { this.disliked = disliked; }
}
