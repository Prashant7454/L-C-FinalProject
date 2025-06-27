package Application.command.userDashboard;

import Application.command.MenuAction;
import Application.menu.UserMenu;

public class LogoutAction implements MenuAction {
    @Override
    public String getName() {
        return "Logout";
    }

    @Override
    public void execute(int userId) {
        System.out.println("You have been logged out.");
        new UserMenu().showMenu();
    }
}
