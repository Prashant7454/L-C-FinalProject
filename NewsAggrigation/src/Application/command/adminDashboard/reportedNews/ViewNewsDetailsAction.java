package Application.command.adminDashboard.reportedNews;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.news.News;
import Application.util.NewsUtil;

import java.util.Map;
import java.util.Scanner;

public class ViewNewsDetailsAction implements MenuAction {

    private final Scanner scanner = new Scanner(System.in);
    private final Map<Integer, News> newsMap;

    public ViewNewsDetailsAction(Map<Integer, News> newsMap) {
        this.newsMap = newsMap;
    }

    @Override
    public String getName() {
        return "View News Details";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("== View News Details ==");

        try {
            if (newsMap.isEmpty()) {
                System.out.println("No reported news available.");
                return;
            }

            System.out.print("Enter the number of the news article to view details: ");
            int selectedNumber = Integer.parseInt(scanner.nextLine().trim());

            if (!newsMap.containsKey(selectedNumber)) {
                System.out.println("Invalid selection.");
                return;
            }

            News selectedNews = newsMap.get(selectedNumber);
            
            System.out.println("\n=== News Article Details ===");
            NewsUtil.showArticleDetails(selectedNews);
            System.out.println("Report Count: " + selectedNews.getReportCount());
            System.out.println("Hidden Status: " + (selectedNews.getIsHide() != null && selectedNews.getIsHide() == 1 ? "HIDDEN" : "VISIBLE"));
            System.out.println("===========================\n");

        } catch (Exception e) {
            System.err.println("Error viewing news details: " + e.getMessage());
        }
    }
} 