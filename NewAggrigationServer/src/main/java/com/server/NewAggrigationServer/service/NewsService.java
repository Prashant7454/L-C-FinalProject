package com.server.NewAggrigationServer.service;

import com.server.NewAggrigationServer.dto.NewsDTO;

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

}
