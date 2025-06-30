package com.server.NewAggrigationServer.service;

import com.server.NewAggrigationServer.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO dto);
    CategoryDTO getCategoryById(int id);
    List<CategoryDTO> getAllCategories();
    List<CategoryDTO> getAllCategoriesByIds(List<Integer> categoryIds);
}

