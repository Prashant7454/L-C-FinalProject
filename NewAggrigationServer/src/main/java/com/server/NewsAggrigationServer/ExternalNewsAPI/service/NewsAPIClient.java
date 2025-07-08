package com.server.NewsAggrigationServer.ExternalNewsAPI.service;

import com.server.NewsAggrigationServer.model.News;

import java.util.List;

public interface NewsAPIClient {
    public List<News> fetchNews();
}
