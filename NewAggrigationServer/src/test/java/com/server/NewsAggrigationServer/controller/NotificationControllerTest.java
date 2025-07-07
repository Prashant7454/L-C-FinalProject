package com.server.NewsAggrigationServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.NewsAggrigationServer.dto.NewsDTO;
import com.server.NewsAggrigationServer.dto.NotificationDTO;
import com.server.NewsAggrigationServer.service.NotificationService;
import com.server.NewsAggrigationServer.service.NewsNotificationService;
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
public class NotificationControllerTest {

    @Mock
    private NotificationService notificationService;

    @Mock
    private NewsNotificationService newsNotificationService;

    @InjectMocks
    private NotificationController notificationController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(notificationController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void createNotification_Success() throws Exception {
        NotificationDTO inputDto = new NotificationDTO();
        inputDto.setNewsId(1);
        inputDto.setUserId(2);

        NotificationDTO expectedDto = new NotificationDTO();
        expectedDto.setId(10);
        expectedDto.setNewsId(1);
        expectedDto.setUserId(2);

        when(notificationService.createNotification(any(NotificationDTO.class))).thenReturn(expectedDto);

        mockMvc.perform(post("/api/notifications")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.newsId").value(1))
                .andExpect(jsonPath("$.userId").value(2));

        verify(notificationService, times(1)).createNotification(any(NotificationDTO.class));
    }

    @Test
    void getNotificationsByUser_Success() throws Exception {
        Integer userId = 2;
        NewsDTO news1 = new NewsDTO();
        news1.setId(1);
        NewsDTO news2 = new NewsDTO();
        news2.setId(2);
        List<NewsDTO> newsList = Arrays.asList(news1, news2);

        when(notificationService.getNotificationsByUserId(userId)).thenReturn(newsList);

        mockMvc.perform(get("/api/notifications/user/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        verify(notificationService, times(1)).getNotificationsByUserId(userId);
    }

    @Test
    void clearAll_Success() throws Exception {
        Integer userId = 2;
        doNothing().when(notificationService).clearNotifications(userId);

        mockMvc.perform(delete("/api/notifications/clear/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().string("All notifications cleared for userId: 2"));

        verify(notificationService, times(1)).clearNotifications(userId);
    }

    @Test
    void deleteNotification_Success() throws Exception {
        Integer newsId = 1;
        Integer userId = 2;
        doNothing().when(notificationService).deleteNotification(newsId, userId);

        mockMvc.perform(delete("/api/notifications/delete")
                .param("newsId", newsId.toString())
                .param("userId", userId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("Notification deleted for newsId 1 and userId 2"));

        verify(notificationService, times(1)).deleteNotification(newsId, userId);
    }

    @Test
    void testNotificationForNews_Success() throws Exception {
        Integer newsId = 1;
        mockMvc.perform(post("/api/notifications/test/{newsId}", newsId))
                .andExpect(status().isOk())
                .andExpect(content().string("Notification test endpoint called for newsId: 1"));
    }
} 