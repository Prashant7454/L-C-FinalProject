package com.server.NewsAggrigationServer.ExternalNewsAPI.service.impl;

import java.util.ArrayList;
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
            
            // Filter out duplicate news articles
            List<News> newNewsArticles = filterDuplicateNews(newsAPIArticles);
            
            if (!newNewsArticles.isEmpty()) {
                // Save only new news articles
                List<News> savedNews = articleRepository.saveAll(newNewsArticles);
                
                // Assign categories to saved news articles
                System.out.println("Assigning categories to " + savedNews.size() + " new news articles from NewsAPI...");
                categoryAssignmentService.assignCategoriesToNewsList(savedNews);
                
                // Send notifications only for newly inserted news articles
                System.out.println("Sending notifications for " + savedNews.size() + " new news articles from NewsAPI...");
                newsNotificationService.sendNotificationsForNewsList(savedNews);
            } else {
                System.out.println("No new news articles found from NewsAPI (all were duplicates)");
            }
        } catch (Exception e) {
            System.err.println("Error while syncing from client: " + newsClients.getClass().getSimpleName());
            e.printStackTrace();
        }
    }

    private void fetchTheNews(){
        try {
            List<News> theNewsAPIArticles = theNewsAPIClient.fetchNews();
            
            // Filter out duplicate news articles
            List<News> newNewsArticles = filterDuplicateNews(theNewsAPIArticles);
            
            if (!newNewsArticles.isEmpty()) {
                // Save only new news articles
                List<News> savedNews = articleRepository.saveAll(newNewsArticles);
                
                // Assign categories to saved news articles
                System.out.println("Assigning categories to " + savedNews.size() + " new news articles from TheNewsAPI...");
                categoryAssignmentService.assignCategoriesToNewsList(savedNews);
                
                // Send notifications only for newly inserted news articles
                System.out.println("Sending notifications for " + savedNews.size() + " new news articles from TheNewsAPI...");
                newsNotificationService.sendNotificationsForNewsList(savedNews);
            } else {
                System.out.println("No new news articles found from TheNewsAPI (all were duplicates)");
            }
        } catch (Exception e) {
            System.err.println("Error while syncing from client: " + theNewsAPIClient.getClass().getSimpleName());
            e.printStackTrace();
        }
    }
    
    /**
     * Filters out duplicate news articles by checking if they already exist in the database
     * @param newsArticles List of news articles to filter
     * @return List of news articles that don't exist in the database
     */
    private List<News> filterDuplicateNews(List<News> newsArticles) {
        List<News> newNewsArticles = new ArrayList<>();
        
        for (News news : newsArticles) {
            if (news.getUrl() != null && !news.getUrl().trim().isEmpty()) {
                // Check if news with this URL already exists
                if (!articleRepository.existsByUrl(news.getUrl())) {
                    newNewsArticles.add(news);
                } else {
                    System.out.println("Skipping duplicate news: " + news.getTitle());
                }
            } else {
                // If URL is null or empty, we can't check for duplicates, so include it
                // but log a warning
                System.out.println("Warning: News article has no URL, including it: " + news.getTitle());
                newNewsArticles.add(news);
            }
        }
        
        System.out.println("Filtered " + newsArticles.size() + " articles, " + 
                          newNewsArticles.size() + " are new");
        
        return newNewsArticles;
    }
}

