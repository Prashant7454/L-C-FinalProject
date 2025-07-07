package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.NotificationConfigDTO;
import com.server.NewsAggrigationServer.model.NotificationConfiguration;
import com.server.NewsAggrigationServer.repository.NotificationConfigurationRepository;
import com.server.NewsAggrigationServer.service.impl.NotificationConfigServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificationConfigServiceImplTest {

    @Mock
    private NotificationConfigurationRepository configRepository;

    @InjectMocks
    private NotificationConfigServiceImpl configService;

    private NotificationConfiguration config;
    private NotificationConfigDTO dto;

    @BeforeEach
    void setUp() {
        config = new NotificationConfiguration();
        config.setId(10);
        config.setCategoryId(1);
        config.setEnabled(true);
        config.setUserId(2);

        dto = new NotificationConfigDTO();
        dto.setId(10);
        dto.setCategoryId(1);
        dto.setEnabled(true);
        dto.setUserId(2);
    }

    @Test
    void saveConfig_Success() {
        when(configRepository.save(any(NotificationConfiguration.class))).thenReturn(config);
        NotificationConfigDTO result = configService.saveConfig(dto);
        assertNotNull(result);
        assertEquals(10, result.getId());
        verify(configRepository, times(1)).save(any(NotificationConfiguration.class));
    }

    @Test
    void getNotificationConfigurationByCategoryId_Success() {
        when(configRepository.getNotificationConfigurationByCategoryId(1)).thenReturn(config);
        NotificationConfigDTO result = configService.getNotificationConfigurationByCategoryId(1);
        assertNotNull(result);
        assertEquals(1, result.getCategoryId());
        verify(configRepository, times(1)).getNotificationConfigurationByCategoryId(1);
    }

    @Test
    void getNotificationConfigurationsByUserId_Success() {
        NotificationConfiguration config2 = new NotificationConfiguration();
        config2.setId(11); config2.setCategoryId(2); config2.setEnabled(false); config2.setUserId(2);
        List<NotificationConfiguration> configs = Arrays.asList(config, config2);
        when(configRepository.findByUserId(2)).thenReturn(configs);
        List<NotificationConfigDTO> result = configService.getNotificationConfigurationsByUserId(2);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(10, result.get(0).getId());
        assertEquals(11, result.get(1).getId());
        verify(configRepository, times(1)).findByUserId(2);
    }

    @Test
    void getNotificationConfigurationByUserIdAndCategoryId_Success() {
        when(configRepository.findByUserIdAndCategoryId(2, 1)).thenReturn(config);
        NotificationConfigDTO result = configService.getNotificationConfigurationByUserIdAndCategoryId(2, 1);
        assertNotNull(result);
        assertEquals(1, result.getCategoryId());
        verify(configRepository, times(1)).findByUserIdAndCategoryId(2, 1);
    }

    @Test
    void getNotificationConfigurationByUserIdAndCategoryId_NotFound() {
        when(configRepository.findByUserIdAndCategoryId(2, 1)).thenReturn(null);
        NotificationConfigDTO result = configService.getNotificationConfigurationByUserIdAndCategoryId(2, 1);
        assertNull(result);
        verify(configRepository, times(1)).findByUserIdAndCategoryId(2, 1);
    }

    @Test
    void updateNotificationConfiguration_UpdateExisting() {
        when(configRepository.findByUserIdAndCategoryId(2, 1)).thenReturn(config);
        when(configRepository.save(any(NotificationConfiguration.class))).thenReturn(config);
        NotificationConfigDTO result = configService.updateNotificationConfiguration(dto);
        assertNotNull(result);
        assertEquals(10, result.getId());
        verify(configRepository, times(1)).findByUserIdAndCategoryId(2, 1);
        verify(configRepository, times(1)).save(any(NotificationConfiguration.class));
    }

    @Test
    void updateNotificationConfiguration_CreateNew() {
        when(configRepository.findByUserIdAndCategoryId(2, 1)).thenReturn(null);
        when(configRepository.save(any(NotificationConfiguration.class))).thenReturn(config);
        NotificationConfigDTO result = configService.updateNotificationConfiguration(dto);
        assertNotNull(result);
        assertEquals(10, result.getId());
        verify(configRepository, times(1)).findByUserIdAndCategoryId(2, 1);
        verify(configRepository, times(1)).save(any(NotificationConfiguration.class));
    }

    @Test
    void deleteNotificationConfiguration_Success() {
        doNothing().when(configRepository).deleteByUserIdAndCategoryId(2, 1);
        configService.deleteNotificationConfiguration(2, 1);
        verify(configRepository, times(1)).deleteByUserIdAndCategoryId(2, 1);
    }
} 