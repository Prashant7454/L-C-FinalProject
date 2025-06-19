package com.server.NewAggrigationServer.service;

import com.server.NewAggrigationServer.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO dto);
    List<CategoryDTO> getAllCategories();
    public List<CategoryDTO> getAllCategoriesByIds(List<Integer> categoryIds);
}

