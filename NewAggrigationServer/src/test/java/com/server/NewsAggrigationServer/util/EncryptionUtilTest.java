package com.server.NewsAggrigationServer.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EncryptionUtilTest {

    private EncryptionUtil encryptionUtil;

    @BeforeEach
    void setUp() {
        encryptionUtil = new EncryptionUtil();
    }

    @Test
    void testEncryptionUtilCreation() {
        assertNotNull(encryptionUtil);
    }

    @Test
    void testEncryptSimpleString() {
        String raw = "password123";
        String encrypted = encryptionUtil.encrypt(raw);
        
        assertNotNull(encrypted);
        assertNotEquals(raw, encrypted);
        assertTrue(encrypted.length() > 0);
    }

    @Test
    void testDecryptSimpleString() {
        String raw = "password123";
        String encrypted = encryptionUtil.encrypt(raw);
        String decrypted = encryptionUtil.decrypt(encrypted);
        
        assertEquals(raw, decrypted);
    }

    @Test
    void testEncryptAndDecryptEmptyString() {
        String raw = "";
        String encrypted = encryptionUtil.encrypt(raw);
        String decrypted = encryptionUtil.decrypt(encrypted);
        
        assertEquals(raw, decrypted);
    }

    @Test
    void testEncryptAndDecryptSpecialCharacters() {
        String raw = "pass@word#123!";
        String encrypted = encryptionUtil.encrypt(raw);
        String decrypted = encryptionUtil.decrypt(encrypted);
        
        assertEquals(raw, decrypted);
    }

    @Test
    void testEncryptAndDecryptUnicodeCharacters() {
        String raw = "password with émojis 🚀 and symbols ©®™";
        String encrypted = encryptionUtil.encrypt(raw);
        String decrypted = encryptionUtil.decrypt(encrypted);
        
        assertEquals(raw, decrypted);
    }

    @Test
    void testEncryptAndDecryptLongString() {
        String raw = "This is a very long password that contains many characters and should be properly encrypted and decrypted without any issues. It includes numbers 123, symbols @#$%, and spaces.";
        String encrypted = encryptionUtil.encrypt(raw);
        String decrypted = encryptionUtil.decrypt(encrypted);
        
        assertEquals(raw, decrypted);
    }

    @Test
    void testEncryptAndDecryptNumbers() {
        String raw = "1234567890";
        String encrypted = encryptionUtil.encrypt(raw);
        String decrypted = encryptionUtil.decrypt(encrypted);
        
        assertEquals(raw, decrypted);
    }

    @Test
    void testEncryptAndDecryptMixedCase() {
        String raw = "PaSsWoRd123";
        String encrypted = encryptionUtil.encrypt(raw);
        String decrypted = encryptionUtil.decrypt(encrypted);
        
        assertEquals(raw, decrypted);
    }

    @Test
    void testEncryptNullString() {
        assertThrows(NullPointerException.class, () -> {
            encryptionUtil.encrypt(null);
        });
    }

    @Test
    void testDecryptNullString() {
        assertThrows(NullPointerException.class, () -> {
            encryptionUtil.decrypt(null);
        });
    }

    @Test
    void testDecryptInvalidBase64() {
        assertThrows(IllegalArgumentException.class, () -> {
            encryptionUtil.decrypt("invalid-base64-string!");
        });
    }

    @Test
    void testEncryptAndDecryptMultipleTimes() {
        String raw = "testpassword";
        
        // Encrypt and decrypt multiple times
        String encrypted1 = encryptionUtil.encrypt(raw);
        String decrypted1 = encryptionUtil.decrypt(encrypted1);
        
        String encrypted2 = encryptionUtil.encrypt(raw);
        String decrypted2 = encryptionUtil.decrypt(encrypted2);
        
        assertEquals(raw, decrypted1);
        assertEquals(raw, decrypted2);
        assertEquals(encrypted1, encrypted2); // Same input should produce same output
    }

    @Test
    void testEncryptAndDecryptWhitespace() {
        String raw = "   password with spaces   ";
        String encrypted = encryptionUtil.encrypt(raw);
        String decrypted = encryptionUtil.decrypt(encrypted);
        
        assertEquals(raw, decrypted);
    }

    @Test
    void testEncryptAndDecryptNewlines() {
        String raw = "password\nwith\nnewlines";
        String encrypted = encryptionUtil.encrypt(raw);
        String decrypted = encryptionUtil.decrypt(encrypted);
        
        assertEquals(raw, decrypted);
    }
} 