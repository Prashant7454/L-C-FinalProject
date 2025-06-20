package Application.menu;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.command.dasboard.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDashboardMenu {
    private final List<MenuAction> actions = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private final LoginResponse userSession;

    public UserDashboardMenu(LoginResponse userSession) {
        this.userSession = userSession;

        actions.add(new HeadlinesAction());
        actions.add(new SavedArticlesAction());
        actions.add(new SearchAction());
        actions.add(new NotificationAction());
        actions.add(new LogoutAction());
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== User Dashboard ===");
            for (int i = 0; i < actions.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, actions.get(i).getName());
            }
            System.out.print("Choose an option: ");
            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input);
                if (choice < 1 || choice > actions.size()) {
                    System.out.println("Invalid option. Try again.");
                } else {
                    actions.get(choice - 1).execute();
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
