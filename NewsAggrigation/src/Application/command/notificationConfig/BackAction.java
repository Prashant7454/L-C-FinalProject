package Application.command.notificationConfig;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;

public class BackAction implements MenuAction {
    @Override
    public String getName() {
        return "Back";
    }

    @Override
    public void execute(LoginResponse response) {
        // This will break the menu loop and return to the previous menu
        return;
    }
} 