package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.UserDTO;
import com.server.NewsAggrigationServer.exception.DatabaseException;
import com.server.NewsAggrigationServer.exception.FoundDuplicateUserNameException;
import com.server.NewsAggrigationServer.exception.ResourceNotFoundException;
import com.server.NewsAggrigationServer.model.User;
import com.server.NewsAggrigationServer.repository.UserRepository;
import com.server.NewsAggrigationServer.service.impl.UserServiceImpl;
import com.server.NewsAggrigationServer.util.EncryptionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EncryptionUtil encryptionUtil;

    @InjectMocks
    private UserServiceImpl userService;

    private User testUser;
    private UserDTO testUserDTO;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1);
        testUser.setUsername("testuser");
        testUser.setEmail("test@example.com");
        testUser.setPassword("encrypted");
        testUser.setRole("USER");

        testUserDTO = new UserDTO();
        testUserDTO.setId(1);
        testUserDTO.setUsername("testuser");
        testUserDTO.setEmail("test@example.com");
        testUserDTO.setPassword("password");
        testUserDTO.setRole("USER");
    }

    @Test
    void createUser_Success() {
        UserDTO inputDto = new UserDTO();
        inputDto.setUsername("testuser");
        inputDto.setEmail("test@example.com");
        inputDto.setPassword("password");
        inputDto.setRole("USER");

        when(encryptionUtil.encrypt("password")).thenReturn("encrypted");
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        UserDTO result = userService.createUser(inputDto);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("testuser", result.getUsername());
        assertEquals("test@example.com", result.getEmail());
        assertEquals("USER", result.getRole());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void createUser_DuplicateUsername_ThrowsException() {
        UserDTO inputDto = new UserDTO();
        inputDto.setUsername("testuser");
        inputDto.setEmail("test@example.com");
        inputDto.setPassword("password");
        inputDto.setRole("USER");

        when(encryptionUtil.encrypt("password")).thenReturn("encrypted");
        when(userRepository.save(any(User.class))).thenThrow(new DataIntegrityViolationException("Duplicate"));

        assertThrows(FoundDuplicateUserNameException.class, () -> userService.createUser(inputDto));
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void createUser_DatabaseError_ThrowsException() {
        UserDTO inputDto = new UserDTO();
        inputDto.setUsername("testuser");
        inputDto.setEmail("test@example.com");
        inputDto.setPassword("password");
        inputDto.setRole("USER");

        when(encryptionUtil.encrypt("password")).thenReturn("encrypted");
        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException("DB error"));

        assertThrows(DatabaseException.class, () -> userService.createUser(inputDto));
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void getAllUsers_Success() {
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("user1");
        user1.setEmail("user1@example.com");
        user1.setPassword("encrypted");
        user1.setRole("USER");

        User user2 = new User();
        user2.setId(2);
        user2.setUsername("user2");
        user2.setEmail("user2@example.com");
        user2.setPassword("encrypted");
        user2.setRole("ADMIN");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<UserDTO> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUsername());
        assertEquals("user2", result.get(1).getUsername());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getUserByEmail_Success() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(testUser));

        UserDTO result = userService.getUserByEmail("test@example.com");

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("testuser", result.getUsername());
        assertEquals("test@example.com", result.getEmail());
        assertEquals("USER", result.getRole());
        verify(userRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void getUserByEmail_NotFound_ThrowsException() {
        when(userRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.getUserByEmail("notfound@example.com"));
        verify(userRepository, times(1)).findByEmail("notfound@example.com");
    }
} 