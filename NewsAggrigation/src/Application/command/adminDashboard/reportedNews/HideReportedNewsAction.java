package Application.command.adminDashboard.reportedNews;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.news.News;
import Application.news.service.NewsService;

import java.util.Map;
import java.util.Scanner;

public class HideReportedNewsAction implements MenuAction {

    private final NewsService newsService = new NewsService();
    private final Scanner scanner = new Scanner(System.in);
    private final Map<Integer, News> newsMap;

    public HideReportedNewsAction(Map<Integer, News> newsMap) {
        this.newsMap = newsMap;
    }

    @Override
    public String getName() {
        return "Hide Reported News";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("== Hide Reported News ==");

        try {
            if (newsMap.isEmpty()) {
                System.out.println("No reported news available.");
                return;
            }

            System.out.print("Enter the number of the news article to hide: ");
            int selectedNumber = Integer.parseInt(scanner.nextLine().trim());

            if (!newsMap.containsKey(selectedNumber)) {
                System.out.println("Invalid selection.");
                return;
            }

            News selectedNews = newsMap.get(selectedNumber);
            
            // Check if news is already hidden
            if (selectedNews.getIsHide() != null && selectedNews.getIsHide() == 1) {
                System.out.println("News article '" + selectedNews.getTitle() + "' is already hidden.");
                return;
            }

            // Confirm action
            System.out.print("Are you sure you want to hide news article '" + selectedNews.getTitle() + "'? (y/n): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            
            if (!confirmation.equals("y") && !confirmation.equals("yes")) {
                System.out.println("Operation cancelled.");
                return;
            }

            // Hide the news
            News updatedNews = newsService.hideNews(selectedNews.getId());
            System.out.println("News article '" + updatedNews.getTitle() + "' has been hidden successfully.");
            System.out.println("This article will no longer be visible to users.");

        } catch (Exception e) {
            System.err.println("Error hiding reported news: " + e.getMessage());
        }
    }
} 