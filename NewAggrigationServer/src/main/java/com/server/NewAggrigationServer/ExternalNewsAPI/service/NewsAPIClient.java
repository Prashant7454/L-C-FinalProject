package com.server.NewAggrigationServer.ExternalNewsAPI.service;

import com.server.NewAggrigationServer.model.News;

import java.util.List;

public interface NewsAPIClient {
    public List<News> fetchNews();
}
