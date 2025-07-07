package com.server.NewsAggrigationServer.service.impl;

import com.server.NewsAggrigationServer.dto.CategoryKeywordDTO;
import com.server.NewsAggrigationServer.model.CategoryKeyword;
import com.server.NewsAggrigationServer.repository.CategoryKeywordRepository;
import com.server.NewsAggrigationServer.service.CategoryKeywordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryKeywordServiceImpl implements CategoryKeywordService {

    private static final Logger log = LoggerFactory.getLogger(CategoryKeywordServiceImpl.class);

    @Autowired
    private CategoryKeywordRepository repository;

    @Override
    public CategoryKeywordDTO createCategoryKeyword(CategoryKeywordDTO dto) {
        log.info("Creating category keyword: {}", dto);
        CategoryKeyword entity = new CategoryKeyword();
        entity.setCategoryId(dto.getCategoryId());
        entity.setKeywordId(dto.getKeywordId());
        try {
            CategoryKeyword saved = repository.save(entity);
            log.info("CategoryKeyword created with ID: {}", saved.getId());
            dto.setId(saved.getId());
            return dto;
        } catch (Exception ex) {
            log.error("Error creating CategoryKeyword: {}", ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public List<CategoryKeywordDTO> getAllCategoryKeywords() {
        log.info("Fetching all category keywords");
        List<CategoryKeyword> keywords = repository.findAll();
        log.info("Fetched {} category keywords", keywords.size());
        return keywords.stream().map(entity -> {
            CategoryKeywordDTO dto = new CategoryKeywordDTO();
            dto.setId(entity.getId());
            dto.setCategoryId(entity.getCategoryId());
            dto.setKeywordId(entity.getKeywordId());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<CategoryKeywordDTO> getCategoryKeywordsByCategoryId(Integer categoryId) {
        log.info("Fetching category keywords by categoryId: {}", categoryId);
        if(categoryId == null){
            log.warn("categoryId is null. Returning empty list.");
            return new ArrayList<>();
        }
        List<CategoryKeyword> keywords = repository.findByCategoryId(categoryId);
        log.info("Fetched {} category keywords for categoryId {}", keywords.size(), categoryId);
        return keywords.stream().map(entity -> {
            CategoryKeywordDTO dto = new CategoryKeywordDTO();
            dto.setId(entity.getId());
            dto.setCategoryId(entity.getCategoryId());
            dto.setKeywordId(entity.getKeywordId());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<CategoryKeywordDTO> getCategoryKeywordsByKeywordId(Integer keywordId) {
        log.info("Fetching category keywords by keywordId: {}", keywordId);
        if(keywordId == null){
            log.warn("keywordId is null. Returning empty list.");
            return new ArrayList<>();
        }
        List<CategoryKeyword> keywords = repository.findByKeywordId(keywordId);
        log.info("Fetched {} category keywords for keywordId {}", keywords.size(), keywordId);
        return keywords.stream().map(entity -> {
            CategoryKeywordDTO dto = new CategoryKeywordDTO();
            dto.setId(entity.getId());
            dto.setCategoryId(entity.getCategoryId());
            dto.setKeywordId(entity.getKeywordId());
            return dto;
        }).collect(Collectors.toList());
    }
}
