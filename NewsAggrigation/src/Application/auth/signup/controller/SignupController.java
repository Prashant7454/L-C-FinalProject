package Application.auth.signup.controller;

import Application.auth.signup.SignupRequest;
import Application.auth.signup.SignupResponse;
import Application.util.HttpClientUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SignupController {

    private static final String SIGNUP_API_URL = "http://localhost:8081/api/auth/signup";
    private final Gson gson = new GsonBuilder().create();

    public SignupResponse signup(SignupRequest signupRequest) throws Exception {
        String jsonBody = gson.toJson(signupRequest);
        String responseJson = HttpClientUtil.sendRequest(SIGNUP_API_URL, "POST", jsonBody);
        return gson.fromJson(responseJson, SignupResponse.class);
    }
}
