package com.server.NewAggrigationServer.service;

import com.server.NewAggrigationServer.dto.CategoryKeywordDTO;

import java.util.List;

public interface CategoryKeywordService {
    CategoryKeywordDTO createCategoryKeyword(CategoryKeywordDTO dto);
    List<CategoryKeywordDTO> getAllCategoryKeywords();
}
