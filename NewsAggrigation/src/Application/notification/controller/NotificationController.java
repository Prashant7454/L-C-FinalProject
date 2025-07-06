package Application.notification.controller;

import Application.news.News;
import Application.util.HttpClientUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class NotificationController {
    private static final String NOTIFICATION_API_URL = "http://localhost:8081/api/notifications";
    private final Gson gson = new GsonBuilder().create();
    private final Type newsListType = new TypeToken<List<News>>() {}.getType();

    public List<News> getNotificationsByUser(Integer userId) throws Exception {
        String api = NOTIFICATION_API_URL + "/user/" + userId;
        String responseJson = HttpClientUtil.sendRequest(api, "GET", null);
        return gson.fromJson(responseJson, newsListType);
    }

    public void clearNotificationsByUser(Integer userId) throws Exception {
        String api = NOTIFICATION_API_URL + "/clear/" + userId;
        HttpClientUtil.sendRequest(api, "DELETE", null);
    }

    public void deleteNotification(Integer newsId, Integer userId) throws Exception {
        String api = NOTIFICATION_API_URL + "/delete?newsId=" + newsId + "&userId=" + userId;
        HttpClientUtil.sendRequest(api, "DELETE", null);
    }
}
