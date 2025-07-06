package Application.command.userDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.menu.NotificationConfigMenu;

public class NotificationAction implements MenuAction {
    @Override
    public String getName() {
        return "Notification Configuration";
    }

    @Override
    public void execute(LoginResponse response) {
        new NotificationConfigMenu(response).showMenu(response);
    }
}
