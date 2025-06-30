package Application.newsCategory.service;

import Application.newsCategory.controller.NewsCategoryController;

import java.util.List;

public class NewsCategoryService {
    NewsCategoryController newsCategoryController;

    public NewsCategoryService(){
        newsCategoryController = new NewsCategoryController();
    }
    public List<Integer> getNewsIdByCategoryId(Integer categoryId)throws Exception{
        return newsCategoryController.getNewsIdByCategoryId(categoryId);
    }
}
