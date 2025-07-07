package com.server.NewsAggrigationServer.service.impl;

import com.server.NewsAggrigationServer.model.News;
import com.server.NewsAggrigationServer.repository.NewsRepository;
import com.server.NewsAggrigationServer.service.NewsHidingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsHidingServiceImpl implements NewsHidingService {

    private static final Logger log = LoggerFactory.getLogger(NewsHidingServiceImpl.class);

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public int hideNewsByKeywords(List<String> keywords) {
        if (keywords == null || keywords.isEmpty()) {
            return 0;
        }

        // Find news articles containing the keywords
        List<News> newsToHide = findNewsByKeywords(keywords);
        
        int hiddenCount = 0;
        for (News news : newsToHide) {
            if (hideNewsById(news.getId())) {
                hiddenCount++;
            }
        }
        
        System.out.println("Hidden " + hiddenCount + " news articles based on keywords: " + keywords);
        return hiddenCount;
    }

    @Override
    public boolean hideNewsById(Integer newsId) {
        log.info("Hiding news with ID: {}", newsId);
        try {
            News news = newsRepository.findById(newsId).orElse(null);
            if (news == null) {
                return false;
            }
            
            // Check if news is already hidden
            if (news.getIsHide() != null && news.getIsHide() == 1) {
                return false;
            }
            
            // Hide the news
            news.setIsHide(1);
            newsRepository.save(news);
            
            System.out.println("Hidden news ID " + newsId + ": " + news.getTitle());
            log.info("News with ID: {} hidden successfully", newsId);
            return true;
            
        } catch (Exception e) {
            log.error("Failed to hide news with ID: {}. Error: {}", newsId, e.getMessage(), e);
            System.err.println("Error hiding news ID " + newsId + ": " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean unhideNewsById(Integer newsId) {
        try {
            News news = newsRepository.findById(newsId).orElse(null);
            if (news == null) {
                return false;
            }
            
            // Check if news is already visible
            if (news.getIsHide() == null || news.getIsHide() == 0) {
                return false;
            }
            
            // Unhide the news
            news.setIsHide(0);
            newsRepository.save(news);
            
            System.out.println("Unhidden news ID " + newsId + ": " + news.getTitle());
            return true;
            
        } catch (Exception e) {
            System.err.println("Error unhiding news ID " + newsId + ": " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<News> findNewsByKeywords(List<String> keywords) {
        if (keywords == null || keywords.isEmpty()) {
            return List.of();
        }

        // Get all news articles
        List<News> allNews = newsRepository.findAll();
        
        // Filter news that contain any of the keywords
        return allNews.stream()
                .filter(news -> containsAnyKeyword(news, keywords))
                .collect(Collectors.toList());
    }

    @Override
    public int getNewsCountByKeywords(List<String> keywords) {
        return findNewsByKeywords(keywords).size();
    }

    private boolean containsAnyKeyword(News news, List<String> keywords) {
        if (news == null || keywords == null || keywords.isEmpty()) {
            return false;
        }

        // Create search text from title and description
        String searchText = createSearchText(news).toLowerCase();
        
        // Check if any keyword is contained in the search text
        return keywords.stream()
                .anyMatch(keyword -> searchText.contains(keyword.toLowerCase()));
    }

    private String createSearchText(News news) {
        StringBuilder searchText = new StringBuilder();
        
        if (news.getTitle() != null) {
            searchText.append(news.getTitle()).append(" ");
        }
        
        if (news.getDescription() != null) {
            searchText.append(news.getDescription()).append(" ");
        }
        
        return searchText.toString();
    }
} 