package Application.command.userDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.menu.NotificationMenu;
import Application.news.News;
import Application.notification.service.NotificationService;
import Application.util.NewsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NotificationAction implements MenuAction {

    private final NotificationService notificationService = new NotificationService();

    public NotificationAction(){

    }

    @Override
    public String getName() {
        return "Notification";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("Notifications: ");
        List<News> newsList = new ArrayList<>();
        try {
            newsList = notificationService.getNotificationByUserId(response.getUserId());
        }
        catch (Exception e){
            System.out.println("Error :" + e.getMessage());
        }

        Map<Integer,News> newsMap = NewsUtil.processNewsList(newsList);
        NewsUtil.printNewsList(newsMap);
        new NotificationMenu(newsMap).showMenu(response);
    }
}
