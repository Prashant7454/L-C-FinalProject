package Application.util;

import Application.category.Category;

import java.util.List;

public class CategoryUtil {
    public static void printAllCategories(List<Category> categories) {
        if (categories == null || categories.isEmpty()) {
            System.out.println("(No categories available)");
            return;
        }
        for (Category category : categories) {
            System.out.println(category.getId() + ". " + category.getName());
        }
    }
}
