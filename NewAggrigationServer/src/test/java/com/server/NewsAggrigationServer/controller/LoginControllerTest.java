package com.server.NewsAggrigationServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.NewsAggrigationServer.dto.LoginRequestDTO;
import com.server.NewsAggrigationServer.dto.LoginResponseDTO;
import com.server.NewsAggrigationServer.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController loginController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private LoginRequestDTO loginRequestDTO;
    private LoginResponseDTO loginResponseDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
        objectMapper = new ObjectMapper();

        loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUsername("testuser");
        loginRequestDTO.setPassword("password123");

        loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setToken("test-token");
        loginResponseDTO.setMessage("Login successful");
        loginResponseDTO.setRole("USER");
    }

    @Test
    void testLogin_Success() throws Exception {
        // Arrange
        when(loginService.login(anyString(), anyString())).thenReturn(loginResponseDTO);

        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(loginResponseDTO.getToken()))
                .andExpect(jsonPath("$.message").value(loginResponseDTO.getMessage()))
                .andExpect(jsonPath("$.role").value(loginResponseDTO.getRole()));
    }

    @Test
    void testLogin_WithEmptyCredentials() throws Exception {
        // Arrange
        LoginRequestDTO emptyRequest = new LoginRequestDTO();
        emptyRequest.setUsername("");
        emptyRequest.setPassword("");

        when(loginService.login("", "")).thenReturn(loginResponseDTO);

        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(emptyRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void testLogin_WithNullCredentials() throws Exception {
        // Arrange
        LoginRequestDTO nullRequest = new LoginRequestDTO();
        nullRequest.setUsername(null);
        nullRequest.setPassword(null);

        when(loginService.login(null, null)).thenReturn(loginResponseDTO);

        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nullRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void testLogin_WithSpecialCharacters() throws Exception {
        // Arrange
        LoginRequestDTO specialRequest = new LoginRequestDTO();
        specialRequest.setUsername("test_user-123");
        specialRequest.setPassword("pass@word#123!");

        when(loginService.login("test_user-123", "pass@word#123!")).thenReturn(loginResponseDTO);

        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(specialRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void testLogin_WithLongCredentials() throws Exception {
        // Arrange
        String longUsername = "very_long_username_that_might_be_used_in_real_world_scenarios";
        String longPassword = "very_long_password_with_many_characters_and_symbols_123!@#$%^&*()";
        
        LoginRequestDTO longRequest = new LoginRequestDTO();
        longRequest.setUsername(longUsername);
        longRequest.setPassword(longPassword);

        when(loginService.login(longUsername, longPassword)).thenReturn(loginResponseDTO);

        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(longRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void testLogin_WithUnicodeCharacters() throws Exception {
        // Arrange
        LoginRequestDTO unicodeRequest = new LoginRequestDTO();
        unicodeRequest.setUsername("testuser_émojis_🚀");
        unicodeRequest.setPassword("pass@word_émojis_🚀_123");

        when(loginService.login("testuser_émojis_🚀", "pass@word_émojis_🚀_123")).thenReturn(loginResponseDTO);

        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(unicodeRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void testLogin_WithWhitespace() throws Exception {
        // Arrange
        LoginRequestDTO whitespaceRequest = new LoginRequestDTO();
        whitespaceRequest.setUsername("  testuser  ");
        whitespaceRequest.setPassword("  password123  ");

        when(loginService.login("  testuser  ", "  password123  ")).thenReturn(loginResponseDTO);

        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(whitespaceRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void testLogin_WithInvalidContentType() throws Exception {
        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.TEXT_PLAIN)
                .content("invalid content"))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void testLogin_WithInvalidJson() throws Exception {
        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ invalid json }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testLogin_WithMissingFields() throws Exception {
        // Arrange
        String jsonWithMissingFields = "{}";

        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithMissingFields))
                .andExpect(status().isOk()); // Controller doesn't validate, service handles validation
    }
} 