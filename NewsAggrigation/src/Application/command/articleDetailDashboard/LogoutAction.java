package Application.command.articleDetailDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.menu.UserMenu;

import java.awt.*;

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
