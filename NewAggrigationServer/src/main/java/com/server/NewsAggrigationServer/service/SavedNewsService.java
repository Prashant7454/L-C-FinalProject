package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.NewsDTO;
import com.server.NewsAggrigationServer.dto.SavedNewsDTO;
import java.util.List;

public interface SavedNewsService {
    SavedNewsDTO saveNews(SavedNewsDTO dto);
    List<NewsDTO> getSavedNewsByUserId(Integer userId);
    void deleteSavedNews(Integer userId, Integer newsId);
}

