package Application.newsCategory;

public class NewsCategory {
    private Integer id;
    private Integer newsId;
    private Integer categoryId;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getNewsId() { return newsId; }
    public void setNewsId(Integer newsId) { this.newsId = newsId; }

    public Integer getCategoryId() { return categoryId; }
    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }
}
