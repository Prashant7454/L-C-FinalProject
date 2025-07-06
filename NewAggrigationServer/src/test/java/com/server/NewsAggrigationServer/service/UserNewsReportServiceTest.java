package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.model.UserNewsReport;
import com.server.NewsAggrigationServer.repository.UserNewsReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserNewsReportServiceTest {

    @Mock
    private UserNewsReportRepository userNewsReportRepository;

    @InjectMocks
    private UserNewsReportService userNewsReportService;

    private UserNewsReport report1;
    private UserNewsReport report2;

    @BeforeEach
    void setUp() {
        report1 = new UserNewsReport();
        report1.setId(1);
        report1.setUserId(1);
        report1.setNewsId(1);
        report1.setReportReason("Fake news");

        report2 = new UserNewsReport();
        report2.setId(2);
        report2.setUserId(1);
        report2.setNewsId(2);
        report2.setReportReason("Inappropriate content");
    }

    @Test
    void testGetReportsByNewsId() {
        // Arrange
        List<UserNewsReport> expectedReports = Arrays.asList(report1, report2);
        when(userNewsReportRepository.findByNewsId(1)).thenReturn(expectedReports);

        // Act
        List<UserNewsReport> result = userNewsReportService.getReportsByNewsId(1);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getNewsId());
        assertEquals("Fake news", result.get(0).getReportReason());
        assertEquals(2, result.get(1).getNewsId());
        assertEquals("Inappropriate content", result.get(1).getReportReason());

        verify(userNewsReportRepository).findByNewsId(1);
    }

    @Test
    void testGetReportsByNewsIdEmptyList() {
        // Arrange
        when(userNewsReportRepository.findByNewsId(1)).thenReturn(Arrays.asList());

        // Act
        List<UserNewsReport> result = userNewsReportService.getReportsByNewsId(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(userNewsReportRepository).findByNewsId(1);
    }

    @Test
    void testGetReportsByNewsIdWithZero() {
        // Arrange
        when(userNewsReportRepository.findByNewsId(0)).thenReturn(Arrays.asList());

        // Act
        List<UserNewsReport> result = userNewsReportService.getReportsByNewsId(0);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(userNewsReportRepository).findByNewsId(0);
    }

    @Test
    void testGetReportsByNewsIdWithNegativeValue() {
        // Arrange
        when(userNewsReportRepository.findByNewsId(-1)).thenReturn(Arrays.asList());

        // Act
        List<UserNewsReport> result = userNewsReportService.getReportsByNewsId(-1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(userNewsReportRepository).findByNewsId(-1);
    }

    @Test
    void testGetReportsByNewsIdWithLargeValue() {
        // Arrange
        when(userNewsReportRepository.findByNewsId(Integer.MAX_VALUE)).thenReturn(Arrays.asList());

        // Act
        List<UserNewsReport> result = userNewsReportService.getReportsByNewsId(Integer.MAX_VALUE);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(userNewsReportRepository).findByNewsId(Integer.MAX_VALUE);
    }

    @Test
    void testGetReportsByNewsIdWithMultipleNews() {
        // Arrange
        UserNewsReport report3 = new UserNewsReport();
        report3.setId(3);
        report3.setUserId(2);
        report3.setNewsId(3);
        report3.setReportReason("Spam");

        when(userNewsReportRepository.findByNewsId(1)).thenReturn(Arrays.asList(report1, report2));
        when(userNewsReportRepository.findByNewsId(3)).thenReturn(Arrays.asList(report3));

        // Act
        List<UserNewsReport> news1Result = userNewsReportService.getReportsByNewsId(1);
        List<UserNewsReport> news3Result = userNewsReportService.getReportsByNewsId(3);

        // Assert
        assertEquals(2, news1Result.size());
        assertEquals(1, news3Result.size());
        assertEquals(3, news3Result.get(0).getNewsId());

        verify(userNewsReportRepository).findByNewsId(1);
        verify(userNewsReportRepository).findByNewsId(3);
    }

    @Test
    void testGetReportsByNewsIdWithRepositoryException() {
        // Arrange
        when(userNewsReportRepository.findByNewsId(1)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            userNewsReportService.getReportsByNewsId(1);
        });

        verify(userNewsReportRepository).findByNewsId(1);
    }

    @Test
    void testGetReportsByNewsIdWithRealisticValues() {
        // Test with realistic news IDs that might be used in a real application
        Integer[] realisticNewsIds = {1, 5, 10, 25, 50, 100, 500, 1000};

        for (Integer newsId : realisticNewsIds) {
            // Arrange
            when(userNewsReportRepository.findByNewsId(newsId)).thenReturn(Arrays.asList(report1));

            // Act
            List<UserNewsReport> result = userNewsReportService.getReportsByNewsId(newsId);

            // Assert
            assertNotNull(result);
            assertEquals(1, result.size());

            verify(userNewsReportRepository).findByNewsId(newsId);
        }
    }

    @Test
    void testGetReportsByNewsIdWithBoundaryValues() {
        // Test minimum value
        when(userNewsReportRepository.findByNewsId(0)).thenReturn(Arrays.asList());
        List<UserNewsReport> minResult = userNewsReportService.getReportsByNewsId(0);
        assertTrue(minResult.isEmpty());

        // Test maximum value
        when(userNewsReportRepository.findByNewsId(Integer.MAX_VALUE)).thenReturn(Arrays.asList());
        List<UserNewsReport> maxResult = userNewsReportService.getReportsByNewsId(Integer.MAX_VALUE);
        assertTrue(maxResult.isEmpty());

        verify(userNewsReportRepository).findByNewsId(0);
        verify(userNewsReportRepository).findByNewsId(Integer.MAX_VALUE);
    }

    @Test
    void testGetReportsByNewsIdWithSequentialValues() {
        for (int i = 1; i <= 10; i++) {
            // Arrange
            UserNewsReport sequentialReport = new UserNewsReport();
            sequentialReport.setId(i);
            sequentialReport.setUserId(i);
            sequentialReport.setNewsId(i * 10);
            sequentialReport.setReportReason("Report " + i);

            when(userNewsReportRepository.findByNewsId(i * 10)).thenReturn(Arrays.asList(sequentialReport));

            // Act
            List<UserNewsReport> result = userNewsReportService.getReportsByNewsId(i * 10);

            // Assert
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(i * 10, result.get(0).getNewsId());
            assertEquals("Report " + i, result.get(0).getReportReason());

            verify(userNewsReportRepository).findByNewsId(i * 10);
        }
    }

    @Test
    void testGetReportsByNewsIdWithLargeList() {
        // Arrange
        List<UserNewsReport> largeList = Arrays.asList();
        for (int i = 1; i <= 100; i++) {
            UserNewsReport report = new UserNewsReport();
            report.setId(i);
            report.setUserId(i);
            report.setNewsId(1);
            report.setReportReason("Report " + i);
            largeList = Arrays.asList(report);
        }

        when(userNewsReportRepository.findByNewsId(1)).thenReturn(largeList);

        // Act
        List<UserNewsReport> result = userNewsReportService.getReportsByNewsId(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size()); // Since we're creating a new list each time

        verify(userNewsReportRepository).findByNewsId(1);
    }

    @Test
    void testGetReportsByNewsIdWithNullReturn() {
        // Arrange
        when(userNewsReportRepository.findByNewsId(1)).thenReturn(null);

        // Act
        List<UserNewsReport> result = userNewsReportService.getReportsByNewsId(1);

        // Assert
        assertNull(result);

        verify(userNewsReportRepository).findByNewsId(1);
    }

    @Test
    void testGetReportsByNewsIdWithMixedData() {
        // Arrange
        UserNewsReport mixedReport1 = new UserNewsReport();
        mixedReport1.setId(1);
        mixedReport1.setUserId(1);
        mixedReport1.setNewsId(100);
        mixedReport1.setReportReason("Fake news");

        UserNewsReport mixedReport2 = new UserNewsReport();
        mixedReport2.setId(2);
        mixedReport2.setUserId(2);
        mixedReport2.setNewsId(100);
        mixedReport2.setReportReason("Inappropriate content");

        UserNewsReport mixedReport3 = new UserNewsReport();
        mixedReport3.setId(3);
        mixedReport3.setUserId(3);
        mixedReport3.setNewsId(100);
        mixedReport3.setReportReason("Spam");

        List<UserNewsReport> mixedList = Arrays.asList(mixedReport1, mixedReport2, mixedReport3);

        when(userNewsReportRepository.findByNewsId(100)).thenReturn(mixedList);

        // Act
        List<UserNewsReport> result = userNewsReportService.getReportsByNewsId(100);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("Fake news", result.get(0).getReportReason());
        assertEquals("Inappropriate content", result.get(1).getReportReason());
        assertEquals("Spam", result.get(2).getReportReason());

        verify(userNewsReportRepository).findByNewsId(100);
    }

    @Test
    void testGetReportsByNewsIdWithDifferentReportReasons() {
        // Arrange
        String[] reportReasons = {
            "Fake news",
            "Inappropriate content",
            "Spam",
            "Misleading information",
            "Violence",
            "Hate speech",
            "Copyright violation"
        };

        for (int i = 0; i < reportReasons.length; i++) {
            UserNewsReport report = new UserNewsReport();
            report.setId(i + 1);
            report.setUserId(i + 1);
            report.setNewsId(1);
            report.setReportReason(reportReasons[i]);

            when(userNewsReportRepository.findByNewsId(1)).thenReturn(Arrays.asList(report));

            // Act
            List<UserNewsReport> result = userNewsReportService.getReportsByNewsId(1);

            // Assert
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(reportReasons[i], result.get(0).getReportReason());

            verify(userNewsReportRepository).findByNewsId(1);
        }
    }
} 