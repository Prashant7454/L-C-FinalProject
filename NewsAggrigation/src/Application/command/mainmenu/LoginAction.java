package Application.command.mainmenu;

import Application.auth.login.LoginResponse;
import Application.auth.login.service.LoginService;
import Application.command.MenuAction;
import Application.menu.AdminDashboardMenu;
import Application.menu.UserDashboardMenu;

import java.util.Scanner;

public class LoginAction implements MenuAction {

    private final LoginService loginService = new LoginService();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getName() {
        return "Login";
    }

    @Override
    public void execute(LoginResponse unused) {
        String username = promptInput("Enter username: ");
        String password = promptInput("Enter password: ");

        try {
            LoginResponse response = loginService.login(username, password);
            handleLoginResponse(response);
        } catch (Exception e) {
            System.err.println("Error during login: " + e.getMessage());
        }
    }

    private String promptInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private void handleLoginResponse(LoginResponse response) {
        if (response == null) {
            System.out.println("Login failed. Invalid credentials.");
            return;
        }

        System.out.println("Login successful. Welcome to the dashboard!");

        switch (response.getRole().toLowerCase()) {
            case "admin" -> new AdminDashboardMenu(response).showMenu(response);
            case "user" -> new UserDashboardMenu(response).showMenu(response);
            default -> System.out.println("⚠️ Unknown role: " + response.getRole());
        }
    }
}
