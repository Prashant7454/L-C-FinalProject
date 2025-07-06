package Application.keyword.controller;

import Application.keyword.Keyword;
import Application.util.HttpClientUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class KeywordController {
    private static final String KEYWORD_API_URL = "http://localhost:8081/api/keywords";
    private final Gson gson = new GsonBuilder().create();
    private final Type keywordListType = new TypeToken<List<Keyword>>() {}.getType();

    public Keyword addKeyword(Keyword keyword) throws Exception {
        String jsonBody = gson.toJson(keyword);
        String responseJson = HttpClientUtil.sendRequest(KEYWORD_API_URL, "POST", jsonBody);
        return gson.fromJson(responseJson, Keyword.class);
    }

    public List<Keyword> getAllKeywords() throws Exception {
        String responseJson = HttpClientUtil.sendRequest(KEYWORD_API_URL, "GET", null);
        return gson.fromJson(responseJson, keywordListType);
    }
} 