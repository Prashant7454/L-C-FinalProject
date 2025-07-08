package com.server.NewsAggrigationServer.ExternalNewsAPI.service;

import com.server.NewsAggrigationServer.model.News;

import java.util.List;

public interface TheNewsAPIClient {
    public List<News> fetchNews();
}
