package Application.menu;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.command.userDashboard.articleFilterDashboard.articleDashboard.OpenArticleAction;
import Application.command.userDashboard.articleFilterDashboard.articleDashboard.BackAction;
import Application.news.News;
import Application.util.MenuUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleMenu implements Menu{
    private final List<MenuAction> actions = new ArrayList<>();
    private Map<Integer,News> newsMap;
    public ArticleMenu(Menu previousMenu, Map<Integer,News> newsMap) {
        this.newsMap = newsMap;
        actions.add(new OpenArticleAction(previousMenu,newsMap));
        actions.add(new BackAction(previousMenu));
    }

    @Override
    public void showMenu(LoginResponse response) {
        MenuUtil.showMenu(actions,response);
    }
}
