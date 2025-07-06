package Application.command.adminDashboard;

import Application.auth.login.LoginResponse;
import Application.category.Category;
import Application.category.service.CategoryService;
import Application.command.MenuAction;
import Application.util.CategoryUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ManageCategoryVisibilityAction implements MenuAction {

    private final CategoryService categoryService = new CategoryService();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getName() {
        return "Manage Category Visibility";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("== Manage Category Visibility ==");

        try {
            while (true) {
                // Get all categories (including hidden ones for admin view)
                List<Category> categories = categoryService.getAllCategories();
                Map<Integer, Category> categoryMap = CategoryUtil.processCategoryForAdmin(categories);
                
                if (categoryMap.size() == 0) {
                    System.out.println("No categories available.");
                    return;
                }

                System.out.println("\nCurrent Categories:");
                CategoryUtil.printCategoryForAdmin(categoryMap);
                
                System.out.println("\nOptions:");
                System.out.println("1. Hide a category");
                System.out.println("2. Unhide a category");
                System.out.println("3. Back to admin dashboard");
                
                System.out.print("Select option: ");
                String option = scanner.nextLine().trim();

                switch (option) {
                    case "1":
                        hideCategory(categoryMap);
                        break;
                    case "2":
                        unhideCategory(categoryMap);
                        break;
                    case "3":
                        System.out.println("Returning to admin dashboard...");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }

        } catch (Exception e) {
            System.err.println("Error managing category visibility: " + e.getMessage());
        }
    }

    private void hideCategory(Map<Integer, Category> categoryMap) throws Exception {
        // Show only visible categories for hiding
        Map<Integer, Category> visibleCategories = new HashMap<>();
        int count = 1;
        for (Category category : categoryMap.values()) {
            if (category.getIsHide() == null || category.getIsHide() == 0) {
                visibleCategories.put(count, category);
                count++;
            }
        }

        if (visibleCategories.isEmpty()) {
            System.out.println("No visible categories to hide.");
            return;
        }

        System.out.println("\nVisible Categories (can be hidden):");
        for (Map.Entry<Integer, Category> entry : visibleCategories.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getName());
        }

        System.out.print("Select category to hide: ");
        int selectedNumber = Integer.parseInt(scanner.nextLine().trim());

        if (!visibleCategories.containsKey(selectedNumber)) {
            System.out.println("Invalid selection.");
            return;
        }

        Category selectedCategory = visibleCategories.get(selectedNumber);
        
        // Confirm action
        System.out.print("Are you sure you want to hide category '" + selectedCategory.getName() + "'? (y/n): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        
        if (!confirmation.equals("y") && !confirmation.equals("yes")) {
            System.out.println("Operation cancelled.");
            return;
        }

        // Hide the category
        Category updatedCategory = categoryService.hideCategory(selectedCategory.getId());
        System.out.println("Category '" + updatedCategory.getName() + "' has been hidden successfully.");
        System.out.println("All news from this category will no longer be visible to users.");
    }

    private void unhideCategory(Map<Integer, Category> categoryMap) throws Exception {
        // Show only hidden categories for unhiding
        Map<Integer, Category> hiddenCategories = new HashMap<>();
        int count = 1;
        for (Category category : categoryMap.values()) {
            if (category.getIsHide() != null && category.getIsHide() == 1) {
                hiddenCategories.put(count, category);
                count++;
            }
        }

        if (hiddenCategories.isEmpty()) {
            System.out.println("No hidden categories to unhide.");
            return;
        }

        System.out.println("\nHidden Categories (can be unhidden):");
        for (Map.Entry<Integer, Category> entry : hiddenCategories.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getName());
        }

        System.out.print("Select category to unhide: ");
        int selectedNumber = Integer.parseInt(scanner.nextLine().trim());

        if (!hiddenCategories.containsKey(selectedNumber)) {
            System.out.println("Invalid selection.");
            return;
        }

        Category selectedCategory = hiddenCategories.get(selectedNumber);
        
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
    }
} 