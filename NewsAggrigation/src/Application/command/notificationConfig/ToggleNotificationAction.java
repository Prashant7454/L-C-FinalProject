package Application.command.notificationConfig;

import Application.auth.login.LoginResponse;
import Application.category.Category;
import Application.category.service.CategoryService;
import Application.command.MenuAction;
import Application.notificationConfig.NotificationConfig;
import Application.notificationConfig.service.NotificationConfigService;
import Application.util.CategoryUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ToggleNotificationAction implements MenuAction {
    private final NotificationConfigService notificationConfigService = new NotificationConfigService();
    private final CategoryService categoryService = new CategoryService();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getName() {
        return "Toggle Notification Settings";
    }

    @Override
    public void execute(LoginResponse response) {
        try {
            Integer userId = response.getUserId();
            
            // Get all visible categories
            List<Category> categories = categoryService.getAllVisibleCategories();
            Map<Integer, Category> categoryMap = CategoryUtil.processCategory(categories);
            
            // Display categories
            System.out.println("\n=== Select Category to Toggle ===");
            CategoryUtil.printCategory(categoryMap);
            
            System.out.print("Enter category number: ");
            int selectedCategoryNumber = Integer.parseInt(scanner.nextLine().trim());
            
            if (selectedCategoryNumber < 1 || selectedCategoryNumber > categoryMap.size()) {
                System.out.println("Invalid category number!");
                return;
            }
            
            Category selectedCategory = categoryMap.get(selectedCategoryNumber);
            
            // Get current configuration
            NotificationConfig currentConfig = notificationConfigService
                .getNotificationConfigurationByUserIdAndCategoryId(userId, selectedCategory.getId());
            
            boolean newStatus;
            if (currentConfig == null || currentConfig.getEnabled() == null || !currentConfig.getEnabled()) {
                newStatus = true;
                System.out.println("Enabling notifications for category: " + selectedCategory.getName());
            } else {
                newStatus = false;
                System.out.println("Disabling notifications for category: " + selectedCategory.getName());
            }
            
            // Create or update configuration
            NotificationConfig config = new NotificationConfig();
            config.setUserId(userId);
            config.setCategoryId(selectedCategory.getId());
            config.setEnabled(newStatus);
            
            if (currentConfig != null) {
                config.setId(currentConfig.getId());
            }
            
            notificationConfigService.updateNotificationConfiguration(config);
            
            System.out.println("Notification configuration updated successfully!");
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
            
        } catch (Exception e) {
            System.out.println("Error toggling notification configuration: " + e.getMessage());
        }
    }
} 