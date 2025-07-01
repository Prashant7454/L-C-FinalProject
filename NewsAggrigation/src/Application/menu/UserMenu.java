package Application.menu;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.command.mainmenu.ExitAction;
import Application.command.mainmenu.LoginAction;
import Application.command.mainmenu.SignupAction;
import Application.util.MenuUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserMenu {
    private final List<MenuAction> actions = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public UserMenu() {
        // Add actions without modifying this class later
        actions.add(new LoginAction());
        actions.add(new SignupAction());
        actions.add(new ExitAction());
    }

    public void showMenu(LoginResponse response) {
        MenuUtil.showMenu(actions,response);
    }
}

