package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.LoginRequestDTO;
import com.server.NewsAggrigationServer.dto.LoginResponseDTO;
import com.server.NewsAggrigationServer.model.User;
import com.server.NewsAggrigationServer.repository.UserRepository;
import com.server.NewsAggrigationServer.util.EncryptionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EncryptionUtil encryptionUtil;

    @InjectMocks
    private LoginService loginService;

    private LoginRequestDTO loginRequestDTO;
    private User user;

    @BeforeEach
    void setUp() {
        loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUsername("testuser");
        loginRequestDTO.setPassword("testpassword");

        user = new User();
        user.setId(1);
        user.setUsername("testuser");
        user.setPassword("encryptedpassword");
        user.setEmail("test@example.com");
        user.setIsActive(1);
    }

    @Test
    void testLoginWithValidCredentials() {
        // Arrange
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(encryptionUtil.encrypt("testpassword")).thenReturn("encryptedpassword");

        // Act
        LoginResponseDTO result = loginService.login(loginRequestDTO);

        // Assert
        assertNotNull(result);
        assertTrue(result.getSuccess());
        assertEquals("Login successful", result.getMessage());
        assertEquals(1, result.getUserId());
        assertEquals("testuser", result.getUsername());
        assertEquals("test@example.com", result.getEmail());

        verify(userRepository).findByUsername("testuser");
        verify(encryptionUtil).encrypt("testpassword");
    }

    @Test
    void testLoginWithInvalidUsername() {
        // Arrange
        when(userRepository.findByUsername("nonexistentuser")).thenReturn(Optional.empty());

        loginRequestDTO.setUsername("nonexistentuser");

        // Act
        LoginResponseDTO result = loginService.login(loginRequestDTO);

        // Assert
        assertNotNull(result);
        assertFalse(result.getSuccess());
        assertEquals("Invalid username or password", result.getMessage());
        assertNull(result.getUserId());
        assertNull(result.getUsername());
        assertNull(result.getEmail());

        verify(userRepository).findByUsername("nonexistentuser");
        verify(encryptionUtil, never()).encrypt(anyString());
    }

    @Test
    void testLoginWithInvalidPassword() {
        // Arrange
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(encryptionUtil.encrypt("wrongpassword")).thenReturn("wrongencryptedpassword");

        loginRequestDTO.setPassword("wrongpassword");

        // Act
        LoginResponseDTO result = loginService.login(loginRequestDTO);

        // Assert
        assertNotNull(result);
        assertFalse(result.getSuccess());
        assertEquals("Invalid username or password", result.getMessage());
        assertNull(result.getUserId());
        assertNull(result.getUsername());
        assertNull(result.getEmail());

        verify(userRepository).findByUsername("testuser");
        verify(encryptionUtil).encrypt("wrongpassword");
    }

    @Test
    void testLoginWithInactiveUser() {
        // Arrange
        user.setIsActive(0);
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(encryptionUtil.encrypt("testpassword")).thenReturn("encryptedpassword");

        // Act
        LoginResponseDTO result = loginService.login(loginRequestDTO);

        // Assert
        assertNotNull(result);
        assertFalse(result.getSuccess());
        assertEquals("Account is deactivated", result.getMessage());
        assertNull(result.getUserId());
        assertNull(result.getUsername());
        assertNull(result.getEmail());

        verify(userRepository).findByUsername("testuser");
        verify(encryptionUtil).encrypt("testpassword");
    }

    @Test
    void testLoginWithNullUsername() {
        // Arrange
        loginRequestDTO.setUsername(null);

        // Act
        LoginResponseDTO result = loginService.login(loginRequestDTO);

        // Assert
        assertNotNull(result);
        assertFalse(result.getSuccess());
        assertEquals("Username and password are required", result.getMessage());
        assertNull(result.getUserId());
        assertNull(result.getUsername());
        assertNull(result.getEmail());

        verify(userRepository, never()).findByUsername(anyString());
        verify(encryptionUtil, never()).encrypt(anyString());
    }

    @Test
    void testLoginWithNullPassword() {
        // Arrange
        loginRequestDTO.setPassword(null);

        // Act
        LoginResponseDTO result = loginService.login(loginRequestDTO);

        // Assert
        assertNotNull(result);
        assertFalse(result.getSuccess());
        assertEquals("Username and password are required", result.getMessage());
        assertNull(result.getUserId());
        assertNull(result.getUsername());
        assertNull(result.getEmail());

        verify(userRepository, never()).findByUsername(anyString());
        verify(encryptionUtil, never()).encrypt(anyString());
    }

    @Test
    void testLoginWithEmptyUsername() {
        // Arrange
        loginRequestDTO.setUsername("");

        // Act
        LoginResponseDTO result = loginService.login(loginRequestDTO);

        // Assert
        assertNotNull(result);
        assertFalse(result.getSuccess());
        assertEquals("Username and password are required", result.getMessage());
        assertNull(result.getUserId());
        assertNull(result.getUsername());
        assertNull(result.getEmail());

        verify(userRepository, never()).findByUsername(anyString());
        verify(encryptionUtil, never()).encrypt(anyString());
    }

    @Test
    void testLoginWithEmptyPassword() {
        // Arrange
        loginRequestDTO.setPassword("");

        // Act
        LoginResponseDTO result = loginService.login(loginRequestDTO);

        // Assert
        assertNotNull(result);
        assertFalse(result.getSuccess());
        assertEquals("Username and password are required", result.getMessage());
        assertNull(result.getUserId());
        assertNull(result.getUsername());
        assertNull(result.getEmail());

        verify(userRepository, never()).findByUsername(anyString());
        verify(encryptionUtil, never()).encrypt(anyString());
    }

    @Test
    void testLoginWithWhitespaceUsername() {
        // Arrange
        loginRequestDTO.setUsername("   ");

        // Act
        LoginResponseDTO result = loginService.login(loginRequestDTO);

        // Assert
        assertNotNull(result);
        assertFalse(result.getSuccess());
        assertEquals("Username and password are required", result.getMessage());
        assertNull(result.getUserId());
        assertNull(result.getUsername());
        assertNull(result.getEmail());

        verify(userRepository, never()).findByUsername(anyString());
        verify(encryptionUtil, never()).encrypt(anyString());
    }

    @Test
    void testLoginWithWhitespacePassword() {
        // Arrange
        loginRequestDTO.setPassword("   ");

        // Act
        LoginResponseDTO result = loginService.login(loginRequestDTO);

        // Assert
        assertNotNull(result);
        assertFalse(result.getSuccess());
        assertEquals("Username and password are required", result.getMessage());
        assertNull(result.getUserId());
        assertNull(result.getUsername());
        assertNull(result.getEmail());

        verify(userRepository, never()).findByUsername(anyString());
        verify(encryptionUtil, never()).encrypt(anyString());
    }

    @Test
    void testLoginWithSpecialCharacters() {
        // Arrange
        loginRequestDTO.setUsername("user@123");
        loginRequestDTO.setPassword("pass@word#123");

        user.setUsername("user@123");
        user.setPassword("encryptedspecialpassword");

        when(userRepository.findByUsername("user@123")).thenReturn(Optional.of(user));
        when(encryptionUtil.encrypt("pass@word#123")).thenReturn("encryptedspecialpassword");

        // Act
        LoginResponseDTO result = loginService.login(loginRequestDTO);

        // Assert
        assertNotNull(result);
        assertTrue(result.getSuccess());
        assertEquals("Login successful", result.getMessage());
        assertEquals(1, result.getUserId());
        assertEquals("user@123", result.getUsername());

        verify(userRepository).findByUsername("user@123");
        verify(encryptionUtil).encrypt("pass@word#123");
    }

    @Test
    void testLoginWithLongCredentials() {
        // Arrange
        String longUsername = "verylongusername" + "a".repeat(100);
        String longPassword = "verylongpassword" + "b".repeat(100);

        loginRequestDTO.setUsername(longUsername);
        loginRequestDTO.setPassword(longPassword);

        user.setUsername(longUsername);
        user.setPassword("encryptedlongpassword");

        when(userRepository.findByUsername(longUsername)).thenReturn(Optional.of(user));
        when(encryptionUtil.encrypt(longPassword)).thenReturn("encryptedlongpassword");

        // Act
        LoginResponseDTO result = loginService.login(loginRequestDTO);

        // Assert
        assertNotNull(result);
        assertTrue(result.getSuccess());
        assertEquals("Login successful", result.getMessage());
        assertEquals(1, result.getUserId());
        assertEquals(longUsername, result.getUsername());

        verify(userRepository).findByUsername(longUsername);
        verify(encryptionUtil).encrypt(longPassword);
    }

    @Test
    void testLoginWithUnicodeCharacters() {
        // Arrange
        loginRequestDTO.setUsername("userémojis🚀");
        loginRequestDTO.setPassword("passémojis🚀");

        user.setUsername("userémojis🚀");
        user.setPassword("encryptedunicodepassword");

        when(userRepository.findByUsername("userémojis🚀")).thenReturn(Optional.of(user));
        when(encryptionUtil.encrypt("passémojis🚀")).thenReturn("encryptedunicodepassword");

        // Act
        LoginResponseDTO result = loginService.login(loginRequestDTO);

        // Assert
        assertNotNull(result);
        assertTrue(result.getSuccess());
        assertEquals("Login successful", result.getMessage());
        assertEquals(1, result.getUserId());
        assertEquals("userémojis🚀", result.getUsername());

        verify(userRepository).findByUsername("userémojis🚀");
        verify(encryptionUtil).encrypt("passémojis🚀");
    }

    @Test
    void testLoginWithRepositoryException() {
        // Arrange
        when(userRepository.findByUsername("testuser")).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            loginService.login(loginRequestDTO);
        });

        verify(userRepository).findByUsername("testuser");
        verify(encryptionUtil, never()).encrypt(anyString());
    }

    @Test
    void testLoginWithEncryptionException() {
        // Arrange
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(encryptionUtil.encrypt("testpassword")).thenThrow(new RuntimeException("Encryption error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            loginService.login(loginRequestDTO);
        });

        verify(userRepository).findByUsername("testuser");
        verify(encryptionUtil).encrypt("testpassword");
    }

    @Test
    void testLoginWithNullUserFields() {
        // Arrange
        user.setEmail(null);
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(encryptionUtil.encrypt("testpassword")).thenReturn("encryptedpassword");

        // Act
        LoginResponseDTO result = loginService.login(loginRequestDTO);

        // Assert
        assertNotNull(result);
        assertTrue(result.getSuccess());
        assertEquals("Login successful", result.getMessage());
        assertEquals(1, result.getUserId());
        assertEquals("testuser", result.getUsername());
        assertNull(result.getEmail());

        verify(userRepository).findByUsername("testuser");
        verify(encryptionUtil).encrypt("testpassword");
    }
} 