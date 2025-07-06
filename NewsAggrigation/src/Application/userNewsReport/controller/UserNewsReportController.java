package Application.userNewsReport.controller;

import Application.userNewsReport.UserNewsReport;
import Application.util.HttpClientUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class UserNewsReportController {
    private static final String USER_NEWS_REPORT_API_URL = "http://localhost:8081/api/user-news-report";
    private final Gson gson = new GsonBuilder().create();

    public UserNewsReport reportNews(UserNewsReport userNewsReport) throws Exception {
        String api = USER_NEWS_REPORT_API_URL + "/report";
        String jsonBody = gson.toJson(userNewsReport);
        String responseJson = HttpClientUtil.sendRequest(api, "POST", jsonBody);
        return gson.fromJson(responseJson, UserNewsReport.class);
    }

    public List<UserNewsReport> getAllReports() throws Exception {
        String api = USER_NEWS_REPORT_API_URL;
        String responseJson = HttpClientUtil.sendRequest(api, "GET", null);
        Type listType = new TypeToken<List<UserNewsReport>>(){}.getType();
        return gson.fromJson(responseJson, listType);
    }

    public UserNewsReport getReportByUserAndNews(Integer userId, Integer newsId) throws Exception {
        String api = USER_NEWS_REPORT_API_URL + "/check?userId=" + userId + "&newsId=" + newsId;
        String responseJson = HttpClientUtil.sendRequest(api, "GET", null);
        return gson.fromJson(responseJson, UserNewsReport.class);
    }
} 