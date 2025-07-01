package Application.command.articalFilterDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.command.adminDashboard.ViewAllCategoriesAction;
import Application.news.DateRangeNewsRequest;
import Application.news.News;
import Application.news.service.NewsService;
import Application.newsCategory.service.NewsCategoryService;
import Application.util.NewsPrinterUtil;

import java.util.List;
import java.util.Scanner;

public class DateRangeNewsAction implements MenuAction {

    private final Scanner scanner = new Scanner(System.in);
    private final NewsCategoryService newsCategoryService = new NewsCategoryService();
    private final NewsService newsService = new NewsService();
    private final ViewAllCategoriesAction viewAllCategoriesAction = new ViewAllCategoriesAction();

    @Override
    public String getName() {
        return "Date Range Filter";
    }

    @Override
    public void execute(LoginResponse response) {
        try {
            String startDate = getDateInput("Enter start date (yyyy-mm-dd): ", "T00:00:00");
            String endDate = getDateInput("Enter end date (yyyy-mm-dd): ", "T23:59:59");

            viewAllCategoriesAction.execute(response);

            System.out.print("Enter category ID: ");
            int categoryId = Integer.parseInt(scanner.nextLine().trim());

            List<Integer> newsIds = newsCategoryService.getNewsIdByCategoryId(categoryId);

            DateRangeNewsRequest request = new DateRangeNewsRequest();
            request.setStart(startDate);
            request.setEnd(endDate);
            request.setIds(newsIds);

            List<News> newsList = newsService.getNewsByIdAndDateRange(request);
            NewsPrinterUtil.printNewsList(newsList);

        } catch (Exception e) {
            System.err.println("Error: " + e.getStackTrace());
        }
    }

    private String getDateInput(String prompt, String timeSuffix) {
        System.out.print(prompt);
        String date = scanner.nextLine().trim();
        return date + timeSuffix;
    }
}
