package Application.command.articalDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;

public class TodayNewsAction implements MenuAction {
    @Override
    public String getName() {
        return "Today";
    }

    @Override
    public void execute(LoginResponse response) {
        return;
    }
}
