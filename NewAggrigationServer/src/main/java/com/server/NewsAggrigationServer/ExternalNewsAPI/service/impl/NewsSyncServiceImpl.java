package com.server.NewsAggrigationServer.ExternalNewsAPI.service.impl;

import java.util.List;

import com.server.NewsAggrigationServer.ExternalNewsAPI.service.NewsAPIClient;
import com.server.NewsAggrigationServer.ExternalNewsAPI.service.NewsSyncService;
import com.server.NewsAggrigationServer.ExternalNewsAPI.service.TheNewsAPIClient;
import com.server.NewsAggrigationServer.model.News;
import com.server.NewsAggrigationServer.repository.NewsRepository;
import com.server.NewsAggrigationServer.service.CategoryAssignmentService;
import com.server.NewsAggrigationServer.service.NewsNotificationService;
import org.springframework.stereotype.Service;

@Service
public class NewsSyncServiceImpl implements NewsSyncService {
    private final NewsAPIClient newsClients;
    private final NewsRepository articleRepository;
    private final TheNewsAPIClient theNewsAPIClient;
    private final CategoryAssignmentService categoryAssignmentService;
    private final NewsNotificationService newsNotificationService;

    public NewsSyncServiceImpl(NewsAPIClient newsClients, TheNewsAPIClient theNewsAPIClient, 
                              NewsRepository articleRepository, CategoryAssignmentService categoryAssignmentService,
                              NewsNotificationService newsNotificationService) {
        this.newsClients = newsClients;
        this.articleRepository = articleRepository;
        this.theNewsAPIClient = theNewsAPIClient;
        this.categoryAssignmentService = categoryAssignmentService;
        this.newsNotificationService = newsNotificationService;
    }
    
    @Override
    public void syncAllFeeds() {
        fetchNewsApi();
        fetchTheNews();
    }

    private void fetchNewsApi(){
        try {
            List<News> newsAPIArticles = newsClients.fetchNews();
            // Save news articles first
            List<News> savedNews = articleRepository.saveAll(newsAPIArticles);
            
            // Assign categories to saved news articles
            if (!savedNews.isEmpty()) {
                System.out.println("Assigning categories to " + savedNews.size() + " news articles from NewsAPI...");
                categoryAssignmentService.assignCategoriesToNewsList(savedNews);
                
                // Send notifications for new news articles
                System.out.println("Sending notifications for " + savedNews.size() + " news articles from NewsAPI...");
                newsNotificationService.sendNotificationsForNewsList(savedNews);
            }
        } catch (Exception e) {
            System.err.println("Error while syncing from client: " + newsClients.getClass().getSimpleName());
            e.printStackTrace();
        }
    }

    private void fetchTheNews(){
        try {
            List<News> theNewsAPIArticles = theNewsAPIClient.fetchNews();
            // Save news articles first
            List<News> savedNews = articleRepository.saveAll(theNewsAPIArticles);
            
            // Assign categories to saved news articles
            if (!savedNews.isEmpty()) {
                System.out.println("Assigning categories to " + savedNews.size() + " news articles from TheNewsAPI...");
                categoryAssignmentService.assignCategoriesToNewsList(savedNews);
                
                // Send notifications for new news articles
                System.out.println("Sending notifications for " + savedNews.size() + " news articles from TheNewsAPI...");
                newsNotificationService.sendNotificationsForNewsList(savedNews);
            }
        } catch (Exception e) {
            System.err.println("Error while syncing from client: " + theNewsAPIClient.getClass().getSimpleName());
            e.printStackTrace();
        }
    }
}

