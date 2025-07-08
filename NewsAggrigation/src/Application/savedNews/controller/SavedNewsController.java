package Application.savedNews.controller;

import Application.savedNews.SavedNews;
import Application.util.HttpClientUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class SavedNewsController {
    private static final String NEWS_CATEGORY_API_URL = "http://localhost:8081/api/saved-news";
    private final Gson gson = new GsonBuilder().create();

    public SavedNews saveNews(SavedNews savedNews) throws Exception{
        String api = NEWS_CATEGORY_API_URL;
        String jsonBody = gson.toJson(savedNews);
        String responseJson = HttpClientUtil.sendRequest(api, "POST", jsonBody);
        return gson.fromJson(responseJson, SavedNews.class);
    }

    public boolean unsaveNews(Integer userId, Integer newsId) throws Exception {
        String api = NEWS_CATEGORY_API_URL + "?userId=" + userId + "&newsId=" + newsId;
        String responseJson = HttpClientUtil.sendRequest(api, "DELETE", null);
        // Optionally, check responseJson for success message
        return responseJson != null && responseJson.contains("deleted successfully");
    }
}
