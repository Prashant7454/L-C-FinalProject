package com.server.NewsAggrigationServer.service.impl;

import com.server.NewsAggrigationServer.dto.CategoryDTO;
import com.server.NewsAggrigationServer.model.Category;
import com.server.NewsAggrigationServer.repository.CategoryRepository;
import com.server.NewsAggrigationServer.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO createCategory(CategoryDTO dto) {
        log.info("Creating category with name: {}", dto.getName());
        if (categoryRepository.existsByName(dto.getName())) {
            log.warn("Category creation failed: name '{}' already exists", dto.getName());
            throw new RuntimeException("Category already exists with name: " + dto.getName());
        }
        Category category = new Category(dto.getName());
        category = categoryRepository.save(category);
        log.info("Category created successfully with ID: {}", category.getId());
        dto.setId(category.getId());
        return dto;
    }

    @Override
    public CategoryDTO getCategoryById(int id) {
        log.info("Fetching category by id: {}", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Category not found with id: {}", id);
                    return new RuntimeException("Category not found with id: " + id);
                });
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setIsHide(category.getIsHide());
        log.info("Category found: {}", dto);
        return dto;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        log.info("Fetching all categories");
        List<Category> categories = categoryRepository.findAll();
        log.info("Fetched {} categories", categories.size());
        return categories.stream()
                .map(c -> {
                    CategoryDTO dto = new CategoryDTO();
                    dto.setId(c.getId());
                    dto.setName(c.getName());
                    dto.setIsHide(c.getIsHide());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> getAllCategoriesByIds(List<Integer> categoryIds) {
        log.info("Fetching categories by ids: {}", categoryIds);
        List<Category> categories = categoryRepository.findByIdIn(categoryIds);
        log.info("Fetched {} categories for ids {}", categories.size(), categoryIds);
        return categories.stream()
                .map(c -> {
                    CategoryDTO dto = new CategoryDTO();
                    dto.setId(c.getId());
                    dto.setName(c.getName());
                    dto.setIsHide(c.getIsHide());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> getAllVisibleCategories() {
        log.info("Fetching all visible categories");
        List<Category> categories = categoryRepository.findByIsHide(0);
        log.info("Fetched {} visible categories", categories.size());
        return categories.stream()
                .map(c -> {
                    CategoryDTO dto = new CategoryDTO();
                    dto.setId(c.getId());
                    dto.setName(c.getName());
                    dto.setIsHide(c.getIsHide());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> getVisibleCategoriesByIds(List<Integer> categoryIds) {
        log.info("Fetching visible categories by ids: {}", categoryIds);
        List<Category> categories = categoryRepository.findByIdInAndIsHide(categoryIds, 0);
        log.info("Fetched {} visible categories for ids {}", categories.size(), categoryIds);
        return categories.stream()
                .map(c -> {
                    CategoryDTO dto = new CategoryDTO();
                    dto.setId(c.getId());
                    dto.setName(c.getName());
                    dto.setIsHide(c.getIsHide());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO hideCategory(Integer id) {
        log.info("Hiding category with id: {}", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Category not found for hiding with id: {}", id);
                    return new RuntimeException("Category not found with id: " + id);
                });
        category.setIsHide(1);
        category = categoryRepository.save(category);
        log.info("Category with id {} is now hidden", id);
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setIsHide(category.getIsHide());
        return dto;
    }

    @Override
    public CategoryDTO unhideCategory(Integer id) {
        log.info("Unhiding category with id: {}", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Category not found for unhiding with id: {}", id);
                    return new RuntimeException("Category not found with id: " + id);
                });
        category.setIsHide(0);
        category = categoryRepository.save(category);
        log.info("Category with id {} is now visible", id);
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setIsHide(category.getIsHide());
        return dto;
    }
}

