package Application.menu;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.command.notificationConfig.*;
import Application.util.MenuUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NotificationConfigMenu implements Menu {
    private final List<MenuAction> actions = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private final LoginResponse userSession;

    public NotificationConfigMenu(LoginResponse userSession) {
        this.userSession = userSession;

        actions.add(new ViewNotificationConfigAction());
        actions.add(new ToggleNotificationAction());
        actions.add(new AddKeywordToCategoryAction());
        actions.add(new BackAction());
    }

    @Override
    public void showMenu(LoginResponse response) {
        MenuUtil.showMenu(actions, response);
    }
} 