package Application.menu;

import Application.auth.login.LoginResponse;

public interface Menu {
    void showMenu(LoginResponse response);
}
