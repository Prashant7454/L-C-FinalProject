package Application.command.adminDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.menu.UserMenu;

public class AdminLogoutAction implements MenuAction {
    @Override
    public String getName() {
        return "Logout";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("Logging out admin...");
        new UserMenu().showMenu();
    }
}

