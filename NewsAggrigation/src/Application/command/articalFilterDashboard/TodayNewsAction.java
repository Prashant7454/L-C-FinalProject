package Application.command.articalFilterDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.command.adminDashboard.ViewAllCategoriesAction;
import Application.news.News;
import Application.news.service.NewsService;
import Application.newsCategory.service.NewsCategoryService;
import Application.util.NewsPrinterUtil;

import java.util.List;
import java.util.Scanner;

public class TodayNewsAction implements MenuAction {

    private final ViewAllCategoriesAction viewAllCategoriesAction = new ViewAllCategoriesAction();
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
            viewAllCategoriesAction.execute(response);

            System.out.print("Enter category ID: ");
            int categoryId = Integer.parseInt(scanner.nextLine().trim());

            List<Integer> newsIds = newsCategoryService.getNewsIdByCategoryId(categoryId);
            List<News> todayNews = newsService.getTodayNewsById(newsIds);

            NewsPrinterUtil.printNewsList(todayNews);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
