package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO dto);
    CategoryDTO getCategoryById(int id);
    List<CategoryDTO> getAllCategories();
    List<CategoryDTO> getAllCategoriesByIds(List<Integer> categoryIds);
    
    // New methods for visible categories
    List<CategoryDTO> getAllVisibleCategories();
    List<CategoryDTO> getVisibleCategoriesByIds(List<Integer> categoryIds);
    
    // Admin methods for hiding/unhiding categories
    CategoryDTO hideCategory(Integer id);
    CategoryDTO unhideCategory(Integer id);
}

