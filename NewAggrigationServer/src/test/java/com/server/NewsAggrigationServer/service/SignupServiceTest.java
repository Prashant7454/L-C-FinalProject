package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.SignupRequestDTO;
import com.server.NewsAggrigationServer.dto.SignupResponseDTO;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SignupServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EncryptionUtil encryptionUtil;

    @InjectMocks
    private SignupService signupService;

    private SignupRequestDTO signupRequestDTO;
    private User savedUser;

    @BeforeEach
    void setUp() {
        signupRequestDTO = new SignupRequestDTO();
        signupRequestDTO.setUsername("newuser");
        signupRequestDTO.setPassword("newpassword");
        signupRequestDTO.setEmail("newuser@example.com");

        savedUser = new User();
        savedUser.setId(1);
        savedUser.setUsername("newuser");
        savedUser.setPassword("encryptedpassword");
        savedUser.setEmail("newuser@example.com");
        savedUser.setIsActive(1);
    }

    @Test
    void testSignupWithValidData() {
        // Arrange
        when(userRepository.findByUsername("newuser")).thenReturn(Optional.empty());
        when(userRepository.findByEmail("newuser@example.com")).thenReturn(Optional.empty());
        when(encryptionUtil.encrypt("newpassword")).thenReturn("encryptedpassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Act
        SignupResponseDTO result = signupService.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertTrue(result.getSuccess());
        assertEquals("User registered successfully", result.getMessage());
        assertEquals(1, result.getUserId());

        verify(userRepository).findByUsername("newuser");
        verify(userRepository).findByEmail("newuser@example.com");
        verify(encryptionUtil).encrypt("newpassword");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testSignupWithExistingUsername() {
        // Arrange
        when(userRepository.findByUsername("existinguser")).thenReturn(Optional.of(savedUser));

        signupRequestDTO.setUsername("existinguser");

        // Act
        SignupResponseDTO result = signupService.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertFalse(result.getSuccess());
        assertEquals("Username already exists", result.getMessage());
        assertNull(result.getUserId());

        verify(userRepository).findByUsername("existinguser");
        verify(userRepository, never()).findByEmail(anyString());
        verify(encryptionUtil, never()).encrypt(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testSignupWithExistingEmail() {
        // Arrange
        when(userRepository.findByUsername("newuser")).thenReturn(Optional.empty());
        when(userRepository.findByEmail("existing@example.com")).thenReturn(Optional.of(savedUser));

        signupRequestDTO.setEmail("existing@example.com");

        // Act
        SignupResponseDTO result = signupService.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertFalse(result.getSuccess());
        assertEquals("Email already exists", result.getMessage());
        assertNull(result.getUserId());

        verify(userRepository).findByUsername("newuser");
        verify(userRepository).findByEmail("existing@example.com");
        verify(encryptionUtil, never()).encrypt(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testSignupWithNullUsername() {
        // Arrange
        signupRequestDTO.setUsername(null);

        // Act
        SignupResponseDTO result = signupService.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertFalse(result.getSuccess());
        assertEquals("Username, password, and email are required", result.getMessage());
        assertNull(result.getUserId());

        verify(userRepository, never()).findByUsername(anyString());
        verify(userRepository, never()).findByEmail(anyString());
        verify(encryptionUtil, never()).encrypt(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testSignupWithNullPassword() {
        // Arrange
        signupRequestDTO.setPassword(null);

        // Act
        SignupResponseDTO result = signupService.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertFalse(result.getSuccess());
        assertEquals("Username, password, and email are required", result.getMessage());
        assertNull(result.getUserId());

        verify(userRepository, never()).findByUsername(anyString());
        verify(userRepository, never()).findByEmail(anyString());
        verify(encryptionUtil, never()).encrypt(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testSignupWithNullEmail() {
        // Arrange
        signupRequestDTO.setEmail(null);

        // Act
        SignupResponseDTO result = signupService.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertFalse(result.getSuccess());
        assertEquals("Username, password, and email are required", result.getMessage());
        assertNull(result.getUserId());

        verify(userRepository, never()).findByUsername(anyString());
        verify(userRepository, never()).findByEmail(anyString());
        verify(encryptionUtil, never()).encrypt(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testSignupWithEmptyUsername() {
        // Arrange
        signupRequestDTO.setUsername("");

        // Act
        SignupResponseDTO result = signupService.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertFalse(result.getSuccess());
        assertEquals("Username, password, and email are required", result.getMessage());
        assertNull(result.getUserId());

        verify(userRepository, never()).findByUsername(anyString());
        verify(userRepository, never()).findByEmail(anyString());
        verify(encryptionUtil, never()).encrypt(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testSignupWithEmptyPassword() {
        // Arrange
        signupRequestDTO.setPassword("");

        // Act
        SignupResponseDTO result = signupService.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertFalse(result.getSuccess());
        assertEquals("Username, password, and email are required", result.getMessage());
        assertNull(result.getUserId());

        verify(userRepository, never()).findByUsername(anyString());
        verify(userRepository, never()).findByEmail(anyString());
        verify(encryptionUtil, never()).encrypt(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testSignupWithEmptyEmail() {
        // Arrange
        signupRequestDTO.setEmail("");

        // Act
        SignupResponseDTO result = signupService.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertFalse(result.getSuccess());
        assertEquals("Username, password, and email are required", result.getMessage());
        assertNull(result.getUserId());

        verify(userRepository, never()).findByUsername(anyString());
        verify(userRepository, never()).findByEmail(anyString());
        verify(encryptionUtil, never()).encrypt(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testSignupWithWhitespaceUsername() {
        // Arrange
        signupRequestDTO.setUsername("   ");

        // Act
        SignupResponseDTO result = signupService.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertFalse(result.getSuccess());
        assertEquals("Username, password, and email are required", result.getMessage());
        assertNull(result.getUserId());

        verify(userRepository, never()).findByUsername(anyString());
        verify(userRepository, never()).findByEmail(anyString());
        verify(encryptionUtil, never()).encrypt(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testSignupWithWhitespacePassword() {
        // Arrange
        signupRequestDTO.setPassword("   ");

        // Act
        SignupResponseDTO result = signupService.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertFalse(result.getSuccess());
        assertEquals("Username, password, and email are required", result.getMessage());
        assertNull(result.getUserId());

        verify(userRepository, never()).findByUsername(anyString());
        verify(userRepository, never()).findByEmail(anyString());
        verify(encryptionUtil, never()).encrypt(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testSignupWithWhitespaceEmail() {
        // Arrange
        signupRequestDTO.setEmail("   ");

        // Act
        SignupResponseDTO result = signupService.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertFalse(result.getSuccess());
        assertEquals("Username, password, and email are required", result.getMessage());
        assertNull(result.getUserId());

        verify(userRepository, never()).findByUsername(anyString());
        verify(userRepository, never()).findByEmail(anyString());
        verify(encryptionUtil, never()).encrypt(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testSignupWithSpecialCharacters() {
        // Arrange
        signupRequestDTO.setUsername("user@123");
        signupRequestDTO.setPassword("pass@word#123");
        signupRequestDTO.setEmail("user@123@example.com");

        when(userRepository.findByUsername("user@123")).thenReturn(Optional.empty());
        when(userRepository.findByEmail("user@123@example.com")).thenReturn(Optional.empty());
        when(encryptionUtil.encrypt("pass@word#123")).thenReturn("encryptedspecialpassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Act
        SignupResponseDTO result = signupService.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertTrue(result.getSuccess());
        assertEquals("User registered successfully", result.getMessage());
        assertEquals(1, result.getUserId());

        verify(userRepository).findByUsername("user@123");
        verify(userRepository).findByEmail("user@123@example.com");
        verify(encryptionUtil).encrypt("pass@word#123");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testSignupWithLongCredentials() {
        // Arrange
        String longUsername = "verylongusername" + "a".repeat(100);
        String longPassword = "verylongpassword" + "b".repeat(100);
        String longEmail = "verylongemail" + "c".repeat(100) + "@example.com";

        signupRequestDTO.setUsername(longUsername);
        signupRequestDTO.setPassword(longPassword);
        signupRequestDTO.setEmail(longEmail);

        when(userRepository.findByUsername(longUsername)).thenReturn(Optional.empty());
        when(userRepository.findByEmail(longEmail)).thenReturn(Optional.empty());
        when(encryptionUtil.encrypt(longPassword)).thenReturn("encryptedlongpassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Act
        SignupResponseDTO result = signupService.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertTrue(result.getSuccess());
        assertEquals("User registered successfully", result.getMessage());
        assertEquals(1, result.getUserId());

        verify(userRepository).findByUsername(longUsername);
        verify(userRepository).findByEmail(longEmail);
        verify(encryptionUtil).encrypt(longPassword);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testSignupWithUnicodeCharacters() {
        // Arrange
        signupRequestDTO.setUsername("userémojis🚀");
        signupRequestDTO.setPassword("passémojis🚀");
        signupRequestDTO.setEmail("userémojis🚀@example.com");

        when(userRepository.findByUsername("userémojis🚀")).thenReturn(Optional.empty());
        when(userRepository.findByEmail("userémojis🚀@example.com")).thenReturn(Optional.empty());
        when(encryptionUtil.encrypt("passémojis🚀")).thenReturn("encryptedunicodepassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Act
        SignupResponseDTO result = signupService.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertTrue(result.getSuccess());
        assertEquals("User registered successfully", result.getMessage());
        assertEquals(1, result.getUserId());

        verify(userRepository).findByUsername("userémojis🚀");
        verify(userRepository).findByEmail("userémojis🚀@example.com");
        verify(encryptionUtil).encrypt("passémojis🚀");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testSignupWithRepositoryException() {
        // Arrange
        when(userRepository.findByUsername("newuser")).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            signupService.signup(signupRequestDTO);
        });

        verify(userRepository).findByUsername("newuser");
        verify(userRepository, never()).findByEmail(anyString());
        verify(encryptionUtil, never()).encrypt(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testSignupWithEncryptionException() {
        // Arrange
        when(userRepository.findByUsername("newuser")).thenReturn(Optional.empty());
        when(userRepository.findByEmail("newuser@example.com")).thenReturn(Optional.empty());
        when(encryptionUtil.encrypt("newpassword")).thenThrow(new RuntimeException("Encryption error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            signupService.signup(signupRequestDTO);
        });

        verify(userRepository).findByUsername("newuser");
        verify(userRepository).findByEmail("newuser@example.com");
        verify(encryptionUtil).encrypt("newpassword");
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testSignupWithSaveException() {
        // Arrange
        when(userRepository.findByUsername("newuser")).thenReturn(Optional.empty());
        when(userRepository.findByEmail("newuser@example.com")).thenReturn(Optional.empty());
        when(encryptionUtil.encrypt("newpassword")).thenReturn("encryptedpassword");
        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException("Save error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            signupService.signup(signupRequestDTO);
        });

        verify(userRepository).findByUsername("newuser");
        verify(userRepository).findByEmail("newuser@example.com");
        verify(encryptionUtil).encrypt("newpassword");
        verify(userRepository).save(any(User.class));
    }
} 