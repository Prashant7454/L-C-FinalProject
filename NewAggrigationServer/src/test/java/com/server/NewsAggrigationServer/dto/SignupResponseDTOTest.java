package com.server.NewsAggrigationServer.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SignupResponseDTOTest {

    private SignupResponseDTO signupResponseDTO;

    @BeforeEach
    void setUp() {
        signupResponseDTO = new SignupResponseDTO();
    }

    @Test
    void testSignupResponseDTOCreation() {
        assertNotNull(signupResponseDTO);
    }

    @Test
    void testSuccessGetterAndSetter() {
        Boolean expectedSuccess = true;
        signupResponseDTO.setSuccess(expectedSuccess);
        assertEquals(expectedSuccess, signupResponseDTO.getSuccess());
    }

    @Test
    void testMessageGetterAndSetter() {
        String expectedMessage = "Signup successful";
        signupResponseDTO.setMessage(expectedMessage);
        assertEquals(expectedMessage, signupResponseDTO.getMessage());
    }

    @Test
    void testUserIdGetterAndSetter() {
        Integer expectedUserId = 1;
        signupResponseDTO.setUserId(expectedUserId);
        assertEquals(expectedUserId, signupResponseDTO.getUserId());
    }

    @Test
    void testSignupResponseDTOWithAllFields() {
        Boolean success = true;
        String message = "User registered successfully";
        Integer userId = 1;

        signupResponseDTO.setSuccess(success);
        signupResponseDTO.setMessage(message);
        signupResponseDTO.setUserId(userId);

        assertEquals(success, signupResponseDTO.getSuccess());
        assertEquals(message, signupResponseDTO.getMessage());
        assertEquals(userId, signupResponseDTO.getUserId());
    }

    @Test
    void testSignupResponseDTOWithNullValues() {
        signupResponseDTO.setSuccess(null);
        signupResponseDTO.setMessage(null);
        signupResponseDTO.setUserId(null);

        assertNull(signupResponseDTO.getSuccess());
        assertNull(signupResponseDTO.getMessage());
        assertNull(signupResponseDTO.getUserId());
    }

    @Test
    void testSignupResponseDTOWithEmptyString() {
        signupResponseDTO.setMessage("");
        assertEquals("", signupResponseDTO.getMessage());
    }

    @Test
    void testSignupResponseDTOWithZeroValue() {
        signupResponseDTO.setUserId(0);
        assertEquals(0, signupResponseDTO.getUserId());
    }

    @Test
    void testSignupResponseDTOWithNegativeValue() {
        signupResponseDTO.setUserId(-1);
        assertEquals(-1, signupResponseDTO.getUserId());
    }

    @Test
    void testSignupResponseDTOWithLargeValue() {
        Integer largeUserId = Integer.MAX_VALUE;
        signupResponseDTO.setUserId(largeUserId);
        assertEquals(largeUserId, signupResponseDTO.getUserId());
    }

    @Test
    void testSignupResponseDTOWithSpecialCharacters() {
        String messageWithSpecialChars = "Signup failed with error @#$%^&*()";
        signupResponseDTO.setMessage(messageWithSpecialChars);
        assertEquals(messageWithSpecialChars, signupResponseDTO.getMessage());
    }

    @Test
    void testSignupResponseDTOWithUnicodeCharacters() {
        String messageWithUnicode = "Signup message with émojis 🚀";
        signupResponseDTO.setMessage(messageWithUnicode);
        assertEquals(messageWithUnicode, signupResponseDTO.getMessage());
    }

    @Test
    void testSignupResponseDTOWithLongString() {
        String longMessage = "This is a very long signup response message that contains many characters and should be properly handled by the getter and setter methods without any issues.";
        signupResponseDTO.setMessage(longMessage);
        assertEquals(longMessage, signupResponseDTO.getMessage());
    }

    @Test
    void testSignupResponseDTOWithBooleanValues() {
        // Test true
        signupResponseDTO.setSuccess(true);
        assertEquals(true, signupResponseDTO.getSuccess());

        // Test false
        signupResponseDTO.setSuccess(false);
        assertEquals(false, signupResponseDTO.getSuccess());
    }

    @Test
    void testSignupResponseDTOWithWhitespace() {
        String messageWithWhitespace = "  Signup message  ";
        signupResponseDTO.setMessage(messageWithWhitespace);
        assertEquals(messageWithWhitespace, signupResponseDTO.getMessage());
    }

    @Test
    void testSignupResponseDTOWithNewlines() {
        String messageWithNewlines = "Signup\nmessage";
        signupResponseDTO.setMessage(messageWithNewlines);
        assertEquals(messageWithNewlines, signupResponseDTO.getMessage());
    }

    @Test
    void testSignupResponseDTOWithNumbers() {
        String messageWithNumbers = "User 123 registered successfully";
        signupResponseDTO.setMessage(messageWithNumbers);
        assertEquals(messageWithNumbers, signupResponseDTO.getMessage());
    }

    @Test
    void testSignupResponseDTOWithMixedCase() {
        String messageWithMixedCase = "Signup Message";
        signupResponseDTO.setMessage(messageWithMixedCase);
        assertEquals(messageWithMixedCase, signupResponseDTO.getMessage());
    }

    @Test
    void testSignupResponseDTOWithDifferentMessages() {
        String[] messages = {
            "User registered successfully",
            "Signup failed - username already exists",
            "Invalid email format",
            "Password too short",
            "Account created successfully"
        };

        for (String message : messages) {
            signupResponseDTO.setMessage(message);
            assertEquals(message, signupResponseDTO.getMessage());
        }
    }

    @Test
    void testSignupResponseDTOWithRealisticValues() {
        // Test with realistic values that might be used in a real application
        Integer[] realisticUserIds = {1, 5, 10, 25, 50, 100, 500, 1000};

        for (Integer userId : realisticUserIds) {
            signupResponseDTO.setUserId(userId);
            assertEquals(userId, signupResponseDTO.getUserId());
        }
    }

    @Test
    void testSignupResponseDTOWithSuccessScenarios() {
        // Test successful signup scenarios
        signupResponseDTO.setSuccess(true);
        signupResponseDTO.setMessage("User registered successfully");
        signupResponseDTO.setUserId(1);

        assertEquals(true, signupResponseDTO.getSuccess());
        assertEquals("User registered successfully", signupResponseDTO.getMessage());
        assertEquals(1, signupResponseDTO.getUserId());
    }

    @Test
    void testSignupResponseDTOWithFailureScenarios() {
        // Test failed signup scenarios
        signupResponseDTO.setSuccess(false);
        signupResponseDTO.setMessage("Username already exists");
        signupResponseDTO.setUserId(null);

        assertEquals(false, signupResponseDTO.getSuccess());
        assertEquals("Username already exists", signupResponseDTO.getMessage());
        assertNull(signupResponseDTO.getUserId());
    }
} 