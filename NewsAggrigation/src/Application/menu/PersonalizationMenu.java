package Application.menu;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.command.userDashboard.articleFilterDashboard.articleDashboard.OpenArticleAction;
import Application.command.userDashboard.personalization.*;
import Application.news.News;
import Application.util.MenuUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PersonalizationMenu implements Menu {
    private final List<MenuAction> actions = new ArrayList<>();
    private final Map<Integer, News> newsMap;

    public PersonalizationMenu(Map<Integer, News> newsMap) {
        this.newsMap = newsMap;
        actions.add(new OpenArticleAction(this,newsMap));
        actions.add(new ViewMoreRecommendationsAction(newsMap));
        actions.add(new ViewTopInterestCategoriesAction());
        actions.add(new BackToMainMenuAction());
    }

    @Override
    public void showMenu(LoginResponse response) {
        MenuUtil.showMenu(actions, response);
    }
} 