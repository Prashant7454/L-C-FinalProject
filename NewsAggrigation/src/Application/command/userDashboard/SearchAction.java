package Application.command.userDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.news.News;
import Application.news.service.NewsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchAction implements MenuAction {
    @Override
    public String getName() {
        return "Search";
    }

    @Override
    public void execute(LoginResponse response) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Search Articles...");
        System.out.print("Enter Keyword: ");
        String keyword = scanner.next();
        NewsService newsService = new NewsService();
        List<News> newsList = new ArrayList<>();
        try{
            newsList = newsService.searchNews(keyword);
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
