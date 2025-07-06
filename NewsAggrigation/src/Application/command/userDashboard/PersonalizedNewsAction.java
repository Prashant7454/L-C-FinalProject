package Application.command.userDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.menu.ArticleMenu;
import Application.menu.ArticleFilterMenu;
import Application.menu.PersonalizationMenu;
import Application.news.News;
import Application.personalization.service.NewsPersonalizationService;
import Application.util.NewsUtil;

import java.util.List;
import java.util.Map;

public class PersonalizedNewsAction implements MenuAction {
    private final NewsPersonalizationService personalizationService;

    public PersonalizedNewsAction() {
        this.personalizationService = new NewsPersonalizationService();
    }

    @Override
    public String getName() {
        return "Personalized News Recommendations";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("== Personalized News Recommendations ==");
        System.out.println("Based on your interests, reading history, and preferences...\n");

        try {
            Integer userId = response.getUserId();
            
            // Get personalized news recommendations
            List<News> personalizedNews = personalizationService.getPersonalizedNews(userId, 20);
            
            if (personalizedNews.isEmpty()) {
                System.out.println("No personalized recommendations available yet.");
                System.out.println("Try reading some articles, liking content, or configuring your notification preferences to get personalized recommendations.");
                return;
            }

            // Process and display the news
            Map<Integer, News> newsMap = NewsUtil.processNewsList(personalizedNews);
            NewsUtil.printNewsList(newsMap);
            
            // Pass the processed news map to the personalization menu
            new PersonalizationMenu(newsMap).showMenu(response);
            
            // Show article menu for interaction
            new ArticleMenu(new ArticleFilterMenu(), newsMap).showMenu(response);
            
        } catch (Exception e) {
            System.err.println("Error loading personalized news: " + e.getMessage());
        }
    }
} 