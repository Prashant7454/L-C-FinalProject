package Application.command.notification;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.command.articleDashboard.OpenArticleAction;
import Application.menu.Menu;
import Application.menu.NotificationMenu;
import Application.news.News;
import Application.util.NewsUtil;

import java.util.Map;
import java.util.Scanner;

public class OpenNotificationAction implements MenuAction {

    private final Scanner scanner = new Scanner(System.in);
    private Map<Integer, News> newsMap;

    public OpenNotificationAction(Map<Integer, News> newsMap){
        this.newsMap = newsMap;
    }

    @Override
    public String getName() {
        return "Open Notification";
    }

    @Override
    public void execute(LoginResponse response) {
        Menu previousMenu = new NotificationMenu(newsMap);
        new OpenArticleAction(previousMenu,newsMap).execute(response);
    }
}