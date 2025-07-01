package Application.command.adminDashboard;

import Application.auth.login.LoginResponse;
import Application.category.Category;
import Application.category.service.CategoryService;
import Application.command.MenuAction;
import Application.util.CategoryUtil;

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
            CategoryUtil.printAllCategories(categories);
        } catch (Exception e) {
            System.err.println("Error fetching categories: " + e.getMessage());
        }
    }

}
