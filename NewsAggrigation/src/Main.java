import Application.auth.login.LoginResponse;
import Application.auth.login.controller.LoginController;
import Application.auth.login.service.LoginService;
import Application.menu.UserMenu;

public class Main {
    public static void main(String[] args) {
        new UserMenu().showMenu(null);
    }
}
