package Application.command.adminDashboard;

import Application.auth.login.LoginResponse;
import Application.category.service.CategoryService;
import Application.command.MenuAction;

public class AddNewsCategoryAction implements MenuAction {

    private final CategoryService categoryService;
    private final ViewAllCategoriesAction viewAllCategoriesAction;

    public AddNewsCategoryAction() {
        this.categoryService = new CategoryService();
        this.viewAllCategoriesAction = new ViewAllCategoriesAction();
    }

    @Override
    public String getName() {
        return "Add New News Category";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("== Add New Category ==");
        try {
            viewAllCategoriesAction.execute(response);
            categoryService.addCategory();
        } catch (Exception e) {
            System.err.println("Failed to add new category: " + e.getMessage());
        }
    }
}
