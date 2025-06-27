package Application.command.userDashboard;

import Application.command.MenuAction;

public class NotificationAction implements MenuAction {
    @Override
    public String getName() {
        return "Notification";
    }

    @Override
    public void execute(int userId) {
        System.out.println("Fetching top notification...");
        return; // breaks the dashboard loop
    }
}
