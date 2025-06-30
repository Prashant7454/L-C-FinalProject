package Application.command.userDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.menu.ArticleMenu;
import Application.news.News;
import Application.news.service.NewsService;

import java.util.ArrayList;
import java.util.List;

public class HeadlinesAction implements MenuAction {
    @Override
    public String getName() {
        return "Headlines";
    }

    @Override
    public void execute(LoginResponse response) {
        new ArticleMenu().showMenu(response);
    }
}
