package Application.command.adminDashboard.newsHiding;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.menu.AdminDashboardMenu;

public class BackToAdminAction implements MenuAction {

    @Override
    public String getName() {
        return "Back to Admin Dashboard";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("Returning to admin dashboard...");
        new AdminDashboardMenu(response).showMenu(response);
    }
} 