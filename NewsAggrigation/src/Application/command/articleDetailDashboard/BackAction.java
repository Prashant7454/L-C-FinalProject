package Application.command.articleDetailDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.menu.ArticleMenu;

public class BackAction implements MenuAction {
    @Override
    public String getName() {
        return "Back";
    }

    @Override
    public void execute(LoginResponse response) {
        new ArticleMenu().showMenu(response);
    }
}
