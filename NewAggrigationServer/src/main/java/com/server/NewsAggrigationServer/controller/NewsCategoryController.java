package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.dto.NewsCategoryDTO;
import com.server.NewsAggrigationServer.service.NewsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news-category")
public class NewsCategoryController {

    @Autowired
    private NewsCategoryService newsCategoryService;

    @PostMapping
    public NewsCategoryDTO assignCategoryToNews(@RequestBody NewsCategoryDTO dto) {
        return newsCategoryService.assignCategory(dto);
    }

    @DeleteMapping
    public String removeCategory(@RequestParam Integer newsId, @RequestParam Integer categoryId) {
        newsCategoryService.removeCategoryFromNews(newsId, categoryId);
        return "Category removed from news successfully";
    }

    @DeleteMapping("/delete-all")
    public String removeAllCategoriesFromNews(@RequestParam Integer newsId) {
        newsCategoryService.removeCategoriesByNewsId(newsId);
        return "All categories removed from news successfully";
    }

    @GetMapping
    public List<NewsCategoryDTO> getAllNewsCategory(){
        return newsCategoryService.getAllNewsCategory();
    }

    @GetMapping("/categoryId/{categoryId}")
    public List<Integer> getNewsIdByCategoryId(@PathVariable Integer categoryId){
        return newsCategoryService.getNewsIdByCategoryId(categoryId);
    }
}

