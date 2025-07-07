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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void createUser_Success() throws Exception {
        UserDTO inputDto = new UserDTO();
        inputDto.setUsername("testuser");
        inputDto.setEmail("test@example.com");
        inputDto.setPassword("password");
        inputDto.setRole("USER");

        UserDTO expectedDto = new UserDTO();
        expectedDto.setId(1);
        expectedDto.setUsername("testuser");
        expectedDto.setEmail("test@example.com");
        expectedDto.setPassword("***");
        expectedDto.setRole("USER");

        when(userService.createUser(any(UserDTO.class))).thenReturn(expectedDto);

        mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.role").value("USER"));

        verify(userService, times(1)).createUser(any(UserDTO.class));
    }

    @Test
    void getAllUsers_Success() throws Exception {
        List<UserDTO> expectedUsers = Arrays.asList(
                createUserDTO(1, "user1", "user1@example.com", "***", "USER"),
                createUserDTO(2, "user2", "user2@example.com", "***", "ADMIN")
        );

        when(userService.getAllUsers()).thenReturn(expectedUsers);

        mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].username").value("user1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].username").value("user2"));

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void getUserByEmail_Success() throws Exception {
        String email = "user1@example.com";
        UserDTO expectedDto = createUserDTO(1, "user1", email, "***", "USER");

        when(userService.getUserByEmail(email)).thenReturn(expectedDto);

        mockMvc.perform(get("/api/user/email/{email}", email))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("user1"))
                .andExpect(jsonPath("$.email").value(email));

        verify(userService, times(1)).getUserByEmail(email);
    }

    private UserDTO createUserDTO(Integer id, String username, String email, String password, String role) {
        UserDTO dto = new UserDTO();
        dto.setId(id);
        dto.setUsername(username);
        dto.setEmail(email);
        dto.setPassword(password);
        dto.setRole(role);
        return dto;
    }
} 