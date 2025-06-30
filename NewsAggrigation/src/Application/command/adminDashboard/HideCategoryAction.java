package Application.command.adminDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;

public class HideCategoryAction implements MenuAction {
    @Override
    public String getName() {
        return "Hide Category";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("Hide Category");
        return;
    }
}
