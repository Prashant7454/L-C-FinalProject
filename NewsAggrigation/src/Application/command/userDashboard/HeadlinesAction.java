package Application.command.userDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.menu.ArticleFilterMenu;

public class HeadlinesAction implements MenuAction {
    @Override
    public String getName() {
        return "Headlines";
    }

    @Override
    public void execute(LoginResponse response) {
        new ArticleFilterMenu().showMenu(response);
    }
}
