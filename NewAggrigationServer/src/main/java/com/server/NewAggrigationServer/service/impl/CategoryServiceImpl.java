package com.server.NewAggrigationServer.service.impl;

import com.server.NewAggrigationServer.dto.CategoryDTO;
import com.server.NewAggrigationServer.model.Category;
import com.server.NewAggrigationServer.repository.CategoryRepository;
import com.server.NewAggrigationServer.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
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

