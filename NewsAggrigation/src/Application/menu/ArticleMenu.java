package Application.menu;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.command.adminDashboard.*;
import Application.command.articalDashboard.BackAction;
import Application.command.articalDashboard.DateRangeNewsAction;
import Application.command.articalDashboard.TodayNewsAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArticleMenu {
    private final List<MenuAction> actions = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public ArticleMenu() {

        actions.add(new TodayNewsAction());
        actions.add(new DateRangeNewsAction());
        actions.add(new BackAction());
    }

    public void showMenu(LoginResponse response) {
        while (true) {
            System.out.println("\nPlease choose the options below");
            for (int i = 0; i < actions.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, actions.get(i).getName());
            }

            System.out.print("Choose an option: ");
            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= actions.size()) {
                    actions.get(choice - 1).execute(response);
                } else {
                    System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }
}
