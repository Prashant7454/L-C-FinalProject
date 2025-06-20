package Application.auth.login.service;

import Application.auth.login.LoginRequest;
import Application.auth.login.LoginResponse;
import Application.auth.login.controller.LoginController;

public class LoginService {
    public LoginResponse login(String username, String password) throws Exception{
        LoginRequest loginRequest = getLoginRequest(username,password);
        LoginController loginController = new LoginController();
        return loginController.login(loginRequest);
    }

    private LoginRequest getLoginRequest(String username, String password){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        return loginRequest;
    }
}
