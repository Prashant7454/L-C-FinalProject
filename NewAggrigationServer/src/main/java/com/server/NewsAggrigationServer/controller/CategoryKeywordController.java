package com.server.NewsAggrigationServer.controller;
import com.server.NewsAggrigationServer.dto.CategoryKeywordDTO;
import com.server.NewsAggrigationServer.service.CategoryKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category-keyword")
public class CategoryKeywordController {

    @Autowired
    private CategoryKeywordService categoryKeywordService;

    @PostMapping
    public CategoryKeywordDTO create(@RequestBody CategoryKeywordDTO dto) {
        return categoryKeywordService.createCategoryKeyword(dto);
    }

    @GetMapping
    public List<CategoryKeywordDTO> getAll() {
        return categoryKeywordService.getAllCategoryKeywords();
    }

    @GetMapping("/category/{categoryId}")
    public List<CategoryKeywordDTO> getByCategoryId(@PathVariable Integer categoryId) {
        return categoryKeywordService.getCategoryKeywordsByCategoryId(categoryId);
    }

    @GetMapping("/keyword/{keywordId}")
    public List<CategoryKeywordDTO> getByKeywordId(@PathVariable Integer keywordId) {
        return categoryKeywordService.getCategoryKeywordsByKeywordId(keywordId);
    }
}
