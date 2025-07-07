package com.server.NewsAggrigationServer.service.impl;

import com.server.NewsAggrigationServer.dto.NewsDTO;
import com.server.NewsAggrigationServer.exception.ExceptionConstants;
import com.server.NewsAggrigationServer.exception.NewsServiceException;
import com.server.NewsAggrigationServer.exception.ResourceNotFoundException;
import com.server.NewsAggrigationServer.model.News;
import com.server.NewsAggrigationServer.repository.NewsRepository;
import com.server.NewsAggrigationServer.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    private static final Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public NewsDTO createNews(NewsDTO dto) {
        log.info("Creating news: {}", dto);
        try {
            News news = new News();
            mapDtoToEntity(dto, news);
            News saved = newsRepository.save(news);
            log.info("News created with ID: {}", saved.getId());
            return mapEntityToDto(saved);
        } catch (Exception e) {
            log.error("Error creating news: {}", e.getMessage(), e);
            throw new NewsServiceException(
                ExceptionConstants.NEWS_SAVE_ERROR,
                ExceptionConstants.NEWS_SERVICE_ERROR,
                "CREATE_NEWS"
            );
        }
    }

    @Override
    public NewsDTO updateNews(Integer id, NewsDTO dto) {
        log.info("Updating news with id: {} and dto: {}", id, dto);
        try {
            News news = newsRepository.findById(id)
                    .orElseThrow(() -> {
                        log.warn("News not found with ID: {}", id);
                        return new ResourceNotFoundException(ExceptionConstants.NEWS_NOT_FOUND + " with ID: " + id);
                    });
            mapDtoToEntity(dto, news);
            News updated = newsRepository.save(news);
            log.info("News updated with ID: {}", updated.getId());
            return mapEntityToDto(updated);
        } catch (ResourceNotFoundException e) {
            log.warn("Update failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error updating news: {}", e.getMessage(), e);
            throw new NewsServiceException(
                ExceptionConstants.NEWS_UPDATE_ERROR,
                ExceptionConstants.NEWS_SERVICE_ERROR,
                "UPDATE_NEWS"
            );
        }
    }

    @Override
    public NewsDTO getNewsById(Integer id) {
        log.info("Fetching news by id: {}", id);
        News news = newsRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("News not found with ID: {}", id);
                    return new ResourceNotFoundException(ExceptionConstants.NEWS_NOT_FOUND + " with ID: " + id);
                });
        log.info("News found: {}", news.getId());
        return mapEntityToDto(news);
    }

    @Override
    public List<NewsDTO> getAllNews() {
        log.info("Fetching all news");
        List<News> newsList = newsRepository.findAll();
        log.info("Fetched {} news articles", newsList.size());
        return newsList.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NewsDTO> getNews(String searchString) {
        log.info("Fetching news by search string: {}", searchString);
        List<News> matchedNews = newsRepository.search(searchString);
        log.info("Fetched {} news articles for search string '{}'", matchedNews.size(), searchString);
        return matchedNews.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NewsDTO> getNewsByIds(List<Integer> ids) {
        log.info("Fetching news by ids: {}", ids);
        List<News> newsList = newsRepository.findByIdIn(ids);
        log.info("Fetched {} news articles for ids {}", newsList.size(), ids);
        return newsList.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void addMultipleNews(List<NewsDTO> newsList) {
        log.info("Adding multiple news articles: {}", newsList != null ? newsList.size() : 0);
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
        log.info("Saved {} news articles", newsEntities.size());
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
        dto.setReportCount(news.getReportCount());
        dto.setIsHide(news.getIsHide());
        return dto;
    }

    private void mapDtoToEntity(NewsDTO dto, News news) {
        log.debug("Mapping DTO to Entity: {}", dto);
        news.setTitle(dto.getTitle());
        news.setDescription(dto.getDescription());
        news.setSource(dto.getSource());
        news.setUrl(dto.getUrl());
        news.setPublishAt(dto.getPublishAt());
        news.setKeyword(dto.getKeyword());
        news.setLikeCount(dto.getLikeCount());
        news.setDisLikeCount(dto.getDisLikeCount());
        news.setReportCount(dto.getReportCount());
        news.setIsHide(dto.getIsHide());
        log.debug("Entity after mapping: {}", news);
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

    // New methods for visible news only
    @Override
    public List<NewsDTO> getAllVisibleNews() {
        return newsRepository.findByIsHide(0).stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NewsDTO> getVisibleNews(String searchString) {
        List<News> matchedNews = newsRepository.searchVisible(searchString);
        return matchedNews.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NewsDTO> getVisibleNewsByIds(List<Integer> ids) {
        List<News> newsList = newsRepository.findByIdInAndIsHide(ids, 0);
        return newsList.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NewsDTO> getVisibleTodayNewsByIds(List<Integer> ids) {
        LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusSeconds(1);
        List<News> newsList = newsRepository.findByIdInAndPublishAtBetweenAndIsHide(ids, startOfDay, endOfDay, 0);
        return newsList.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NewsDTO> getVisibleNewsByIdsAndDateRange(List<Integer> ids, LocalDateTime startDate, LocalDateTime endDate) {
        List<News> newsList = newsRepository.findByIdInAndPublishAtBetweenAndIsHide(ids, startDate, endDate, 0);
        return newsList.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NewsDTO> getNewsInVisibleCategories() {
        return newsRepository.findNewsInVisibleCategories().stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NewsDTO> getNewsByIdsInVisibleCategories(List<Integer> newsIds) {
        return newsRepository.findNewsByIdsInVisibleCategories(newsIds).stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NewsDTO> getReportedNews() {
        return newsRepository.findByReportCountGreaterThan(0).stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public NewsDTO hideNews(Integer id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("News not found with id: " + id));
        
        news.setIsHide(1);
        news = newsRepository.save(news);
        
        return mapEntityToDto(news);
    }

    @Override
    public NewsDTO unhideNews(Integer id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("News not found with id: " + id));
        
        news.setIsHide(0);
        news = newsRepository.save(news);
        
        return mapEntityToDto(news);
    }
}

