package Application.auth.login.controller;

import Application.auth.login.LoginRequest;
import Application.auth.login.LoginResponse;
import Application.exception.AuthenticationException;
import Application.exception.ExceptionConstants;
import Application.exception.NetworkException;
import Application.util.HttpClientUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;

public class LoginController {

    private static final String LOGIN_API_URL = "http://localhost:8081/api/auth/login";
    private final Gson gson = new GsonBuilder().create();

    public LoginResponse login(LoginRequest loginRequest) throws AuthenticationException, NetworkException {
        try {
            String jsonBody = gson.toJson(loginRequest);
            String responseJson = HttpClientUtil.sendRequest(LOGIN_API_URL, "POST", jsonBody);
            
            // Parse response
            LoginResponse response = gson.fromJson(responseJson, LoginResponse.class);
            
            // Check if login was successful
            if (response == null) {
                throw new AuthenticationException(ExceptionConstants.LOGIN_FAILED);
            }
            
            if (response.getMessage() != null && response.getMessage().contains("Invalid")) {
                throw new AuthenticationException(ExceptionConstants.INVALID_CREDENTIALS);
            }
            
            return response;
            
        } catch (JsonSyntaxException e) {
            throw new AuthenticationException(ExceptionConstants.LOGIN_FAILED + " - Invalid response format", e);
        } catch (IOException e) {
            throw new NetworkException(ExceptionConstants.CONNECTION_FAILED, LOGIN_API_URL, e);
        } catch (Exception e) {
            throw new NetworkException(ExceptionConstants.CONNECTION_FAILED, LOGIN_API_URL, e);
        }
    }
}
