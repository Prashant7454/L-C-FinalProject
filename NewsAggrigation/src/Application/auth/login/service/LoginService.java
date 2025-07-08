package Application.auth.login.service;

import Application.auth.login.LoginRequest;
import Application.auth.login.LoginResponse;
import Application.auth.login.controller.LoginController;
import Application.exception.AuthenticationException;
import Application.exception.ExceptionConstants;
import Application.exception.ExceptionHandler;
import Application.exception.NetworkException;
import Application.exception.ValidationException;

public class LoginService {
    
    public LoginResponse login(String username, String password) throws AuthenticationException, NetworkException, ValidationException {
        try {
            // Validate input
            validateLoginInput(username, password);
            
            LoginRequest loginRequest = getLoginRequest(username, password);
            LoginController loginController = new LoginController();
            return loginController.login(loginRequest);
            
        } catch (AuthenticationException | NetworkException | ValidationException e) {
            ExceptionHandler.handleException(e);
            throw e;
        } catch (Exception e) {
            ExceptionHandler.handleGenericException(e);
            throw new AuthenticationException(ExceptionConstants.LOGIN_FAILED, e);
        }
    }

    private void validateLoginInput(String username, String password) throws ValidationException {
        if (username == null || username.trim().isEmpty()) {
            throw new ValidationException(ExceptionConstants.EMPTY_FIELD, "username");
        }
        
        if (password == null || password.trim().isEmpty()) {
            throw new ValidationException(ExceptionConstants.EMPTY_FIELD, "password");
        }
        
        if (username.length() < 3 || username.length() > 20) {
            throw new ValidationException(ExceptionConstants.INVALID_USERNAME, "username");
        }
        
        if (password.length() < 8) {
            throw new ValidationException(ExceptionConstants.INVALID_PASSWORD, "password");
        }
    }

    private LoginRequest getLoginRequest(String username, String password) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        return loginRequest;
    }
}
