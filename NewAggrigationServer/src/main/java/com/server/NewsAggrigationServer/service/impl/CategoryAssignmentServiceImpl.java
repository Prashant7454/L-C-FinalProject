package com.server.NewsAggrigationServer.service.impl;

import com.server.NewsAggrigationServer.model.CategoryKeyword;
import com.server.NewsAggrigationServer.model.Keyword;
import com.server.NewsAggrigationServer.model.News;
import com.server.NewsAggrigationServer.model.NewsCategory;
import com.server.NewsAggrigationServer.repository.CategoryKeywordRepository;
import com.server.NewsAggrigationServer.repository.KeywordRepository;
import com.server.NewsAggrigationServer.repository.NewsCategoryRepository;
import com.server.NewsAggrigationServer.repository.NewsRepository;
import com.server.NewsAggrigationServer.service.CategoryAssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryAssignmentServiceImpl implements CategoryAssignmentService {

    private static final Logger log = LoggerFactory.getLogger(CategoryAssignmentServiceImpl.class);

    @Autowired
    private CategoryKeywordRepository categoryKeywordRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private NewsCategoryRepository newsCategoryRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public void assignCategoriesToNews(News news) {
        log.info("Assigning categories to news: {}", news != null ? news.getTitle() : null);
        if (news == null || news.getTitle() == null) {
            log.warn("News or news title is null. Skipping category assignment.");
            return;
        }
        String searchText = createSearchText(news);
        log.debug("Search text for category assignment: {}", searchText);
        List<Integer> matchingCategoryIds = findMatchingCategories(searchText);
        log.info("Found {} matching categories for news '{}': {}", matchingCategoryIds.size(), news.getTitle(), matchingCategoryIds);
        for (Integer categoryId : matchingCategoryIds) {
            assignCategoryToNews(news.getId(), categoryId);
        }
        log.info("Assigned {} categories to news: {}", matchingCategoryIds.size(), news.getTitle());
    }

    @Override
    public void assignCategoriesToNewsList(List<News> newsList) {
        log.info("Starting category assignment for {} news articles...", newsList != null ? newsList.size() : 0);
        if (newsList == null || newsList.isEmpty()) {
            log.warn("News list is null or empty. Skipping batch category assignment.");
            return;
        }
        for (News news : newsList) {
            assignCategoriesToNews(news);
        }
        log.info("Completed category assignment for all news articles.");
    }

    @Override
    public List<Integer> findMatchingCategories(String text) {
        if (text == null || text.trim().isEmpty()) {
            log.warn("Input text is null or empty. No categories will be matched.");
            return new ArrayList<>();
        }

        String lowerText = text.toLowerCase();

        List<Keyword> allKeywords = keywordRepository.findAll();
        List<CategoryKeyword> allCategoryKeywords = categoryKeywordRepository.findAll();

        Map<String, List<Integer>> keywordToCategories = new HashMap<>();
        
        for (CategoryKeyword ck : allCategoryKeywords) {
            Keyword keyword = allKeywords.stream()
                    .filter(k -> k.getId().equals(ck.getKeywordId()))
                    .findFirst()
                    .orElse(null);
            
            if (keyword != null) {
                keywordToCategories.computeIfAbsent(keyword.getName().toLowerCase(), k -> new ArrayList<>())
                        .add(ck.getCategoryId());
            }
        }

        Set<Integer> matchingCategoryIds = new HashSet<>();
        
        for (String keyword : keywordToCategories.keySet()) {
            if (lowerText.contains(keyword)) {
                matchingCategoryIds.addAll(keywordToCategories.get(keyword));
            }
        }
        
        log.debug("Keyword to categories map: {}", keywordToCategories);
        log.info("Matched category IDs: {}", matchingCategoryIds);
        return new ArrayList<>(matchingCategoryIds);
    }

    private String createSearchText(News news) {
        StringBuilder searchText = new StringBuilder();
        
        if (news.getTitle() != null) {
            searchText.append(news.getTitle()).append(" ");
        }
        
        if (news.getDescription() != null) {
            searchText.append(news.getDescription()).append(" ");
        }
        
        return searchText.toString();
    }

    private void assignCategoryToNews(Integer newsId, Integer categoryId) {
        boolean alreadyAssigned = newsCategoryRepository.existsByNewsIdAndCategoryId(newsId, categoryId);
        if (!alreadyAssigned) {
            NewsCategory newsCategory = new NewsCategory(newsId, categoryId);
            newsCategoryRepository.save(newsCategory);
            log.info("Assigned category ID {} to news ID {}", categoryId, newsId);
        } else {
            log.debug("Category ID {} already assigned to news ID {}", categoryId, newsId);
        }
    }

    @Override
    public List<News> findUncategorizedNews() {
        List<News> allNews = newsRepository.findAll();
        return allNews.stream()
                .filter(news -> {
                    List<Integer> categoryIds = newsCategoryRepository.findByNewsId(news.getId())
                            .stream()
                            .map(cat -> cat.getCategoryId())
                            .toList();
                    return categoryIds.isEmpty();
                })
                .toList();
    }

    @Override
    public int countUncategorizedNews() {
        List<News> allNews = newsRepository.findAll();
        return (int) allNews.stream()
                .filter(news -> {
                    List<Integer> categoryIds = newsCategoryRepository.findByNewsId(news.getId())
                            .stream()
                            .map(cat -> cat.getCategoryId())
                            .toList();
                    return categoryIds.isEmpty();
                })
                .count();
    }
} 