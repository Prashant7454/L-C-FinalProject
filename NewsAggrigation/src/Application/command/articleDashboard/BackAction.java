package Application.command.articleDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.menu.Menu;

public class BackAction implements MenuAction {
    Menu previousMenu;
    public BackAction(Menu previousMenu){
        this.previousMenu = previousMenu;
    }

    @Override
    public String getName() {
        return "Back";
    }

    @Override
    public void execute(LoginResponse response) {
        previousMenu.showMenu(response);
    }
}
