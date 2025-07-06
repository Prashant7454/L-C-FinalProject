package Application.categoryKeyword.controller;

import Application.categoryKeyword.CategoryKeyword;
import Application.util.HttpClientUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class CategoryKeywordController {
    private static final String CATEGORY_KEYWORD_API_URL = "http://localhost:8081/api/category-keyword";
    private final Gson gson = new GsonBuilder().create();
    private final Type categoryKeywordListType = new TypeToken<List<CategoryKeyword>>() {}.getType();

    public CategoryKeyword createCategoryKeyword(CategoryKeyword categoryKeyword) throws Exception {
        String jsonBody = gson.toJson(categoryKeyword);
        String responseJson = HttpClientUtil.sendRequest(CATEGORY_KEYWORD_API_URL, "POST", jsonBody);
        return gson.fromJson(responseJson, CategoryKeyword.class);
    }

    public List<CategoryKeyword> getAllCategoryKeywords() throws Exception {
        String responseJson = HttpClientUtil.sendRequest(CATEGORY_KEYWORD_API_URL, "GET", null);
        return gson.fromJson(responseJson, categoryKeywordListType);
    }

    public List<CategoryKeyword> getCategoryKeywordsByCategoryId(Integer categoryId) throws Exception {
        String api = CATEGORY_KEYWORD_API_URL + "/category/" + categoryId;
        String responseJson = HttpClientUtil.sendRequest(api, "GET", null);
        return gson.fromJson(responseJson, categoryKeywordListType);
    }

    public List<CategoryKeyword> getCategoryKeywordsByKeywordId(Integer keywordId) throws Exception {
        String api = CATEGORY_KEYWORD_API_URL + "/keyword/" + keywordId;
        String responseJson = HttpClientUtil.sendRequest(api, "GET", null);
        return gson.fromJson(responseJson, categoryKeywordListType);
    }
} 