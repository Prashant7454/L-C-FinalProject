package com.server.NewsAggrigationServer.service.impl;

import com.server.NewsAggrigationServer.dto.CategoryDTO;
import com.server.NewsAggrigationServer.model.Category;
import com.server.NewsAggrigationServer.repository.CategoryRepository;
import com.server.NewsAggrigationServer.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO createCategory(CategoryDTO dto) {
        if (categoryRepository.existsByName(dto.getName())) {
            throw new RuntimeException("Category already exists with name: " + dto.getName());
        }

        Category category = new Category(dto.getName());
        category = categoryRepository.save(category);
        dto.setId(category.getId());
        return dto;
    }

    @Override
    public CategoryDTO getCategoryById(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(c -> {
                    CategoryDTO dto = new CategoryDTO();
                    dto.setId(c.getId());
                    dto.setName(c.getName());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> getAllCategoriesByIds(List<Integer> categoryIds) {
        return categoryRepository.findByIdIn(categoryIds).stream()
                .map(c -> {
                    CategoryDTO dto = new CategoryDTO();
                    dto.setId(c.getId());
                    dto.setName(c.getName());
                    return dto;
                }).collect(Collectors.toList());
    }
}

