package Application.menu;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.command.articleDashboard.OpenArticleAction;
import Application.command.articleDashboard.BackAction;
import Application.util.MenuUtil;

import java.util.ArrayList;
import java.util.List;

public class ArticleMenu implements Menu{
    private final List<MenuAction> actions = new ArrayList<>();

    public ArticleMenu(Menu previousMenu) {

        actions.add(new BackAction(previousMenu));
        actions.add(new OpenArticleAction(previousMenu));
    }

    @Override
    public void showMenu(LoginResponse response) {
        MenuUtil.showMenu(actions,response);
    }
}
