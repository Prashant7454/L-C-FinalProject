package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.UserNewsReportDTO;
import com.server.NewsAggrigationServer.model.UserNewsReport;
import com.server.NewsAggrigationServer.repository.UserNewsReportRepository;
import com.server.NewsAggrigationServer.service.impl.UserNewsReportServiceImpl;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserNewsReportServiceImplTest {

    @Mock
    private UserNewsReportRepository repository;

    @InjectMocks
    private UserNewsReportServiceImpl service;

    private UserNewsReport testReport;
    private UserNewsReportDTO testReportDTO;

    @BeforeEach
    void setUp() {
        testReport = new UserNewsReport();
        testReport.setId(1);
        testReport.setUserId(2);
        testReport.setNewsId(3);
        testReport.setIsReported(1);

        testReportDTO = new UserNewsReportDTO();
        testReportDTO.setId(1);
        testReportDTO.setUserId(2);
        testReportDTO.setNewsId(3);
        testReportDTO.setIsReported(1);
    }

    @Test
    void reportNews_NewReport_Success() {
        when(repository.findByUserIdAndNewsId(2, 3)).thenReturn(Optional.empty());
        when(repository.save(any(UserNewsReport.class))).thenReturn(testReport);

        UserNewsReportDTO result = service.reportNews(testReportDTO);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(2, result.getUserId());
        assertEquals(3, result.getNewsId());
        assertEquals(1, result.getIsReported());

        verify(repository, times(1)).findByUserIdAndNewsId(2, 3);
        verify(repository, times(1)).save(any(UserNewsReport.class));
    }

    @Test
    void reportNews_ExistingReport_UpdatesIsReported() {
        UserNewsReport existing = new UserNewsReport();
        existing.setId(1);
        existing.setUserId(2);
        existing.setNewsId(3);
        existing.setIsReported(0);

        when(repository.findByUserIdAndNewsId(2, 3)).thenReturn(Optional.of(existing));
        when(repository.save(any(UserNewsReport.class))).thenReturn(testReport);

        UserNewsReportDTO result = service.reportNews(testReportDTO);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(1, result.getIsReported());
        verify(repository, times(1)).findByUserIdAndNewsId(2, 3);
        verify(repository, times(1)).save(existing);
    }

    @Test
    void getAllReports_ReturnsList() {
        UserNewsReport report2 = new UserNewsReport();
        report2.setId(2);
        report2.setUserId(3);
        report2.setNewsId(4);
        report2.setIsReported(1);

        List<UserNewsReport> reports = Arrays.asList(testReport, report2);
        when(repository.findAll()).thenReturn(reports);

        List<UserNewsReportDTO> result = service.getAllReports();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
        verify(repository, times(1)).findAll();
    }

    @Test
    void getReportByUserAndNews_Found() {
        when(repository.findByUserIdAndNewsId(2, 3)).thenReturn(Optional.of(testReport));
        UserNewsReportDTO result = service.getReportByUserAndNews(2, 3);
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(2, result.getUserId());
        assertEquals(3, result.getNewsId());
        assertEquals(1, result.getIsReported());
        verify(repository, times(1)).findByUserIdAndNewsId(2, 3);
    }

    @Test
    void getReportByUserAndNews_NotFound() {
        when(repository.findByUserIdAndNewsId(2, 3)).thenReturn(Optional.empty());
        UserNewsReportDTO result = service.getReportByUserAndNews(2, 3);
        assertNull(result);
        verify(repository, times(1)).findByUserIdAndNewsId(2, 3);
    }
} 