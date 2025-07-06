package Application.personalization.service;

import Application.news.News;
import Application.personalization.controller.NewsPersonalizationController;

import java.util.List;

public class NewsPersonalizationService {
    private final NewsPersonalizationController personalizationController;

    public NewsPersonalizationService() {
        this.personalizationController = new NewsPersonalizationController();
    }

    /**
     * Get personalized news recommendations for a user
     */
    public List<News> getPersonalizedNews(Integer userId, int limit) throws Exception {
        return personalizationController.getPersonalizedNews(userId, limit);
    }

    /**
     * Get personalized news recommendations with pagination
     */
    public List<News> getPersonalizedNewsPaginated(Integer userId, int page, int size) throws Exception {
        return personalizationController.getPersonalizedNewsPaginated(userId, page, size);
    }

    /**
     * Calculate user's interest score for a specific news article
     */
    public double getUserInterestScore(Integer userId, Integer newsId) throws Exception {
        return personalizationController.getUserInterestScore(userId, newsId);
    }

    /**
     * Get user's top interest categories
     */
    public List<Integer> getUserTopInterestCategories(Integer userId, int limit) throws Exception {
        return personalizationController.getUserTopInterestCategories(userId, limit);
    }

    /**
     * Record that a user has read an article
     */
    public void recordArticleRead(Integer userId, Integer newsId) throws Exception {
        personalizationController.recordArticleRead(userId, newsId);
    }
} 