package Application.news.controller;

import Application.util.HttpClientUtil;
import Application.news.DateRangeNewsRequest;
import Application.news.News;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class NewsController {

    private static final String NEWS_API_URL = "http://localhost:8081/api/news";
    private final Gson gson = new GsonBuilder().create();
    private final Type newsListType = new TypeToken<List<News>>() {}.getType();

    public List<News> getAllNews() throws Exception {
        String responseJson = HttpClientUtil.sendRequest(NEWS_API_URL, "GET", null);
        return gson.fromJson(responseJson, newsListType);
    }

    public List<News> getTodayNewsById(List<Integer> newsIds) throws Exception {
        String api = NEWS_API_URL + "/today";
        String jsonBody = gson.toJson(newsIds);
        String responseJson = HttpClientUtil.sendRequest(api, "POST", jsonBody);
        return gson.fromJson(responseJson, newsListType);
    }

    public List<News> getNewsByIdAndDateRange(DateRangeNewsRequest request) throws Exception {
        String api = NEWS_API_URL + "/date-range";
        String jsonBody = gson.toJson(request);
        String responseJson = HttpClientUtil.sendRequest(api, "POST", jsonBody);
        return gson.fromJson(responseJson, newsListType);
    }

    public List<News> searchNews(String keyword) throws Exception {
        String api = NEWS_API_URL + "/search?searchString=" + keyword;
        String responseJson = HttpClientUtil.sendRequest(api, "GET", null);
        return gson.fromJson(responseJson, newsListType);
    }

    public List<News> savedNews(int userId) throws Exception {
        String api = NEWS_API_URL + "/save/" + userId;
        String responseJson = HttpClientUtil.sendRequest(api, "GET", null);
        return gson.fromJson(responseJson, newsListType);
    }

    public News getNewsById(Integer newsId) throws Exception{
        String api = NEWS_API_URL + "/" + newsId;
        String responseJson = HttpClientUtil.sendRequest(api, "GET", null);
        return gson.fromJson(responseJson, News.class);
    }

    public News updateNewsLikeAndDisLikeCount(News news) throws Exception{
        String api = NEWS_API_URL + "/" + news.getId();
        String jsonBody = gson.toJson(news);
        
        // Debug: Print what we're sending to server
        System.out.println("Sending to server: " + jsonBody);
        
        String responseJson = HttpClientUtil.sendRequest(api, "PUT", jsonBody);
        
        // Debug: Print server response
        System.out.println("Server response: " + responseJson);
        
        return gson.fromJson(responseJson, News.class);
    }

    public News updateNews(News news) throws Exception{
        String api = NEWS_API_URL + "/" + news.getId();
        String jsonBody = gson.toJson(news);
        String responseJson = HttpClientUtil.sendRequest(api, "PUT", jsonBody);
        return gson.fromJson(responseJson, News.class);
    }

    // New methods for visible news only
    public List<News> getAllVisibleNews() throws Exception {
        String api = NEWS_API_URL + "/visible";
        String responseJson = HttpClientUtil.sendRequest(api, "GET", null);
        return gson.fromJson(responseJson, newsListType);
    }

    public List<News> searchVisibleNews(String keyword) throws Exception {
        String api = NEWS_API_URL + "/visible/search?searchString=" + keyword;
        String responseJson = HttpClientUtil.sendRequest(api, "GET", null);
        return gson.fromJson(responseJson, newsListType);
    }

    public List<News> getVisibleTodayNewsById(List<Integer> newsIds) throws Exception {
        String api = NEWS_API_URL + "/visible/today";
        String jsonBody = gson.toJson(newsIds);
        String responseJson = HttpClientUtil.sendRequest(api, "POST", jsonBody);
        return gson.fromJson(responseJson, newsListType);
    }

    public List<News> getVisibleNewsByIdAndDateRange(DateRangeNewsRequest request) throws Exception {
        String api = NEWS_API_URL + "/visible/date-range";
        String jsonBody = gson.toJson(request);
        String responseJson = HttpClientUtil.sendRequest(api, "POST", jsonBody);
        return gson.fromJson(responseJson, newsListType);
    }

    // New methods for news in visible categories
    public List<News> getNewsInVisibleCategories() throws Exception {
        String api = NEWS_API_URL + "/visible-categories";
        String responseJson = HttpClientUtil.sendRequest(api, "GET", null);
        return gson.fromJson(responseJson, newsListType);
    }

    public List<News> getNewsByIdsInVisibleCategories(List<Integer> newsIds) throws Exception {
        String api = NEWS_API_URL + "/visible-categories/list";
        String jsonBody = gson.toJson(newsIds);
        String responseJson = HttpClientUtil.sendRequest(api, "POST", jsonBody);
        return gson.fromJson(responseJson, newsListType);
    }
}
