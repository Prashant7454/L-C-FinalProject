package Application.util;

import Application.category.Category;
import Application.category.service.CategoryService;
import Application.menu.ArticleMenu;
import Application.news.News;
import Application.news.service.NewsService;

import java.util.*;

public class NewsUtil {
    static NewsService newsService = new NewsService();
    static CategoryService categoryService = new CategoryService();
    static Scanner scanner = new Scanner(System.in);

    public static void printNewsList(Map<Integer,News> newsMap) {
        if (newsMap.isEmpty()) {
            System.out.println("(No news articles found.)");
            return;
        }

        System.out.println("== News Articles ==");
        int visibleCount = 0;
        for(int i = 1; i <= newsMap.size(); i++){
            News news = newsMap.get(i);
            if (!isNewsHidden(news)) {
                visibleCount++;
                System.out.printf("%d - %s%n", visibleCount, news.getTitle());
            }
        }
        
        if (visibleCount == 0) {
            System.out.println("(No visible news articles found.)");
        }
    }

    public static void showArticleDetails(News news) throws Exception{
        // Check if news is hidden
        if (isNewsHidden(news)) {
            System.out.println("This article has been removed due to community reports.");
            return;
        }

        List<Category> categories = categoryService.getCategoryByNewsId(news.getId());
        
        // Filter out hidden categories
        categories = categories.stream()
                .filter(category -> category.getIsHide() == null || category.getIsHide() == 0)
                .collect(java.util.stream.Collectors.toList());

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

     public static List<Integer> getAllNewsId(List<News> newsList){
        List<Integer> newsIds = new ArrayList<>();
        for(News news: newsList){
            newsIds.add(news.getId());
        }
        return newsIds;
    }

    public static Map<Integer,News> processNewsList(List<News> newsList){
        Map<Integer,News> newsMap = new HashMap<>();
        Integer count = 1;
        for(News news: newsList){
            if (!isNewsHidden(news)) {
            newsMap.put(count,news);
            count++;
            }
        }
        return newsMap;
    }

    public static News findNewsById(List<News> newsList, int id) {
        for (News news : newsList) {
            if (news.getId() == id) {
                return news;
            }
        }
        return null; // or throw exception if not found
    }

    public static List<News> filterVisibleNews(List<News> newsList) {
        return newsList.stream()
                .filter(news -> news.getIsHide() == null || news.getIsHide() == 0)
                .collect(java.util.stream.Collectors.toList());
    }

    public static boolean isNewsHidden(News news) {
        return news != null && news.getIsHide() != null && news.getIsHide() == 1;
    }

    public static List<News> filterNewsByVisibleCategories(List<News> newsList, CategoryService categoryService) {
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
    }
}
