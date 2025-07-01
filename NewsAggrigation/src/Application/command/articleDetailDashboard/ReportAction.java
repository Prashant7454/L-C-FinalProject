package Application.command.articleDetailDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;

public class ReportAction implements MenuAction {
    @Override
    public String getName() {
        return "Report Article";
    }

    @Override
    public void execute(LoginResponse response) {

    }
}
