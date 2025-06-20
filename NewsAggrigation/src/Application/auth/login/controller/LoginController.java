package Application.auth.login.controller;

import Application.auth.login.LoginRequest;
import Application.auth.login.LoginResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginController {

    private static final String LOGIN_API_URL = "http://localhost:8081/api/auth/login";

    public LoginResponse login(LoginRequest loginRequest) throws Exception{

        Gson gson = new GsonBuilder().create();
        String jsonInput = gson.toJson(loginRequest);

        HttpURLConnection conn = getConnection();

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInput.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        InputStream inputStream;

        if (conn.getResponseCode() >= 200 && conn.getResponseCode() < 300) {
            inputStream = conn.getInputStream(); // success response
        } else {
            inputStream = conn.getErrorStream(); // error response
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));

        StringBuilder responseStr = new StringBuilder();
        String responseLine;
        while ((responseLine = reader.readLine()) != null) {
            responseStr.append(responseLine.trim());
        }

        return gson.fromJson(responseStr.toString(), LoginResponse.class);
    }

    private HttpURLConnection getConnection() throws Exception{
        URL url = new URL(LOGIN_API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        return conn;
    }
}
