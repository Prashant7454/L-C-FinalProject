package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.model.News;

import java.util.List;

public interface CategoryAssignmentService {

    void assignCategoriesToNews(News news);

    void assignCategoriesToNewsList(List<News> newsList);

    List<Integer> findMatchingCategories(String text);

    List<News> findUncategorizedNews();

    int countUncategorizedNews();
} 