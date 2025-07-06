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

public class ViewNotificationConfigAction implements MenuAction {
    private final NotificationConfigService notificationConfigService = new NotificationConfigService();
    private final CategoryService categoryService = new CategoryService();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getName() {
        return "View Notification Configuration";
    }

    @Override
    public void execute(LoginResponse response) {
        try {
            Integer userId = response.getUserId();
            
            // Get all visible categories
            List<Category> categories = categoryService.getAllVisibleCategories();
            Map<Integer, Category> categoryMap = CategoryUtil.processCategory(categories);
            
            // Get user's notification configurations
            List<NotificationConfig> userConfigs = notificationConfigService.getNotificationConfigurationsByUserId(userId);
            Map<Integer, NotificationConfig> configMap = new HashMap<>();
            
            for (NotificationConfig config : userConfigs) {
                configMap.put(config.getCategoryId(), config);
            }
            
            System.out.println("\n=== Notification Configuration ===");
            System.out.println("Category\t\t\tStatus");
            System.out.println("--------\t\t\t------");
            
            for (int i = 1; i <= categoryMap.size(); i++) {
                Category category = categoryMap.get(i);
                NotificationConfig config = configMap.get(category.getId());
                
                String status = (config != null && config.getEnabled() != null && config.getEnabled()) 
                    ? "ENABLED" : "DISABLED";
                
                System.out.printf("%d. %-20s\t%s%n", i, category.getName(), status);
            }
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
            
        } catch (Exception e) {
            System.out.println("Error viewing notification configuration: " + e.getMessage());
        }
    }
} 