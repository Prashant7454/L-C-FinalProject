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

public class HideNewsByKeywordsAction implements MenuAction {

    private final NewsHidingService newsHidingService = new NewsHidingService();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getName() {
        return "Hide News by Keywords";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("== Hide News by Keywords ==");

        try {
            // Get keywords from user
            List<String> keywords = getKeywordsFromUser();
            
            if (keywords.isEmpty()) {
                System.out.println("No keywords provided. Operation cancelled.");
                return;
            }

            System.out.println("Keywords to search for: " + keywords);

            // First, check how many news articles would be affected
            int affectedCount = newsHidingService.getNewsCountByKeywords(keywords);
            
            if (affectedCount == 0) {
                System.out.println("No news articles found containing the specified keywords.");
                return;
            }

            System.out.println("Found " + affectedCount + " news articles containing the keywords.");

            // Show preview of news that would be hidden
            System.out.println("\nPreview of news articles that would be hidden:");
            List<News> previewNews = newsHidingService.findNewsByKeywords(keywords);
            Map<Integer, News> newsMap = NewsUtil.processNewsList(previewNews);
            NewsUtil.printNewsList(newsMap);

            // Confirm action
            System.out.print("\nDo you want to hide these " + affectedCount + " news articles? (y/n): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            
            if (!confirmation.equals("y") && !confirmation.equals("yes")) {
                System.out.println("Operation cancelled.");
                return;
            }

            // Hide the news articles
            String result = newsHidingService.hideNewsByKeywords(keywords);
            System.out.println("Result: " + result);

        } catch (Exception e) {
            System.err.println("Error hiding news by keywords: " + e.getMessage());
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