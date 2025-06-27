package com.server.NewAggrigationServer.ExternalNewsAPI.service;

import com.server.NewAggrigationServer.model.News;

import java.util.List;

public interface TheNewsAPIClient {
    public List<News> fetchNews();
}
