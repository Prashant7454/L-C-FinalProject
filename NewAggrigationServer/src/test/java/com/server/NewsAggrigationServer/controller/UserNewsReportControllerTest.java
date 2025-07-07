package com.server.NewsAggrigationServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.NewsAggrigationServer.dto.UserNewsReportDTO;
import com.server.NewsAggrigationServer.service.UserNewsReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class UserNewsReportControllerTest {

    @Mock
    private UserNewsReportService reportService;

    @InjectMocks
    private UserNewsReportController reportController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(reportController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void reportNews_Success() throws Exception {
        UserNewsReportDTO inputDto = createDTO(1, 2, 3, 1);
        UserNewsReportDTO expectedDto = createDTO(1, 2, 3, 1);

        when(reportService.reportNews(any(UserNewsReportDTO.class))).thenReturn(expectedDto);

        mockMvc.perform(post("/api/user-news-report/report")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.userId").value(2))
                .andExpect(jsonPath("$.newsId").value(3))
                .andExpect(jsonPath("$.isReported").value(1));

        verify(reportService, times(1)).reportNews(any(UserNewsReportDTO.class));
    }

    @Test
    void getAllReports_Success() throws Exception {
        List<UserNewsReportDTO> expectedList = Arrays.asList(
                createDTO(1, 2, 3, 1),
                createDTO(2, 3, 4, 1)
        );
        when(reportService.getAllReports()).thenReturn(expectedList);

        mockMvc.perform(get("/api/user-news-report"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        verify(reportService, times(1)).getAllReports();
    }

    @Test
    void getReportByUserAndNews_Success() throws Exception {
        UserNewsReportDTO expectedDto = createDTO(1, 2, 3, 1);
        when(reportService.getReportByUserAndNews(2, 3)).thenReturn(expectedDto);

        mockMvc.perform(get("/api/user-news-report/check")
                .param("userId", "2")
                .param("newsId", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.userId").value(2))
                .andExpect(jsonPath("$.newsId").value(3))
                .andExpect(jsonPath("$.isReported").value(1));

        verify(reportService, times(1)).getReportByUserAndNews(2, 3);
    }

    private UserNewsReportDTO createDTO(Integer id, Integer userId, Integer newsId, Integer isReported) {
        UserNewsReportDTO dto = new UserNewsReportDTO();
        dto.setId(id);
        dto.setUserId(userId);
        dto.setNewsId(newsId);
        dto.setIsReported(isReported);
        return dto;
    }
} 