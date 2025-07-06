package com.server.NewsAggrigationServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.NewsAggrigationServer.dto.UserDTO;
import com.server.NewsAggrigationServer.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private UserDTO testUserDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();

        testUserDTO = new UserDTO();
        testUserDTO.setId(1);
        testUserDTO.setUsername("testuser");
        testUserDTO.setEmail("test@example.com");
        testUserDTO.setPassword("password123");
        testUserDTO.setRole("USER");
    }

    @Test
    void testCreateUser_Success() throws Exception {
        // Arrange
        when(userService.createUser(any(UserDTO.class))).thenReturn(testUserDTO);

        // Act & Assert
        mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testUserDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testUserDTO.getId()))
                .andExpect(jsonPath("$.username").value(testUserDTO.getUsername()))
                .andExpect(jsonPath("$.email").value(testUserDTO.getEmail()))
                .andExpect(jsonPath("$.role").value(testUserDTO.getRole()));
    }

    @Test
    void testCreateUser_InvalidRequest() throws Exception {
        // Arrange
        UserDTO invalidUserDTO = new UserDTO();
        // Missing required fields

        // Act & Assert
        mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidUserDTO)))
                .andExpect(status().isOk()); // Controller doesn't validate, service handles validation
    }

    @Test
    void testGetAllUsers_Success() throws Exception {
        // Arrange
        UserDTO user1 = new UserDTO();
        user1.setId(1);
        user1.setUsername("user1");
        user1.setEmail("user1@example.com");
        user1.setRole("USER");

        UserDTO user2 = new UserDTO();
        user2.setId(2);
        user2.setUsername("user2");
        user2.setEmail("user2@example.com");
        user2.setRole("ADMIN");

        List<UserDTO> users = Arrays.asList(user1, user2);
        when(userService.getAllUsers()).thenReturn(users);

        // Act & Assert
        mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(user1.getId()))
                .andExpect(jsonPath("$[0].username").value(user1.getUsername()))
                .andExpect(jsonPath("$[1].id").value(user2.getId()))
                .andExpect(jsonPath("$[1].username").value(user2.getUsername()));
    }

    @Test
    void testGetAllUsers_EmptyList() throws Exception {
        // Arrange
        when(userService.getAllUsers()).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void testGetUserByEmail_Success() throws Exception {
        // Arrange
        when(userService.getUserByEmail("test@example.com")).thenReturn(testUserDTO);

        // Act & Assert
        mockMvc.perform(get("/api/user/email/test@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testUserDTO.getId()))
                .andExpect(jsonPath("$.username").value(testUserDTO.getUsername()))
                .andExpect(jsonPath("$.email").value(testUserDTO.getEmail()))
                .andExpect(jsonPath("$.role").value(testUserDTO.getRole()));
    }

    @Test
    void testGetUserByEmail_WithSpecialCharacters() throws Exception {
        // Arrange
        String emailWithSpecialChars = "test+user@example.com";
        when(userService.getUserByEmail(emailWithSpecialChars)).thenReturn(testUserDTO);

        // Act & Assert
        mockMvc.perform(get("/api/user/email/{email}", emailWithSpecialChars))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(testUserDTO.getEmail()));
    }

    @Test
    void testGetUserByEmail_EmptyEmail() throws Exception {
        // Arrange
        when(userService.getUserByEmail("")).thenReturn(testUserDTO);

        // Act & Assert
        mockMvc.perform(get("/api/user/email/"))
                .andExpect(status().isOk());
    }
} 