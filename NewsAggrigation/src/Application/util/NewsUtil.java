package Application.util;

import Application.category.Category;
import Application.category.service.CategoryService;
import Application.menu.ArticleMenu;
import Application.news.News;
import Application.news.service.NewsService;

import java.util.List;
import java.util.Scanner;

public class NewsUtil {
    static NewsService newsService = new NewsService();
    static CategoryService categoryService = new CategoryService();
    static Scanner scanner = new Scanner(System.in);

    public static void printNewsList(List<News> newsList) {
        if (newsList == null || newsList.isEmpty()) {
            System.out.println("(No news articles found.)");
            return;
        }

        System.out.println("== News Articles ==");
        for (News news : newsList) {
            System.out.printf("%d - %s%n", news.getId(), news.getTitle());
        }
    }

    public static void showArticleDetails(Integer newsId) throws Exception{
        News news =  newsService.getNewsById(newsId);
        List<Category> categories = categoryService.getCategoryByNewsId(news.getId());

        System.out.println("Article Id: " + news.getId());
        System.out.println();
        System.out.println(news.getTitle());
        System.out.println();
        System.out.println(news.getDescription());
        System.out.println();
        System.out.println("source: " + news.getSource());
        System.out.println();
        System.out.println("URL:");
        System.out.println(news.getUrl());
        System.out.println();
        System.out.print("Category: ");

        for(int i = 0;  i< categories.size(); i++){
            System.out.print(categories.get(i).getName());
            if(i<categories.size()-1){
                System.out.println(", ");
            }
        }

        System.out.println();
        System.out.println("Like: " + news.getLikeCount());
        System.out.println("DisLikeCount: " + news.getDisLikeCount());
        System.out.println();

    }
}
