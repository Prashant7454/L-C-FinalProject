package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.dto.CategoryDTO;
import com.server.NewsAggrigationServer.service.CategoryService;
import com.server.NewsAggrigationServer.service.NewsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private NewsCategoryService newsCategoryService;

    @PostMapping
    public CategoryDTO createCategory(@RequestBody CategoryDTO dto) {
        return categoryService.createCategory(dto);
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{newsId}")
    public List<CategoryDTO> getCategoriesForNews(@PathVariable Integer newsId) {
        return newsCategoryService.getCategoriesByNewsId(newsId);
    }

    @PostMapping("/by-ids")
    public List<CategoryDTO> getCategoriesByIds(@RequestBody List<Integer> ids) {
        return categoryService.getAllCategoriesByIds(ids);
    }

    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable int id) {
        return categoryService.getCategoryById(id);
    }
}

