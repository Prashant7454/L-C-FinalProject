package Application.news.service;

import Application.news.News;
import Application.news.controller.NewsController;

import java.util.List;

public class NewsService {
    private NewsController newsController;
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
}
