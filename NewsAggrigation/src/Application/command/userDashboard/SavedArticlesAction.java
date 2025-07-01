package Application.command.userDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.news.News;
import Application.news.service.NewsService;
import Application.util.NewsPrinterUtil;

import java.util.List;

public class SavedArticlesAction implements MenuAction {

    private final NewsService newsService = new NewsService();

    @Override
    public String getName() {
        return "SavedArticle";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("Fetching your saved articles...");
        List<News> newsList = fetchSavedArticles(response.getUserId());
        NewsPrinterUtil.printNewsList(newsList);
    }

    private List<News> fetchSavedArticles(int userId) {
        try {
            return newsService.savedNews(userId);
        } catch (Exception e) {
            System.err.println("Failed to fetch saved articles: " + e.getMessage());
            return List.of(); // Return empty list on failure
        }
    }
}
