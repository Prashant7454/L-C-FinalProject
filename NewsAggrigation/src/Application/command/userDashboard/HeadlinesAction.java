package Application.command.userDashboard;

import Application.command.MenuAction;
import Application.news.News;
import Application.news.service.NewsService;

import java.util.ArrayList;
import java.util.List;

public class HeadlinesAction implements MenuAction {
    @Override
    public String getName() {
        return "Headlines";
    }

    @Override
    public void execute(int userId) {
        System.out.println("Fetching top headlines...");
        NewsService newsService = new NewsService();
        List<News> newsList = new ArrayList<>();
        try{
            newsList = newsService.getAllNews();
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
