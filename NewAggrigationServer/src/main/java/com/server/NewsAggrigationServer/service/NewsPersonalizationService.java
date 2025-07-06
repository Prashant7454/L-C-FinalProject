package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.NewsDTO;
import java.util.List;

public interface NewsPersonalizationService {
    
    /**
     * Get personalized news recommendations for a user
     * @param userId The user ID
     * @param limit Maximum number of recommendations to return
     * @return List of personalized news articles
     */
    List<NewsDTO> getPersonalizedNews(Integer userId, int limit);
    
    /**
     * Get personalized news recommendations for a user with pagination
     * @param userId The user ID
     * @param page Page number (0-based)
     * @param size Page size
     * @return List of personalized news articles for the specified page
     */
    List<NewsDTO> getPersonalizedNewsPaginated(Integer userId, int page, int size);
    
    /**
     * Calculate user's interest score for a specific news article
     * @param userId The user ID
     * @param newsId The news article ID
     * @return Interest score (0.0 to 1.0)
     */
    double calculateUserInterestScore(Integer userId, Integer newsId);
    
    /**
     * Get user's top interest categories based on their behavior
     * @param userId The user ID
     * @param limit Maximum number of categories to return
     * @return List of category IDs with their interest scores
     */
    List<Integer> getUserTopInterestCategories(Integer userId, int limit);
    
    /**
     * Update user's reading history when they view an article
     * @param userId The user ID
     * @param newsId The news article ID
     */
    void recordArticleRead(Integer userId, Integer newsId);
} 