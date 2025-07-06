package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.model.News;

import java.util.List;

public interface CategoryAssignmentService {
    
    /**
     * Assigns categories to news based on keywords in the title and content
     * @param news The news article to assign categories to
     */
    void assignCategoriesToNews(News news);
    
    /**
     * Assigns categories to a list of news articles
     * @param newsList List of news articles to assign categories to
     */
    void assignCategoriesToNewsList(List<News> newsList);
    
    /**
     * Finds matching categories based on keywords in the given text
     * @param text The text to search for keywords (title, content, etc.)
     * @return List of category IDs that match the keywords
     */
    List<Integer> findMatchingCategories(String text);
} 