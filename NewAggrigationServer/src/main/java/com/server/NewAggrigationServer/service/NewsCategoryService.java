package com.server.NewAggrigationServer.service;

import com.server.NewAggrigationServer.dto.CategoryDTO;
import com.server.NewAggrigationServer.dto.NewsCategoryDTO;

import java.util.List;

public interface NewsCategoryService {
    NewsCategoryDTO assignCategory(NewsCategoryDTO dto);
    List<CategoryDTO> getCategoriesByNewsId(Integer newsId);
    void removeCategoryFromNews(Integer newsId, Integer categoryId);
    void removeCategoriesByNewsId(Integer newsId);
}
