package Application.notification.service;

import Application.news.News;
import Application.notification.controller.NotificationController;

import java.util.List;

public class NotificationService {
    private final NotificationController notificationController = new NotificationController();

    public List<News> getNotificationByUserId(Integer userId) throws Exception{
        return notificationController.getNotificationsByUser(userId);
    }

    public void clearNotificationsByUserId(Integer userId) throws Exception{
        notificationController.clearNotificationsByUser(userId);
    }
}
