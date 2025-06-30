package Application.command.userDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.news.News;
import Application.news.service.NewsService;

import java.util.ArrayList;
import java.util.List;

public class SavedArticlesAction implements MenuAction {
    @Override
    public String getName() {
        return "SavedArticle";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("Fetching top saved Articles...");
        NewsService newsService = new NewsService();
        List<News> newsList = new ArrayList<>();
        try{
            newsList = newsService.savedNews(response.getUserId());
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            System.out.println(e);
        }
        showAllNews(newsList);
    }

    private void showAllNews(List<News> NewsList){
        System.out.print("  ID  |  ");
        System.out.println("Title");
        for(News news: NewsList){
            System.out.print(news.getId()+ "  |  ");
            System.out.println(news.getTitle());
        }
    }
}
