package Application.command.userDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.menu.ArticleMenu;
import Application.menu.UserDashboardMenu;
import Application.news.News;
import Application.news.service.NewsService;
import Application.util.NewsUtil;

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
        NewsUtil.printNewsList(newsList);
        new ArticleMenu(new UserDashboardMenu(response)).showMenu(response);
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
