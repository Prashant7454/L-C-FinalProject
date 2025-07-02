package com.server.NewsAggrigationServer.service.impl;

import com.server.NewsAggrigationServer.dto.NewsDTO;
import com.server.NewsAggrigationServer.exception.ResourceNotFoundException;
import com.server.NewsAggrigationServer.model.News;
import com.server.NewsAggrigationServer.repository.NewsRepository;
import com.server.NewsAggrigationServer.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public NewsDTO createNews(NewsDTO dto) {
        News news = new News();
        mapDtoToEntity(dto, news);
        News saved = newsRepository.save(news);
        return mapEntityToDto(saved);
    }

    @Override
    public NewsDTO updateNews(Integer id, NewsDTO dto) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News not found with ID: " + id));
        mapDtoToEntity(dto, news);
        News updated = newsRepository.save(news);
        return mapEntityToDto(updated);
    }

    @Override
    public NewsDTO getNewsById(Integer id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News not found with ID: " + id));
        return mapEntityToDto(news);
    }

    @Override
    public List<NewsDTO> getAllNews() {
        return newsRepository.findAll().stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NewsDTO> getNews(String searchString) {
        List<News> matchedNews = newsRepository.search(searchString);
        return matchedNews.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NewsDTO> getNewsByIds(List<Integer> ids) {
        List<News> newsList = newsRepository.findByIdIn(ids);
        return newsList.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void addMultipleNews(List<NewsDTO> newsList) {
        List<News> newsEntities = newsList.stream().map(dto -> {
            News news = new News();
            news.setTitle(dto.getTitle());
            news.setDescription(dto.getDescription());
            news.setSource(dto.getSource());
            news.setPublishAt(dto.getPublishAt());
            news.setUrl(dto.getUrl());
            news.setKeyword(dto.getKeyword());
            return news;
        }).collect(Collectors.toList());

        newsRepository.saveAll(newsEntities);
    }

    private NewsDTO mapEntityToDto(News news) {
        NewsDTO dto = new NewsDTO();
        dto.setId(news.getId());
        dto.setTitle(news.getTitle());
        dto.setDescription(news.getDescription());
        dto.setSource(news.getSource());
        dto.setUrl(news.getUrl());
        dto.setPublishAt(news.getPublishAt());
        dto.setKeyword(news.getKeyword());
        dto.setLikeCount(news.getLikeCount());
        dto.setDisLikeCount(news.getDisLikeCount());
        return dto;
    }

    private void mapDtoToEntity(NewsDTO dto, News news) {
        news.setTitle(dto.getTitle());
        news.setDescription(dto.getDescription());
        news.setSource(dto.getSource());
        news.setUrl(dto.getUrl());
        news.setPublishAt(dto.getPublishAt());
        news.setKeyword(dto.getKeyword());
        news.setLikeCount(dto.getLikeCount());
        news.setDisLikeCount(dto.getDisLikeCount());
    }

    @Override
    public List<NewsDTO> getTodayNewsByIds(List<Integer> ids) {
        LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusSeconds(1);
        List<News> newsList = newsRepository.findByIdInAndPublishAtBetween(ids, startOfDay, endOfDay);
        return newsList.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NewsDTO> getNewsByIdsAndDateRange(List<Integer> ids, LocalDateTime startDate, LocalDateTime endDate) {
        List<News> newsList = newsRepository.findByIdInAndPublishAtBetween(ids, startDate, endDate);
        return newsList.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }
}

