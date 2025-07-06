package Application.news;

public class News {
    private Integer id;
    private String title;
    private String description;
    private String source;
    private String url;
    private String publishAt;
    private String keyword;
    private Integer likeCount;
    private Integer disLikeCount;
    private Integer reportCount;
    private Integer isHide;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublishAt() {
        return publishAt;
    }

    public void setPublishAt(String publishAt) {
        this.publishAt = publishAt;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getDisLikeCount() {
        return disLikeCount;
    }

    public void setDisLikeCount(Integer disLikeCount) {
        this.disLikeCount = disLikeCount;
    }

    public Integer getReportCount() {
        return reportCount;
    }

    public void setReportCount(Integer reportCount) {
        this.reportCount = reportCount;
    }

    public Integer getIsHide() {
        return isHide;
    }

    public void setIsHide(Integer isHide) {
        this.isHide = isHide;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", source='" + source + '\'' +
                ", url='" + url + '\'' +
                ", publishAt='" + publishAt + '\'' +
                ", keyword='" + keyword + '\'' +
                ", likeCount=" + likeCount +
                ", disLikeCount=" + disLikeCount +
                ", reportCount=" + reportCount +
                ", isHide=" + isHide +
                '}';
    }
}
