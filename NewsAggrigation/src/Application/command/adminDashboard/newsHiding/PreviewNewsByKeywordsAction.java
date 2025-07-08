package Application.command.adminDashboard.newsHiding;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.news.News;
import Application.newsHiding.service.NewsHidingService;
import Application.util.NewsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PreviewNewsByKeywordsAction implements MenuAction {

    private final NewsHidingService newsHidingService = new NewsHidingService();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getName() {
        return "Preview News by Keywords";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("== Preview News by Keywords ==");

        try {
            // Get keywords from user
            List<String> keywords = getKeywordsFromUser();
            
            if (keywords.isEmpty()) {
                System.out.println("No keywords provided. Operation cancelled.");
                return;
            }

            System.out.println("Keywords to search for: " + keywords);

            // Find news articles containing the keywords
            List<News> foundNews = newsHidingService.findNewsByKeywords(keywords);
            
            if (foundNews.isEmpty()) {
                System.out.println("No news articles found containing the specified keywords.");
                return;
            }

            System.out.println("Found " + foundNews.size() + " news articles containing the keywords:\n");
            
            // Display the news articles
            Map<Integer, News> newsMap = NewsUtil.processNewsList(foundNews);
            NewsUtil.printNewsList(newsMap);

        } catch (Exception e) {
            System.err.println("Error previewing news by keywords: " + e.getMessage());
        }
    }

    private List<String> getKeywordsFromUser() {
        List<String> keywords = new ArrayList<>();
        
        System.out.println("Enter keywords to search for (one per line, press Enter twice to finish):");
        System.out.println("Example keywords: spam, clickbait, fake news, etc.");
        
        String input;
        while (true) {
            System.out.print("Keyword (or press Enter to finish): ");
            input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                break;
            }
            
            if (!input.isEmpty()) {
                keywords.add(input);
            }
        }
        
        return keywords;
    }
} 