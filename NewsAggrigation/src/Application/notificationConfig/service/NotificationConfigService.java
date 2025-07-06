package Application.notificationConfig.service;

import Application.notificationConfig.NotificationConfig;
import Application.notificationConfig.controller.NotificationConfigController;

import java.util.List;

public class NotificationConfigService {
    private final NotificationConfigController notificationConfigController;

    public NotificationConfigService() {
        notificationConfigController = new NotificationConfigController();
    }

    public NotificationConfig saveConfig(NotificationConfig config) throws Exception {
        return notificationConfigController.saveConfig(config);
    }

    public List<NotificationConfig> getNotificationConfigurationsByUserId(Integer userId) throws Exception {
        return notificationConfigController.getNotificationConfigurationsByUserId(userId);
    }

    public NotificationConfig getNotificationConfigurationByUserIdAndCategoryId(Integer userId, Integer categoryId) throws Exception {
        return notificationConfigController.getNotificationConfigurationByUserIdAndCategoryId(userId, categoryId);
    }

    public NotificationConfig updateNotificationConfiguration(NotificationConfig config) throws Exception {
        return notificationConfigController.updateNotificationConfiguration(config);
    }

    public void deleteNotificationConfiguration(Integer userId, Integer categoryId) throws Exception {
        notificationConfigController.deleteNotificationConfiguration(userId, categoryId);
    }
} 