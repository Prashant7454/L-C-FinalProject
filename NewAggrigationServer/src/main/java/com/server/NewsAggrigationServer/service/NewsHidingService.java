package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.model.News;

import java.util.List;

public interface NewsHidingService {
    
    /**
     * Hides news articles that contain any of the specified keywords
     * @param keywords List of keywords to search for
     * @return Number of news articles that were hidden
     */
    int hideNewsByKeywords(List<String> keywords);
    
    /**
     * Hides a specific news article by ID
     * @param newsId ID of the news article to hide
     * @return true if the news was hidden, false if it was already hidden
     */
    boolean hideNewsById(Integer newsId);
    
    /**
     * Unhides a specific news article by ID
     * @param newsId ID of the news article to unhide
     * @return true if the news was unhidden, false if it was already visible
     */
    boolean unhideNewsById(Integer newsId);
    
    /**
     * Finds news articles that contain any of the specified keywords
     * @param keywords List of keywords to search for
     * @return List of news articles that contain the keywords
     */
    List<News> findNewsByKeywords(List<String> keywords);
    
    /**
     * Gets count of news articles that contain any of the specified keywords
     * @param keywords List of keywords to search for
     * @return Count of news articles containing the keywords
     */
    int getNewsCountByKeywords(List<String> keywords);
} 