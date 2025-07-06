package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.dto.SignupRequestDTO;
import com.server.NewsAggrigationServer.dto.SignupResponseDTO;
import com.server.NewsAggrigationServer.service.SignupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SignupControllerTest {

    @Mock
    private SignupService signupService;

    @InjectMocks
    private SignupController signupController;

    private SignupRequestDTO signupRequestDTO;
    private SignupResponseDTO signupResponseDTO;

    @BeforeEach
    void setUp() {
        signupRequestDTO = new SignupRequestDTO();
        signupRequestDTO.setUsername("testuser");
        signupRequestDTO.setEmail("test@example.com");
        signupRequestDTO.setPassword("password123");
        signupRequestDTO.setFirstName("Test");
        signupRequestDTO.setLastName("User");

        signupResponseDTO = new SignupResponseDTO();
        signupResponseDTO.setId(1);
        signupResponseDTO.setUsername("testuser");
        signupResponseDTO.setEmail("test@example.com");
        signupResponseDTO.setFirstName("Test");
        signupResponseDTO.setLastName("User");
        signupResponseDTO.setMessage("User registered successfully");
    }

    @Test
    void testSignup() {
        // Arrange
        when(signupService.signup(any(SignupRequestDTO.class))).thenReturn(signupResponseDTO);

        // Act
        SignupResponseDTO result = signupController.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("testuser", result.getUsername());
        assertEquals("test@example.com", result.getEmail());
        assertEquals("Test", result.getFirstName());
        assertEquals("User", result.getLastName());
        assertEquals("User registered successfully", result.getMessage());
        verify(signupService).signup(signupRequestDTO);
    }

    @Test
    void testSignupWithNullInput() {
        // Arrange
        when(signupService.signup(null)).thenThrow(new IllegalArgumentException("Signup request cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            signupController.signup(null);
        });
        verify(signupService).signup(null);
    }

    @Test
    void testSignupWithNullUsername() {
        // Arrange
        signupRequestDTO.setUsername(null);
        when(signupService.signup(signupRequestDTO)).thenThrow(new IllegalArgumentException("Username cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            signupController.signup(signupRequestDTO);
        });
        verify(signupService).signup(signupRequestDTO);
    }

    @Test
    void testSignupWithEmptyUsername() {
        // Arrange
        signupRequestDTO.setUsername("");
        when(signupService.signup(signupRequestDTO)).thenThrow(new IllegalArgumentException("Username cannot be empty"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            signupController.signup(signupRequestDTO);
        });
        verify(signupService).signup(signupRequestDTO);
    }

    @Test
    void testSignupWithNullEmail() {
        // Arrange
        signupRequestDTO.setEmail(null);
        when(signupService.signup(signupRequestDTO)).thenThrow(new IllegalArgumentException("Email cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            signupController.signup(signupRequestDTO);
        });
        verify(signupService).signup(signupRequestDTO);
    }

    @Test
    void testSignupWithEmptyEmail() {
        // Arrange
        signupRequestDTO.setEmail("");
        when(signupService.signup(signupRequestDTO)).thenThrow(new IllegalArgumentException("Email cannot be empty"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            signupController.signup(signupRequestDTO);
        });
        verify(signupService).signup(signupRequestDTO);
    }

    @Test
    void testSignupWithNullPassword() {
        // Arrange
        signupRequestDTO.setPassword(null);
        when(signupService.signup(signupRequestDTO)).thenThrow(new IllegalArgumentException("Password cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            signupController.signup(signupRequestDTO);
        });
        verify(signupService).signup(signupRequestDTO);
    }

    @Test
    void testSignupWithEmptyPassword() {
        // Arrange
        signupRequestDTO.setPassword("");
        when(signupService.signup(signupRequestDTO)).thenThrow(new IllegalArgumentException("Password cannot be empty"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            signupController.signup(signupRequestDTO);
        });
        verify(signupService).signup(signupRequestDTO);
    }

    @Test
    void testSignupWithServiceException() {
        // Arrange
        when(signupService.signup(any(SignupRequestDTO.class))).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            signupController.signup(signupRequestDTO);
        });
        verify(signupService).signup(signupRequestDTO);
    }

    @Test
    void testSignupWithSpecialCharacters() {
        // Arrange
        signupRequestDTO.setUsername("test@#$%^&*()");
        signupRequestDTO.setEmail("test@#$%^&*().com");
        signupRequestDTO.setPassword("pass@#$%^&*()");
        signupRequestDTO.setFirstName("Test@#$%^&*()");
        signupRequestDTO.setLastName("User@#$%^&*()");
        when(signupService.signup(signupRequestDTO)).thenReturn(signupResponseDTO);

        // Act
        SignupResponseDTO result = signupController.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(signupService).signup(signupRequestDTO);
    }

    @Test
    void testSignupWithUnicode() {
        // Arrange
        signupRequestDTO.setUsername("test\u00E9\u00F1\u00FC");
        signupRequestDTO.setEmail("test\u00E9\u00F1\u00FC@example.com");
        signupRequestDTO.setPassword("pass\u00E9\u00F1\u00FC");
        signupRequestDTO.setFirstName("Test\u00E9\u00F1\u00FC");
        signupRequestDTO.setLastName("User\u00E9\u00F1\u00FC");
        when(signupService.signup(signupRequestDTO)).thenReturn(signupResponseDTO);

        // Act
        SignupResponseDTO result = signupController.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(signupService).signup(signupRequestDTO);
    }

    @Test
    void testSignupWithLongValues() {
        // Arrange
        String longString = "a".repeat(1000);
        signupRequestDTO.setUsername(longString);
        signupRequestDTO.setEmail(longString + "@example.com");
        signupRequestDTO.setPassword(longString);
        signupRequestDTO.setFirstName(longString);
        signupRequestDTO.setLastName(longString);
        when(signupService.signup(signupRequestDTO)).thenReturn(signupResponseDTO);

        // Act
        SignupResponseDTO result = signupController.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(signupService).signup(signupRequestDTO);
    }

    @Test
    void testSignupWithWhitespaceOnly() {
        // Arrange
        signupRequestDTO.setUsername("   ");
        signupRequestDTO.setEmail("   ");
        signupRequestDTO.setPassword("   ");
        signupRequestDTO.setFirstName("   ");
        signupRequestDTO.setLastName("   ");
        when(signupService.signup(signupRequestDTO)).thenThrow(new IllegalArgumentException("Fields cannot be whitespace only"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            signupController.signup(signupRequestDTO);
        });
        verify(signupService).signup(signupRequestDTO);
    }

    @Test
    void testSignupWithLeadingTrailingWhitespace() {
        // Arrange
        signupRequestDTO.setUsername("  testuser  ");
        signupRequestDTO.setEmail("  test@example.com  ");
        signupRequestDTO.setPassword("  password123  ");
        signupRequestDTO.setFirstName("  Test  ");
        signupRequestDTO.setLastName("  User  ");
        when(signupService.signup(signupRequestDTO)).thenReturn(signupResponseDTO);

        // Act
        SignupResponseDTO result = signupController.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(signupService).signup(signupRequestDTO);
    }

    @Test
    void testSignupWithInvalidEmailFormat() {
        // Arrange
        signupRequestDTO.setEmail("invalid-email");
        when(signupService.signup(signupRequestDTO)).thenThrow(new IllegalArgumentException("Invalid email format"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            signupController.signup(signupRequestDTO);
        });
        verify(signupService).signup(signupRequestDTO);
    }

    @Test
    void testSignupWithWeakPassword() {
        // Arrange
        signupRequestDTO.setPassword("123");
        when(signupService.signup(signupRequestDTO)).thenThrow(new IllegalArgumentException("Password too weak"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            signupController.signup(signupRequestDTO);
        });
        verify(signupService).signup(signupRequestDTO);
    }

    @Test
    void testSignupWithDuplicateUsername() {
        // Arrange
        when(signupService.signup(signupRequestDTO)).thenThrow(new IllegalArgumentException("Username already exists"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            signupController.signup(signupRequestDTO);
        });
        verify(signupService).signup(signupRequestDTO);
    }

    @Test
    void testSignupWithDuplicateEmail() {
        // Arrange
        when(signupService.signup(signupRequestDTO)).thenThrow(new IllegalArgumentException("Email already exists"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            signupController.signup(signupRequestDTO);
        });
        verify(signupService).signup(signupRequestDTO);
    }

    @Test
    void testSignupWithNullFirstName() {
        // Arrange
        signupRequestDTO.setFirstName(null);
        when(signupService.signup(signupRequestDTO)).thenReturn(signupResponseDTO);

        // Act
        SignupResponseDTO result = signupController.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(signupService).signup(signupRequestDTO);
    }

    @Test
    void testSignupWithEmptyFirstName() {
        // Arrange
        signupRequestDTO.setFirstName("");
        when(signupService.signup(signupRequestDTO)).thenReturn(signupResponseDTO);

        // Act
        SignupResponseDTO result = signupController.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(signupService).signup(signupRequestDTO);
    }

    @Test
    void testSignupWithNullLastName() {
        // Arrange
        signupRequestDTO.setLastName(null);
        when(signupService.signup(signupRequestDTO)).thenReturn(signupResponseDTO);

        // Act
        SignupResponseDTO result = signupController.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(signupService).signup(signupRequestDTO);
    }

    @Test
    void testSignupWithEmptyLastName() {
        // Arrange
        signupRequestDTO.setLastName("");
        when(signupService.signup(signupRequestDTO)).thenReturn(signupResponseDTO);

        // Act
        SignupResponseDTO result = signupController.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(signupService).signup(signupRequestDTO);
    }

    @Test
    void testSignupWithZeroId() {
        // Arrange
        signupResponseDTO.setId(0);
        when(signupService.signup(signupRequestDTO)).thenReturn(signupResponseDTO);

        // Act
        SignupResponseDTO result = signupController.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.getId());
        verify(signupService).signup(signupRequestDTO);
    }

    @Test
    void testSignupWithNegativeId() {
        // Arrange
        signupResponseDTO.setId(-1);
        when(signupService.signup(signupRequestDTO)).thenReturn(signupResponseDTO);

        // Act
        SignupResponseDTO result = signupController.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(-1, result.getId());
        verify(signupService).signup(signupRequestDTO);
    }

    @Test
    void testSignupWithLargeId() {
        // Arrange
        signupResponseDTO.setId(Integer.MAX_VALUE);
        when(signupService.signup(signupRequestDTO)).thenReturn(signupResponseDTO);

        // Act
        SignupResponseDTO result = signupController.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(Integer.MAX_VALUE, result.getId());
        verify(signupService).signup(signupRequestDTO);
    }

    @Test
    void testSignupWithNullMessage() {
        // Arrange
        signupResponseDTO.setMessage(null);
        when(signupService.signup(signupRequestDTO)).thenReturn(signupResponseDTO);

        // Act
        SignupResponseDTO result = signupController.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertNull(result.getMessage());
        verify(signupService).signup(signupRequestDTO);
    }

    @Test
    void testSignupWithEmptyMessage() {
        // Arrange
        signupResponseDTO.setMessage("");
        when(signupService.signup(signupRequestDTO)).thenReturn(signupResponseDTO);

        // Act
        SignupResponseDTO result = signupController.signup(signupRequestDTO);

        // Assert
        assertNotNull(result);
        assertEquals("", result.getMessage());
        verify(signupService).signup(signupRequestDTO);
    }
} 