package Application.userNewsReport;

public class UserNewsReport {
    private Integer id;
    private Integer userId;
    private Integer newsId;
    private Integer isReported;

    // Getters & Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getNewsId() { return newsId; }
    public void setNewsId(Integer newsId) { this.newsId = newsId; }

    public Integer getIsReported() { return isReported; }
    public void setIsReported(Integer isReported) { this.isReported = isReported; }
} 