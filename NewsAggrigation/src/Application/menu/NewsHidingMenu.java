package Application.menu;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.command.adminDashboard.newsHiding.*;
import Application.command.adminDashboard.newsHiding.HideNewsByKeywordsAction;
import Application.util.MenuUtil;

import java.util.ArrayList;
import java.util.List;

public class NewsHidingMenu implements Menu {
    private final List<MenuAction> actions = new ArrayList<>();

    public NewsHidingMenu() {
        actions.add(new HideNewsByKeywordsAction());
        actions.add(new PreviewNewsByKeywordsAction());
        actions.add(new BackToAdminAction());
    }

    @Override
    public void showMenu(LoginResponse response) {
        MenuUtil.showMenu(actions, response);
    }
} 