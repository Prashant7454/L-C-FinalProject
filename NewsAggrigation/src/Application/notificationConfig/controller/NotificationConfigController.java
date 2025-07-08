package Application.notificationConfig.controller;

import Application.notificationConfig.NotificationConfig;
import Application.util.HttpClientUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class NotificationConfigController {
    private static final String NOTIFICATION_CONFIG_API_URL = "http://localhost:8081/api/notification-config";
    private final Gson gson = new GsonBuilder().create();
    private final Type configListType = new TypeToken<List<NotificationConfig>>() {}.getType();

    public NotificationConfig saveConfig(NotificationConfig config) throws Exception {
        String jsonBody = gson.toJson(config);
        String responseJson = HttpClientUtil.sendRequest(NOTIFICATION_CONFIG_API_URL, "POST", jsonBody);
        return gson.fromJson(responseJson, NotificationConfig.class);
    }

    public List<NotificationConfig> getNotificationConfigurationsByUserId(Integer userId) throws Exception {
        String api = NOTIFICATION_CONFIG_API_URL + "/user/" + userId;
        String responseJson = HttpClientUtil.sendRequest(api, "GET", null);
        return gson.fromJson(responseJson, configListType);
    }

    public NotificationConfig getNotificationConfigurationByUserIdAndCategoryId(Integer userId, Integer categoryId) throws Exception {
        String api = NOTIFICATION_CONFIG_API_URL + "/user/" + userId + "/category/" + categoryId;
        String responseJson = HttpClientUtil.sendRequest(api, "GET", null);
        return gson.fromJson(responseJson, NotificationConfig.class);
    }

    public NotificationConfig updateNotificationConfiguration(NotificationConfig config) throws Exception {
        String jsonBody = gson.toJson(config);
        String responseJson = HttpClientUtil.sendRequest(NOTIFICATION_CONFIG_API_URL, "PUT", jsonBody);
        return gson.fromJson(responseJson, NotificationConfig.class);
    }

    public void deleteNotificationConfiguration(Integer userId, Integer categoryId) throws Exception {
        String api = NOTIFICATION_CONFIG_API_URL + "/user/" + userId + "/category/" + categoryId;
        HttpClientUtil.sendRequest(api, "DELETE", null);
    }
} 