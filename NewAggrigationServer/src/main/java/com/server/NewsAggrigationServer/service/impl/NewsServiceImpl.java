package com.server.NewsAggrigationServer.service.impl;

import com.server.NewsAggrigationServer.dto.NewsDTO;
import com.server.NewsAggrigationServer.exception.ExceptionConstants;
import com.server.NewsAggrigationServer.exception.NewsServiceException;
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
        try {
            News news = new News();
            mapDtoToEntity(dto, news);
            News saved = newsRepository.save(news);
            return mapEntityToDto(saved);
        } catch (Exception e) {
            throw new NewsServiceException(
                ExceptionConstants.NEWS_SAVE_ERROR,
                ExceptionConstants.NEWS_SERVICE_ERROR,
                "CREATE_NEWS"
            );
        }
    }

    @Override
    public NewsDTO updateNews(Integer id, NewsDTO dto) {
        try {
            News news = newsRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(ExceptionConstants.NEWS_NOT_FOUND + " with ID: " + id));
            
            mapDtoToEntity(dto, news);
            News updated = newsRepository.save(news);
            return mapEntityToDto(updated);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new NewsServiceException(
                ExceptionConstants.NEWS_UPDATE_ERROR,
                ExceptionConstants.NEWS_SERVICE_ERROR,
                "UPDATE_NEWS"
            );
        }
    }

    @Override
    public NewsDTO getNewsById(Integer id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionConstants.NEWS_NOT_FOUND + " with ID: " + id));
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
        dto.setReportCount(news.getReportCount());
        dto.setIsHide(news.getIsHide());
        return dto;
    }

    private void mapDtoToEntity(NewsDTO dto, News news) {
        System.out.println("Mapping DTO to Entity:");
        System.out.println("DTO like count: " + dto.getLikeCount());
        System.out.println("DTO dislike count: " + dto.getDisLikeCount());
        
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
        
        System.out.println("Entity like count after mapping: " + news.getLikeCount());
        System.out.println("Entity dislike count after mapping: " + news.getDisLikeCount());
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

