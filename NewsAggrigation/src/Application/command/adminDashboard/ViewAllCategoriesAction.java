package Application.command.adminDashboard;

import Application.auth.login.LoginResponse;
import Application.category.Category;
import Application.category.service.CategoryService;
import Application.command.MenuAction;

import java.util.List;

public class ViewAllCategoriesAction implements MenuAction {

    private final CategoryService categoryService;

    public ViewAllCategoriesAction() {
        this.categoryService = new CategoryService();
    }

    @Override
    public String getName() {
        return "View All Categories";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("== Categories ==");
        try {
            List<Category> categories = categoryService.getAllCategories();
            printAllCategories(categories);
        } catch (Exception e) {
            System.err.println("Error fetching categories: " + e.getMessage());
        }
    }

    private void printAllCategories(List<Category> categories) {
        if (categories == null || categories.isEmpty()) {
            System.out.println("(No categories available)");
            return;
        }
        for (Category category : categories) {
            System.out.println(category.getId() + ". " + category.getName());
        }
    }
}
