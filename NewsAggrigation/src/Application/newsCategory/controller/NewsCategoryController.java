package Application.newsCategory.controller;

import Application.util.HttpClientUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class NewsCategoryController {

    private static final String NEWS_CATEGORY_API_URL = "http://localhost:8081/api/news-category";
    private final Gson gson = new GsonBuilder().create();
    private final Type listType = new TypeToken<List<Integer>>() {}.getType();

    public List<Integer> getNewsIdByCategoryId(Integer categoryId) throws Exception {
        String api = NEWS_CATEGORY_API_URL + "/categoryId/" + categoryId;
        String responseJson = HttpClientUtil.sendRequest(api, "GET", null);
        return gson.fromJson(responseJson, listType);
    }
}
