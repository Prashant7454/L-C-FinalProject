package Application.command;

import Application.auth.login.LoginResponse;

public interface MenuAction {
    String getName();
    void execute(LoginResponse response);
}