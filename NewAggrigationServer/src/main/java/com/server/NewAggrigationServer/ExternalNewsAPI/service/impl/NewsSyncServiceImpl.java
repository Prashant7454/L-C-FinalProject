package com.server.NewAggrigationServer.ExternalNewsAPI.service.impl;

import java.util.List;

import com.server.NewAggrigationServer.ExternalNewsAPI.service.NewsAPIClient;
import com.server.NewAggrigationServer.ExternalNewsAPI.service.NewsSyncService;
import com.server.NewAggrigationServer.model.News;
import com.server.NewAggrigationServer.repository.NewsRepository;
import org.springframework.stereotype.Service;

@Service
public class NewsSyncServiceImpl implements NewsSyncService {
    private final List<NewsAPIClient> newsClients;
    private final NewsRepository articleRepository;

    public NewsSyncServiceImpl(List<NewsAPIClient> newsClients, NewsRepository articleRepository) {
        this.newsClients = newsClients;
        this.articleRepository = articleRepository;
    }
    @Override
    public void syncAllFeeds() {
        for (NewsAPIClient client : newsClients) {
            try {
                List<News> articles = client.fetchNews();
                articleRepository.saveAll(articles);
            } catch (Exception e) {
                System.err.println("Error while syncing from client: " + client.getClass().getSimpleName());
                e.printStackTrace();
            }
        }
    }
}

