package Application.command.userDashboard.articleFilterDashboard.articleDashboard.articleDetailDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.menu.UserMenu;

public class LogoutAction implements MenuAction {
    @Override
    public String getName() {
        return "Logout";
    }

    @Override
    public void execute(LoginResponse response) {
        new UserMenu().showMenu(response);
    }
}
