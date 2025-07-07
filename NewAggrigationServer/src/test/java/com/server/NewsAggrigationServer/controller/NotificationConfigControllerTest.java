package com.server.NewsAggrigationServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.NewsAggrigationServer.dto.NotificationConfigDTO;
import com.server.NewsAggrigationServer.service.NotificationConfigService;
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
public class NotificationConfigControllerTest {

    @Mock
    private NotificationConfigService configService;

    @InjectMocks
    private NotificationConfigController configController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(configController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void saveConfig_Success() throws Exception {
        NotificationConfigDTO inputDto = new NotificationConfigDTO();
        inputDto.setCategoryId(1);
        inputDto.setEnabled(true);
        inputDto.setUserId(2);

        NotificationConfigDTO expectedDto = new NotificationConfigDTO();
        expectedDto.setId(10);
        expectedDto.setCategoryId(1);
        expectedDto.setEnabled(true);
        expectedDto.setUserId(2);

        when(configService.saveConfig(any(NotificationConfigDTO.class))).thenReturn(expectedDto);

        mockMvc.perform(post("/api/notification-config")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.categoryId").value(1))
                .andExpect(jsonPath("$.enabled").value(true))
                .andExpect(jsonPath("$.userId").value(2));

        verify(configService, times(1)).saveConfig(any(NotificationConfigDTO.class));
    }

    @Test
    void getNotificationConfigurationsByUserId_Success() throws Exception {
        Integer userId = 2;
        NotificationConfigDTO dto1 = new NotificationConfigDTO();
        dto1.setId(1); dto1.setCategoryId(1); dto1.setEnabled(true); dto1.setUserId(userId);
        NotificationConfigDTO dto2 = new NotificationConfigDTO();
        dto2.setId(2); dto2.setCategoryId(2); dto2.setEnabled(false); dto2.setUserId(userId);
        List<NotificationConfigDTO> dtoList = Arrays.asList(dto1, dto2);

        when(configService.getNotificationConfigurationsByUserId(userId)).thenReturn(dtoList);

        mockMvc.perform(get("/api/notification-config/user/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        verify(configService, times(1)).getNotificationConfigurationsByUserId(userId);
    }

    @Test
    void getNotificationConfigurationByUserIdAndCategoryId_Success() throws Exception {
        Integer userId = 2;
        Integer categoryId = 1;
        NotificationConfigDTO dto = new NotificationConfigDTO();
        dto.setId(1); dto.setCategoryId(categoryId); dto.setEnabled(true); dto.setUserId(userId);

        when(configService.getNotificationConfigurationByUserIdAndCategoryId(userId, categoryId)).thenReturn(dto);

        mockMvc.perform(get("/api/notification-config/user/{userId}/category/{categoryId}", userId, categoryId))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".id").value(1))
                .andExpect(jsonPath(".categoryId").value(categoryId))
                .andExpect(jsonPath(".enabled").value(true))
                .andExpect(jsonPath(".userId").value(userId));

        verify(configService, times(1)).getNotificationConfigurationByUserIdAndCategoryId(userId, categoryId);
    }

    @Test
    void updateNotificationConfiguration_Success() throws Exception {
        NotificationConfigDTO inputDto = new NotificationConfigDTO();
        inputDto.setCategoryId(1);
        inputDto.setEnabled(false);
        inputDto.setUserId(2);

        NotificationConfigDTO expectedDto = new NotificationConfigDTO();
        expectedDto.setId(10);
        expectedDto.setCategoryId(1);
        expectedDto.setEnabled(false);
        expectedDto.setUserId(2);

        when(configService.updateNotificationConfiguration(any(NotificationConfigDTO.class))).thenReturn(expectedDto);

        mockMvc.perform(put("/api/notification-config")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".id").value(10))
                .andExpect(jsonPath(".categoryId").value(1))
                .andExpect(jsonPath(".enabled").value(false))
                .andExpect(jsonPath(".userId").value(2));

        verify(configService, times(1)).updateNotificationConfiguration(any(NotificationConfigDTO.class));
    }

    @Test
    void deleteNotificationConfiguration_Success() throws Exception {
        Integer userId = 2;
        Integer categoryId = 1;
        doNothing().when(configService).deleteNotificationConfiguration(userId, categoryId);

        mockMvc.perform(delete("/api/notification-config/user/{userId}/category/{categoryId}", userId, categoryId))
                .andExpect(status().isOk());

        verify(configService, times(1)).deleteNotificationConfiguration(userId, categoryId);
    }
} 