package Application.command.dasboard;

import Application.command.MenuAction;
import Application.menu.UserMenu;

public class LogoutAction implements MenuAction {
    @Override
    public String getName() {
        return "Logout";
    }

    @Override
    public void execute() {
        System.out.println("You have been logged out.");
        new UserMenu().showMenu();;
    }
}
