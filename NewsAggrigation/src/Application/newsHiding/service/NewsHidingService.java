package Application.newsHiding.service;

import Application.news.News;
import Application.newsHiding.controller.NewsHidingController;

import java.util.List;

public class NewsHidingService {
    private final NewsHidingController newsHidingController;

    public NewsHidingService() {
        this.newsHidingController = new NewsHidingController();
    }

    /**
     * Hide news articles that contain any of the specified keywords
     */
    public String hideNewsByKeywords(List<String> keywords) throws Exception {
        return newsHidingController.hideNewsByKeywords(keywords);
    }

    /**
     * Find news articles that contain any of the specified keywords (without hiding them)
     */
    public List<News> findNewsByKeywords(List<String> keywords) throws Exception {
        return newsHidingController.findNewsByKeywords(keywords);
    }

    /**
     * Get count of news articles that contain any of the specified keywords
     */
    public int getNewsCountByKeywords(List<String> keywords) throws Exception {
        return newsHidingController.getNewsCountByKeywords(keywords);
    }

    /**
     * Hide a specific news article by ID
     */
    public String hideNewsById(Integer newsId) throws Exception {
        return newsHidingController.hideNewsById(newsId);
    }

    /**
     * Unhide a specific news article by ID
     */
    public String unhideNewsById(Integer newsId) throws Exception {
        return newsHidingController.unhideNewsById(newsId);
    }
} 