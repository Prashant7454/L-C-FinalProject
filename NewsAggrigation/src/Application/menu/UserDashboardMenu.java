package Application.menu;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.command.userDashboard.*;
import Application.util.MenuUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDashboardMenu implements Menu{
    private final List<MenuAction> actions = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private final LoginResponse userSession;

    public UserDashboardMenu(LoginResponse userSession) {
        this.userSession = userSession;

        actions.add(new HeadlinesAction());
        actions.add(new PersonalizedNewsAction());
        actions.add(new SavedArticlesAction());
        actions.add(new SearchAction());
        actions.add(new NotificationAction());
        actions.add(new NotificationConfigurationAction());
        actions.add(new LogoutAction());
    }

    @Override
    public void showMenu(LoginResponse response) {
        MenuUtil.showMenu(actions,response);
    }
}
