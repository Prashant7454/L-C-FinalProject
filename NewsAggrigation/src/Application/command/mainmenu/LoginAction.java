package Application.command.mainmenu;

import Application.auth.login.LoginResponse;
import Application.auth.login.service.LoginService;
import Application.command.MenuAction;
import Application.menu.AdminDashboardMenu;
import Application.menu.UserDashboardMenu;

import java.util.Scanner;

public class LoginAction implements MenuAction {
    private final LoginService loginService = new LoginService();
    Scanner scanner = null;

    public LoginAction(){
        scanner = new Scanner(System.in);
    }

    @Override
    public String getName() {
        return "Login";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.print("Enter Username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        response = null;
        try {
            response = loginService.login(username, password);
        }
        catch (Exception e){
            return;
        }

        if (response != null) {
            System.out.println("Welcome to the Dashboard!");
            if (response.getRole().equalsIgnoreCase("admin")) {
                new AdminDashboardMenu(response).showMenu(response);
            } else {
                new UserDashboardMenu(response).showMenu(response);
            }
        } else {
            System.out.println("Login failed.");
        }
    }
}
