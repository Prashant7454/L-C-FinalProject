package Application.util;

import Application.category.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryUtil {
    public static Map<Integer,Category> processCategory(List<Category> categories) {
        Category allCategory = new Category();
        allCategory.setId(0);
        allCategory.setName("All");
        Map<Integer,Category> categoryMap = new HashMap<>();
        categoryMap.put(1,allCategory);
        if (categories == null || categories.isEmpty()) {
            return categoryMap;
        }
        int count = 2;
        for (Category category : categories) {
            // Only add visible categories
            if (category.getIsHide() == null || category.getIsHide() == 0) {
                categoryMap.put(count,category);
                count++;
            }
        }

        return categoryMap;
    }

    public static void printCategory(Map<Integer,Category> categoryMap){
        for(int i = 1; i <= categoryMap.size(); i++){
            System.out.println( i + ". " + categoryMap.get(i).getName());
        }
    }

    public static boolean isCategoryHidden(Category category) {
        return category != null && category.getIsHide() != null && category.getIsHide() == 1;
    }

    // Admin-specific methods for viewing all categories including hidden ones
    public static Map<Integer, Category> processCategoryForAdmin(List<Category> categories) {
        Map<Integer, Category> categoryMap = new HashMap<>();
        if (categories == null || categories.isEmpty()) {
            return categoryMap;
        }
        int count = 1;
        for (Category category : categories) {
            categoryMap.put(count, category);
            count++;
        }
        return categoryMap;
    }

    public static void printCategoryForAdmin(Map<Integer, Category> categoryMap) {
        for (int i = 1; i <= categoryMap.size(); i++) {
            Category category = categoryMap.get(i);
            String status = (category.getIsHide() != null && category.getIsHide() == 1) ? " [HIDDEN]" : " [VISIBLE]";
            System.out.println(i + ". " + category.getName() + status);
        }
    }
}
