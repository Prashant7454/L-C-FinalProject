package Application.command.notification;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.notification.service.NotificationService;

public class ClearNotificationAction implements MenuAction {

    private final NotificationService notificationService = new NotificationService();
    @Override
    public String getName() {
        return "Clear All Notifications";
    }

    @Override
    public void execute(LoginResponse response) {
        try {
            notificationService.clearNotificationsByUserId(response.getUserId());
        } catch (Exception e) {
            System.err.println("Error clearing notifications: " + e.getMessage());
        }
        return;
    }
}
