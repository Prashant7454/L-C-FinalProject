package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.NewsDTO;
import java.util.List;

public interface NewsPersonalizationService {

    List<NewsDTO> getPersonalizedNews(Integer userId, int limit);

    List<NewsDTO> getPersonalizedNewsPaginated(Integer userId, int page, int size);

    double calculateUserInterestScore(Integer userId, Integer newsId);

    List<Integer> getUserTopInterestCategories(Integer userId, int limit);

    void recordArticleRead(Integer userId, Integer newsId);
} 