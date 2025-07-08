package Application.command.userDashboard.personalization;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.news.News;
import Application.personalization.service.NewsPersonalizationService;
import Application.util.NewsUtil;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ViewMoreRecommendationsAction implements MenuAction {
    private final NewsPersonalizationService personalizationService;
    private final Scanner scanner;
    private final Map<Integer, News> newsMap;

    public ViewMoreRecommendationsAction(Map<Integer, News> newsMap) {
        this.personalizationService = new NewsPersonalizationService();
        this.scanner = new Scanner(System.in);
        this.newsMap = newsMap;
    }

    @Override
    public String getName() {
        return "View More Recommendations";
    }

    @Override
    public void execute(LoginResponse response) {
        try {
            System.out.println("\n== More Personalized Recommendations ==");
            Integer userId = response.getUserId();
            
            // Get more recommendations from the next page
            List<News> moreNews = personalizationService.getPersonalizedNewsPaginated(userId, 1, 10);
            
            if (moreNews.isEmpty()) {
                System.out.println("No more recommendations available.");
            } else {
                Map<Integer, News> additionalNewsMap = NewsUtil.processNewsList(moreNews);
                NewsUtil.printNewsList(additionalNewsMap);
            }
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
            
        } catch (Exception e) {
            System.err.println("Error loading more recommendations: " + e.getMessage());
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
        }
    }
} 