package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.dto.UserNewsReportDTO;
import com.server.NewsAggrigationServer.service.UserNewsReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserNewsReportControllerTest {

    @Mock
    private UserNewsReportService reportService;

    @InjectMocks
    private UserNewsReportController userNewsReportController;

    private UserNewsReportDTO reportDTO1;
    private UserNewsReportDTO reportDTO2;
    private List<UserNewsReportDTO> reportList;

    @BeforeEach
    void setUp() {
        reportDTO1 = new UserNewsReportDTO();
        reportDTO1.setId(1);
        reportDTO1.setUserId(1);
        reportDTO1.setNewsId(1);
        reportDTO1.setReason("Inappropriate content");
        reportDTO1.setReportedAt(LocalDateTime.now());

        reportDTO2 = new UserNewsReportDTO();
        reportDTO2.setId(2);
        reportDTO2.setUserId(2);
        reportDTO2.setNewsId(1);
        reportDTO2.setReason("Fake news");
        reportDTO2.setReportedAt(LocalDateTime.now());

        reportList = Arrays.asList(reportDTO1, reportDTO2);
    }

    @Test
    void testReportNews() {
        // Arrange
        when(reportService.reportNews(any(UserNewsReportDTO.class))).thenReturn(reportDTO1);

        // Act
        UserNewsReportDTO result = userNewsReportController.reportNews(reportDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(1, result.getUserId());
        assertEquals(1, result.getNewsId());
        assertEquals("Inappropriate content", result.getReason());
        verify(reportService).reportNews(reportDTO1);
    }

    @Test
    void testReportNewsWithNullInput() {
        // Arrange
        when(reportService.reportNews(null)).thenThrow(new IllegalArgumentException("Report cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userNewsReportController.reportNews(null);
        });
        verify(reportService).reportNews(null);
    }

    @Test
    void testReportNewsWithNullUserId() {
        // Arrange
        reportDTO1.setUserId(null);
        when(reportService.reportNews(reportDTO1)).thenThrow(new IllegalArgumentException("User ID cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userNewsReportController.reportNews(reportDTO1);
        });
        verify(reportService).reportNews(reportDTO1);
    }

    @Test
    void testReportNewsWithNullNewsId() {
        // Arrange
        reportDTO1.setNewsId(null);
        when(reportService.reportNews(reportDTO1)).thenThrow(new IllegalArgumentException("News ID cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userNewsReportController.reportNews(reportDTO1);
        });
        verify(reportService).reportNews(reportDTO1);
    }

    @Test
    void testReportNewsWithServiceException() {
        // Arrange
        when(reportService.reportNews(any(UserNewsReportDTO.class))).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            userNewsReportController.reportNews(reportDTO1);
        });
        verify(reportService).reportNews(reportDTO1);
    }

    @Test
    void testGetAllReports() {
        // Arrange
        when(reportService.getAllReports()).thenReturn(reportList);

        // Act
        List<UserNewsReportDTO> result = userNewsReportController.getAllReports();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
        assertEquals(1, result.get(0).getUserId());
        assertEquals(2, result.get(1).getUserId());
        assertEquals(1, result.get(0).getNewsId());
        assertEquals(1, result.get(1).getNewsId());
        verify(reportService).getAllReports();
    }

    @Test
    void testGetAllReportsWithEmptyList() {
        // Arrange
        when(reportService.getAllReports()).thenReturn(Collections.emptyList());

        // Act
        List<UserNewsReportDTO> result = userNewsReportController.getAllReports();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(reportService).getAllReports();
    }

    @Test
    void testGetAllReportsWithServiceException() {
        // Arrange
        when(reportService.getAllReports()).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            userNewsReportController.getAllReports();
        });
        verify(reportService).getAllReports();
    }

    @Test
    void testGetReportByUserAndNews() {
        // Arrange
        when(reportService.getReportByUserAndNews(1, 1)).thenReturn(reportDTO1);

        // Act
        UserNewsReportDTO result = userNewsReportController.getReportByUserAndNews(1, 1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(1, result.getUserId());
        assertEquals(1, result.getNewsId());
        assertEquals("Inappropriate content", result.getReason());
        verify(reportService).getReportByUserAndNews(1, 1);
    }

    @Test
    void testGetReportByUserAndNewsWithNullUserId() {
        // Arrange
        when(reportService.getReportByUserAndNews(null, 1)).thenThrow(new IllegalArgumentException("User ID cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userNewsReportController.getReportByUserAndNews(null, 1);
        });
        verify(reportService).getReportByUserAndNews(null, 1);
    }

    @Test
    void testGetReportByUserAndNewsWithNullNewsId() {
        // Arrange
        when(reportService.getReportByUserAndNews(1, null)).thenThrow(new IllegalArgumentException("News ID cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userNewsReportController.getReportByUserAndNews(1, null);
        });
        verify(reportService).getReportByUserAndNews(1, null);
    }

    @Test
    void testGetReportByUserAndNewsWithServiceException() {
        // Arrange
        when(reportService.getReportByUserAndNews(1, 1)).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            userNewsReportController.getReportByUserAndNews(1, 1);
        });
        verify(reportService).getReportByUserAndNews(1, 1);
    }

    @Test
    void testReportNewsWithZeroValues() {
        // Arrange
        reportDTO1.setUserId(0);
        reportDTO1.setNewsId(0);
        when(reportService.reportNews(reportDTO1)).thenThrow(new IllegalArgumentException("Invalid IDs"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userNewsReportController.reportNews(reportDTO1);
        });
        verify(reportService).reportNews(reportDTO1);
    }

    @Test
    void testReportNewsWithNegativeValues() {
        // Arrange
        reportDTO1.setUserId(-1);
        reportDTO1.setNewsId(-1);
        when(reportService.reportNews(reportDTO1)).thenThrow(new IllegalArgumentException("Invalid IDs"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userNewsReportController.reportNews(reportDTO1);
        });
        verify(reportService).reportNews(reportDTO1);
    }

    @Test
    void testReportNewsWithLargeValues() {
        // Arrange
        reportDTO1.setUserId(Integer.MAX_VALUE);
        reportDTO1.setNewsId(Integer.MAX_VALUE);
        when(reportService.reportNews(reportDTO1)).thenReturn(reportDTO1);

        // Act
        UserNewsReportDTO result = userNewsReportController.reportNews(reportDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(Integer.MAX_VALUE, result.getUserId());
        assertEquals(Integer.MAX_VALUE, result.getNewsId());
        verify(reportService).reportNews(reportDTO1);
    }

    @Test
    void testGetReportByUserAndNewsWithZeroValues() {
        // Arrange
        when(reportService.getReportByUserAndNews(0, 0)).thenThrow(new IllegalArgumentException("Invalid IDs"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userNewsReportController.getReportByUserAndNews(0, 0);
        });
        verify(reportService).getReportByUserAndNews(0, 0);
    }

    @Test
    void testGetReportByUserAndNewsWithNegativeValues() {
        // Arrange
        when(reportService.getReportByUserAndNews(-1, -1)).thenThrow(new IllegalArgumentException("Invalid IDs"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userNewsReportController.getReportByUserAndNews(-1, -1);
        });
        verify(reportService).getReportByUserAndNews(-1, -1);
    }

    @Test
    void testGetReportByUserAndNewsWithLargeValues() {
        // Arrange
        when(reportService.getReportByUserAndNews(Integer.MAX_VALUE, Integer.MAX_VALUE)).thenReturn(reportDTO1);

        // Act
        UserNewsReportDTO result = userNewsReportController.getReportByUserAndNews(Integer.MAX_VALUE, Integer.MAX_VALUE);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(reportService).getReportByUserAndNews(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    @Test
    void testReportNewsWithEmptyReason() {
        // Arrange
        reportDTO1.setReason("");
        when(reportService.reportNews(reportDTO1)).thenReturn(reportDTO1);

        // Act
        UserNewsReportDTO result = userNewsReportController.reportNews(reportDTO1);

        // Assert
        assertNotNull(result);
        assertEquals("", result.getReason());
        verify(reportService).reportNews(reportDTO1);
    }

    @Test
    void testReportNewsWithNullReason() {
        // Arrange
        reportDTO1.setReason(null);
        when(reportService.reportNews(reportDTO1)).thenReturn(reportDTO1);

        // Act
        UserNewsReportDTO result = userNewsReportController.reportNews(reportDTO1);

        // Assert
        assertNotNull(result);
        assertNull(result.getReason());
        verify(reportService).reportNews(reportDTO1);
    }

    @Test
    void testReportNewsWithLongReason() {
        // Arrange
        String longReason = "a".repeat(1000);
        reportDTO1.setReason(longReason);
        when(reportService.reportNews(reportDTO1)).thenReturn(reportDTO1);

        // Act
        UserNewsReportDTO result = userNewsReportController.reportNews(reportDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(longReason, result.getReason());
        verify(reportService).reportNews(reportDTO1);
    }

    @Test
    void testReportNewsWithSpecialCharacters() {
        // Arrange
        reportDTO1.setReason("Test@#$%^&*() reason");
        when(reportService.reportNews(reportDTO1)).thenReturn(reportDTO1);

        // Act
        UserNewsReportDTO result = userNewsReportController.reportNews(reportDTO1);

        // Assert
        assertNotNull(result);
        assertEquals("Test@#$%^&*() reason", result.getReason());
        verify(reportService).reportNews(reportDTO1);
    }

    @Test
    void testReportNewsWithUnicode() {
        // Arrange
        reportDTO1.setReason("Test\u00E9\u00F1\u00FC reason");
        when(reportService.reportNews(reportDTO1)).thenReturn(reportDTO1);

        // Act
        UserNewsReportDTO result = userNewsReportController.reportNews(reportDTO1);

        // Assert
        assertNotNull(result);
        assertEquals("Test\u00E9\u00F1\u00FC reason", result.getReason());
        verify(reportService).reportNews(reportDTO1);
    }

    @Test
    void testGetAllReportsWithLargeList() {
        // Arrange
        List<UserNewsReportDTO> largeList = Arrays.asList(
            reportDTO1, reportDTO2, reportDTO1, reportDTO2, reportDTO1,
            reportDTO2, reportDTO1, reportDTO2, reportDTO1, reportDTO2
        );
        when(reportService.getAllReports()).thenReturn(largeList);

        // Act
        List<UserNewsReportDTO> result = userNewsReportController.getAllReports();

        // Assert
        assertNotNull(result);
        assertEquals(10, result.size());
        verify(reportService).getAllReports();
    }

    @Test
    void testReportNewsWithZeroId() {
        // Arrange
        reportDTO1.setId(0);
        when(reportService.reportNews(reportDTO1)).thenReturn(reportDTO1);

        // Act
        UserNewsReportDTO result = userNewsReportController.reportNews(reportDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.getId());
        verify(reportService).reportNews(reportDTO1);
    }

    @Test
    void testReportNewsWithNegativeId() {
        // Arrange
        reportDTO1.setId(-1);
        when(reportService.reportNews(reportDTO1)).thenReturn(reportDTO1);

        // Act
        UserNewsReportDTO result = userNewsReportController.reportNews(reportDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(-1, result.getId());
        verify(reportService).reportNews(reportDTO1);
    }

    @Test
    void testReportNewsWithLargeId() {
        // Arrange
        reportDTO1.setId(Integer.MAX_VALUE);
        when(reportService.reportNews(reportDTO1)).thenReturn(reportDTO1);

        // Act
        UserNewsReportDTO result = userNewsReportController.reportNews(reportDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(Integer.MAX_VALUE, result.getId());
        verify(reportService).reportNews(reportDTO1);
    }
} 