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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HttpClientUtil.class})
public class LoginControllerTest {
    private LoginController loginController;
    private Gson gson;

    @Before
    public void setUp() {
        loginController = new LoginController();
        gson = new GsonBuilder().create();
        PowerMockito.mockStatic(HttpClientUtil.class);
    }

    @Test
    public void testLogin_Success() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setUsername("user");
        request.setPassword("pass");
        LoginResponse expectedResponse = new LoginResponse();
        expectedResponse.setMessage("Login successful");
        expectedResponse.setUserId(1);
        expectedResponse.setToken("token123");
        expectedResponse.setRole("USER");
        expectedResponse.setStatus(200);
        String responseJson = gson.toJson(expectedResponse);

        PowerMockito.when(HttpClientUtil.sendRequest(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(responseJson);

        LoginResponse actualResponse = loginController.login(request);
        assertNotNull(actualResponse);
        assertEquals("Login successful", actualResponse.getMessage());
        assertEquals(Integer.valueOf(1), actualResponse.getUserId());
        assertEquals("token123", actualResponse.getToken());
        assertEquals("USER", actualResponse.getRole());
        assertEquals(200, actualResponse.getStatus());
    }

    @Test
    public void testLogin_InvalidCredentials() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setUsername("user");
        request.setPassword("wrong");
        LoginResponse invalidResponse = new LoginResponse();
        invalidResponse.setMessage("Invalid username or password");
        String responseJson = gson.toJson(invalidResponse);

        PowerMockito.when(HttpClientUtil.sendRequest(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(responseJson);

        try {
            loginController.login(request);
            fail("Expected AuthenticationException");
        } catch (AuthenticationException ex) {
            assertEquals(ExceptionConstants.INVALID_CREDENTIALS, ex.getMessage());
        }
    }

    @Test
    public void testLogin_NullResponse() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setUsername("user");
        request.setPassword("pass");

        PowerMockito.when(HttpClientUtil.sendRequest(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(null);

        try {
            loginController.login(request);
            fail("Expected AuthenticationException");
        } catch (AuthenticationException ex) {
            assertEquals(ExceptionConstants.LOGIN_FAILED, ex.getMessage());
        }
    }

    @Test
    public void testLogin_InvalidJson() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setUsername("user");
        request.setPassword("pass");

        PowerMockito.when(HttpClientUtil.sendRequest(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn("not a json");

        try {
            loginController.login(request);
            fail("Expected AuthenticationException");
        } catch (AuthenticationException ex) {
            assertTrue(ex.getMessage().contains(ExceptionConstants.LOGIN_FAILED));
        }
    }

    @Test
    public void testLogin_IOException() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setUsername("user");
        request.setPassword("pass");

        PowerMockito.when(HttpClientUtil.sendRequest(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenThrow(new IOException("Connection error"));

        try {
            loginController.login(request);
            fail("Expected NetworkException");
        } catch (NetworkException ex) {
            assertEquals(ExceptionConstants.CONNECTION_FAILED, ex.getMessage());
        }
    }
}