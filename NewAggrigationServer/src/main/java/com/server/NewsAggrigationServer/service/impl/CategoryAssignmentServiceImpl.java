package com.server.NewsAggrigationServer.service.impl;

import com.server.NewsAggrigationServer.model.CategoryKeyword;
import com.server.NewsAggrigationServer.model.Keyword;
import com.server.NewsAggrigationServer.model.News;
import com.server.NewsAggrigationServer.model.NewsCategory;
import com.server.NewsAggrigationServer.repository.CategoryKeywordRepository;
import com.server.NewsAggrigationServer.repository.KeywordRepository;
import com.server.NewsAggrigationServer.repository.NewsCategoryRepository;
import com.server.NewsAggrigationServer.service.CategoryAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryAssignmentServiceImpl implements CategoryAssignmentService {

    @Autowired
    private CategoryKeywordRepository categoryKeywordRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private NewsCategoryRepository newsCategoryRepository;

    @Override
    public void assignCategoriesToNews(News news) {
        if (news == null || news.getTitle() == null) {
            return;
        }

        // Create search text from title and content
        String searchText = createSearchText(news);
        
        // Find matching categories
        List<Integer> matchingCategoryIds = findMatchingCategories(searchText);
        
        // Assign categories to news
        for (Integer categoryId : matchingCategoryIds) {
            assignCategoryToNews(news.getId(), categoryId);
        }
        
        System.out.println("Assigned " + matchingCategoryIds.size() + " categories to news: " + news.getTitle());
    }

    @Override
    public void assignCategoriesToNewsList(List<News> newsList) {
        if (newsList == null || newsList.isEmpty()) {
            return;
        }

        System.out.println("Starting category assignment for " + newsList.size() + " news articles...");
        
        for (News news : newsList) {
            assignCategoriesToNews(news);
        }
        
        System.out.println("Completed category assignment for all news articles.");
    }

    @Override
    public List<Integer> findMatchingCategories(String text) {
        if (text == null || text.trim().isEmpty()) {
            return new ArrayList<>();
        }

        // Convert text to lowercase for case-insensitive matching
        String lowerText = text.toLowerCase();
        
        // Get all keywords and their associated categories
        List<Keyword> allKeywords = keywordRepository.findAll();
        List<CategoryKeyword> allCategoryKeywords = categoryKeywordRepository.findAll();
        
        // Create a map of keyword name to category IDs
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
        
        // Find matching keywords in the text
        Set<Integer> matchingCategoryIds = new HashSet<>();
        
        for (String keyword : keywordToCategories.keySet()) {
            if (lowerText.contains(keyword)) {
                matchingCategoryIds.addAll(keywordToCategories.get(keyword));
            }
        }
        
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
        // Check if the category is already assigned to this news
        boolean alreadyAssigned = newsCategoryRepository.existsByNewsIdAndCategoryId(newsId, categoryId);
        
        if (!alreadyAssigned) {
            NewsCategory newsCategory = new NewsCategory(newsId, categoryId);
            newsCategoryRepository.save(newsCategory);
            System.out.println("Assigned category ID " + categoryId + " to news ID " + newsId);
        }
    }
} 