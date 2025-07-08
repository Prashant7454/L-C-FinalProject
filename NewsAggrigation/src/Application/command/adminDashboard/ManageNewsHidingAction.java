package Application.command.adminDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.menu.NewsHidingMenu;

public class ManageNewsHidingAction implements MenuAction {

    @Override
    public String getName() {
        return "Manage News Hiding";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("== Manage News Hiding ==");
        new NewsHidingMenu().showMenu(response);
    }
} 