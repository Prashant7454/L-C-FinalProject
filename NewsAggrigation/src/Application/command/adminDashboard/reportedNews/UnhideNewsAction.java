package Application.command.adminDashboard.reportedNews;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.news.News;
import Application.news.service.NewsService;

import java.util.Map;
import java.util.Scanner;

public class UnhideNewsAction implements MenuAction {

    private final NewsService newsService = new NewsService();
    private final Scanner scanner = new Scanner(System.in);
    private final Map<Integer, News> newsMap;

    public UnhideNewsAction(Map<Integer, News> newsMap) {
        this.newsMap = newsMap;
    }

    @Override
    public String getName() {
        return "Unhide News";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("== Unhide News ==");

        try {
            if (newsMap.isEmpty()) {
                System.out.println("No reported news available.");
                return;
            }

            System.out.print("Enter the number of the news article to unhide: ");
            int selectedNumber = Integer.parseInt(scanner.nextLine().trim());

            if (!newsMap.containsKey(selectedNumber)) {
                System.out.println("Invalid selection.");
                return;
            }

            News selectedNews = newsMap.get(selectedNumber);
            
            // Check if news is already visible
            if (selectedNews.getIsHide() == null || selectedNews.getIsHide() == 0) {
                System.out.println("News article '" + selectedNews.getTitle() + "' is already visible.");
                return;
            }

            // Confirm action
            System.out.print("Are you sure you want to unhide news article '" + selectedNews.getTitle() + "'? (y/n): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            
            if (!confirmation.equals("y") && !confirmation.equals("yes")) {
                System.out.println("Operation cancelled.");
                return;
            }

            // Unhide the news
            News updatedNews = newsService.unhideNews(selectedNews.getId());
            System.out.println("News article '" + updatedNews.getTitle() + "' has been unhidden successfully.");
            System.out.println("This article will now be visible to users.");

        } catch (Exception e) {
            System.err.println("Error unhiding news: " + e.getMessage());
        }
    }
} 