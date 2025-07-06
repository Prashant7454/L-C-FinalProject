package com.server.NewsAggrigationServer.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExternalNewsSourceDTOTest {

    private ExternalNewsSourceDTO externalNewsSourceDTO;

    @BeforeEach
    void setUp() {
        externalNewsSourceDTO = new ExternalNewsSourceDTO();
    }

    @Test
    void testExternalNewsSourceDTOCreation() {
        assertNotNull(externalNewsSourceDTO);
    }

    @Test
    void testIdGetterAndSetter() {
        Integer expectedId = 1;
        externalNewsSourceDTO.setId(expectedId);
        assertEquals(expectedId, externalNewsSourceDTO.getId());
    }

    @Test
    void testNameGetterAndSetter() {
        String expectedName = "CNN";
        externalNewsSourceDTO.setName(expectedName);
        assertEquals(expectedName, externalNewsSourceDTO.getName());
    }

    @Test
    void testUrlGetterAndSetter() {
        String expectedUrl = "https://cnn.com";
        externalNewsSourceDTO.setUrl(expectedUrl);
        assertEquals(expectedUrl, externalNewsSourceDTO.getUrl());
    }

    @Test
    void testApiKeyGetterAndSetter() {
        String expectedApiKey = "api_key_123";
        externalNewsSourceDTO.setApiKey(expectedApiKey);
        assertEquals(expectedApiKey, externalNewsSourceDTO.getApiKey());
    }

    @Test
    void testIsActiveGetterAndSetter() {
        Integer expectedIsActive = 1;
        externalNewsSourceDTO.setIsActive(expectedIsActive);
        assertEquals(expectedIsActive, externalNewsSourceDTO.getIsActive());
    }

    @Test
    void testLastFetchDateGetterAndSetter() {
        String expectedLastFetchDate = "2024-01-01";
        externalNewsSourceDTO.setLastFetchDate(expectedLastFetchDate);
        assertEquals(expectedLastFetchDate, externalNewsSourceDTO.getLastFetchDate());
    }

    @Test
    void testFetchIntervalGetterAndSetter() {
        Integer expectedFetchInterval = 3600;
        externalNewsSourceDTO.setFetchInterval(expectedFetchInterval);
        assertEquals(expectedFetchInterval, externalNewsSourceDTO.getFetchInterval());
    }

    @Test
    void testExternalNewsSourceDTOWithAllFields() {
        Integer id = 1;
        String name = "BBC News";
        String url = "https://bbc.com/news";
        String apiKey = "bbc_api_key_123";
        Integer isActive = 1;
        String lastFetchDate = "2024-01-15";
        Integer fetchInterval = 1800;

        externalNewsSourceDTO.setId(id);
        externalNewsSourceDTO.setName(name);
        externalNewsSourceDTO.setUrl(url);
        externalNewsSourceDTO.setApiKey(apiKey);
        externalNewsSourceDTO.setIsActive(isActive);
        externalNewsSourceDTO.setLastFetchDate(lastFetchDate);
        externalNewsSourceDTO.setFetchInterval(fetchInterval);

        assertEquals(id, externalNewsSourceDTO.getId());
        assertEquals(name, externalNewsSourceDTO.getName());
        assertEquals(url, externalNewsSourceDTO.getUrl());
        assertEquals(apiKey, externalNewsSourceDTO.getApiKey());
        assertEquals(isActive, externalNewsSourceDTO.getIsActive());
        assertEquals(lastFetchDate, externalNewsSourceDTO.getLastFetchDate());
        assertEquals(fetchInterval, externalNewsSourceDTO.getFetchInterval());
    }

    @Test
    void testExternalNewsSourceDTOWithNullValues() {
        externalNewsSourceDTO.setId(null);
        externalNewsSourceDTO.setName(null);
        externalNewsSourceDTO.setUrl(null);
        externalNewsSourceDTO.setApiKey(null);
        externalNewsSourceDTO.setIsActive(null);
        externalNewsSourceDTO.setLastFetchDate(null);
        externalNewsSourceDTO.setFetchInterval(null);

        assertNull(externalNewsSourceDTO.getId());
        assertNull(externalNewsSourceDTO.getName());
        assertNull(externalNewsSourceDTO.getUrl());
        assertNull(externalNewsSourceDTO.getApiKey());
        assertNull(externalNewsSourceDTO.getIsActive());
        assertNull(externalNewsSourceDTO.getLastFetchDate());
        assertNull(externalNewsSourceDTO.getFetchInterval());
    }

    @Test
    void testExternalNewsSourceDTOWithEmptyStrings() {
        externalNewsSourceDTO.setName("");
        externalNewsSourceDTO.setUrl("");
        externalNewsSourceDTO.setApiKey("");
        externalNewsSourceDTO.setLastFetchDate("");

        assertEquals("", externalNewsSourceDTO.getName());
        assertEquals("", externalNewsSourceDTO.getUrl());
        assertEquals("", externalNewsSourceDTO.getApiKey());
        assertEquals("", externalNewsSourceDTO.getLastFetchDate());
    }

    @Test
    void testExternalNewsSourceDTOWithZeroValues() {
        externalNewsSourceDTO.setId(0);
        externalNewsSourceDTO.setIsActive(0);
        externalNewsSourceDTO.setFetchInterval(0);

        assertEquals(0, externalNewsSourceDTO.getId());
        assertEquals(0, externalNewsSourceDTO.getIsActive());
        assertEquals(0, externalNewsSourceDTO.getFetchInterval());
    }

    @Test
    void testExternalNewsSourceDTOWithNegativeValues() {
        externalNewsSourceDTO.setId(-1);
        externalNewsSourceDTO.setIsActive(-1);
        externalNewsSourceDTO.setFetchInterval(-1);

        assertEquals(-1, externalNewsSourceDTO.getId());
        assertEquals(-1, externalNewsSourceDTO.getIsActive());
        assertEquals(-1, externalNewsSourceDTO.getFetchInterval());
    }

    @Test
    void testExternalNewsSourceDTOWithLargeValues() {
        Integer largeId = Integer.MAX_VALUE;
        Integer largeIsActive = Integer.MAX_VALUE - 1;
        Integer largeFetchInterval = Integer.MAX_VALUE - 2;

        externalNewsSourceDTO.setId(largeId);
        externalNewsSourceDTO.setIsActive(largeIsActive);
        externalNewsSourceDTO.setFetchInterval(largeFetchInterval);

        assertEquals(largeId, externalNewsSourceDTO.getId());
        assertEquals(largeIsActive, externalNewsSourceDTO.getIsActive());
        assertEquals(largeFetchInterval, externalNewsSourceDTO.getFetchInterval());
    }

    @Test
    void testExternalNewsSourceDTOWithSpecialCharacters() {
        String nameWithSpecialChars = "News@Source#2023";
        String urlWithSpecialChars = "https://example.com/news?param=value&other=123";
        String apiKeyWithSpecialChars = "api@key#123!";
        String dateWithSpecialChars = "2024-12-31T23:59:59";

        externalNewsSourceDTO.setName(nameWithSpecialChars);
        externalNewsSourceDTO.setUrl(urlWithSpecialChars);
        externalNewsSourceDTO.setApiKey(apiKeyWithSpecialChars);
        externalNewsSourceDTO.setLastFetchDate(dateWithSpecialChars);

        assertEquals(nameWithSpecialChars, externalNewsSourceDTO.getName());
        assertEquals(urlWithSpecialChars, externalNewsSourceDTO.getUrl());
        assertEquals(apiKeyWithSpecialChars, externalNewsSourceDTO.getApiKey());
        assertEquals(dateWithSpecialChars, externalNewsSourceDTO.getLastFetchDate());
    }

    @Test
    void testExternalNewsSourceDTOWithUnicodeCharacters() {
        String nameWithUnicode = "News Source with émojis 🚀";
        String urlWithUnicode = "https://example.com/news_émojis_🚀";
        String apiKeyWithUnicode = "api_key_émojis_🚀_123";
        String dateWithUnicode = "2024-01-01_émojis_🚀";

        externalNewsSourceDTO.setName(nameWithUnicode);
        externalNewsSourceDTO.setUrl(urlWithUnicode);
        externalNewsSourceDTO.setApiKey(apiKeyWithUnicode);
        externalNewsSourceDTO.setLastFetchDate(dateWithUnicode);

        assertEquals(nameWithUnicode, externalNewsSourceDTO.getName());
        assertEquals(urlWithUnicode, externalNewsSourceDTO.getUrl());
        assertEquals(apiKeyWithUnicode, externalNewsSourceDTO.getApiKey());
        assertEquals(dateWithUnicode, externalNewsSourceDTO.getLastFetchDate());
    }

    @Test
    void testExternalNewsSourceDTOWithLongStrings() {
        String longName = "Very Long News Source Name That Contains Many Characters And Should Be Properly Handled";
        String longUrl = "https://very-long-domain-name.com/very/long/path/to/news/api/endpoint/with/many/parameters?param1=value1&param2=value2&param3=value3";
        String longApiKey = "very_long_api_key_that_contains_many_characters_and_should_be_properly_handled_with_numbers_123_and_symbols_@#$%";
        String longDate = "2024-12-31T23:59:59.999Z_with_very_long_timestamp_format";

        externalNewsSourceDTO.setName(longName);
        externalNewsSourceDTO.setUrl(longUrl);
        externalNewsSourceDTO.setApiKey(longApiKey);
        externalNewsSourceDTO.setLastFetchDate(longDate);

        assertEquals(longName, externalNewsSourceDTO.getName());
        assertEquals(longUrl, externalNewsSourceDTO.getUrl());
        assertEquals(longApiKey, externalNewsSourceDTO.getApiKey());
        assertEquals(longDate, externalNewsSourceDTO.getLastFetchDate());
    }

    @Test
    void testExternalNewsSourceDTOWithWhitespace() {
        String nameWithWhitespace = "  CNN News  ";
        String urlWithWhitespace = "  https://cnn.com  ";
        String apiKeyWithWhitespace = "  api_key_123  ";
        String dateWithWhitespace = "  2024-01-01  ";

        externalNewsSourceDTO.setName(nameWithWhitespace);
        externalNewsSourceDTO.setUrl(urlWithWhitespace);
        externalNewsSourceDTO.setApiKey(apiKeyWithWhitespace);
        externalNewsSourceDTO.setLastFetchDate(dateWithWhitespace);

        assertEquals(nameWithWhitespace, externalNewsSourceDTO.getName());
        assertEquals(urlWithWhitespace, externalNewsSourceDTO.getUrl());
        assertEquals(apiKeyWithWhitespace, externalNewsSourceDTO.getApiKey());
        assertEquals(dateWithWhitespace, externalNewsSourceDTO.getLastFetchDate());
    }

    @Test
    void testExternalNewsSourceDTOWithNewlines() {
        String nameWithNewlines = "CNN\nNews";
        String urlWithNewlines = "https://cnn.com\nnews";
        String apiKeyWithNewlines = "api\nkey";
        String dateWithNewlines = "2024-01-01\n12:00:00";

        externalNewsSourceDTO.setName(nameWithNewlines);
        externalNewsSourceDTO.setUrl(urlWithNewlines);
        externalNewsSourceDTO.setApiKey(apiKeyWithNewlines);
        externalNewsSourceDTO.setLastFetchDate(dateWithNewlines);

        assertEquals(nameWithNewlines, externalNewsSourceDTO.getName());
        assertEquals(urlWithNewlines, externalNewsSourceDTO.getUrl());
        assertEquals(apiKeyWithNewlines, externalNewsSourceDTO.getApiKey());
        assertEquals(dateWithNewlines, externalNewsSourceDTO.getLastFetchDate());
    }

    @Test
    void testExternalNewsSourceDTOWithNumbers() {
        String nameWithNumbers = "News123";
        String urlWithNumbers = "https://news123.com";
        String apiKeyWithNumbers = "api_key_123456";
        String dateWithNumbers = "2024-01-01_123";

        externalNewsSourceDTO.setName(nameWithNumbers);
        externalNewsSourceDTO.setUrl(urlWithNumbers);
        externalNewsSourceDTO.setApiKey(apiKeyWithNumbers);
        externalNewsSourceDTO.setLastFetchDate(dateWithNumbers);

        assertEquals(nameWithNumbers, externalNewsSourceDTO.getName());
        assertEquals(urlWithNumbers, externalNewsSourceDTO.getUrl());
        assertEquals(apiKeyWithNumbers, externalNewsSourceDTO.getApiKey());
        assertEquals(dateWithNumbers, externalNewsSourceDTO.getLastFetchDate());
    }

    @Test
    void testExternalNewsSourceDTOWithMixedCase() {
        String nameWithMixedCase = "CNN News";
        String urlWithMixedCase = "https://CNN.com/News";
        String apiKeyWithMixedCase = "API_Key_123";
        String dateWithMixedCase = "2024-01-01_AM";

        externalNewsSourceDTO.setName(nameWithMixedCase);
        externalNewsSourceDTO.setUrl(urlWithMixedCase);
        externalNewsSourceDTO.setApiKey(apiKeyWithMixedCase);
        externalNewsSourceDTO.setLastFetchDate(dateWithMixedCase);

        assertEquals(nameWithMixedCase, externalNewsSourceDTO.getName());
        assertEquals(urlWithMixedCase, externalNewsSourceDTO.getUrl());
        assertEquals(apiKeyWithMixedCase, externalNewsSourceDTO.getApiKey());
        assertEquals(dateWithMixedCase, externalNewsSourceDTO.getLastFetchDate());
    }

    @Test
    void testExternalNewsSourceDTOWithActiveStatus() {
        // Test active (1)
        externalNewsSourceDTO.setIsActive(1);
        assertEquals(1, externalNewsSourceDTO.getIsActive());

        // Test inactive (0)
        externalNewsSourceDTO.setIsActive(0);
        assertEquals(0, externalNewsSourceDTO.getIsActive());
    }

    @Test
    void testExternalNewsSourceDTOWithDifferentFetchIntervals() {
        Integer[] fetchIntervals = {300, 600, 900, 1800, 3600, 7200, 86400};

        for (Integer interval : fetchIntervals) {
            externalNewsSourceDTO.setFetchInterval(interval);
            assertEquals(interval, externalNewsSourceDTO.getFetchInterval());
        }
    }

    @Test
    void testExternalNewsSourceDTOWithDifferentDateFormats() {
        String[] dateFormats = {
            "2024-01-01",
            "2024-12-31",
            "2024-02-29",
            "2023-06-15",
            "2025-03-08",
            "2024-01-01T12:00:00Z",
            "2024-12-31T23:59:59.999Z"
        };

        for (String date : dateFormats) {
            externalNewsSourceDTO.setLastFetchDate(date);
            assertEquals(date, externalNewsSourceDTO.getLastFetchDate());
        }
    }

    @Test
    void testExternalNewsSourceDTOWithRealisticValues() {
        // Test with realistic values that might be used in a real application
        Integer[] realisticIds = {1, 5, 10, 25, 50, 100, 500, 1000};

        for (Integer id : realisticIds) {
            externalNewsSourceDTO.setId(id);
            externalNewsSourceDTO.setIsActive(id % 2); // Alternate between 0 and 1
            externalNewsSourceDTO.setFetchInterval(id * 60); // Convert to seconds

            assertEquals(id, externalNewsSourceDTO.getId());
            assertEquals(id % 2, externalNewsSourceDTO.getIsActive());
            assertEquals(id * 60, externalNewsSourceDTO.getFetchInterval());
        }
    }

    @Test
    void testExternalNewsSourceDTOWithBoundaryValues() {
        // Test minimum values
        externalNewsSourceDTO.setId(0);
        externalNewsSourceDTO.setIsActive(0);
        externalNewsSourceDTO.setFetchInterval(0);

        assertEquals(0, externalNewsSourceDTO.getId());
        assertEquals(0, externalNewsSourceDTO.getIsActive());
        assertEquals(0, externalNewsSourceDTO.getFetchInterval());

        // Test maximum values
        externalNewsSourceDTO.setId(Integer.MAX_VALUE);
        externalNewsSourceDTO.setIsActive(Integer.MAX_VALUE);
        externalNewsSourceDTO.setFetchInterval(Integer.MAX_VALUE);

        assertEquals(Integer.MAX_VALUE, externalNewsSourceDTO.getId());
        assertEquals(Integer.MAX_VALUE, externalNewsSourceDTO.getIsActive());
        assertEquals(Integer.MAX_VALUE, externalNewsSourceDTO.getFetchInterval());
    }
} 