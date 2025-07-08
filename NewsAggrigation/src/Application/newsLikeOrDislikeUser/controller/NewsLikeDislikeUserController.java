package Application.newsLikeOrDislikeUser.controller;

import Application.newsLikeOrDislikeUser.NewsLikeDislikeUser;
import Application.util.HttpClientUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class NewsLikeDislikeUserController {
    private static final String NEWS_LIKE_DISLIKE_API_URL = "http://localhost:8081/api/news-reactions";
    private final Gson gson = new GsonBuilder().create();
    private final Type listType = new TypeToken<List<Integer>>() {}.getType();

    public NewsLikeDislikeUser getReaction(Integer newsId, Integer userId) throws Exception {
        String api = NEWS_LIKE_DISLIKE_API_URL + "/" + newsId + "/" + userId;
        String responseJson = HttpClientUtil.sendRequest(api, "GET", null);
        return gson.fromJson(responseJson, NewsLikeDislikeUser.class);
    }

    public NewsLikeDislikeUser saveOrUpdateReaction(NewsLikeDislikeUser newsLikeDislikeUser) throws Exception{
        String api = NEWS_LIKE_DISLIKE_API_URL;
        String jsonBody = gson.toJson(newsLikeDislikeUser);
        String responseJson = HttpClientUtil.sendRequest(api, "POST", jsonBody);
        return gson.fromJson(responseJson, NewsLikeDislikeUser.class);
    }
}
