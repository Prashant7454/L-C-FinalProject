package com.server.NewsAggrigationServer.scheduler;

import com.server.NewsAggrigationServer.ExternalNewsAPI.service.NewsSyncService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NewsFetchScheduler {
    private final NewsSyncService newsSyncService;

    public NewsFetchScheduler(NewsSyncService newsSyncService) {
        this.newsSyncService = newsSyncService;
    }

    @Scheduled(fixedRate = 3 * 60 * 6000) // 3 hrs
    public void fetchAllNewsSources() {
        System.out.println("Fetching news at: " + LocalDateTime.now());
        newsSyncService.syncAllFeeds();
    }
}
