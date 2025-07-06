package Application.command.userDashboard.personalization;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.personalization.service.NewsPersonalizationService;

import java.util.List;
import java.util.Scanner;

public class ViewTopInterestCategoriesAction implements MenuAction {
    private final NewsPersonalizationService personalizationService;
    private final Scanner scanner;

    public ViewTopInterestCategoriesAction() {
        this.personalizationService = new NewsPersonalizationService();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String getName() {
        return "View Top Interest Categories";
    }

    @Override
    public void execute(LoginResponse response) {
        try {
            System.out.println("\n== Your Top Interest Categories ==");
            Integer userId = response.getUserId();
            
            List<Integer> topCategories = personalizationService.getUserTopInterestCategories(userId, 5);
            
            if (topCategories.isEmpty()) {
                System.out.println("No interest categories available yet.");
                System.out.println("Start reading articles and configuring your preferences to build your interest profile.");
            } else {
                System.out.println("Based on your reading behavior, you're most interested in:");
                for (int i = 0; i < topCategories.size(); i++) {
                    System.out.println((i + 1) + ". Category ID: " + topCategories.get(i));
                }
                System.out.println("\nThese categories influence your personalized recommendations.");
            }
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
            
        } catch (Exception e) {
            System.err.println("Error loading interest categories: " + e.getMessage());
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
        }
    }
} 