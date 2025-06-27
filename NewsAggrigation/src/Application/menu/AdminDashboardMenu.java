package Application.menu;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.command.adminDashboard.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminDashboardMenu {
    private final List<MenuAction> actions = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private final LoginResponse adminSession;

    public AdminDashboardMenu(LoginResponse adminSession) {
        this.adminSession = adminSession;

        actions.add(new ViewServerListAction());
        actions.add(new ViewServerDetailsAction());
        actions.add(new EditServerDetailsAction());
        actions.add(new AddNewsCategoryAction());
        actions.add(new AdminLogoutAction());
    }

    public void showMenu(int userId) {
        while (true) {
            System.out.println("\n=== Admin Dashboard ===");
            for (int i = 0; i < actions.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, actions.get(i).getName());
            }

            System.out.print("Choose an option: ");
            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= actions.size()) {
                    actions.get(choice - 1).execute(userId);
                } else {
                    System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }
}

