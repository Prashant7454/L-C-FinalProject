package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.model.News;
import java.util.List;

public interface NewsNotificationService {
    void sendNotificationsForNews(News news, List<Integer> categoryIds);

    void sendNotificationsForNewsList(List<News> newsList);
} 