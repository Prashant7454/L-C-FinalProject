package Application.menu;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.command.userDashboard.notification.BackAction;
import Application.command.userDashboard.notification.ClearNotificationAction;
import Application.command.userDashboard.notification.OpenNotificationAction;
import Application.news.News;
import Application.util.MenuUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NotificationMenu implements Menu{
    private final List<MenuAction> actions = new ArrayList<>();
    private Map<Integer, News> newsMap;

    public NotificationMenu( Map<Integer,News> newsMap) {
        this.newsMap = newsMap;
        actions.add(new ClearNotificationAction());
        actions.add(new OpenNotificationAction(newsMap));
        actions.add(new BackAction());
    }

    @Override
    public void showMenu(LoginResponse response) {
        MenuUtil.showMenu(actions, response);
    }
}
