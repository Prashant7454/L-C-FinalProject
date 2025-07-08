package Application.category.service;

import Application.category.Category;
import Application.category.controller.CategoryController;

import java.util.List;
import java.util.Scanner;

public class CategoryService {
    private CategoryController categoryController;

    public CategoryService(){
        categoryController = new CategoryController();
    }

    public Category addCategory() throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new Category: ");
        String categoryName = scanner.next();
        Category newCategory = new Category();
        newCategory.setName(categoryName);
        return categoryController.addCategory(newCategory);
    }

    public List<Category> getAllCategories() throws Exception{
        return categoryController.getAllCategories();
    }

    public Category getCategoryById(int id) throws Exception{
        return categoryController.getCategoryById(id);
    }

    public List<Category> getCategoryByNewsId(int NewsId) throws Exception{
        return categoryController.getCategoryByNewsId(NewsId);
    }

    // New methods for visible categories only
    public List<Category> getAllVisibleCategories() throws Exception{
        return categoryController.getAllVisibleCategories();
    }

    public List<Category> getVisibleCategoriesByIds(List<Integer> ids) throws Exception{
        return categoryController.getVisibleCategoriesByIds(ids);
    }

    // Admin methods for hiding/unhiding categories
    public Category hideCategory(Integer id) throws Exception{
        return categoryController.hideCategory(id);
    }

    public Category unhideCategory(Integer id) throws Exception{
        return categoryController.unhideCategory(id);
    }
}
