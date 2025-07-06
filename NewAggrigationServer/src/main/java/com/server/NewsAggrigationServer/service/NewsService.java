package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.NewsDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface NewsService {
    NewsDTO createNews(NewsDTO dto);
    NewsDTO updateNews(Integer id, NewsDTO dto);
    NewsDTO getNewsById(Integer id);
    List<NewsDTO> getAllNews();
    List<NewsDTO> getNews(String searchString);
    List<NewsDTO> getNewsByIds(List<Integer> ids);
    void addMultipleNews(List<NewsDTO> newsList);
    List<NewsDTO> getTodayNewsByIds(List<Integer> ids);
    List<NewsDTO> getNewsByIdsAndDateRange(List<Integer> ids, LocalDateTime startDate, LocalDateTime endDate);
    List<NewsDTO> getReportedNews();
    
    // New methods for visible news only
    List<NewsDTO> getAllVisibleNews();
    List<NewsDTO> getVisibleNews(String searchString);
    List<NewsDTO> getVisibleNewsByIds(List<Integer> ids);
    List<NewsDTO> getVisibleTodayNewsByIds(List<Integer> ids);
    List<NewsDTO> getVisibleNewsByIdsAndDateRange(List<Integer> ids, LocalDateTime startDate, LocalDateTime endDate);
    
    // New methods for news in visible categories
    List<NewsDTO> getNewsInVisibleCategories();
    List<NewsDTO> getNewsByIdsInVisibleCategories(List<Integer> newsIds);
}
