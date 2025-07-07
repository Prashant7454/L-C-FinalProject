package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.CategoryKeywordDTO;

import java.util.List;

public interface CategoryKeywordService {
    CategoryKeywordDTO createCategoryKeyword(CategoryKeywordDTO dto);

    List<CategoryKeywordDTO> getAllCategoryKeywords();

    List<CategoryKeywordDTO> getCategoryKeywordsByCategoryId(Integer categoryId);

    List<CategoryKeywordDTO> getCategoryKeywordsByKeywordId(Integer keywordId);
}
