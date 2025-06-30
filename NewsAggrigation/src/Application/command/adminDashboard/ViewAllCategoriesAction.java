package Application.command.adminDashboard;

import Application.auth.login.LoginResponse;
import Application.category.Category;
import Application.category.service.CategoryService;
import Application.command.MenuAction;

import java.util.ArrayList;
import java.util.List;

public class ViewAllCategoriesAction implements MenuAction {
    @Override
    public String getName() {
        return "View All Categories";
    }

    @Override
    public void execute(LoginResponse response){
        System.out.println("Categories");
        CategoryService categoryService = new CategoryService();
        List<Category> categories = new ArrayList<>();
        try{
            categories = categoryService.getAllCategories();
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        printAllCategories(categories);
    }

    private void printAllCategories(List<Category> categories){
        for(Category category: categories){
            System.out.print(category.getId()+".");
            System.out.println(category.getName());
        }
    }
}
