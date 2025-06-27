package Application.command.adminDashboard;

import Application.command.MenuAction;
import Application.menu.UserMenu;

public class AdminLogoutAction implements MenuAction {
    @Override
    public String getName() {
        return "Logout";
    }

    @Override
    public void execute(int userId) {
        System.out.println("Logging out admin...");
        new UserMenu().showMenu();
    }
}

