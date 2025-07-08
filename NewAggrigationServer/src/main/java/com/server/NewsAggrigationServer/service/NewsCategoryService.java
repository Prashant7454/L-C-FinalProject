package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.CategoryDTO;
import com.server.NewsAggrigationServer.dto.NewsCategoryDTO;

import java.util.List;

public interface NewsCategoryService {
    NewsCategoryDTO assignCategory(NewsCategoryDTO dto);

    List<CategoryDTO> getCategoriesByNewsId(Integer newsId);

    void removeCategoryFromNews(Integer newsId, Integer categoryId);

    void removeCategoriesByNewsId(Integer newsId);

    List<NewsCategoryDTO> getAllNewsCategory();

    List<Integer> getNewsIdByCategoryId(Integer categoryId);
}
