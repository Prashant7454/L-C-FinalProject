package Application.util;

import Application.news.News;

import java.util.List;

public class NewsPrinterUtil {

    private NewsPrinterUtil() {
        // Prevent instantiation
    }

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
}
