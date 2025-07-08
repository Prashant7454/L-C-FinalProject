package com.server.NewsAggrigationServer.service.impl;

import com.server.NewsAggrigationServer.dto.NewsDTO;
import com.server.NewsAggrigationServer.dto.SavedNewsDTO;
import com.server.NewsAggrigationServer.model.SavedNews;
import com.server.NewsAggrigationServer.repository.SavedNewsRepository;
import com.server.NewsAggrigationServer.service.NewsService;
import com.server.NewsAggrigationServer.service.SavedNewsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    @Transactional
    public void deleteSavedNews(Integer userId, Integer newsId) {
        boolean exists = savedNewsRepository.existsByUserIdAndNewsId(userId, newsId);
        if (!exists) {
            throw new RuntimeException("Saved news not found for userId " + userId + " and newsId " + newsId);
        }
        savedNewsRepository.deleteByUserIdAndNewsId(userId, newsId);
    }
}

