package Application.menu;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.command.mainmenu.ExitAction;
import Application.command.mainmenu.LoginAction;
import Application.command.mainmenu.SignupAction;

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

    public void showMenu() {
        while (true) {
            System.out.println("\n=== User Menu ===");
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
                    actions.get(choice - 1).execute(null);
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }
}

