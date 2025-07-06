package Application.categoryKeyword.service;

import Application.categoryKeyword.CategoryKeyword;
import Application.categoryKeyword.controller.CategoryKeywordController;

import java.util.List;

public class CategoryKeywordService {
    private final CategoryKeywordController categoryKeywordController;

    public CategoryKeywordService() {
        categoryKeywordController = new CategoryKeywordController();
    }

    public CategoryKeyword createCategoryKeyword(CategoryKeyword categoryKeyword) throws Exception {
        return categoryKeywordController.createCategoryKeyword(categoryKeyword);
    }

    public List<CategoryKeyword> getAllCategoryKeywords() throws Exception {
        return categoryKeywordController.getAllCategoryKeywords();
    }

    public List<CategoryKeyword> getCategoryKeywordsByCategoryId(Integer categoryId) throws Exception {
        return categoryKeywordController.getCategoryKeywordsByCategoryId(categoryId);
    }

    public List<CategoryKeyword> getCategoryKeywordsByKeywordId(Integer keywordId) throws Exception {
        return categoryKeywordController.getCategoryKeywordsByKeywordId(keywordId);
    }
} 