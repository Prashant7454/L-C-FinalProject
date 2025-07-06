package Application.newsHiding.controller;

import Application.news.News;
import Application.util.HttpClientUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class NewsHidingController {

    private static final String NEWS_HIDING_API_URL = "http://localhost:8081/api/news-hiding";
    private final Gson gson = new GsonBuilder().create();
    private final Type newsListType = new TypeToken<List<News>>() {}.getType();

    /**
     * Hide news articles that contain any of the specified keywords
     */
    public String hideNewsByKeywords(List<String> keywords) throws Exception {
        String api = NEWS_HIDING_API_URL + "/hide-by-keywords";
        String jsonBody = gson.toJson(keywords);
        return HttpClientUtil.sendRequest(api, "POST", jsonBody);
    }

    /**
     * Find news articles that contain any of the specified keywords (without hiding them)
     */
    public List<News> findNewsByKeywords(List<String> keywords) throws Exception {
        String api = NEWS_HIDING_API_URL + "/find-by-keywords";
        String jsonBody = gson.toJson(keywords);
        String responseJson = HttpClientUtil.sendRequest(api, "POST", jsonBody);
        return gson.fromJson(responseJson, newsListType);
    }

    /**
     * Get count of news articles that contain any of the specified keywords
     */
    public int getNewsCountByKeywords(List<String> keywords) throws Exception {
        String api = NEWS_HIDING_API_URL + "/count-by-keywords";
        String jsonBody = gson.toJson(keywords);
        String responseJson = HttpClientUtil.sendRequest(api, "POST", jsonBody);
        return Integer.parseInt(responseJson);
    }

    /**
     * Hide a specific news article by ID
     */
    public String hideNewsById(Integer newsId) throws Exception {
        String api = NEWS_HIDING_API_URL + "/hide/" + newsId;
        return HttpClientUtil.sendRequest(api, "PUT", null);
    }

    /**
     * Unhide a specific news article by ID
     */
    public String unhideNewsById(Integer newsId) throws Exception {
        String api = NEWS_HIDING_API_URL + "/unhide/" + newsId;
        return HttpClientUtil.sendRequest(api, "PUT", null);
    }
} 