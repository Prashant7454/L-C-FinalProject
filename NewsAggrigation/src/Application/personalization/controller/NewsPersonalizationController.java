package Application.personalization.controller;

import Application.news.News;
import Application.util.HttpClientUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class NewsPersonalizationController {
    private static final String PERSONALIZATION_API_URL = "http://localhost:8081/api/personalization";
    private final Gson gson = new GsonBuilder().create();
    private final Type newsListType = new TypeToken<List<News>>() {}.getType();
    private final Type integerListType = new TypeToken<List<Integer>>() {}.getType();

    /**
     * Get personalized news recommendations for a user
     */
    public List<News> getPersonalizedNews(Integer userId, int limit) throws Exception {
        String api = PERSONALIZATION_API_URL + "/user/" + userId + "?limit=" + limit;
        String responseJson = HttpClientUtil.sendRequest(api, "GET", null);
        return gson.fromJson(responseJson, newsListType);
    }

    /**
     * Get personalized news recommendations with pagination
     */
    public List<News> getPersonalizedNewsPaginated(Integer userId, int page, int size) throws Exception {
        String api = PERSONALIZATION_API_URL + "/user/" + userId + "/page?page=" + page + "&size=" + size;
        String responseJson = HttpClientUtil.sendRequest(api, "GET", null);
        return gson.fromJson(responseJson, newsListType);
    }

    /**
     * Calculate user's interest score for a specific news article
     */
    public double getUserInterestScore(Integer userId, Integer newsId) throws Exception {
        String api = PERSONALIZATION_API_URL + "/user/" + userId + "/news/" + newsId + "/score";
        String responseJson = HttpClientUtil.sendRequest(api, "GET", null);
        return Double.parseDouble(responseJson);
    }

    /**
     * Get user's top interest categories
     */
    public List<Integer> getUserTopInterestCategories(Integer userId, int limit) throws Exception {
        String api = PERSONALIZATION_API_URL + "/user/" + userId + "/top-categories?limit=" + limit;
        String responseJson = HttpClientUtil.sendRequest(api, "GET", null);
        return gson.fromJson(responseJson, integerListType);
    }

    /**
     * Record that a user has read an article
     */
    public String recordArticleRead(Integer userId, Integer newsId) throws Exception {
        String api = PERSONALIZATION_API_URL + "/user/" + userId + "/news/" + newsId + "/read";
        return HttpClientUtil.sendRequest(api, "POST", null);
    }
} 