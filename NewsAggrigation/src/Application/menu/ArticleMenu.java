package Application.menu;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.command.articleDashboard.OpenArticleAction;
import Application.command.articleDashboard.BackAction;
import Application.util.MenuUtil;

import java.util.ArrayList;
import java.util.List;

public class ArticleMenu {
    private final List<MenuAction> actions = new ArrayList<>();

    public ArticleMenu() {

        actions.add(new BackAction());
        actions.add(new OpenArticleAction());
    }

    public void showMenu(LoginResponse response) {
        MenuUtil.showMenu(actions,response);
    }
}
