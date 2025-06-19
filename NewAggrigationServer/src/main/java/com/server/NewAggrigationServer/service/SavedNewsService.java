package com.server.NewAggrigationServer.service;

import com.server.NewAggrigationServer.dto.NewsDTO;
import com.server.NewAggrigationServer.dto.SavedNewsDTO;
import java.util.List;

public interface SavedNewsService {
    SavedNewsDTO saveNews(SavedNewsDTO dto);
    List<NewsDTO> getSavedNewsByUserId(Integer userId);
    void deleteSavedNews(Integer userId, Integer newsId);
}

