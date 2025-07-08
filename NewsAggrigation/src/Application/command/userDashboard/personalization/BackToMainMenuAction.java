package Application.command.userDashboard.personalization;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.menu.UserDashboardMenu;

public class BackToMainMenuAction implements MenuAction {

    @Override
    public String getName() {
        return "Back to Main Menu";
    }

    @Override
    public void execute(LoginResponse response) {
        new UserDashboardMenu(response).showMenu(response);
    }
} 