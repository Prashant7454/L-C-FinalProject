package com.server.NewsAggrigationServer.service.impl;

import com.server.NewsAggrigationServer.dto.NotificationDTO;
import com.server.NewsAggrigationServer.model.NotificationConfiguration;
import com.server.NewsAggrigationServer.model.User;
import com.server.NewsAggrigationServer.repository.NotificationConfigurationRepository;
import com.server.NewsAggrigationServer.repository.UserRepository;
import com.server.NewsAggrigationServer.service.CategoryService;
import com.server.NewsAggrigationServer.service.EmailService;
import com.server.NewsAggrigationServer.service.NewsCategoryService;
import com.server.NewsAggrigationServer.service.NewsNotificationService;
import com.server.NewsAggrigationServer.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsNotificationServiceImpl implements NewsNotificationService {

    @Autowired
    private NotificationConfigurationRepository notificationConfigRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NewsCategoryService newsCategoryService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void sendNotificationsForNews(com.server.NewsAggrigationServer.model.News news, List<Integer> categoryIds) {
        for (Integer categoryId : categoryIds) {
            List<NotificationConfiguration> configs = notificationConfigRepository.findByCategoryIdAndEnabledTrue(categoryId);
            
            for (NotificationConfiguration config : configs) {
                try {
                    User user = userRepository.findById(config.getUserId()).orElse(null);
                    if (user == null) continue;

                    emailService.sendNewsNotification(
                        user.getEmail(),
                        user.getUsername(),
                        news.getTitle(),
                        news.getDescription(),
                        getCategoryName(categoryId)
                    );

                    NotificationDTO notificationDTO = new NotificationDTO();
                    notificationDTO.setNewsId(news.getId());
                    notificationDTO.setUserId(user.getId());
                    notificationDTO.setTimestamp(LocalDateTime.now());
                    notificationService.createNotification(notificationDTO);

                    System.out.println("Notification sent to user " + user.getUsername() + 
                                     " for news: " + news.getTitle() + " in category: " + categoryId);
                } catch (Exception e) {
                    System.err.println("Failed to send notification to user " + config.getUserId() + 
                                     " for news " + news.getId() + ": " + e.getMessage());
                }
            }
        }
    }

    @Override
    public void sendNotificationsForNewsList(List<com.server.NewsAggrigationServer.model.News> newsList) {
        for (com.server.NewsAggrigationServer.model.News news : newsList) {
            List<Integer> categoryIds = getCategoryIdsForNews(news.getId());
            if (!categoryIds.isEmpty()) {
                sendNotificationsForNews(news, categoryIds);
            }
        }
    }

    private List<Integer> getCategoryIdsForNews(Integer newsId) {
        try {
            return newsCategoryService.getCategoriesByNewsId(newsId)
                .stream()
                .map(category -> category.getId())
                .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Failed to get category IDs for news " + newsId + ": " + e.getMessage());
            return List.of();
        }
    }

    private String getCategoryName(Integer categoryId) {
        try {
            return categoryService.getCategoryById(categoryId).getName();
        } catch (Exception e) {
            System.err.println("Failed to get category name for ID " + categoryId + ": " + e.getMessage());
            return "Category " + categoryId;
        }
    }
} 