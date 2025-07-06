package com.server.NewsAggrigationServer.service.impl;

import com.server.NewsAggrigationServer.dto.CategoryDTO;
import com.server.NewsAggrigationServer.dto.NewsDTO;
import com.server.NewsAggrigationServer.dto.NotificationConfigDTO;
import com.server.NewsAggrigationServer.dto.UserReadingHistoryDTO;
import com.server.NewsAggrigationServer.model.NotificationConfiguration;
import com.server.NewsAggrigationServer.model.UserReadingHistory;
import com.server.NewsAggrigationServer.repository.NotificationConfigurationRepository;
import com.server.NewsAggrigationServer.repository.UserReadingHistoryRepository;
import com.server.NewsAggrigationServer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class NewsPersonalizationServiceImpl implements NewsPersonalizationService {

    @Autowired
    private NewsService newsService;

    @Autowired
    private NotificationConfigurationRepository notificationConfigRepository;

    @Autowired
    private UserReadingHistoryRepository readingHistoryRepository;

    @Autowired
    private NewsLikeDislikeUserService likeDislikeService;

    @Autowired
    private SavedNewsService savedNewsService;

    @Autowired
    private NewsCategoryService newsCategoryService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryKeywordService categoryKeywordService;

    @Override
    public List<NewsDTO> getPersonalizedNews(Integer userId, int limit) {
        try {
            // Get all visible news articles
            List<NewsDTO> allNews = newsService.getAllVisibleNews();
            
            // Calculate personalized scores for each news article
            Map<Integer, Double> newsScores = new HashMap<>();
            
            for (NewsDTO news : allNews) {
                double score = calculateUserInterestScore(userId, news.getId());
                newsScores.put(news.getId(), score);
            }
            
            // Sort news by personalized score (highest first) and return top results
            return allNews.stream()
                .sorted((n1, n2) -> Double.compare(newsScores.get(n2.getId()), newsScores.get(n1.getId())))
                .limit(limit)
                .collect(Collectors.toList());
                
        } catch (Exception e) {
            System.err.println("Error getting personalized news for user " + userId + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<NewsDTO> getPersonalizedNewsPaginated(Integer userId, int page, int size) {
        try {
            // Get all visible news articles
            List<NewsDTO> allNews = newsService.getAllVisibleNews();
            
            // Calculate personalized scores for each news article
            Map<Integer, Double> newsScores = new HashMap<>();
            
            for (NewsDTO news : allNews) {
                double score = calculateUserInterestScore(userId, news.getId());
                newsScores.put(news.getId(), score);
            }
            
            // Sort news by personalized score (highest first)
            List<NewsDTO> sortedNews = allNews.stream()
                .sorted((n1, n2) -> Double.compare(newsScores.get(n2.getId()), newsScores.get(n1.getId())))
                .collect(Collectors.toList());
            
            // Apply pagination
            int startIndex = page * size;
            int endIndex = Math.min(startIndex + size, sortedNews.size());
            
            if (startIndex >= sortedNews.size()) {
                return new ArrayList<>();
            }
            
            return sortedNews.subList(startIndex, endIndex);
                
        } catch (Exception e) {
            System.err.println("Error getting personalized news for user " + userId + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public double calculateUserInterestScore(Integer userId, Integer newsId) {
        try {
            double totalScore = 0.0;
            
            // 1. Notification preferences weight (40%)
            double notificationScore = calculateNotificationPreferenceScore(userId, newsId);
            totalScore += notificationScore * 0.4;
            
            // 2. Reading history weight (25%)
            double readingHistoryScore = calculateReadingHistoryScore(userId, newsId);
            totalScore += readingHistoryScore * 0.25;
            
            // 3. Like/dislike behavior weight (20%)
            double likeDislikeScore = calculateLikeDislikeScore(userId, newsId);
            totalScore += likeDislikeScore * 0.2;
            
            // 4. Saved articles weight (15%)
            double savedArticlesScore = calculateSavedArticlesScore(userId, newsId);
            totalScore += savedArticlesScore * 0.15;
            
            return Math.min(totalScore, 1.0); // Cap at 1.0
            
        } catch (Exception e) {
            System.err.println("Error calculating interest score for user " + userId + " and news " + newsId + ": " + e.getMessage());
            return 0.0;
        }
    }

    @Override
    public List<Integer> getUserTopInterestCategories(Integer userId, int limit) {
        try {
            Map<Integer, Double> categoryScores = new HashMap<>();
            
            // Get user's notification preferences
            List<NotificationConfiguration> notificationConfigs = notificationConfigRepository.findByUserId(userId);
            for (NotificationConfiguration config : notificationConfigs) {
                if (config.getEnabled()) {
                    categoryScores.put(config.getCategoryId(), categoryScores.getOrDefault(config.getCategoryId(), 0.0) + 0.5);
                }
            }
            
            // Get user's reading history and analyze category preferences
            List<UserReadingHistory> readingHistory = readingHistoryRepository.findByUserIdOrderByReadAtDesc(userId);
            for (UserReadingHistory history : readingHistory) {
                List<CategoryDTO> categories = newsCategoryService.getCategoriesByNewsId(history.getNewsId());
                for (CategoryDTO category : categories) {
                    categoryScores.put(category.getId(), categoryScores.getOrDefault(category.getId(), 0.0) + 0.3);
                }
            }
            
            // Get user's liked articles and analyze category preferences
            List<NewsDTO> likedNews = likeDislikeService.getLikedNewsByUserId(userId);
            for (NewsDTO news : likedNews) {
                List<CategoryDTO> categories = newsCategoryService.getCategoriesByNewsId(news.getId());
                for (CategoryDTO category : categories) {
                    categoryScores.put(category.getId(), categoryScores.getOrDefault(category.getId(), 0.0) + 0.4);
                }
            }
            
            // Get user's saved articles and analyze category preferences
            List<NewsDTO> savedNews = savedNewsService.getSavedNewsByUserId(userId);
            for (NewsDTO news : savedNews) {
                List<CategoryDTO> categories = newsCategoryService.getCategoriesByNewsId(news.getId());
                for (CategoryDTO category : categories) {
                    categoryScores.put(category.getId(), categoryScores.getOrDefault(category.getId(), 0.0) + 0.35);
                }
            }
            
            // Sort categories by score and return top results
            return categoryScores.entrySet().stream()
                .sorted(Map.Entry.<Integer, Double>comparingByValue().reversed())
                .limit(limit)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
                
        } catch (Exception e) {
            System.err.println("Error getting top interest categories for user " + userId + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void recordArticleRead(Integer userId, Integer newsId) {
        try {
            // Check if already recorded
            if (!readingHistoryRepository.existsByUserIdAndNewsId(userId, newsId)) {
                UserReadingHistory readingHistory = new UserReadingHistory(userId, newsId);
                readingHistoryRepository.save(readingHistory);
                System.out.println("Recorded article read: User " + userId + " read article " + newsId);
            }
        } catch (Exception e) {
            System.err.println("Error recording article read for user " + userId + " and news " + newsId + ": " + e.getMessage());
        }
    }

    // Helper methods for calculating different aspects of the interest score

    private double calculateNotificationPreferenceScore(Integer userId, Integer newsId) {
        try {
            List<CategoryDTO> newsCategories = newsCategoryService.getCategoriesByNewsId(newsId);
            List<NotificationConfiguration> userConfigs = notificationConfigRepository.findByUserId(userId);
            
            double score = 0.0;
            for (CategoryDTO category : newsCategories) {
                for (NotificationConfiguration config : userConfigs) {
                    if (config.getCategoryId().equals(category.getId()) && config.getEnabled()) {
                        score += 1.0;
                    }
                }
            }
            
            return Math.min(score / Math.max(newsCategories.size(), 1), 1.0);
        } catch (Exception e) {
            return 0.0;
        }
    }

    private double calculateReadingHistoryScore(Integer userId, Integer newsId) {
        try {
            List<CategoryDTO> newsCategories = newsCategoryService.getCategoriesByNewsId(newsId);
            List<UserReadingHistory> readingHistory = readingHistoryRepository.findByUserIdOrderByReadAtDesc(userId);
            
            if (readingHistory.isEmpty()) {
                return 0.0;
            }
            
            // Get recent reading history (last 30 days)
            LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
            List<UserReadingHistory> recentHistory = readingHistoryRepository.findRecentReadingHistory(userId, thirtyDaysAgo);
            
            double score = 0.0;
            for (CategoryDTO category : newsCategories) {
                for (UserReadingHistory history : recentHistory) {
                    List<CategoryDTO> readCategories = newsCategoryService.getCategoriesByNewsId(history.getNewsId());
                    for (CategoryDTO readCategory : readCategories) {
                        if (readCategory.getId().equals(category.getId())) {
                            score += 0.5;
                        }
                    }
                }
            }
            
            return Math.min(score / Math.max(newsCategories.size(), 1), 1.0);
        } catch (Exception e) {
            return 0.0;
        }
    }

    private double calculateLikeDislikeScore(Integer userId, Integer newsId) {
        try {
            List<CategoryDTO> newsCategories = newsCategoryService.getCategoriesByNewsId(newsId);
            List<NewsDTO> likedNews = likeDislikeService.getLikedNewsByUserId(userId);
            
            double score = 0.0;
            for (CategoryDTO category : newsCategories) {
                for (NewsDTO liked : likedNews) {
                    List<CategoryDTO> likedCategories = newsCategoryService.getCategoriesByNewsId(liked.getId());
                    for (CategoryDTO likedCategory : likedCategories) {
                        if (likedCategory.getId().equals(category.getId())) {
                            score += 0.6;
                        }
                    }
                }
            }
            
            return Math.min(score / Math.max(newsCategories.size(), 1), 1.0);
        } catch (Exception e) {
            return 0.0;
        }
    }

    private double calculateSavedArticlesScore(Integer userId, Integer newsId) {
        try {
            List<CategoryDTO> newsCategories = newsCategoryService.getCategoriesByNewsId(newsId);
            List<NewsDTO> savedNews = savedNewsService.getSavedNewsByUserId(userId);
            
            double score = 0.0;
            for (CategoryDTO category : newsCategories) {
                for (NewsDTO saved : savedNews) {
                    List<CategoryDTO> savedCategories = newsCategoryService.getCategoriesByNewsId(saved.getId());
                    for (CategoryDTO savedCategory : savedCategories) {
                        if (savedCategory.getId().equals(category.getId())) {
                            score += 0.7;
                        }
                    }
                }
            }
            
            return Math.min(score / Math.max(newsCategories.size(), 1), 1.0);
        } catch (Exception e) {
            return 0.0;
        }
    }
} 