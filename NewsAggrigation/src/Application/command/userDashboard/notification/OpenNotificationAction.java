package Application.command.userDashboard.notification;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.command.userDashboard.articleFilterDashboard.articleDashboard.OpenArticleAction;
import Application.menu.ArticleDetailMenu;
import Application.menu.Menu;
import Application.menu.NotificationMenu;
import Application.news.News;
import Application.notification.service.NotificationService;
import Application.personalization.service.NewsPersonalizationService;
import Application.util.NewsUtil;

import java.util.Map;
import java.util.Scanner;

public class OpenNotificationAction implements MenuAction {

    private final Scanner scanner = new Scanner(System.in);
    private Map<Integer, News> newsMap;
    private final NewsPersonalizationService personalizationService;
    private final NotificationService notificationService;

    public OpenNotificationAction(Map<Integer, News> newsMap){
        this.newsMap = newsMap;
        this.personalizationService = new NewsPersonalizationService();
        this.notificationService = new NotificationService();
    }

    @Override
    public String getName() {
        return "Open Notification";
    }

    @Override
    public void execute(LoginResponse response) {
        Menu previousMenu = new NotificationMenu(newsMap);
        System.out.print("Select News : ");
        Integer selectedNewsNumber = scanner.nextInt();

        News selectedNews = newsMap.get(selectedNewsNumber);
        if(selectedNews == null){
            System.out.println("Invalid choose!");
            return;
        }
        try {
            NewsUtil.showArticleDetails(selectedNews);
            notificationService.deleteNotification(selectedNews.getId(),response.getUserId());
            // Record that the user has read this article for personalization
            try {
                personalizationService.recordArticleRead(response.getUserId(), selectedNews.getId());
            } catch (Exception e) {
                // Silently fail - personalization recording shouldn't break the main functionality
                System.err.println("Warning: Could not record article read for personalization: " + e.getMessage());
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        new ArticleDetailMenu(selectedNews.getId(),previousMenu).showMenu(response);
    }
}