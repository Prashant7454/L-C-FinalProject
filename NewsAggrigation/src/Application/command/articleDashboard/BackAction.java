package Application.command.articleDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.command.userDashboard.HeadlinesAction;
import Application.menu.ArticleFilterMenu;
import Application.menu.UserDashboardMenu;

public class BackAction implements MenuAction {
    @Override
    public String getName() {
        return "Back";
    }

    @Override
    public void execute(LoginResponse response) {
        new ArticleFilterMenu().showMenu(response);
    }
}
