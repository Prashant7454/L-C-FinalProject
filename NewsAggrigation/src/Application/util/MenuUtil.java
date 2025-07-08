package Application.util;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;

import java.util.List;
import java.util.Scanner;

public class MenuUtil {

    private static final Scanner scanner = new Scanner(System.in);

    public static void showMenu(List<MenuAction> actions, LoginResponse response) {
        while (true) {
            displayMenu(actions);
            int choice = getUserChoice(actions.size());
            if (choice != -1) {
                executeAction(actions, choice, response);
            }
        }
    }

    private static void displayMenu(List<MenuAction> actions) {
        System.out.println("\nPlease choose the options below");
        for (int i = 0; i < actions.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, actions.get(i).getName());
        }
    }

    public static int getUserChoice(int maxOption) {
        System.out.print("Choose an option: ");
        String input = scanner.nextLine();

        try {
            int choice = Integer.parseInt(input);
            if (choice >= 1 && choice <= maxOption) {
                return choice;
            } else {
                System.out.println("Invalid choice.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number.");
        }
        return -1;
    }

    private static void executeAction(List<MenuAction> actions, int choice, LoginResponse response) {
        actions.get(choice - 1).execute(response);
    }
}
