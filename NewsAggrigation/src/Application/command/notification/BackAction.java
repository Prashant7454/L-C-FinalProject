package Application.command.notification;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.menu.UserDashboardMenu;

public class BackAction implements MenuAction {
    @Override
    public String getName() {
        return "Back";
    }

    @Override
    public void execute(LoginResponse response) {
        new UserDashboardMenu(response).showMenu(response);
    }
}
