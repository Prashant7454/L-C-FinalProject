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
class UserServiceTest {

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
        testUser.setPassword("encryptedPassword");
        testUser.setRole("USER");

        testUserDTO = new UserDTO();
        testUserDTO.setUsername("testuser");
        testUserDTO.setEmail("test@example.com");
        testUserDTO.setPassword("password123");
        testUserDTO.setRole("USER");
    }

    @Test
    void testCreateUser_Success() {
        // Arrange
        when(encryptionUtil.encrypt(anyString())).thenReturn("encryptedPassword");
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        // Act
        UserDTO result = userService.createUser(testUserDTO);

        // Assert
        assertNotNull(result);
        assertEquals(testUser.getId(), result.getId());
        assertEquals(testUserDTO.getUsername(), result.getUsername());
        assertEquals(testUserDTO.getEmail(), result.getEmail());
        assertEquals(testUserDTO.getRole(), result.getRole());
        
        verify(encryptionUtil).encrypt(testUserDTO.getPassword());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testCreateUser_DuplicateUserException() {
        // Arrange
        when(encryptionUtil.encrypt(anyString())).thenReturn("encryptedPassword");
        when(userRepository.save(any(User.class))).thenThrow(DataIntegrityViolationException.class);

        // Act & Assert
        assertThrows(FoundDuplicateUserNameException.class, () -> {
            userService.createUser(testUserDTO);
        });

        verify(encryptionUtil).encrypt(testUserDTO.getPassword());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testCreateUser_DatabaseException() {
        // Arrange
        when(encryptionUtil.encrypt(anyString())).thenReturn("encryptedPassword");
        when(userRepository.save(any(User.class))).thenThrow(RuntimeException.class);

        // Act & Assert
        assertThrows(DatabaseException.class, () -> {
            userService.createUser(testUserDTO);
        });

        verify(encryptionUtil).encrypt(testUserDTO.getPassword());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testGetAllUsers_Success() {
        // Arrange
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("user1");
        user1.setEmail("user1@example.com");
        user1.setPassword("password1");
        user1.setRole("USER");

        User user2 = new User();
        user2.setId(2);
        user2.setUsername("user2");
        user2.setEmail("user2@example.com");
        user2.setPassword("password2");
        user2.setRole("ADMIN");

        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<UserDTO> result = userService.getAllUsers();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("***", result.get(0).getPassword());
        assertEquals("***", result.get(1).getPassword());
        assertEquals(user1.getUsername(), result.get(0).getUsername());
        assertEquals(user2.getUsername(), result.get(1).getUsername());

        verify(userRepository).findAll();
    }

    @Test
    void testGetAllUsers_EmptyList() {
        // Arrange
        when(userRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<UserDTO> result = userService.getAllUsers();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(userRepository).findAll();
    }

    @Test
    void testGetUserByEmail_Success() {
        // Arrange
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(testUser));

        // Act
        UserDTO result = userService.getUserByEmail("test@example.com");

        // Assert
        assertNotNull(result);
        assertEquals(testUser.getId(), result.getId());
        assertEquals(testUser.getUsername(), result.getUsername());
        assertEquals(testUser.getEmail(), result.getEmail());
        assertEquals(testUser.getPassword(), result.getPassword());
        assertEquals(testUser.getRole(), result.getRole());

        verify(userRepository).findByEmail("test@example.com");
    }

    @Test
    void testGetUserByEmail_UserNotFound() {
        // Arrange
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            userService.getUserByEmail("nonexistent@example.com");
        });

        verify(userRepository).findByEmail("nonexistent@example.com");
    }
} 