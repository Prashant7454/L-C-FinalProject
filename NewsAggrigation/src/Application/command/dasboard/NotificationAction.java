package Application.command.dasboard;

import Application.command.MenuAction;

public class NotificationAction implements MenuAction {
    @Override
    public String getName() {
        return "Notification";
    }

    @Override
    public void execute() {
        System.out.println("Fetching top notification...");
        return; // breaks the dashboard loop
    }
}
