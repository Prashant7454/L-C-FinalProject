package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.NewsDTO;
import com.server.NewsAggrigationServer.dto.NotificationDTO;
import com.server.NewsAggrigationServer.model.Notification;
import com.server.NewsAggrigationServer.repository.NotificationRepository;
import com.server.NewsAggrigationServer.service.impl.NotificationServiceImpl;
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
public class NotificationServiceImplTest {

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private NewsService newsService;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    private Notification notification;
    private NotificationDTO notificationDTO;

    @BeforeEach
    void setUp() {
        notification = new Notification(1, 2, LocalDateTime.now());
        notification.setId(10);

        notificationDTO = new NotificationDTO();
        notificationDTO.setId(10);
        notificationDTO.setNewsId(1);
        notificationDTO.setUserId(2);
        notificationDTO.setTimestamp(notification.getTimestamp());
    }

    @Test
    void createNotification_Success() {
        NotificationDTO inputDto = new NotificationDTO();
        inputDto.setNewsId(1);
        inputDto.setUserId(2);

        when(notificationRepository.save(any(Notification.class))).thenReturn(notification);

        NotificationDTO result = notificationService.createNotification(inputDto);

        assertNotNull(result);
        assertEquals(10, result.getId());
        assertEquals(1, result.getNewsId());
        assertEquals(2, result.getUserId());
        assertNotNull(result.getTimestamp());
        verify(notificationRepository, times(1)).save(any(Notification.class));
    }

    @Test
    void getNotificationsByUserId_Success() {
        Integer userId = 2;
        Notification n1 = new Notification(1, userId, LocalDateTime.now());
        n1.setId(10);
        Notification n2 = new Notification(2, userId, LocalDateTime.now());
        n2.setId(11);
        List<Notification> notifications = Arrays.asList(n1, n2);
        List<Integer> newsIds = Arrays.asList(1, 2);
        NewsDTO news1 = new NewsDTO(); news1.setId(1);
        NewsDTO news2 = new NewsDTO(); news2.setId(2);
        List<NewsDTO> newsList = Arrays.asList(news1, news2);

        when(notificationRepository.findByUserId(userId)).thenReturn(notifications);
        when(newsService.getNewsByIds(newsIds)).thenReturn(newsList);

        List<NewsDTO> result = notificationService.getNotificationsByUserId(userId);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
        verify(notificationRepository, times(1)).findByUserId(userId);
        verify(newsService, times(1)).getNewsByIds(newsIds);
    }

    @Test
    void getNotificationsByUserId_Empty() {
        Integer userId = 2;
        when(notificationRepository.findByUserId(userId)).thenReturn(Collections.emptyList());
        when(newsService.getNewsByIds(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<NewsDTO> result = notificationService.getNotificationsByUserId(userId);
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(notificationRepository, times(1)).findByUserId(userId);
        verify(newsService, times(1)).getNewsByIds(Collections.emptyList());
    }

    @Test
    void clearNotifications_Success() {
        Integer userId = 2;
        doNothing().when(notificationRepository).deleteByUserId(userId);
        notificationService.clearNotifications(userId);
        verify(notificationRepository, times(1)).deleteByUserId(userId);
    }

    @Test
    void deleteNotification_Success() {
        Integer newsId = 1;
        Integer userId = 2;
        doNothing().when(notificationRepository).deleteByNewsIdAndUserId(newsId, userId);
        notificationService.deleteNotification(newsId, userId);
        verify(notificationRepository, times(1)).deleteByNewsIdAndUserId(newsId, userId);
    }
} 