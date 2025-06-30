package Application.command.userDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;

public class NotificationAction implements MenuAction {
    @Override
    public String getName() {
        return "Notification";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("Fetching top notification...");
        return; // breaks the dashboard loop
    }
}
