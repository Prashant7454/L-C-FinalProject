package Application.menu;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.command.articleDashboard.OpenArticleAction;
import Application.command.notification.BackAction;
import Application.command.notification.ClearNotificationAction;
import Application.command.notification.OpenNotificationAction;
import Application.news.News;
import Application.util.MenuUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class NotificationMenu implements Menu{
    private final List<MenuAction> actions = new ArrayList<>();
    private Map<Integer, News> newsMap;

    public NotificationMenu( Map<Integer,News> newsMap) {
        this.newsMap = newsMap;
        actions.add(new BackAction());
        actions.add(new ClearNotificationAction());
        actions.add(new OpenNotificationAction(newsMap));
    }

    @Override
    public void showMenu(LoginResponse response) {
        MenuUtil.showMenu(actions, response);
    }
}
