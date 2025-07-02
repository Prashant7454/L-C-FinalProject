package Application.newsLikeOrDislikeUser;

public class NewsLikeDislikeUser {
    private Integer id;
    private Integer newsId;
    private Integer userId;
    private Integer liked;
    private Integer disliked;

    public NewsLikeDislikeUser(){
        this.liked = 0;
        this.disliked = 0;
    }

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

    public Integer isLiked() { return liked; }
    public void setLiked(Integer liked) { this.liked = liked; }

    public Integer isDisliked() { return disliked; }
    public void setDisliked(Integer disliked) { this.disliked = disliked; }

    @Override
    public String toString() {
        return "NewsLikeDislikeUser{" +
                "id=" + id +
                ", newsId=" + newsId +
                ", userId=" + userId +
                ", liked=" + liked +
                ", disliked=" + disliked +
                '}';
    }
}
