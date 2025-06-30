package Application.command.articalDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;

public class DateRangeNewsAction implements MenuAction {
    @Override
    public String getName() {
        return "Date range";
    }

    @Override
    public void execute(LoginResponse response) {
        return;
    }
}
