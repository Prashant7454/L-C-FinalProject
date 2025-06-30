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
}
