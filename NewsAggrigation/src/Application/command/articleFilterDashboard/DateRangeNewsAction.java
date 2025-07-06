package Application.command.articleFilterDashboard;

import Application.auth.login.LoginResponse;
import Application.category.Category;
import Application.category.service.CategoryService;
import Application.command.MenuAction;
import Application.menu.ArticleFilterMenu;
import Application.menu.ArticleMenu;
import Application.news.DateRangeNewsRequest;
import Application.news.News;
import Application.news.service.NewsService;
import Application.newsCategory.service.NewsCategoryService;
import Application.util.CategoryUtil;
import Application.util.NewsUtil;

import java.util.*;

public class DateRangeNewsAction implements MenuAction {

    private final Scanner scanner = new Scanner(System.in);
    private final NewsCategoryService newsCategoryService = new NewsCategoryService();
    private final NewsService newsService = new NewsService();
    private final CategoryService categoryService = new CategoryService();

    @Override
    public String getName() {
        return "Date Range Filter";
    }

    @Override
    public void execute(LoginResponse response) {
        try {
            String startDate = getDateInput("Enter start date (yyyy-mm-dd): ", "T00:00:00");
            String endDate = getDateInput("Enter end date (yyyy-mm-dd): ", "T23:59:59");

            List<Category> categories = categoryService.getAllVisibleCategories();
            Map<Integer,Category> categoryMap = CategoryUtil.processCategory(categories);
            CategoryUtil.printCategory(categoryMap);

            System.out.print("Select Category: ");
            int selectedCategoryNumber = Integer.parseInt(scanner.nextLine().trim());
            List<News> allNews = new ArrayList<>();
            List<Integer> newsIds =  new ArrayList<>();
            if(selectedCategoryNumber == 1){
                allNews = newsService.getNewsInVisibleCategories();
                newsIds = NewsUtil.getAllNewsId(allNews);
            }
            else{
                newsIds = newsCategoryService.getNewsIdByCategoryId(categoryMap.get(selectedCategoryNumber).getId());
            }

            // Filter news by date range and visible categories
            DateRangeNewsRequest request = new DateRangeNewsRequest();
            request.setStart(startDate);
            request.setEnd(endDate);
            request.setIds(newsIds);

            List<News> newsList = newsService.getVisibleNewsByIdAndDateRange(request);
            Map<Integer,News> newsMap = NewsUtil.processNewsList(newsList);
            NewsUtil.printNewsList(newsMap);
            new ArticleMenu(new ArticleFilterMenu(),newsMap).showMenu(response);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private String getDateInput(String prompt, String timeSuffix) {
        System.out.print(prompt);
        String date = scanner.nextLine().trim();
        return date + timeSuffix;
    }
}
