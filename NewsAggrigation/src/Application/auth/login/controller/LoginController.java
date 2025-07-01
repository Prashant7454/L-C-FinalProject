package Application.auth.login.controller;

import Application.auth.login.LoginRequest;
import Application.auth.login.LoginResponse;
import Application.util.HttpClientUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LoginController {

    private static final String LOGIN_API_URL = "http://localhost:8081/api/auth/login";
    private final Gson gson = new GsonBuilder().create();

    public LoginResponse login(LoginRequest loginRequest) throws Exception {
        String jsonBody = gson.toJson(loginRequest);
        String responseJson = HttpClientUtil.sendRequest(LOGIN_API_URL, "POST", jsonBody);
        return gson.fromJson(responseJson, LoginResponse.class);
    }
}
