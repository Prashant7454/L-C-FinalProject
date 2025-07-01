package Application.category.controller;

import Application.category.Category;
import Application.util.HttpClientUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class CategoryController {
    private static final String CATEGORY_API_URL = "http://localhost:8081/api/categories";
    private final Gson gson = new GsonBuilder().create();

    public Category addCategory(Category category) throws Exception {
        String jsonBody = gson.toJson(category);
        String responseJson = HttpClientUtil.sendRequest(CATEGORY_API_URL, "POST", jsonBody);
        return gson.fromJson(responseJson, Category.class);
    }

    public List<Category> getAllCategories() throws Exception {
        String responseJson = HttpClientUtil.sendRequest(CATEGORY_API_URL, "GET", null);
        Type listType = new TypeToken<List<Category>>() {}.getType();
        return gson.fromJson(responseJson, listType);
    }

    public Category getCategoryById(int id) throws Exception {
        String api = CATEGORY_API_URL + "/" + id;
        String responseJson = HttpClientUtil.sendRequest(api, "GET", null);
        return gson.fromJson(responseJson, Category.class);
    }
}
