package Application.news.service;

import Application.news.DateRangeNewsRequest;
import Application.news.News;
import Application.news.controller.NewsController;

import java.util.List;

public class NewsService {
    private final NewsController newsController;
    public NewsService(){
        newsController = new NewsController();
    }
    public List<News> getAllNews() throws Exception{
        return newsController.getAllNews();
    }

    public List<News> searchNews(String keyword) throws Exception{
        return newsController.searchNews(keyword);
    }

    public List<News> savedNews(int userId) throws Exception{
        return newsController.savedNews(userId);
    }

    public List<News> getTodayNewsById(List<Integer> newsIds) throws Exception{
        return newsController.getTodayNewsById(newsIds);
    }

    public List<News> getNewsByIdAndDateRange(DateRangeNewsRequest dateRangeNewsRequest) throws Exception{
        return newsController.getNewsByIdAndDateRange(dateRangeNewsRequest);
    }

    public News getNewsById(Integer newsId) throws Exception{
        return newsController.getNewsById(newsId);
    }

    public boolean isNewsHidden(Integer newsId) throws Exception{
        News news = newsController.getNewsById(newsId);
        return news != null && news.getIsHide() != null && news.getIsHide() == 1;
    }

    public News updateNewsLikeAndDisLikeCount(News news) throws Exception{
        return newsController.updateNewsLikeAndDisLikeCount(news);
    }

    public News updateNews(News news) throws Exception{
        return newsController.updateNews(news);
    }

    // New methods for visible news only
    public List<News> getAllVisibleNews() throws Exception{
        return newsController.getAllVisibleNews();
    }

    public List<News> searchVisibleNews(String keyword) throws Exception{
        return newsController.searchVisibleNews(keyword);
    }

    public List<News> getVisibleTodayNewsById(List<Integer> newsIds) throws Exception{
        return newsController.getVisibleTodayNewsById(newsIds);
    }

    public List<News> getVisibleNewsByIdAndDateRange(DateRangeNewsRequest dateRangeNewsRequest) throws Exception{
        return newsController.getVisibleNewsByIdAndDateRange(dateRangeNewsRequest);
    }

    // New methods for news in visible categories
    public List<News> getNewsInVisibleCategories() throws Exception{
        return newsController.getNewsInVisibleCategories();
    }

    public List<News> getNewsByIdsInVisibleCategories(List<Integer> newsIds) throws Exception{
        return newsController.getNewsByIdsInVisibleCategories(newsIds);
    }

    // Admin methods for managing reported news
    public List<News> getReportedNews() throws Exception{
        return newsController.getReportedNews();
    }

    public News hideNews(Integer id) throws Exception{
        return newsController.hideNews(id);
    }

    public News unhideNews(Integer id) throws Exception{
        return newsController.unhideNews(id);
    }
}
