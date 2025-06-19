package com.server.NewAggrigationServer.service.impl;

import com.server.NewAggrigationServer.dto.CategoryDTO;
import com.server.NewAggrigationServer.dto.NewsCategoryDTO;
import com.server.NewAggrigationServer.model.NewsCategory;
import com.server.NewAggrigationServer.repository.NewsCategoryRepository;
import com.server.NewAggrigationServer.service.CategoryService;
import com.server.NewAggrigationServer.service.NewsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsCategoryServiceImpl implements NewsCategoryService {

    @Autowired
    private NewsCategoryRepository newsCategoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Override
    public NewsCategoryDTO assignCategory(NewsCategoryDTO dto) {
        NewsCategory entity = new NewsCategory(dto.getNewsId(), dto.getCategoryId());
        entity = newsCategoryRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public List<CategoryDTO> getCategoriesByNewsId(Integer newsId) {
        List<Integer> categoryIds = new ArrayList<>();
        List<NewsCategory> newsCategories = newsCategoryRepository.findByNewsId(newsId);
        for(NewsCategory newsCategory: newsCategories){
            categoryIds.add(newsCategory.getCategoryId());
        }
        return categoryService.getAllCategoriesByIds(categoryIds);
    }

    @Override
    public void removeCategoryFromNews(Integer newsId, Integer categoryId) {
        newsCategoryRepository.deleteByNewsIdAndCategoryId(newsId, categoryId);
    }

    @Override
    public void removeCategoriesByNewsId(Integer newsId) {
        newsCategoryRepository.deleteByNewsId(newsId);
    }
}

