package Application.menu;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.command.articleFilterDashboard.BackAction;
import Application.command.articleFilterDashboard.DateRangeNewsAction;
import Application.command.articleFilterDashboard.TodayNewsAction;
import Application.util.MenuUtil;

import java.util.ArrayList;
import java.util.List;

public class ArticleFilterMenu implements Menu{
    private final List<MenuAction> actions = new ArrayList<>();

    public ArticleFilterMenu() {

        actions.add(new TodayNewsAction());
        actions.add(new DateRangeNewsAction());
        actions.add(new BackAction());
    }

    @Override
    public void showMenu(LoginResponse response) {
        MenuUtil.showMenu(actions,response);
    }
}
