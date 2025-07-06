package Application.command.userDashboard.notificationConfig;

import Application.auth.login.LoginResponse;
import Application.category.Category;
import Application.category.service.CategoryService;
import Application.categoryKeyword.CategoryKeyword;
import Application.categoryKeyword.service.CategoryKeywordService;
import Application.command.MenuAction;
import Application.keyword.Keyword;
import Application.keyword.service.KeywordService;
import Application.util.CategoryUtil;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AddKeywordToCategoryAction implements MenuAction {
    private final CategoryService categoryService = new CategoryService();
    private final KeywordService keywordService = new KeywordService();
    private final CategoryKeywordService categoryKeywordService = new CategoryKeywordService();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getName() {
        return "Add Keyword to Category";
    }

    @Override
    public void execute(LoginResponse response) {
        try {
            // Get all visible categories
            List<Category> categories = categoryService.getAllVisibleCategories();
            Map<Integer, Category> categoryMap = CategoryUtil.processCategory(categories);
            
            // Display categories
            System.out.println("\n=== Select Category to Add Keyword ===");
            CategoryUtil.printCategory(categoryMap);
            
            System.out.print("Enter category number: ");
            int selectedCategoryNumber = Integer.parseInt(scanner.nextLine().trim());
            
            if (selectedCategoryNumber < 1 || selectedCategoryNumber > categoryMap.size()) {
                System.out.println("Invalid category number!");
                return;
            }
            
            Category selectedCategory = categoryMap.get(selectedCategoryNumber);
            
            // Get keyword from user
            System.out.print("Enter keyword to add: ");
            String keywordName = scanner.nextLine().trim();
            
            if (keywordName.isEmpty()) {
                System.out.println("Keyword cannot be empty!");
                return;
            }
            
            // Create keyword
            Keyword keyword = new Keyword();
            keyword.setName(keywordName);
            keyword = keywordService.addKeyword(keyword);
            
            // Create category-keyword relationship
            CategoryKeyword categoryKeyword = new CategoryKeyword();
            categoryKeyword.setCategoryId(selectedCategory.getId());
            categoryKeyword.setKeywordId(keyword.getId());
            categoryKeywordService.createCategoryKeyword(categoryKeyword);
            
            System.out.println("Keyword '" + keywordName + "' added to category '" + selectedCategory.getName() + "' successfully!");
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
            
        } catch (Exception e) {
            System.out.println("Error adding keyword to category: " + e.getMessage());
        }
    }
} 