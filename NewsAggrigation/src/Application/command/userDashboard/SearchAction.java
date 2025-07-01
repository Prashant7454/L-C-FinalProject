package Application.command.userDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.news.News;
import Application.news.service.NewsService;
import Application.util.NewsUtil;

import java.util.List;
import java.util.Scanner;

public class SearchAction implements MenuAction {

    private final Scanner scanner = new Scanner(System.in);
    private final NewsService newsService = new NewsService();

    @Override
    public String getName() {
        return "Search";
    }

    @Override
    public void execute(LoginResponse response) {
        String keyword = promptKeyword();
        List<News> newsList = fetchSearchResults(keyword);
        NewsUtil.printNewsList(newsList);
    }

    private String promptKeyword() {
        System.out.println("Search Articles...");
        System.out.print("Enter keyword: ");
        return scanner.nextLine().trim();
    }

    private List<News> fetchSearchResults(String keyword) {
        try {
            return newsService.searchNews(keyword);
        } catch (Exception e) {
            System.err.println("Error while searching: " + e.getMessage());
            return List.of(); // empty list fallback
        }
    }
}
