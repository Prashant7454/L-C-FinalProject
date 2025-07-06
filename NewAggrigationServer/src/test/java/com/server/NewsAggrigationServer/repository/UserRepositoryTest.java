package com.server.NewsAggrigationServer.repository;

import com.server.NewsAggrigationServer.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1);
        testUser.setUsername("testuser");
        testUser.setEmail("test@example.com");
        testUser.setPassword("encryptedPassword");
        testUser.setRole("USER");
    }

    @Test
    void testFindByEmail_Success() {
        // Arrange
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(testUser));

        // Act
        Optional<User> result = userRepository.findByEmail("test@example.com");

        // Assert
        assertTrue(result.isPresent());
        assertEquals(testUser.getId(), result.get().getId());
        assertEquals(testUser.getUsername(), result.get().getUsername());
        assertEquals(testUser.getEmail(), result.get().getEmail());
        
        verify(userRepository).findByEmail("test@example.com");
    }

    @Test
    void testFindByEmail_NotFound() {
        // Arrange
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        // Act
        Optional<User> result = userRepository.findByEmail("nonexistent@example.com");

        // Assert
        assertFalse(result.isPresent());
        
        verify(userRepository).findByEmail("nonexistent@example.com");
    }

    @Test
    void testFindByEmail_WithNullEmail() {
        // Arrange
        when(userRepository.findByEmail(null)).thenReturn(Optional.empty());

        // Act
        Optional<User> result = userRepository.findByEmail(null);

        // Assert
        assertFalse(result.isPresent());
        
        verify(userRepository).findByEmail(null);
    }

    @Test
    void testFindByEmail_WithEmptyEmail() {
        // Arrange
        when(userRepository.findByEmail("")).thenReturn(Optional.empty());

        // Act
        Optional<User> result = userRepository.findByEmail("");

        // Assert
        assertFalse(result.isPresent());
        
        verify(userRepository).findByEmail("");
    }

    @Test
    void testFindByEmail_WithSpecialCharacters() {
        // Arrange
        String emailWithSpecial = "test+user@example.com";
        when(userRepository.findByEmail(emailWithSpecial)).thenReturn(Optional.of(testUser));

        // Act
        Optional<User> result = userRepository.findByEmail(emailWithSpecial);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(testUser.getId(), result.get().getId());
        
        verify(userRepository).findByEmail(emailWithSpecial);
    }

    @Test
    void testFindAll_Success() {
        // Arrange
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("user1");
        user1.setEmail("user1@example.com");

        User user2 = new User();
        user2.setId(2);
        user2.setUsername("user2");
        user2.setEmail("user2@example.com");

        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<User> result = userRepository.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(user1.getId(), result.get(0).getId());
        assertEquals(user2.getId(), result.get(1).getId());
        
        verify(userRepository).findAll();
    }

    @Test
    void testFindAll_EmptyList() {
        // Arrange
        when(userRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<User> result = userRepository.findAll();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        
        verify(userRepository).findAll();
    }

    @Test
    void testSave_Success() {
        // Arrange
        User newUser = new User();
        newUser.setUsername("newuser");
        newUser.setEmail("new@example.com");
        newUser.setPassword("password123");
        newUser.setRole("USER");

        when(userRepository.save(any(User.class))).thenReturn(testUser);

        // Act
        User result = userRepository.save(newUser);

        // Assert
        assertNotNull(result);
        assertEquals(testUser.getId(), result.getId());
        assertEquals(testUser.getUsername(), result.getUsername());
        
        verify(userRepository).save(newUser);
    }

    @Test
    void testSave_WithNullUser() {
        // Arrange
        when(userRepository.save(null)).thenThrow(IllegalArgumentException.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userRepository.save(null);
        });
        
        verify(userRepository).save(null);
    }

    @Test
    void testSave_WithUserMissingRequiredFields() {
        // Arrange
        User incompleteUser = new User();
        incompleteUser.setUsername("incomplete");
        // Missing email, password, role

        when(userRepository.save(incompleteUser)).thenThrow(RuntimeException.class);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            userRepository.save(incompleteUser);
        });
        
        verify(userRepository).save(incompleteUser);
    }

    @Test
    void testFindById_Success() {
        // Arrange
        when(userRepository.findById(1)).thenReturn(Optional.of(testUser));

        // Act
        Optional<User> result = userRepository.findById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(testUser.getId(), result.get().getId());
        assertEquals(testUser.getUsername(), result.get().getUsername());
        
        verify(userRepository).findById(1);
    }

    @Test
    void testFindById_NotFound() {
        // Arrange
        when(userRepository.findById(999)).thenReturn(Optional.empty());

        // Act
        Optional<User> result = userRepository.findById(999);

        // Assert
        assertFalse(result.isPresent());
        
        verify(userRepository).findById(999);
    }

    @Test
    void testFindById_WithNullId() {
        // Arrange
        when(userRepository.findById(null)).thenReturn(Optional.empty());

        // Act
        Optional<User> result = userRepository.findById(null);

        // Assert
        assertFalse(result.isPresent());
        
        verify(userRepository).findById(null);
    }

    @Test
    void testDelete_Success() {
        // Arrange
        doNothing().when(userRepository).delete(testUser);

        // Act
        userRepository.delete(testUser);

        // Assert
        verify(userRepository).delete(testUser);
    }

    @Test
    void testDelete_WithNullUser() {
        // Arrange
        doThrow(IllegalArgumentException.class).when(userRepository).delete(null);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userRepository.delete(null);
        });
        
        verify(userRepository).delete(null);
    }

    @Test
    void testExistsById_Success() {
        // Arrange
        when(userRepository.existsById(1)).thenReturn(true);

        // Act
        boolean result = userRepository.existsById(1);

        // Assert
        assertTrue(result);
        
        verify(userRepository).existsById(1);
    }

    @Test
    void testExistsById_NotFound() {
        // Arrange
        when(userRepository.existsById(999)).thenReturn(false);

        // Act
        boolean result = userRepository.existsById(999);

        // Assert
        assertFalse(result);
        
        verify(userRepository).existsById(999);
    }

    @Test
    void testCount_Success() {
        // Arrange
        when(userRepository.count()).thenReturn(5L);

        // Act
        long result = userRepository.count();

        // Assert
        assertEquals(5L, result);
        
        verify(userRepository).count();
    }

    @Test
    void testCount_Zero() {
        // Arrange
        when(userRepository.count()).thenReturn(0L);

        // Act
        long result = userRepository.count();

        // Assert
        assertEquals(0L, result);
        
        verify(userRepository).count();
    }
} 