package Application.menu;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.command.adminDashboard.reportedNews.*;
import Application.news.News;
import Application.util.MenuUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportedNewsMenu implements Menu {
    private final List<MenuAction> actions = new ArrayList<>();
    private final Map<Integer, News> newsMap;

    public ReportedNewsMenu(Map<Integer, News> newsMap) {
        this.newsMap = newsMap;
        actions.add(new HideReportedNewsAction(newsMap));
        actions.add(new UnhideNewsAction(newsMap));
        actions.add(new ViewNewsDetailsAction(newsMap));
        actions.add(new BackToAdminAction());
    }

    @Override
    public void showMenu(LoginResponse response) {
        MenuUtil.showMenu(actions, response);
    }
} 