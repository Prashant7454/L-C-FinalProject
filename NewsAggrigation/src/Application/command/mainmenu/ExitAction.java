package Application.command.mainmenu;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;

public class ExitAction implements MenuAction {

    @Override
    public String getName() {
        return "Exit";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
