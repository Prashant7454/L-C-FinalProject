package Application.command.adminDashboard;

import Application.auth.login.LoginResponse;
import Application.category.Category;
import Application.category.service.CategoryService;
import Application.command.MenuAction;
import Application.util.CategoryUtil;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UnhideCategoryAction implements MenuAction {

    private final CategoryService categoryService = new CategoryService();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getName() {
        return "Unhide Category";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("== Unhide Category ==");

        try {
            // Get all categories (including hidden ones for admin view)
            List<Category> categories = categoryService.getAllCategories();
            Map<Integer, Category> categoryMap = CategoryUtil.processCategoryForAdmin(categories);
            
            if (categoryMap.size() <= 1) {
                System.out.println("No categories available.");
                return;
            }

            CategoryUtil.printCategoryForAdmin(categoryMap);
            System.out.print("Select category to unhide: ");
            int selectedCategoryNumber = Integer.parseInt(scanner.nextLine().trim());

            if (selectedCategoryNumber < 1 || selectedCategoryNumber > categoryMap.size()) {
                System.out.println("Invalid selection.");
                return;
            }

            Category selectedCategory = categoryMap.get(selectedCategoryNumber);
            
            // Check if category is already visible
            if (selectedCategory.getIsHide() == null || selectedCategory.getIsHide() == 0) {
                System.out.println("Category '" + selectedCategory.getName() + "' is already visible.");
                return;
            }

            // Confirm action
            System.out.print("Are you sure you want to unhide category '" + selectedCategory.getName() + "'? (y/n): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            
            if (!confirmation.equals("y") && !confirmation.equals("yes")) {
                System.out.println("Operation cancelled.");
                return;
            }

            // Unhide the category
            Category updatedCategory = categoryService.unhideCategory(selectedCategory.getId());
            System.out.println("Category '" + updatedCategory.getName() + "' has been unhidden successfully.");
            System.out.println("All news from this category will now be visible to users.");

        } catch (Exception e) {
            System.err.println("Error unhiding category: " + e.getMessage());
        }
    }
} 