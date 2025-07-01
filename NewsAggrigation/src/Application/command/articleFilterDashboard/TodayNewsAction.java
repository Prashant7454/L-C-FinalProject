package Application.command.articleFilterDashboard;

import Application.auth.login.LoginResponse;
import Application.category.Category;
import Application.category.service.CategoryService;
import Application.command.MenuAction;
import Application.menu.ArticleMenu;
import Application.news.News;
import Application.news.service.NewsService;
import Application.newsCategory.service.NewsCategoryService;
import Application.util.CategoryUtil;
import Application.util.NewsUtil;

import java.util.List;
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
            List<Category> categories = categoryService.getAllCategories();
            CategoryUtil.printAllCategories(categories);

            System.out.print("Enter category ID: ");
            int categoryId = Integer.parseInt(scanner.nextLine().trim());

            List<Integer> newsIds = newsCategoryService.getNewsIdByCategoryId(categoryId);
            List<News> todayNews = newsService.getTodayNewsById(newsIds);

            NewsUtil.printNewsList(todayNews);
            new ArticleMenu().showMenu(response);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
