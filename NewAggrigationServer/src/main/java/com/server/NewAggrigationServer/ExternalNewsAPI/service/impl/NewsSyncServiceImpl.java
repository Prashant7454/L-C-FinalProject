package com.server.NewAggrigationServer.ExternalNewsAPI.service.impl;

import java.util.List;

import com.server.NewAggrigationServer.ExternalNewsAPI.service.NewsAPIClient;
import com.server.NewAggrigationServer.ExternalNewsAPI.service.NewsSyncService;
import com.server.NewAggrigationServer.ExternalNewsAPI.service.TheNewsAPIClient;
import com.server.NewAggrigationServer.model.News;
import com.server.NewAggrigationServer.repository.NewsRepository;
import org.springframework.stereotype.Service;

@Service
public class NewsSyncServiceImpl implements NewsSyncService {
    private final NewsAPIClient newsClients;
    private final NewsRepository articleRepository;
    private final TheNewsAPIClient theNewsAPIClient;

    public NewsSyncServiceImpl(NewsAPIClient newsClients, TheNewsAPIClient theNewsAPIClient, NewsRepository articleRepository) {
        this.newsClients = newsClients;
        this.articleRepository = articleRepository;
        this.theNewsAPIClient = theNewsAPIClient;
    }
    @Override
    public void syncAllFeeds() {
        fetchNewsApi();
        fetchTheNews();
    }

    private void fetchNewsApi(){
        try {
            List<News> newsAPIArticles = newsClients.fetchNews();
            articleRepository.saveAll(newsAPIArticles);
        } catch (Exception e) {
            System.err.println("Error while syncing from client: " + newsClients.getClass().getSimpleName());
            e.printStackTrace();
        }
    }

    private void fetchTheNews(){
        try {
            List<News> theNewsAPIArticles = theNewsAPIClient.fetchNews();
        } catch (Exception e) {
            System.err.println("Error while syncing from client: " + theNewsAPIClient.getClass().getSimpleName());
            e.printStackTrace();
        }
    }
}

