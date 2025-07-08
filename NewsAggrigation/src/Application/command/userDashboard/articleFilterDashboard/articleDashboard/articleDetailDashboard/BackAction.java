package Application.command.userDashboard.articleFilterDashboard.articleDashboard.articleDetailDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.menu.Menu;
import Application.news.News;

import java.util.HashMap;
import java.util.Map;

public class BackAction implements MenuAction {
    Menu previousMenu;
    Map<Integer,News> newsMap = new HashMap<>();
    public BackAction(Menu previousMenu, Map<Integer, News> newsMap){
        this.previousMenu = previousMenu;
        this.newsMap = newsMap;
    }

    @Override
    public String getName() {
        return "Back";
    }

    @Override
    public void execute(LoginResponse response) {
        previousMenu.showMenu(response);
    }
}
