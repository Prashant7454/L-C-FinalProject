package Application.externalNewsApi.controller;

import Application.externalNewsApi.ExternalNewsApi;
import Application.util.HttpClientUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ExternalNewsApiController {
    private static final String EXTERNAL_NEWS_API_URL = "http://localhost:8081/api/external";
    private final Gson gson = new GsonBuilder().create();

    public List<ExternalNewsApi> getAllExternalNewsApiDetails() throws Exception {
        String api = EXTERNAL_NEWS_API_URL + "/source";
        String responseJson = HttpClientUtil.sendRequest(api, "GET", null);
        Type listType = new TypeToken<List<ExternalNewsApi>>() {}.getType();
        return gson.fromJson(responseJson, listType);
    }

    public ExternalNewsApi updateExternalNewsApiKey(ExternalNewsApi externalNewsApi) throws Exception {
        String api = EXTERNAL_NEWS_API_URL + "/updatesource";
        String jsonBody = gson.toJson(externalNewsApi);
        String responseJson = HttpClientUtil.sendRequest(api, "PUT", jsonBody);
        return gson.fromJson(responseJson, ExternalNewsApi.class);
    }

    public ExternalNewsApi getExternalNewsApiById(int id) throws Exception {
        String api = EXTERNAL_NEWS_API_URL + "/" + id;
        String responseJson = HttpClientUtil.sendRequest(api, "GET", null);
        return gson.fromJson(responseJson, ExternalNewsApi.class);
    }
}
