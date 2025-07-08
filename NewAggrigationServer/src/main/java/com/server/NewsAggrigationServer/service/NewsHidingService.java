package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.model.News;

import java.util.List;

public interface NewsHidingService {

    int hideNewsByKeywords(List<String> keywords);

    boolean hideNewsById(Integer newsId);

    boolean unhideNewsById(Integer newsId);

    List<News> findNewsByKeywords(List<String> keywords);

    int getNewsCountByKeywords(List<String> keywords);
} 