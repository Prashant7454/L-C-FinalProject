package Application.command.userDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.menu.ArticleMenu;
import Application.menu.UserDashboardMenu;
import Application.category.Category;
import Application.category.service.CategoryService;
import Application.news.News;
import Application.news.service.NewsService;
import Application.util.NewsUtil;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SearchAction implements MenuAction {

    private final Scanner scanner = new Scanner(System.in);
    private final NewsService newsService = new NewsService();
    private final CategoryService categoryService = new CategoryService();

    @Override
    public String getName() {
        return "Search";
    }

    @Override
    public void execute(LoginResponse response) {
        String keyword = promptKeyword();
        List<News> newsList = fetchSearchResults(keyword);
        Map<Integer,News> newsMap = NewsUtil.processNewsList(newsList);
        NewsUtil.printNewsList(newsMap);
        new ArticleMenu(new UserDashboardMenu(response),newsMap).showMenu(response);
    }

    private String promptKeyword() {
        System.out.println("Search Articles...");
        System.out.print("Enter keyword: ");
        return scanner.nextLine().trim();
    }

    private List<News> fetchSearchResults(String keyword) {
        try {
            List<News> newsList = newsService.searchVisibleNews(keyword);
            // Filter out news from hidden categories
            return newsList.stream()
                    .filter(news -> {
                        try {
                            List<Category> categories = categoryService.getCategoryByNewsId(news.getId());
                            return categories.stream().allMatch(category -> 
                                category.getIsHide() == null || category.getIsHide() == 0);
                        } catch (Exception e) {
                            return false;
                        }
                    })
                    .collect(java.util.stream.Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error while searching: " + e.getMessage());
            return List.of(); // empty list fallback
        }
    }
}
