package Application.command.articalDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.command.adminDashboard.ViewAllCategoriesAction;
import Application.news.News;
import Application.news.service.NewsService;
import Application.newsCategory.service.NewsCategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TodayNewsAction implements MenuAction {
    @Override
    public String getName() {
        return "Today";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("Please choose the options below for Headlines");
        ViewAllCategoriesAction viewAllCategoriesAction = new ViewAllCategoriesAction();
        try {
            viewAllCategoriesAction.execute(response);
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        Scanner scanner = new Scanner(System.in);
        Integer categoryId = scanner.nextInt();
        List<Integer> newsIds = new ArrayList<>();
        NewsCategoryService newsCategoryService = new NewsCategoryService();
        try{
            newsIds = newsCategoryService.getNewsIdByCategoryId(categoryId);
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        NewsService newsService = new NewsService();
        List<News> newsList = new ArrayList<>();
        try{
            newsList = newsService.getTodayNewsById(newsIds);
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        printNews(newsList);
    }

    private void printNews(List<News> newsList){
        for(News news: newsList){
            System.out.print(news.getId()+".");
            System.out.print(" - ");
            System.out.println(news.getTitle());
        }
    }
}
