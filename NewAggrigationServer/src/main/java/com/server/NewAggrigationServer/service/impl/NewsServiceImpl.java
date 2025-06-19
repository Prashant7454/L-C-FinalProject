package com.server.NewAggrigationServer.service.impl;

import com.server.NewAggrigationServer.dto.NewsDTO;
import com.server.NewAggrigationServer.exception.ResourceNotFoundException;
import com.server.NewAggrigationServer.model.News;
import com.server.NewAggrigationServer.repository.NewsRepository;
import com.server.NewAggrigationServer.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        return newsList.stream().map(news -> {
            NewsDTO dto = new NewsDTO();
            dto.setId(news.getId());
            dto.setTitle(news.getTitle());
            dto.setDescription(news.getDescription());
            dto.setSource(news.getSource());
            dto.setPublishAt(news.getPublishAt());
            dto.setUrl(news.getUrl());
            dto.setKeyword(news.getKeyword());
            return dto;
        }).collect(Collectors.toList());
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
        return dto;
    }

    private void mapDtoToEntity(NewsDTO dto, News news) {
        news.setTitle(dto.getTitle());
        news.setDescription(dto.getDescription());
        news.setSource(dto.getSource());
        news.setUrl(dto.getUrl());
        news.setPublishAt(dto.getPublishAt());
        news.setKeyword(dto.getKeyword());
    }
}

