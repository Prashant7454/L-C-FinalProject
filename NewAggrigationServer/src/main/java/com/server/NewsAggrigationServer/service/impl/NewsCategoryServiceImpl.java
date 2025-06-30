package com.server.NewsAggrigationServer.service.impl;

import com.server.NewsAggrigationServer.dto.CategoryDTO;
import com.server.NewsAggrigationServer.dto.NewsCategoryDTO;
import com.server.NewsAggrigationServer.model.NewsCategory;
import com.server.NewsAggrigationServer.repository.NewsCategoryRepository;
import com.server.NewsAggrigationServer.service.CategoryService;
import com.server.NewsAggrigationServer.service.NewsCategoryService;
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

    @Override
    public List<NewsCategoryDTO> getAllNewsCategory() {
        List<NewsCategory> newsCategories = newsCategoryRepository.findAll();
        List<NewsCategoryDTO> newsCategoryDTOList = new ArrayList<>();
        for(NewsCategory newsCategory: newsCategories){
            NewsCategoryDTO dto = new NewsCategoryDTO();
            dto.setCategoryId(newsCategory.getCategoryId());
            dto.setId(newsCategory.getId());
            dto.setNewsId(newsCategory.getNewsId());
            newsCategoryDTOList.add(dto);
        }
        return newsCategoryDTOList;
    }

    @Override
    public List<Integer> getNewsIdByCategoryId(Integer categoryId) {
        List<Integer> newsId = new ArrayList<>();
        List<NewsCategory> newsCategories = newsCategoryRepository.findByCategoryId(categoryId);
        for(NewsCategory newsCategory: newsCategories){
            newsId.add(newsCategory.getNewsId());
        }
        return newsId;
    }
}

