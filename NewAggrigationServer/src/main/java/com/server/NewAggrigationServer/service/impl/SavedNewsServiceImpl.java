package com.server.NewAggrigationServer.service.impl;

import com.server.NewAggrigationServer.dto.NewsDTO;
import com.server.NewAggrigationServer.dto.SavedNewsDTO;
import com.server.NewAggrigationServer.model.SavedNews;
import com.server.NewAggrigationServer.repository.SavedNewsRepository;
import com.server.NewAggrigationServer.service.NewsService;
import com.server.NewAggrigationServer.service.SavedNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SavedNewsServiceImpl implements SavedNewsService {

    @Autowired
    private SavedNewsRepository savedNewsRepository;

    @Autowired
    private NewsService newsService;


    @Override
    public SavedNewsDTO saveNews(SavedNewsDTO dto) {
        boolean alreadySaved = savedNewsRepository.existsByUserIdAndNewsId(dto.getUserId(), dto.getNewsId());
        if (alreadySaved) {
            throw new RuntimeException("This news is already saved by the user.");
        }

        SavedNews saved = savedNewsRepository.save(
                new SavedNews(dto.getNewsId(), dto.getUserId())
        );

        dto.setId(saved.getId());
        return dto;
    }

    @Override
    public List<NewsDTO> getSavedNewsByUserId(Integer userId) {
        List<Integer> savesNewsId = new ArrayList<>();
        List<SavedNews> savedNews = savedNewsRepository.findByUserId(userId);
        for(SavedNews news: savedNews){
            savesNewsId.add(news.getNewsId());
        }
        return newsService.getNewsByIds(savesNewsId);
    }

    @Override
    public void deleteSavedNews(Integer userId, Integer newsId) {
        boolean exists = savedNewsRepository.existsByUserIdAndNewsId(userId, newsId);
        if (!exists) {
            throw new RuntimeException("Saved news not found for userId " + userId + " and newsId " + newsId);
        }
        savedNewsRepository.deleteByUserIdAndNewsId(userId, newsId);
    }
}

