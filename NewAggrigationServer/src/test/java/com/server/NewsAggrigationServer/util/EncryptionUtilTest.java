package com.server.NewsAggrigationServer.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EncryptionUtilTest {
    @Test
    void testEncryptAndDecrypt() {
        EncryptionUtil util = new EncryptionUtil();
        String raw = "mySecret123";
        String encrypted = util.encrypt(raw);
        assertNotNull(encrypted);
        assertNotEquals(raw, encrypted);
        String decrypted = util.decrypt(encrypted);
        assertEquals(raw, decrypted);
    }

    @Test
    void testDecryptInvalidBase64() {
        EncryptionUtil util = new EncryptionUtil();
        assertThrows(IllegalArgumentException.class, () -> util.decrypt("not_base64"));
    }
}
