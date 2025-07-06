package Application.command.articleFilterDashboard;

import Application.auth.login.LoginResponse;
import Application.category.Category;
import Application.category.service.CategoryService;
import Application.command.MenuAction;
import Application.menu.ArticleFilterMenu;
import Application.menu.ArticleMenu;
import Application.news.News;
import Application.news.service.NewsService;
import Application.newsCategory.service.NewsCategoryService;
import Application.util.CategoryUtil;
import Application.util.NewsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TodayNewsAction implements MenuAction {

    private final CategoryService categoryService = new CategoryService();
    private final NewsCategoryService newsCategoryService = new NewsCategoryService();
    private final NewsService newsService = new NewsService();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getName() {
        return "Today's News";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("== Today's Headlines by Category ==");

        try {
            List<Category> categories = categoryService.getAllVisibleCategories();
            Map<Integer,Category> categoryMap = CategoryUtil.processCategory(categories);
            CategoryUtil.printCategory(categoryMap);
            System.out.print("Select Category: ");
            int selectedCategoryNumber = Integer.parseInt(scanner.nextLine().trim());

            List<News> todayNews = new ArrayList<>();
            if(selectedCategoryNumber == 1){
                todayNews = newsService.getNewsInVisibleCategories();
            }
            else{
                List<Integer> newsIds = newsCategoryService.getNewsIdByCategoryId(categoryMap.get(selectedCategoryNumber).getId());
                todayNews = newsService.getNewsByIdsInVisibleCategories(newsIds);
            }

            Map<Integer,News> newsMap = NewsUtil.processNewsList(todayNews);
            NewsUtil.printNewsList(newsMap);
            new ArticleMenu(new ArticleFilterMenu(),newsMap).showMenu(response);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
